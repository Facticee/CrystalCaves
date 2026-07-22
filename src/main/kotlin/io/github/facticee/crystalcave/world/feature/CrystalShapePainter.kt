package io.github.facticee.crystalcave.world.feature

import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.ceil
import kotlin.math.sqrt


object CrystalShapePainter {

    fun paintChamber(
        level: WorldGenLevel,
        random: RandomSource,
        palette: CrystalPalette,
        center: BlockPos,
        radius: Float,
        thickness: Float,
        yScale: Float = 1.0f
    ) {
        val outerR = radius + thickness
        val innerR = radius + (thickness * 0.4f)
        val fillR = radius

        val range = ceil(outerR).toInt()
        val yRange = ceil(outerR / yScale).toInt()
        val mutable = BlockPos.MutableBlockPos()

        for (x in -range..range) {
            for (y in -yRange..yRange) {
                val worldY = center.y + y
                for (z in -range..range) {
                    val sy = y * yScale
                    val dist = sqrt((x * x).toDouble() + (sy * sy).toDouble() + (z * z).toDouble()).toFloat()
                    if (dist > outerR) continue

                    mutable.set(center.x + x, worldY, center.z + z)
                    val currentState = level.getBlockState(mutable)
                    if (currentState.`is`(Blocks.BEDROCK)) continue

                    when {
                        dist <= fillR -> {
                            if (!currentState.isAir) {
                                level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 3)
                            }
                        }
                        dist <= innerR -> {
                            val roll = random.nextFloat()
                            val state: BlockState = if (roll < palette.oreFillChance) {
                                CrystalPalette.pickOre(random, palette)
                            } else {
                                palette.innerLayer.getState(level, random, mutable)
                            }
                            level.setBlock(mutable, state, 3)
                        }
                        else -> {
                            level.setBlock(mutable, palette.outerLayer.getState(level, random, mutable), 3)
                        }
                    }
                }
            }
        }
    }

}