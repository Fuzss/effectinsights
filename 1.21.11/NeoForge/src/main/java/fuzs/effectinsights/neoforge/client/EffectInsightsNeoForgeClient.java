package fuzs.effectinsights.neoforge.client;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.client.EffectInsightsClient;
import fuzs.effectinsights.client.gui.tooltip.MobEffectTooltipLines;
import fuzs.effectinsights.config.ClientConfig;
import fuzs.effectinsights.data.client.ModLanguageProvider;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.GatherEffectScreenTooltipsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = EffectInsights.MOD_ID, dist = Dist.CLIENT)
public class EffectInsightsNeoForgeClient {

    public EffectInsightsNeoForgeClient() {
        ClientModConstructor.construct(EffectInsights.MOD_ID, EffectInsightsClient::new);
        registerEventHandlers(NeoForge.EVENT_BUS);
        DataProviderHelper.registerDataProviders(EffectInsights.MOD_ID, ModLanguageProvider::new);
    }

    private static void registerEventHandlers(IEventBus eventBus) {
        eventBus.addListener((final GatherEffectScreenTooltipsEvent event) -> {
            if (EffectInsights.CONFIG.get(ClientConfig.class).effectWidgetTooltips.widgetTooltips()) {
                event.getTooltip().clear();
                event.getTooltip()
                        .addAll(MobEffectTooltipLines.getMobEffectWidgetTooltipLines(event.getEffectInstance()));
            }
        });
    }
}
