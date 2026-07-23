package io.github.facticee.crystalcave.registry

import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.EquipmentAssets

object ModArmorMaterials {

    val REPAIRS_MOONSTONE_ARMOR = TagKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath("crystalcave", "repairs_moonstone_armor")
    )

    val MOONSTONE_EQUIPMENT_ASSET = ResourceKey.create(
        EquipmentAssets.ROOT_ID,
        Identifier.fromNamespaceAndPath("crystalcave", "moonstone")
    )


    private val MOONSTONE_DEFENSE = mapOf(
        ArmorType.BOOTS to 2,
        ArmorType.LEGGINGS to 5,
        ArmorType.CHESTPLATE to 6,
        ArmorType.HELMET to 2
    )

    val MOONSTONE = ArmorMaterial(
        15,
        MOONSTONE_DEFENSE,
        15,
        SoundEvents.ARMOR_EQUIP_DIAMOND,
        2.0f,
        0.0f,
        REPAIRS_MOONSTONE_ARMOR,
        MOONSTONE_EQUIPMENT_ASSET
    )
}