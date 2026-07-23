package io.github.facticee.crystalcave.registry

import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorMaterial
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.equipment.EquipmentAsset
import java.util.EnumMap

object ModArmorMaterials {

    val REPAIRS_MOONSTONE_ARMOR: TagKey<Item> = TagKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath("crystalcave", "repairs_moonstone_armor")
    )

    val MOONSTONE_EQUIPMENT_ASSET: ResourceKey<EquipmentAsset> = ResourceKey.create(
        net.minecraft.world.item.equipment.EquipmentAssets.ROOT_ID,
        Identifier.fromNamespaceAndPath("crystalcave", "moonstone")
    )

    private val MOONSTONE_DEFENSE = EnumMap<ArmorType, Int>(ArmorType::class.java).apply {
        put(ArmorType.BOOTS, 2)
        put(ArmorType.LEGGINGS, 5)
        put(ArmorType.CHESTPLATE, 6)
        put(ArmorType.HELMET, 2)
    }

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