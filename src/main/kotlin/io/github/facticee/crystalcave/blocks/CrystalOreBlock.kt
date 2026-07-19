package io.github.facticee.crystalcave.blocks

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.parameters.LootContextParams

class CrystalOreBlock(properties: Properties) : Block(properties) {
    override fun getDrops(state: BlockState, builder: LootParams.Builder): List<ItemStack> {
        val tool = builder.getOptionalParameter(LootContextParams.TOOL)

        // Use tool.getItem() instead of tool.item
        if (tool != null && tool.`is`(Items.GOLDEN_PICKAXE)) {
            return super.getDrops(state, builder)
        }
        return emptyList()
    }
}