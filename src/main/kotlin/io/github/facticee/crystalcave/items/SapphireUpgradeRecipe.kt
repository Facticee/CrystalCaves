package io.github.facticee.crystalcave.items

import com.mojang.serialization.MapCodec
import io.github.facticee.crystalcave.registry.ModItems
import io.github.facticee.crystalcave.registry.ModRecipes
import net.minecraft.core.component.DataComponents
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.ItemLore
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level
import kotlin.math.ceil

class SapphireUpgradeRecipe : CustomRecipe() {

    override fun matches(input: CraftingInput, level: Level): Boolean {
        if (input.width() != 3 || input.height() != 3) {
            return false
        }

        for (row in 0 until 3) {
            for (col in 0 until 3) {
                val stack = input.getItem(col + row * 3)

                if (row == 1 && col == 1) {
                    if (stack.isEmpty || !stack.isDamageableItem) {
                        return false
                    }
                } else {
                    if (stack.item != ModItems.SAPPHIRE) {
                        return false
                    }
                }
            }
        }

        return true
    }

    override fun assemble(input: CraftingInput): ItemStack {
        val result = input.getItem(4).copy() // nutzt das item was im 4 slot (mitte) ist


        return result
    }

    override fun getSerializer(): RecipeSerializer<out CustomRecipe> {
        return ModRecipes.SAPPHIRE_UPGRADE_SERIALIZER
    }

    companion object {

        val CODEC: MapCodec<SapphireUpgradeRecipe> =
            MapCodec.unit(SapphireUpgradeRecipe())

        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, SapphireUpgradeRecipe> =
            StreamCodec.unit(SapphireUpgradeRecipe())
    }
}