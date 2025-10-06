package fuzs.effectinsights.fabric.client;

import fuzs.effectinsights.EffectInsights;
import fuzs.effectinsights.client.EffectInsightsClient;
import fuzs.puzzleslib.api.client.core.v1.ClientModConstructor;
import net.fabricmc.api.ClientModInitializer;

public class EffectInsightsFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientModConstructor.construct(EffectInsights.MOD_ID, EffectInsightsClient::new);
    }
}
