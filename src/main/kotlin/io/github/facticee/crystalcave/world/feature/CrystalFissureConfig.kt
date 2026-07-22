package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration

data class CrystalFissureConfig(
    val palette: CrystalPalette,
    val length: IntRange2,
    val crossRadius: IntRange2
) : FeatureConfiguration {

    companion object {
        val CODEC: Codec<CrystalFissureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                CrystalPalette.CODEC.fieldOf("palette").forGetter(CrystalFissureConfig::palette),
                IntRange2.CODEC.fieldOf("length").forGetter(CrystalFissureConfig::length),
                IntRange2.CODEC.fieldOf("cross_radius").forGetter(CrystalFissureConfig::crossRadius)
            ).apply(instance, ::CrystalFissureConfig)
        }
    }
}