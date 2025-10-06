package fuzs.effectinsights.neoforge;

import fuzs.effectinsights.EffectInsights;
import fuzs.puzzleslib.api.core.v1.ModConstructor;
import net.neoforged.fml.common.Mod;

@Mod(EffectInsights.MOD_ID)
public class EffectInsightsNeoForge {

    public EffectInsightsNeoForge() {
        ModConstructor.construct(EffectInsights.MOD_ID, EffectInsights::new);
    }
}
