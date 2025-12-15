package fuzs.effectinsights.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.puzzleslib.api.core.v1.ModLoaderEnvironment;
import fuzs.tooltipinsights.api.v1.config.AbstractClientConfig;

public class ClientConfig extends AbstractClientConfig {
    @Config
    public final EffectWidgetTooltips effectWidgetTooltips = new EffectWidgetTooltips();
    @Config
    public final EffectItemTooltips effectItemTooltips = new EffectItemTooltips();

    public static class EffectWidgetTooltips extends StyledTooltips {
        @Config(description = "Add rich tooltips including effect descriptions to effect widgets in inventory screens.")
        boolean widgetTooltips = true;
        @Config
        public final EffectTooltipComponents widgetTooltipLines = new EffectTooltipComponents();

        public boolean widgetTooltips() {
            if (ModLoaderEnvironment.INSTANCE.isModLoaded("stylisheffects")) {
                return false;
            } else if (ModLoaderEnvironment.INSTANCE.isModLoaded("jeed")) {
                return false;
            } else {
                return this.widgetTooltips;
            }
        }
    }

    public static class EffectItemTooltips extends ItemTooltips {
        @Config
        public final EffectDescriptionTargets itemDescriptionTargets = new EffectDescriptionTargets();
        @Config
        public final TooltipComponents itemTooltipLines = new TooltipComponents();
        @Config(description = "Display potion effects for food item tooltips.")
        public boolean foodEffectTooltips = true;
    }

    public static class EffectDescriptionTargets implements ConfigCore {
        @Config(description = "Add effect descriptions to potion items, e.g. potion, splash potion, lingering potion, and tipped arrow.")
        public boolean potionContents = true;
        @Config(description = "Add effect descriptions to food items, e.g. rotten flesh and raw chicken.")
        public boolean consumable = true;
        @Config(description = "Add effect descriptions to ominous bottle items.")
        public boolean ominousBottle = true;
        @Config(description = "Add effect descriptions to suspicious stew items.")
        public boolean suspiciousStew = true;
    }

    public static class EffectTooltipComponents extends TooltipComponents {
        @Config(description = "Add the effect name and duration to tooltips.")
        public boolean displayName = true;
        @Config(description = "Add attributes granted by an effect to tooltips.")
        public boolean effectAttributes = true;
    }
}
