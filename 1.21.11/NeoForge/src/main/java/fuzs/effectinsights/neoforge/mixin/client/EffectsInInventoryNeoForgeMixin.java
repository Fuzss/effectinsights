package fuzs.effectinsights.neoforge.mixin.client;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.config.ClientConfig;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(EffectsInInventory.class)
abstract class EffectsInInventoryNeoForgeMixin {

    @ModifyVariable(method = "renderText(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/Component;Lnet/minecraft/client/gui/Font;IIIIIILnet/minecraft/world/effect/MobEffectInstance;)V",
                    at = @At(value = "LOAD", ordinal = 0),
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;III)V")))
    private boolean renderText(boolean mustClipText) {
        // This makes the tooltip always render, so we can then modify it via the NeoForge event.
        return mustClipText || EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.widgetTooltips;
    }
}
