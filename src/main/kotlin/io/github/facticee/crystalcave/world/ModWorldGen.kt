package io.github.facticee.crystalcave.world

import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature


// Sapphire  (tief , -58 bis -10)  -> Akzent: Moonstone
// Moonstone (nah -10 bis 40)  -> Akzent: Topaz
// Topaz     (mittel -40 bis 25)  -> Akzent: Sapphire

object ModWorldGen {

    fun registerAll() {
        registerGeode("sapphire_geode_placed")
        registerGeode("moonstone_geode_placed")
        registerGeode("topaz_geode_placed")
    }

    private fun registerGeode(placedFeatureName: String) {
        val placedKey: ResourceKey<PlacedFeature> = ResourceKey.create(
            Registries.PLACED_FEATURE,
            Identifier.fromNamespaceAndPath("crystalcave", placedFeatureName)
        )

        BiomeModifications.addFeature(
            BiomeSelectors.foundInOverworld(),
            GenerationStep.Decoration.UNDERGROUND_DECORATION,
            placedKey
        )
    }
}
