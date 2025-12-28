package fuzs.effectinsights.client;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.client.handler.EffectTooltipHandler;
import fuzs.effectinsights.client.handler.FoodTooltipHandler;
import fuzs.effectinsights.config.ClientConfig;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.event.v1.gui.GatherEffectScreenTooltipCallback;
import fuzs.puzzleslib.api.client.event.v1.gui.ItemTooltipCallback;
import fuzs.puzzleslib.api.event.v1.core.EventPhase;
import fuzs.tooltipinsights.api.v1.client.handler.TooltipDescriptionsHandler;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;

public class EffectInsightsClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ItemTooltipCallback.EVENT.register(EventPhase.LAST, EffectTooltipHandler.INSTANCE::onItemTooltip);
        ItemTooltipCallback.EVENT.register(EventPhase.AFTER, FoodTooltipHandler::onItemTooltip);
        GatherEffectScreenTooltipCallback.EVENT.register((AbstractContainerScreen<?> screen, MobEffectInstance mobEffect, List<Component> tooltipLines) -> {
            // TODO add a disabled option, which is then split from not being active to prevent vanilla
            if (EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.itemDescriptions.isActive()) {
                tooltipLines.clear();
                tooltipLines.addAll(MobEffectTooltipLines.getMobEffectWidgetTooltipLines(mobEffect));
            }
        });
    }

    @Override
    public void onClientSetup() {
        TooltipDescriptionsHandler.printMissingDescriptionWarnings(Registries.MOB_EFFECT,
                (Holder.Reference<MobEffect> holder) -> holder.value().getDescriptionId());
    }
}
