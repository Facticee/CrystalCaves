package io.github.facticee.crystalcave.registry

import io.github.facticee.crystalcave.CrystalCaveMod
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack

object ModCreativeTab {

    private val CRYSTAL_CAVE_KEY: ResourceKey<CreativeModeTab> =
        ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            Identifier.fromNamespaceAndPath(
                CrystalCaveMod.MOD_ID,
                "crystal_cave"
            )
        )

    val CRYSTAL_CAVE_TAB: CreativeModeTab = Registry.register(
        BuiltInRegistries.CREATIVE_MODE_TAB,
        CRYSTAL_CAVE_KEY,
        FabricCreativeModeTab.builder()
            .title(Component.translatable("itemGroup.crystalcave.crystal_cave"))
            .icon {
                ItemStack(ModItems.SAPPHIRE)
            }
            .displayItems { _, output ->

                // TEST
                output.accept(ModItems.SAPPHIRE)
                
            }
            .build()
    )

    fun registerAll() {

    }
}