package fuzs.effectinsights.mixin.client;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.config.ClientConfig;
import fuzs.puzzleslib.api.client.gui.v2.tooltip.TooltipBuilder;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(targets = "net.minecraft.client.gui.screens.inventory.BeaconScreen$BeaconPowerButton")
abstract class BeaconScreen$BeaconPowerButtonMixin extends AbstractButton {
    @Shadow
    @Final
    private boolean isPrimary;
    @Shadow
    @Final
    protected int tier;

    public BeaconScreen$BeaconPowerButtonMixin(int i, int j, int k, int l, Component component) {
        super(i, j, k, l, component);
    }

    @Inject(method = "setEffect", at = @At("RETURN"))
    protected void setEffect(Holder<MobEffect> holder, CallbackInfo callback) {
        // TODO add a disabled option, which is then split from not being active to prevent vanilla
        if (EffectInsights.CONFIG.get(ClientConfig.class).effectBeaconTooltips.itemDescriptions.isActive()) {
            MobEffectInstance mobEffect = new MobEffectInstance(holder,
                    0,
                    this.effectinsights$getBeaconEffectAmplifier(holder));
            List<Component> tooltipLines = MobEffectTooltipLines.getBeaconTooltipLines(mobEffect);
            TooltipBuilder.create(tooltipLines).build(this);
        }
    }

    @Unique
    private int effectinsights$getBeaconEffectAmplifier(Holder<MobEffect> holder) {
        // BeaconPowerButton::createEffectDescription does not provide something we can use, so try to mimic it.
        if (!this.isPrimary && this.tier < BeaconBlockEntity.BEACON_EFFECTS.size()) {
            if (!BeaconBlockEntity.BEACON_EFFECTS.get(this.tier).contains(holder)) {
                return 1;
            }
        }

        return 0;
    }
}
