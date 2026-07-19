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

                // crystals
                output.accept(ModItems.SAPPHIRE)
                output.accept(ModItems.MOONSTONE)
                output.accept(ModItems.TOPAZ)
                output.accept(ModItems.RAW_SAPPHIRE)
                output.accept(ModItems.RAW_MOONSTONE)


                // Ores
                output.accept(ModItems.SAPPHIRE_ORE)
                output.accept(ModItems.DEEPSLATE_SAPPHIRE_ORE)
                output.accept(ModItems.MOONSTONE_ORE)
                output.accept(ModItems.DEEPSLATE_MOONSTONE_ORE)
                output.accept(ModItems.TOPAZ_ORE)
                output.accept(ModItems.DEEPSLATE_TOPAZ_ORE)

                // Blocks
                output.accept(ModItems.CRYSTAL_STONE)
                output.accept(ModItems.CRYSTAL_TUFF)

                output.accept(ModItems.SAPPHIRE_BLOCK)
                output.accept(ModItems.MOONSTONE_BLOCK)

                output.accept(ModItems.TOPAZ_BLOCK)
                output.accept(ModItems.TOPAZ_SLAB)
                output.accept(ModItems.TOPAZ_STAIRS)
                output.accept(ModItems.TOPAZ_WALL)
                output.accept(ModItems.TOPAZ_PILLAR)
            }
            .build()
    )

    fun registerAll() {

    }
}