package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import kotlin.math.PI
import kotlin.math.max
import kotlin.math.sin
import kotlin.math.sqrt


class CrystalFissureFeature(codec: Codec<CrystalFissureConfig>) : Feature<CrystalFissureConfig>(codec) {

    private data class Chamber(val center: BlockPos, val radius: Float, val thicknessScale: Float)

    override fun place(context: FeaturePlaceContext<CrystalFissureConfig>): Boolean {
        val level = context.level()
        val random = context.random()
        val config = context.config()
        val origin = context.origin()

        val length = config.length.sample(random)
        val maxRadius = config.crossRadius.sample(random).toFloat()

        val angle = random.nextDouble() * 2.0 * PI
        var axisX: Double
        var axisY: Double
        var axisZ: Double
        if (config.vertical) {
            axisX = kotlin.math.cos(angle) * config.tilt
            axisZ = kotlin.math.sin(angle) * config.tilt
            axisY = 1.0
        } else {
            axisX = kotlin.math.cos(angle)
            axisZ = kotlin.math.sin(angle)
            axisY = (random.nextDouble() - 0.5) * 2.0 * config.tilt
        }
        val axisLen = sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ).coerceAtLeast(0.001)
        axisX /= axisLen; axisY /= axisLen; axisZ /= axisLen

        val halfLen = length / 2.0

        val mainChambers = mutableListOf<Chamber>()
        val shardChambers = mutableListOf<Chamber>()

        var t = -halfLen
        while (t <= halfLen) {
            val progress = (t + halfLen) / length
            val localRadius = (maxRadius * sin(PI * progress)).toFloat().coerceAtLeast(1f)

            val center = BlockPos(
                origin.x + (axisX * t).toInt(),
                origin.y + (axisY * t).toInt(),
                origin.z + (axisZ * t).toInt()
            )

            val thicknessScale = (localRadius / maxRadius).coerceIn(0.35f, 1f)
            mainChambers.add(Chamber(center, localRadius, thicknessScale))

            if (localRadius > 1.5f && random.nextFloat() < config.shardChance) {
                var sx = random.nextDouble() - 0.5
                var sy = (random.nextDouble() - 0.5) * 0.6
                var sz = random.nextDouble() - 0.5
                val len = sqrt(sx * sx + sy * sy + sz * sz).coerceAtLeast(0.001)
                sx /= len; sy /= len; sz /= len

                val shardDist = (localRadius + 2 + random.nextInt(3)).toDouble()
                val shardCenter = BlockPos(
                    center.x + (sx * shardDist).toInt(),
                    center.y + (sy * shardDist).toInt(),
                    center.z + (sz * shardDist).toInt()
                )
                shardChambers.add(Chamber(shardCenter, max(1f, localRadius * 0.4f), 0.6f))
            }

            t += 1.5
        }

        if (mainChambers.isEmpty()) return false

        for (c in mainChambers) CrystalShapePainter.clearFill(level, c.center, c.radius)
        for (c in shardChambers) CrystalShapePainter.clearFill(level, c.center, c.radius)

        for (c in mainChambers) {
            CrystalShapePainter.paintShell(
                level, random, config.palette, c.center, c.radius,
                config.innerThickness * c.thicknessScale,
                config.middleThickness * c.thicknessScale,
                config.outerThickness * c.thicknessScale
            )
        }
        for (c in shardChambers) {
            CrystalShapePainter.paintShell(
                level, random, config.palette, c.center, c.radius,
                config.innerThickness * c.thicknessScale,
                config.middleThickness * c.thicknessScale,
                config.outerThickness * c.thicknessScale
            )
        }

        return true
    }
}