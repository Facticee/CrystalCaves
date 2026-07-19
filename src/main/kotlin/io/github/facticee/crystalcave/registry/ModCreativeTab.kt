package io.github.facticee.crystalcave.registry

import io.github.facticee.crystalcave.CrystalCaveMod
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab

object ModCreativeTab {

    private val CRYSTAL_CAVE_KEY: ResourceKey<CreativeModeTab> =
        ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath(
                CrystalCaveMod.MOD_ID,
                "crystal_cave"
            )
        )


    fun registerAll() {

    }
}