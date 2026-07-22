package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration

data class CrystalFissureConfig(
    val palette: CrystalPalette,
    val length: IntRange2,
    val crossRadius: IntRange2,
    val tilt: Float,
    val innerThickness: Float,
    val middleThickness: Float,
    val outerThickness: Float,
    val shardChance: Float,
    val vertical: Boolean
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<CrystalFissureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                CrystalPalette.CODEC.fieldOf("palette").forGetter(CrystalFissureConfig::palette),
                IntRange2.CODEC.fieldOf("length").forGetter(CrystalFissureConfig::length),
                IntRange2.CODEC.fieldOf("cross_radius").forGetter(CrystalFissureConfig::crossRadius),
                Codec.FLOAT.fieldOf("tilt").forGetter(CrystalFissureConfig::tilt),
                Codec.FLOAT.fieldOf("inner_thickness").forGetter(CrystalFissureConfig::innerThickness),
                Codec.FLOAT.fieldOf("middle_thickness").forGetter(CrystalFissureConfig::middleThickness),
                Codec.FLOAT.fieldOf("outer_thickness").forGetter(CrystalFissureConfig::outerThickness),
                Codec.FLOAT.fieldOf("shard_chance").forGetter(CrystalFissureConfig::shardChance),
                Codec.BOOL.fieldOf("vertical").forGetter(CrystalFissureConfig::vertical)
            ).apply(instance, ::CrystalFissureConfig)
        }
    }
}
