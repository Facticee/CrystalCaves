package io.github.facticee.crystalcave.registry

import io.github.facticee.crystalcave.CrystalCaveMod
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block

object ModItems {
    // ores
    lateinit var SAPPHIRE: Item
    lateinit var MOONSTONE: Item
    lateinit var TOPAZ: Item
    lateinit var RAW_SAPPHIRE: Item
    lateinit var RAW_MOONSTONE: Item

    // blocks
    lateinit var CRYSTAL_STONE: Item
    lateinit var CRYSTAL_TUFF: Item
    lateinit var SAPPHIRE_ORE: Item
    lateinit var DEEPSLATE_SAPPHIRE_ORE: Item
    lateinit var MOONSTONE_ORE: Item
    lateinit var DEEPSLATE_MOONSTONE_ORE: Item
    lateinit var TOPAZ_ORE: Item
    lateinit var DEEPSLATE_TOPAZ_ORE: Item
    lateinit var SAPPHIRE_BLOCK: Item
    lateinit var MOONSTONE_BLOCK: Item
    lateinit var TOPAZ_BLOCK: Item
    lateinit var TOPAZ_SLAB: Item
    lateinit var TOPAZ_STAIRS: Item
    lateinit var TOPAZ_WALL: Item
    lateinit var TOPAZ_PILLAR: Item


    private fun register(name: String, properties: Item.Properties, factory: (Item.Properties) -> Item): Item {
        val id = Identifier.fromNamespaceAndPath(CrystalCaveMod.MOD_ID, name)
        val key = ResourceKey.create(BuiltInRegistries.ITEM.key(), id)

        val item = factory(properties.setId(key))
        return Registry.register(BuiltInRegistries.ITEM, key, item)
    }


    private fun registerBlockItem(name: String, block: Block, properties: Item.Properties = Item.Properties()): Item {
        val id = Identifier.fromNamespaceAndPath(CrystalCaveMod.MOD_ID, name)
        val key = ResourceKey.create(BuiltInRegistries.ITEM.key(), id)

        val blockItem = BlockItem(block, properties.setId(key))
        return Registry.register(BuiltInRegistries.ITEM, key, blockItem)
    }

    fun registerAll() {

        // ores
        SAPPHIRE = register("sapphire", Item.Properties()) { Item(it) }
        MOONSTONE = register("moonstone", Item.Properties()) { Item(it) }
        TOPAZ = register("topaz", Item.Properties()) { Item(it) }
        RAW_SAPPHIRE = register("raw_sapphire", Item.Properties()) { Item(it) }
        RAW_MOONSTONE = register("raw_moonstone", Item.Properties()) { Item(it) }


        // blocks
        CRYSTAL_STONE = registerBlockItem("crystal_stone", ModBlocks.CRYSTAL_STONE)
        CRYSTAL_TUFF = registerBlockItem("crystal_tuff", ModBlocks.CRYSTAL_TUFF)

        SAPPHIRE_ORE = registerBlockItem("sapphire_ore", ModBlocks.SAPPHIRE_ORE)
        DEEPSLATE_SAPPHIRE_ORE = registerBlockItem("deepslate_sapphire_ore", ModBlocks.DEEPSLATE_SAPPHIRE_ORE)
        MOONSTONE_ORE = registerBlockItem("moonstone_ore", ModBlocks.MOONSTONE_ORE)
        DEEPSLATE_MOONSTONE_ORE = registerBlockItem("deepslate_moonstone_ore", ModBlocks.DEEPSLATE_MOONSTONE_ORE)
        TOPAZ_ORE = registerBlockItem("topaz_ore", ModBlocks.TOPAZ_ORE)
        DEEPSLATE_TOPAZ_ORE = registerBlockItem("deepslate_topaz_ore", ModBlocks.DEEPSLATE_TOPAZ_ORE)

        SAPPHIRE_BLOCK = registerBlockItem("sapphire_block", ModBlocks.SAPPHIRE_BLOCK)
        MOONSTONE_BLOCK = registerBlockItem("moonstone_block", ModBlocks.MOONSTONE_BLOCK)

        TOPAZ_BLOCK = registerBlockItem("topaz_block", ModBlocks.TOPAZ_BLOCK)
        TOPAZ_SLAB = registerBlockItem("topaz_slab", ModBlocks.TOPAZ_SLAB)
        TOPAZ_STAIRS = registerBlockItem("topaz_stairs", ModBlocks.TOPAZ_STAIRS)
        TOPAZ_WALL = registerBlockItem("topaz_wall", ModBlocks.TOPAZ_WALL)
        TOPAZ_PILLAR = registerBlockItem("topaz_pillar", ModBlocks.TOPAZ_PILLAR)
    }
}