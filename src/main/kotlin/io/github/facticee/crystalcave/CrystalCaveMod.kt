package io.github.facticee.crystalcave

import io.github.facticee.crystalcave.registry.ModBlocks
import io.github.facticee.crystalcave.registry.ModCreativeTab
import io.github.facticee.crystalcave.items.MoonstoneUpgradeRecipe
import io.github.facticee.crystalcave.registry.ModFeatures
import io.github.facticee.crystalcave.registry.ModItems
import io.github.facticee.crystalcave.registry.ModRecipes
import io.github.facticee.crystalcave.world.ModWorldGen
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.monster.Enemy
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.EquipmentSlot
import org.slf4j.LoggerFactory


class CrystalCaveMod : ModInitializer {

    companion object {
        const val MOD_ID = "crystalcave"
        private val LOGGER = LoggerFactory.getLogger(MOD_ID)
    }

    override fun onInitialize() {


        ModBlocks.registerAll()
        ModCreativeTab.registerAll()
        ModItems.registerAll()
        ModRecipes.registerAll()
        ModFeatures.registerAll()
        ModWorldGen.registerAll()


        ServerTickEvents.END_SERVER_TICK.register { server ->
            if (server.tickCount % 20 != 0) return@register

            val players = server.playerList.players
            for (player in players) {
                if (!hasMoonstone(player)) continue
                val level = player.level()
                val mobs: List<Mob> = level.getEntitiesOfClass(
                    Mob::class.java,
                    player.boundingBox.inflate(64.0)
                ) { mob -> mob is Enemy && mob.getTarget() == null }
                for (mob in mobs) mob.setTarget(player)
            }
        }
    }

    private fun hasMoonstone(player: Player): Boolean {
        if (player.mainHandItem.item == ModItems.MOONSTONE) return true
        if (player.offhandItem.item  == ModItems.MOONSTONE) return true
        if (MoonstoneUpgradeRecipe.hasMoonstoneMarker(player.mainHandItem)) return true
        if (MoonstoneUpgradeRecipe.hasMoonstoneMarker(player.offhandItem)) return true

        val armorSlots = arrayOf(
            EquipmentSlot.HEAD, EquipmentSlot.CHEST,
            EquipmentSlot.LEGS, EquipmentSlot.FEET
        )
        for (slot in armorSlots) {
            val stack = player.getItemBySlot(slot)
            val item = stack.item
            if (item == ModItems.MOONSTONE_HELMET    ||
                item == ModItems.MOONSTONE_CHESTPLATE ||
                item == ModItems.MOONSTONE_LEGGINGS   ||
                item == ModItems.MOONSTONE_BOOTS) return true

            if (MoonstoneUpgradeRecipe.hasMoonstoneMarker(stack)) return true
        }
        return false
    }
}
