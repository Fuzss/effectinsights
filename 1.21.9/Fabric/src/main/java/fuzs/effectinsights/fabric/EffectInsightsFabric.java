package fuzs.effectinsights.fabric;

import fuzs.effectinsights.EffectInsights;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class EffectInsightsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(EffectInsights.MOD_ID, EffectInsights::new);
    }
}
