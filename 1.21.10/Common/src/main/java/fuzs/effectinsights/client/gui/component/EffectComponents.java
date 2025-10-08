package fuzs.effectinsights.client.gui.component;

import com.google.common.collect.ImmutableList;
import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.config.ClientConfig;
import fuzs.tooltipinsights.api.v1.client.gui.component.TooltipComponentExtractor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.OminousBottleAmplifier;
import net.minecraft.world.item.component.SuspiciousStewEffects;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class EffectComponents {
    static final TooltipComponentExtractor<MobEffectInstance, PotionContents> POTION_CONTENTS = new TooltipComponentExtractor<>(
            DataComponents.POTION_CONTENTS) {
        @Override
        protected boolean isEnabled() {
            return EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.itemDescriptionTargets.potionContents;
        }

        @Override
        protected Stream<MobEffectInstance> extractFromComponent(PotionContents potionContents) {
            return StreamSupport.stream(potionContents.getAllEffects().spliterator(), false);
        }
    };
    static final TooltipComponentExtractor<MobEffectInstance, Consumable> CONSUMABLE = new TooltipComponentExtractor<>(
            DataComponents.CONSUMABLE) {
        @Override
        protected boolean isEnabled() {
            return EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.itemDescriptionTargets.consumable;
        }

        @Override
        protected Stream<MobEffectInstance> extractFromComponent(Consumable consumable) {
            return consumable.onConsumeEffects()
                    .stream()
                    .filter(ApplyStatusEffectsConsumeEffect.class::isInstance)
                    .map(ApplyStatusEffectsConsumeEffect.class::cast)
                    .map(ApplyStatusEffectsConsumeEffect::effects)
                    .flatMap(Collection::stream);
        }
    };
    static final TooltipComponentExtractor<MobEffectInstance, OminousBottleAmplifier> OMINOUS_BOTTLE_AMPLIFIER = new TooltipComponentExtractor<>(
            DataComponents.OMINOUS_BOTTLE_AMPLIFIER) {
        @Override
        protected boolean isEnabled() {
            return EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.itemDescriptionTargets.ominousBottle;
        }

        @Override
        protected Stream<MobEffectInstance> extractFromComponent(OminousBottleAmplifier ominousBottleAmplifier) {
            // copied from OminousBottleAmplifier implementation
            return Stream.of(new MobEffectInstance(MobEffects.BAD_OMEN,
                    120_000,
                    ominousBottleAmplifier.value(),
                    false,
                    false,
                    true));
        }
    };
    static final TooltipComponentExtractor<MobEffectInstance, SuspiciousStewEffects> SUSPICIOUS_STEW_EFFECTS = new TooltipComponentExtractor<>(
            DataComponents.SUSPICIOUS_STEW_EFFECTS) {
        @Override
        protected boolean isEnabled() {
            return EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.itemDescriptionTargets.suspiciousStew;
        }

        @Override
        protected Stream<MobEffectInstance> extractFromComponent(SuspiciousStewEffects suspiciousStewEffects) {
            return suspiciousStewEffects.effects().stream().map(SuspiciousStewEffects.Entry::createEffectInstance);
        }
    };
    private static final List<TooltipComponentExtractor<MobEffectInstance, ?>> MOB_EFFECTS_SUPPLIERS = ImmutableList.of(
            SUSPICIOUS_STEW_EFFECTS,
            OMINOUS_BOTTLE_AMPLIFIER,
            CONSUMABLE,
            POTION_CONTENTS);

    private EffectComponents() {
        // NO-OP
    }

    public static Stream<MobEffectInstance> getAllMobEffects(ItemStack itemStack) {
        return MOB_EFFECTS_SUPPLIERS.stream()
                .flatMap((TooltipComponentExtractor<MobEffectInstance, ?> supplier) -> supplier.extractFromItemStack(
                        itemStack));
    }
}
