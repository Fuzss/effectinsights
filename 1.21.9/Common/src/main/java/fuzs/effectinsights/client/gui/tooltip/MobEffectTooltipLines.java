package fuzs.effectinsights.client.gui.tooltip;

import com.google.common.collect.ImmutableList;
import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.config.ClientConfig;
import fuzs.tooltipinsights.api.v1.client.gui.tooltip.DescriptionLines;
import fuzs.tooltipinsights.api.v1.client.gui.tooltip.InternalNameLines;
import fuzs.tooltipinsights.api.v1.client.gui.tooltip.ModNameLines;
import fuzs.tooltipinsights.api.v1.client.gui.tooltip.TooltipLinesExtractor;
import fuzs.tooltipinsights.api.v1.config.AbstractClientConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;

import java.util.List;
import java.util.stream.Stream;

public final class MobEffectTooltipLines {
    static final TooltipLinesExtractor<MobEffectInstance, ClientConfig.EffectTooltipComponents> DISPLAY_NAME = new PotionContentsLines() {
        @Override
        protected boolean isEnabled(ClientConfig.EffectTooltipComponents tooltipComponents) {
            return tooltipComponents.displayName;
        }

        @Override
        protected Stream<Component> modifyTooltipLines(List<Component> tooltipLines, int separatorIndex) {
            if (separatorIndex != -1) {
                return tooltipLines.subList(0, separatorIndex).stream();
            } else {
                return tooltipLines.stream();
            }
        }
    };
    static final TooltipLinesExtractor<MobEffectInstance, AbstractClientConfig.TooltipComponents> DESCRIPTION = new DescriptionLines<>() {
        @Override
        protected String getDescriptionId(MobEffectInstance mobEffect) {
            return mobEffect.getDescriptionId();
        }
    };
    static final TooltipLinesExtractor<MobEffectInstance, AbstractClientConfig.TooltipComponents> MOD_NAME = new ModNameLines<>() {
        @Override
        protected ResourceKey<?> getResourceKey(MobEffectInstance mobEffect) {
            return mobEffect.getEffect().unwrapKey().orElseThrow();
        }
    };
    static final TooltipLinesExtractor<MobEffectInstance, AbstractClientConfig.TooltipComponents> INTERNAL_NAME = new InternalNameLines<>() {
        @Override
        protected ResourceKey<?> getResourceKey(MobEffectInstance mobEffect) {
            return mobEffect.getEffect().unwrapKey().orElseThrow();
        }
    };
    static final TooltipLinesExtractor<MobEffectInstance, ClientConfig.EffectTooltipComponents> ATTRIBUTES = new PotionContentsLines() {
        @Override
        protected boolean isEnabled(ClientConfig.EffectTooltipComponents tooltipComponents) {
            return tooltipComponents.effectAttributes;
        }

        @Override
        protected Stream<Component> modifyTooltipLines(List<Component> tooltipLines, int separatorIndex) {
            if (separatorIndex != -1) {
                return tooltipLines.subList(separatorIndex, tooltipLines.size()).stream();
            } else {
                return Stream.empty();
            }
        }
    };
    static final List<TooltipLinesExtractor<MobEffectInstance, AbstractClientConfig.TooltipComponents>> ITEM_SUPPLIERS = ImmutableList.of(
            DESCRIPTION,
            MOD_NAME,
            INTERNAL_NAME);
    static final List<TooltipLinesExtractor<MobEffectInstance, ClientConfig.EffectTooltipComponents>> WIDGET_SUPPLIERS = ImmutableList.of(
            DISPLAY_NAME,
            DESCRIPTION.cast(),
            MOD_NAME.cast(),
            INTERNAL_NAME.cast(),
            ATTRIBUTES);

    private MobEffectTooltipLines() {
        // NO-OP
    }

    public static List<Component> getMobEffectItemTooltipLines(MobEffectInstance mobEffect) {
        return TooltipLinesExtractor.getTooltipLines(ITEM_SUPPLIERS,
                EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.decorationComponent,
                EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.decorationStyle,
                mobEffect,
                EffectInsights.CONFIG.get(ClientConfig.class).effectItemTooltips.itemTooltipLines);
    }

    public static List<Component> getMobEffectWidgetTooltipLines(MobEffectInstance mobEffect) {
        return TooltipLinesExtractor.getTooltipLines(WIDGET_SUPPLIERS,
                EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.decorationComponent,
                EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.decorationStyle,
                mobEffect,
                EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.widgetTooltipLines);
    }
}
