package io.github.facticee.crystalcave.items

import com.mojang.serialization.MapCodec
import io.github.facticee.crystalcave.registry.ModItems
import io.github.facticee.crystalcave.registry.ModRecipes
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.item.component.ItemLore
import net.minecraft.world.item.crafting.CraftingInput
import net.minecraft.world.item.crafting.CustomRecipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.Level


class MoonstoneUpgradeRecipe : CustomRecipe() {

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
                    if (stack.item != ModItems.MOONSTONE) {
                        return false
                    }
                }
            }
        }

        return true
    }

    override fun assemble(input: CraftingInput): ItemStack {

        val result = input.getItem(4).copy() // 4 weil 0 ist das feld oben links 1 das mitte oben 2 das oben rechts usw

        val tag = CompoundTag()
        tag.putBoolean(MOONSTONE_MARKER_KEY, true)
        result.set(DataComponents.CUSTOM_DATA, CustomData.of(tag))

        val loreLine = Component.literal("Merged with Moonstone")
            .withStyle(Style.EMPTY.withColor(0xB39DDB).withItalic(false))
        val existingLore = result.get(DataComponents.LORE) ?: ItemLore(emptyList())
        result.set(DataComponents.LORE, existingLore.withLineAdded(loreLine))

        return result
    }

    override fun getSerializer(): RecipeSerializer<out CustomRecipe> = ModRecipes.MOONSTONE_UPGRADE_SERIALIZER

    companion object {
        const val MOONSTONE_MARKER_KEY = "crystalcave_moonstone_upgrade"

        fun hasMoonstoneMarker(stack: ItemStack): Boolean {
            val data = stack.get(DataComponents.CUSTOM_DATA) ?: return false
            return data.copyTag().getBoolean(MOONSTONE_MARKER_KEY).orElse(false)
        }

        val CODEC: MapCodec<MoonstoneUpgradeRecipe> = MapCodec.unit(MoonstoneUpgradeRecipe())
        val STREAM_CODEC: StreamCodec<RegistryFriendlyByteBuf, MoonstoneUpgradeRecipe> = StreamCodec.unit(MoonstoneUpgradeRecipe())
    }
}