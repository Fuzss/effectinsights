package fuzs.effectinsights.fabric.mixin.client;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.config.ClientConfig;
import net.minecraft.client.gui.screens.inventory.EffectsInInventory;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EffectsInInventory.class)
abstract class EffectsInInventoryFabricMixin {
    @Unique
    @Nullable
    private MobEffectInstance effectinsights$mobEffect;

    @ModifyVariable(method = "renderEffects", at = @At("STORE"))
    private MobEffectInstance renderEffects(MobEffectInstance mobEffect) {
        this.effectinsights$mobEffect = mobEffect;
        return mobEffect;
    }

    @Inject(method = "renderEffects", at = @At("RETURN"))
    private void renderEffects(CallbackInfo callback) {
        this.effectinsights$mobEffect = null;
    }

    @ModifyVariable(method = "renderText",
                    at = @At(value = "LOAD", ordinal = 0),
                    slice = @Slice(from = @At(value = "INVOKE",
                                              target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;III)V")))
    private boolean renderText(boolean mustClipText) {
        // This makes the tooltip always render, so we can then modify it later on.
        return mustClipText || EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.widgetTooltips();
    }

    @ModifyArg(method = "renderText",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/client/gui/GuiGraphics;setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;II)V"))
    private List<Component> renderText(List<Component> tooltipLines) {
        if (this.effectinsights$mobEffect != null
                && EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.widgetTooltips()) {
            return MobEffectTooltipLines.getMobEffectWidgetTooltipLines(this.effectinsights$mobEffect);
        } else {
            return tooltipLines;
        }
    }
}
