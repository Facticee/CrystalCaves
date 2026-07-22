package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import kotlin.math.PI
import kotlin.math.sin

class CrystalFissureFeature(codec: Codec<CrystalFissureConfig>) : Feature<CrystalFissureConfig>(codec) {

    override fun place(context: FeaturePlaceContext<CrystalFissureConfig>): Boolean {
        val level = context.level()
        val random = context.random()
        val config = context.config()
        val origin = context.origin()

        val length = config.length.sample(random)
        val maxRadius = config.crossRadius.sample(random).toFloat()

        val halfLen = length / 2.0

        var t = -halfLen
        while (t <= halfLen) {
            val progress = (t + halfLen) / length
            val localRadius = (maxRadius * sin(PI * progress)).toFloat()

            val center = BlockPos(
                origin.x + t.toInt(),
                origin.y,
                origin.z
            )

            if (localRadius > 0f) {
            }

            t += 1.5
        }

        return true
    }
}