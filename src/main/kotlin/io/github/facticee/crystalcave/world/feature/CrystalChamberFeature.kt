package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext

class CrystalChamberFeature(codec: Codec<CrystalChamberConfig>) : Feature<CrystalChamberConfig>(codec) {

    override fun place(context: FeaturePlaceContext<CrystalChamberConfig>): Boolean {
        val level = context.level()
        val random = context.random()
        val config = context.config()
        val origin = context.origin()

        val radius = config.radius.sample(random)

        if (radius <= 0) return false

        return true
    }
}