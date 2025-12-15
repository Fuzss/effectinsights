package fuzs.effectinsights.client.gui.tooltip;

import fuzs.effectinsights.config.ClientConfig;
import fuzs.tooltipinsights.api.v1.client.gui.tooltip.TooltipLinesExtractor;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.TickRateManager;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.PotionContents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public abstract class PotionContentsLines extends TooltipLinesExtractor<MobEffectInstance, ClientConfig.EffectTooltipComponents> {

    public PotionContentsLines() {
        super(false);
    }

    @Override
    protected final Stream<Component> getTooltipLines(MobEffectInstance mobEffect) {
        List<Component> tooltipLines = new ArrayList<>();
        TickRateManager tickRateManager = Minecraft.getInstance().level.tickRateManager();
        PotionContents.addPotionTooltip(Collections.singleton(mobEffect),
                tooltipLines::add,
                1.0F,
                tickRateManager.tickrate());
        int index = tooltipLines.indexOf(CommonComponents.EMPTY);
        return this.modifyTooltipLines(tooltipLines, index);
    }

    protected abstract Stream<Component> modifyTooltipLines(List<Component> tooltipLines, int separatorIndex);
}
