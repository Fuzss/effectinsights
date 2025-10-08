package fuzs.effectinsights.client.handler;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.client.gui.component.EffectComponents;
import fuzs.effectinsights.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.config.ClientConfig;
import fuzs.tooltipinsights.api.v1.client.handler.TooltipDescriptionsHandler;
import fuzs.tooltipinsights.api.v1.config.ItemDescriptionMode;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class EffectTooltipHandler extends TooltipDescriptionsHandler<MobEffectInstance> {
    public static final TooltipDescriptionsHandler<MobEffectInstance> INSTANCE = new EffectTooltipHandler();

    private EffectTooltipHandler() {
        // NO-OP
    }

    @Override
    protected ItemDescriptionMode getItemDescriptionMode() {
        return EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.itemDescriptions;
    }

    @Override
    protected Map<String, MobEffectInstance> getByDescriptionId(ItemStack itemStack, HolderLookup.Provider registries) {
        // an item can contain the same effect multiple times, so make sure to include a merge function in our collect call
        return EffectComponents.getAllMobEffects(itemStack)
                .collect(Collectors.toMap(MobEffectInstance::getDescriptionId,
                        Function.identity(),
                        (MobEffectInstance o1, MobEffectInstance o2) -> o1));
    }

    @Override
    protected List<Component> getItemTooltipLines(MobEffectInstance mobEffectInstance) {
        return MobEffectTooltipLines.getMobEffectItemTooltipLines(mobEffectInstance);
    }
}
