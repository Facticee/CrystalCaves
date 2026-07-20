package io.github.facticee.crystalcave

import io.github.facticee.crystalcave.registry.ModBlocks
import io.github.facticee.crystalcave.registry.ModCreativeTab
import io.github.facticee.crystalcave.items.MoonstoneUpgradeRecipe
import io.github.facticee.crystalcave.registry.ModItems
import io.github.facticee.crystalcave.registry.ModRecipes
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.monster.Enemy
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.EquipmentSlot


class CrystalCaveMod : ModInitializer {

    override fun onInitialize() {
        ModBlocks.registerAll()
        ModCreativeTab.registerAll()
        ModItems.registerAll()
        ModRecipes.registerAll()


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

        return false
    }
}
