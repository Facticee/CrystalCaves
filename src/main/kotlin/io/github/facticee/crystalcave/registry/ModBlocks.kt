package io.github.facticee.crystalcave.registry

import io.github.facticee.crystalcave.CrystalCaveMod
import io.github.facticee.crystalcave.blocks.CrystalOreBlock
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour

object ModBlocks {

    lateinit var CRYSTAL_STONE: Block

    lateinit var SAPPHIRE_ORE: Block

    lateinit var MOONSTONE_ORE: Block

    lateinit var TOPAZ_ORE: Block

    lateinit var SAPPHIRE_BLOCK: Block
    lateinit var MOONSTONE_BLOCK: Block

    lateinit var TOPAZ_BLOCK: Block
    lateinit var TOPAZ_SLAB: Block
    lateinit var TOPAZ_STAIRS: Block
    lateinit var TOPAZ_WALL: Block
    lateinit var TOPAZ_PILLAR: Block

    // "basic" block
    private fun register(name: String, properties: BlockBehaviour.Properties): Block {
        val id = Identifier.fromNamespaceAndPath(CrystalCaveMod.MOD_ID, name)
        val key = ResourceKey.create(BuiltInRegistries.BLOCK.key(), id)
        val block = Block(properties.setId(key))
        return Registry.register(BuiltInRegistries.BLOCK, key, block)
    }

    // for blocks that are not "basic" for example a slab (cause not a full block) or the sapphire ore, because it doesnt drop the block itself and is special in that way
    private fun <T : Block> registerSpecial(name: String, properties: BlockBehaviour.Properties, factory: (BlockBehaviour.Properties) -> T): T {
        val id = Identifier.fromNamespaceAndPath(CrystalCaveMod.MOD_ID, name)
        val key = ResourceKey.create(BuiltInRegistries.BLOCK.key(), id)
        val block = factory(properties.setId(key))
        return Registry.register(BuiltInRegistries.BLOCK, key, block)
    }

    fun registerAll() {

        CRYSTAL_STONE = register(
            "crystal_stone",
            BlockBehaviour.Properties.of()
                .strength(2f, 6f)
                .sound(SoundType.STONE)
        )

        SAPPHIRE_ORE = registerSpecial("sapphire_ore", BlockBehaviour.Properties.of().strength(3f)) {
            CrystalOreBlock(it)
        }


        MOONSTONE_ORE = registerSpecial("moonstone_ore", BlockBehaviour.Properties.of().strength(3f)) {
            CrystalOreBlock(it)
        }


        TOPAZ_ORE = registerSpecial("topaz_ore", BlockBehaviour.Properties.of().strength(3f)) {
            CrystalOreBlock(it)
        }


        SAPPHIRE_BLOCK = register(
            "sapphire_block",
            BlockBehaviour.Properties.of()
                .strength(5f)
                .sound(SoundType.AMETHYST)
        )

        MOONSTONE_BLOCK = register(
            "moonstone_block",
            BlockBehaviour.Properties.of()
                .strength(5f)
                .sound(SoundType.AMETHYST)
        )

        TOPAZ_BLOCK = register(
            "topaz_block",
            BlockBehaviour.Properties.of().strength(4f)
        )

        TOPAZ_SLAB = registerSpecial("topaz_slab", BlockBehaviour.Properties.of().strength(4f)) {
            SlabBlock(it)
        }

        TOPAZ_STAIRS = registerSpecial("topaz_stairs", BlockBehaviour.Properties.of().strength(4f)) {
            StairBlock(TOPAZ_BLOCK.defaultBlockState(), it)
        }

        TOPAZ_WALL = registerSpecial("topaz_wall", BlockBehaviour.Properties.of().strength(4f)) {
            WallBlock(it)
        }

        TOPAZ_PILLAR = registerSpecial("topaz_pillar", BlockBehaviour.Properties.of().strength(4f)) {
            RotatedPillarBlock(it)
        }
    }
}