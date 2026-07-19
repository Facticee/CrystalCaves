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

    }
}