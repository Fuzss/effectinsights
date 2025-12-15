package fuzs.effectinsights.data.client;

import fuzs.puzzleslib.api.client.data.v2.AbstractLanguageProvider;
import fuzs.puzzleslib.api.data.v2.core.DataProviderContext;
import net.minecraft.world.effect.MobEffects;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations(TranslationBuilder translationBuilder) {
        this.addVanillaEffects(translationBuilder);
        this.addModEffects(translationBuilder);
    }

    private void addVanillaEffects(TranslationBuilder translationBuilder) {
        translationBuilder.add(MobEffects.ABSORPTION.value(), "desc", "Adds temporary extra hearts.");
        translationBuilder.add(MobEffects.BAD_OMEN.value(),
                "desc",
                "Triggers ominous events in villages and trial chambers.");
        translationBuilder.add(MobEffects.BLINDNESS.value(), "desc", "Impairs vision and disables sprinting.");
        translationBuilder.add(MobEffects.CONDUIT_POWER.value(), "desc", "Enhances underwater capabilities.");
        translationBuilder.add(MobEffects.DARKNESS.value(), "desc", "Creates pulsating darkness effect.");
        translationBuilder.add(MobEffects.DOLPHINS_GRACE.value(), "desc", "Enhances swimming speed.");
        translationBuilder.add(MobEffects.FIRE_RESISTANCE.value(), "desc", "Prevents fire and lava damage.");
        translationBuilder.add(MobEffects.GLOWING.value(), "desc", "Makes entity visible through walls.");
        translationBuilder.add(MobEffects.HASTE.value(), "desc", "Enhances mining and attack speed.");
        translationBuilder.add(MobEffects.HEALTH_BOOST.value(), "desc", "Increases max health.");
        translationBuilder.add(MobEffects.HERO_OF_THE_VILLAGE.value(), "desc", "Improves villager interactions.");
        translationBuilder.add(MobEffects.HUNGER.value(), "desc", "Drains food levels.");
        translationBuilder.add(MobEffects.INFESTED.value(), "desc", "Spawns silverfish when damaged.");
        translationBuilder.add(MobEffects.INSTANT_DAMAGE.value(), "desc", "Harms living, heals undead.");
        translationBuilder.add(MobEffects.INSTANT_HEALTH.value(), "desc", "Heals living, harms undead.");
        translationBuilder.add(MobEffects.INVISIBILITY.value(),
                "desc",
                "Grants invisibility and stealth capabilities.");
        translationBuilder.add(MobEffects.JUMP_BOOST.value(), "desc", "Enhances jumping abilities.");
        translationBuilder.add(MobEffects.LEVITATION.value(), "desc", "Causes upward floating.");
        translationBuilder.add(MobEffects.LUCK.value(), "desc", "Improves loot quality.");
        translationBuilder.add(MobEffects.MINING_FATIGUE.value(), "desc", "Reduces mining efficiency.");
        translationBuilder.add(MobEffects.NAUSEA.value(), "desc", "Distorts vision.");
        translationBuilder.add(MobEffects.NIGHT_VISION.value(), "desc", "Enhances vision in the darkness.");
        translationBuilder.add(MobEffects.OOZING.value(), "desc", "Spawns slimes on death.");
        translationBuilder.add(MobEffects.POISON.value(), "desc", "Causes survivable damage over time.");
        translationBuilder.add(MobEffects.RAID_OMEN.value(), "desc", "Triggers village raid on expiry.");
        translationBuilder.add(MobEffects.REGENERATION.value(), "desc", "Restores health gradually.");
        translationBuilder.add(MobEffects.RESISTANCE.value(), "desc", "Reduces damage taken.");
        translationBuilder.add(MobEffects.SATURATION.value(), "desc", "Restores food levels.");
        translationBuilder.add(MobEffects.SLOW_FALLING.value(), "desc", "Provides safe descent.");
        translationBuilder.add(MobEffects.SLOWNESS.value(), "desc", "Reduces movement speed.");
        translationBuilder.add(MobEffects.SPEED.value(), "desc", "Enhances movement speed.");
        translationBuilder.add(MobEffects.STRENGTH.value(), "desc", "Increases attack damage.");
        translationBuilder.add(MobEffects.TRIAL_OMEN.value(), "desc", "Empowers nearby trial spawners.");
        translationBuilder.add(MobEffects.UNLUCK.value(), "desc", "Reduces loot quality.");
        translationBuilder.add(MobEffects.WATER_BREATHING.value(), "desc", "Enables underwater breathing.");
        translationBuilder.add(MobEffects.WEAKNESS.value(), "desc", "Reduces attack damage.");
        translationBuilder.add(MobEffects.WEAVING.value(), "desc", "Spreads cobwebs on death.");
        translationBuilder.add(MobEffects.WIND_CHARGED.value(), "desc", "Releases wind burst on death.");
        translationBuilder.add(MobEffects.WITHER.value(), "desc", "Causes fatal damage over time.");
    }

    private void addModEffects(TranslationBuilder translationBuilder) {
        translationBuilder.add("effect.alexscaves.deepsight", "desc", "Increases underwater visibility.");
        translationBuilder.add("effect.alexscaves.magnetizing", "desc", "Enables interaction with magnetic forces.");
        translationBuilder.add("effect.alexsmobs.bug_pheromones", "desc", "Makes hostile arthropods neutral.");
        translationBuilder.add("effect.alexsmobs.clinging", "desc", "Enables ceiling walking.");
        translationBuilder.add("effect.alexsmobs.debilitating_sting",
                "desc",
                "Poisons non-arthropods and paralyzes arthropods.");
        translationBuilder.add("effect.alexsmobs.earthquake", "desc", "Causes violent shaking.");
        translationBuilder.add("effect.alexsmobs.ender_flu", "desc", "Spawns an Enderiophage upon expiration.");
        translationBuilder.add("effect.alexsmobs.exsanguination", "desc", "Drains health over time.");
        translationBuilder.add("effect.alexsmobs.fear", "desc", "Prevents movement.");
        translationBuilder.add("effect.alexsmobs.fleet_footed", "desc", "Increases jump and sprint speed.");
        translationBuilder.add("effect.alexsmobs.knockback_resistance", "desc", "Reduces knockback by 50%.");
        translationBuilder.add("effect.alexsmobs.lava_vision", "desc", "Enables seeing through lava.");
        translationBuilder.add("effect.alexsmobs.mosquito_repellent", "desc", "Repels Crimson Mosquitoes.");
        translationBuilder.add("effect.alexsmobs.oiled", "desc", "Enables floating on water and in rain.");
        translationBuilder.add("effect.alexsmobs.orcas_might", "desc", "Increases attack speed.");
        translationBuilder.add("effect.alexsmobs.poison_resistance", "desc", "Prevents poison damage.");
        translationBuilder.add("effect.alexsmobs.power_down", "desc", "Plays an automated message.");
        translationBuilder.add("effect.alexsmobs.soulsteal", "desc", "Adds life steal to attacks.");
        translationBuilder.add("effect.alexsmobs.sunbird_blessing",
                "desc",
                "Improves flight capabilities and reduces fall damage.");
        translationBuilder.add("effect.alexsmobs.sunbird_curse",
                "desc",
                "Impairs flight capabilities and increases fall speed.");
        translationBuilder.add("effect.alexsmobs.tigers_blessing", "desc", "Makes tigers neutral and protective.");
        translationBuilder.add("effect.deeperdarker.sculk_affinity",
                "desc",
                "Prevents vibration emission and detection.");
        translationBuilder.add("effect.enderzoology.displacement", "desc", "Causes random teleportation when damaged.");
        translationBuilder.add("effect.farmersdelight.comfort", "desc", "Enables health regeneration at low hunger.");
        translationBuilder.add("effect.farmersdelight.nourishment", "desc", "Prevents hunger loss unless damaged.");
        translationBuilder.add("effect.farmersrespite.caffeinated", "desc", "Increases movement and attack speed.");
        translationBuilder.add("effect.longshot.longshot", "desc", "Increases projectile speed and damage.");
        translationBuilder.add("effect.mynethersdelight.b_pungent",
                "desc",
                "Reduces fire damage and enables healing near heat.");
        translationBuilder.add("effect.mynethersdelight.g_pungent",
                "desc",
                "Reduces fire damage and enables healing near heat.");
        translationBuilder.add("effect.supplementaries.overencumbered", "desc", "Reduces movement capabilities.");
        translationBuilder.add("effect.via_romana.travellers_fatigue", "desc", "Prevents fast travel.");
    }
}
