package io.github.facticee.crystalcave.registry

import io.github.facticee.crystalcave.items.SapphireUpgradeRecipe
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier
import net.minecraft.world.item.crafting.RecipeSerializer

object ModRecipes {

    val SAPPHIRE_UPGRADE_SERIALIZER: RecipeSerializer<SapphireUpgradeRecipe> =
        Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            Identifier.fromNamespaceAndPath("crystalcave", "sapphire_upgrade"),
            RecipeSerializer(
                SapphireUpgradeRecipe.CODEC,
                SapphireUpgradeRecipe.STREAM_CODEC
            )
        )

    fun registerAll() {
        SAPPHIRE_UPGRADE_SERIALIZER
    }
}
