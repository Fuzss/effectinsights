package fuzs.effectinsights.config;

import fuzs.puzzleslib.api.config.v3.Config;
import fuzs.puzzleslib.api.config.v3.ConfigCore;
import fuzs.tooltipinsights.api.v1.config.AbstractClientConfig;

public class ClientConfig extends AbstractClientConfig {
    @Config
    public final EffectWidgetTooltips effectWidgetTooltips = new EffectWidgetTooltips();
    @Config
    public final EffectItemTooltips effectItemTooltips = new EffectItemTooltips();
    @Config
    public final EffectWidgetTooltips effectBeaconTooltips = new EffectWidgetTooltips();

    public static class EffectWidgetTooltips extends ItemTooltips {
        @Config
        public final EffectTooltipComponents widgetTooltipLines = new EffectTooltipComponents();
    }

    public static class EffectItemTooltips extends ItemTooltips {
        @Config
        public final EffectDescriptionTargets itemDescriptionTargets = new EffectDescriptionTargets();
        @Config
        public final TooltipComponents itemTooltipLines = new TooltipComponents();
        @Config(description = "Display potion effects for consumable item tooltips, such as food or the totem of undying.")
        public boolean consumablesEffectTooltips = true;
    }

    public static class EffectDescriptionTargets implements ConfigCore {
        @Config(description = "Add effect descriptions to potion items.")
        public boolean potion = true;
        @Config(description = "Add effect descriptions to consumable items (such as rotten flesh and raw chicken).")
        public boolean consumable = true;
        @Config(description = "Add effect descriptions to totem of undying items.")
        public boolean totemOfUndying = true;
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
