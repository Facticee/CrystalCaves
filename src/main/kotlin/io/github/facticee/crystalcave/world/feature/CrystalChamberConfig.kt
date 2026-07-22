package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration

data class CrystalChamberConfig(
    val palette: CrystalPalette,
    val radius: IntRange2,
    val innerThickness: Float,
    val middleThickness: Float,
    val outerThickness: Float
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<CrystalChamberConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                CrystalPalette.CODEC.fieldOf("palette").forGetter(CrystalChamberConfig::palette),
                IntRange2.CODEC.fieldOf("radius").forGetter(CrystalChamberConfig::radius),
                Codec.FLOAT.fieldOf("inner_thickness").forGetter(CrystalChamberConfig::innerThickness),
                Codec.FLOAT.fieldOf("middle_thickness").forGetter(CrystalChamberConfig::middleThickness),
                Codec.FLOAT.fieldOf("outer_thickness").forGetter(CrystalChamberConfig::outerThickness)
            ).apply(instance, ::CrystalChamberConfig)
        }
    }
}
