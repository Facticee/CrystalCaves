package io.github.facticee.crystalcave.registry

import io.github.facticee.crystalcave.CrystalCaveMod
import io.github.facticee.crystalcave.world.feature.CrystalChamberConfig
import io.github.facticee.crystalcave.world.feature.CrystalChamberFeature
import io.github.facticee.crystalcave.world.feature.CrystalFissureConfig
import io.github.facticee.crystalcave.world.feature.CrystalFissureFeature
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.feature.Feature


// registers the different cave types - crystal fissure -> long (for spphire it is horizontal and for topaz vertikal)
// crystal chamber -> runde kammer wie geode für moonstone

object ModFeatures {

    lateinit var CRYSTAL_FISSURE: CrystalFissureFeature
    lateinit var CRYSTAL_CHAMBER: CrystalChamberFeature
    
    fun registerAll() {
        CRYSTAL_FISSURE = register("crystal_fissure", CrystalFissureFeature(CrystalFissureConfig.CODEC))
        CRYSTAL_CHAMBER = register("crystal_chamber", CrystalChamberFeature(CrystalChamberConfig.CODEC))
    }

    private fun <T : Feature<*>> register(name: String, feature: T): T {
        val id = Identifier.fromNamespaceAndPath(CrystalCaveMod.MOD_ID, name)
        val key: ResourceKey<Feature<*>> = ResourceKey.create(BuiltInRegistries.FEATURE.key(), id)
        return Registry.register(BuiltInRegistries.FEATURE, key, feature)
    }
}
