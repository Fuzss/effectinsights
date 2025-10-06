package fuzs.effectinsights.client;

import fuzs.effectinsights.client.handler.EffectTooltipHandler;
import fuzs.effectinsights.client.handler.EffectWidgetHandler;
import fuzs.effectinsights.client.handler.FoodTooltipHandler;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.api.client.event.v1.gui.ContainerScreenEvents;
import fuzs.puzzleslib.api.client.event.v1.gui.GatherEffectScreenTooltipCallback;
import fuzs.puzzleslib.api.client.event.v1.gui.ItemTooltipCallback;
import fuzs.puzzleslib.api.client.event.v1.gui.PrepareInventoryMobEffectsCallback;
import fuzs.puzzleslib.api.event.v1.core.EventPhase;
import fuzs.tooltipinsights.api.v1.client.handler.TooltipDescriptionsHandler;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;

public class EffectInsightsClient implements ClientModConstructor {

    @Override
    public void onConstructMod() {
        registerEventHandlers();
    }

    private static void registerEventHandlers() {
        ItemTooltipCallback.EVENT.register(EventPhase.LAST, EffectTooltipHandler.INSTANCE::onItemTooltip);
        ItemTooltipCallback.EVENT.register(EventPhase.AFTER, FoodTooltipHandler::onItemTooltip);
        PrepareInventoryMobEffectsCallback.EVENT.register(EventPhase.LAST, EffectWidgetHandler::onInventoryMobEffects);
        ContainerScreenEvents.FOREGROUND.register(EffectWidgetHandler::onDrawForeground);
        GatherEffectScreenTooltipCallback.EVENT.register(EffectWidgetHandler::onGatherEffectScreenTooltip);
    }

    @Override
    public void onClientSetup() {
        TooltipDescriptionsHandler.printMissingDescriptionWarnings(Registries.MOB_EFFECT,
                (Holder.Reference<MobEffect> holder) -> holder.value().getDescriptionId());
    }
}
