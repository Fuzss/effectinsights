package fuzs.effectinsights.client.handler;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.config.ClientConfig;
import fuzs.tooltipinsights.api.v1.client.handler.TooltipDescriptionsHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class FoodTooltipHandler {

    public static void onItemTooltip(ItemStack itemStack, List<Component> tooltipLines, Item.TooltipContext tooltipContext, @Nullable Player player, TooltipFlag tooltipFlag) {
        if (!EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.consumablesEffectTooltips) {
            return;
        }

        List<ConsumeEffect> consumeEffects = collectConsumeEffects(itemStack);
        if (!consumeEffects.isEmpty()) {
            // Collect all possible effect description ids.
            // This is used to guard against other mods maybe already adding their potion effects to food tooltips,
            Set<String> translationKeys = getAllTranslationKeys(tooltipLines);
            List<Component> potionLines = new ArrayList<>();
            List<Component> attributeLines = new ArrayList<>();
            for (ConsumeEffect consumeEffect : consumeEffects) {
                if (consumeEffect instanceof ApplyStatusEffectsConsumeEffect(
                        List<MobEffectInstance> effects, float probability
                )) {
                    for (MobEffectInstance mobEffect : effects) {
                        if (!translationKeys.contains(mobEffect.getDescriptionId())) {
                            collectPotionTooltipLines(mobEffect,
                                    tooltipContext.tickRate(),
                                    probability,
                                    potionLines,
                                    attributeLines);
                        }
                    }
                }
            }

            if (!potionLines.isEmpty() || !attributeLines.isEmpty()) {
                addPotionTooltipLines(tooltipLines, potionLines, attributeLines);
            }
        }
    }

    private static List<ConsumeEffect> collectConsumeEffects(ItemStack itemStack) {
        if (!itemStack.has(DataComponents.CONSUMABLE) && !itemStack.has(DataComponents.DEATH_PROTECTION)) {
            return Collections.emptyList();
        }

        List<ConsumeEffect> consumeEffects = new ArrayList<>();
        if (itemStack.has(DataComponents.CONSUMABLE)) {
            consumeEffects.addAll(itemStack.get(DataComponents.CONSUMABLE).onConsumeEffects());
        }

        if (itemStack.has(DataComponents.DEATH_PROTECTION)) {
            consumeEffects.addAll(itemStack.get(DataComponents.DEATH_PROTECTION).deathEffects());
        }

        return consumeEffects;
    }

    private static Set<String> getAllTranslationKeys(List<Component> tooltipLines) {
        return tooltipLines.stream().mapMulti((Component component, Consumer<TranslatableContents> consumer) -> {
            TooltipDescriptionsHandler.modifyTranslatableContents(component,
                    UnaryOperator.identity(),
                    (TranslatableContents translatableContents, UnaryOperator<Component> contentsGatherer) -> {
                        consumer.accept(translatableContents);
                        return false;
                    });
        }).map(TranslatableContents::getKey).collect(Collectors.toSet());
    }

    private static void collectPotionTooltipLines(MobEffectInstance mobEffectInstance, float tickRate, float probability, List<Component> potionLines, List<Component> attributeLines) {
        List<Component> potionTooltip = new ArrayList<>();
        PotionContents.addPotionTooltip(Collections.singleton(mobEffectInstance), potionTooltip::add, 1.0F, tickRate);
        if (!potionTooltip.isEmpty()) {
            if (probability != 1.0F) {
                String probabilityString = Mth.floor(probability * 100.0F) + "%";
                potionTooltip.set(0,
                        Component.translatable("potion.withDuration", potionTooltip.getFirst(), probabilityString)
                                .withStyle(ChatFormatting.GOLD));
            }

            int index = potionTooltip.indexOf(CommonComponents.EMPTY);
            if (index != -1) {
                potionLines.addAll(potionTooltip.subList(0, index));
                attributeLines.addAll(potionTooltip.subList(index + 1, potionTooltip.size()));
            } else {
                potionLines.addAll(potionTooltip);
            }
        }
    }

    private static void addPotionTooltipLines(List<Component> tooltipLines, List<Component> potionLines, List<Component> attributeLines) {
        if (tooltipLines.isEmpty()) {
            tooltipLines.addAll(potionLines);
            if (!attributeLines.isEmpty()) {
                tooltipLines.add(CommonComponents.EMPTY);
                tooltipLines.addAll(attributeLines);
            }
        } else {
            if (!attributeLines.isEmpty()) {
                tooltipLines.addAll(1, attributeLines);
                tooltipLines.add(1, CommonComponents.EMPTY);
            }

            tooltipLines.addAll(1, potionLines);
        }
    }
}
