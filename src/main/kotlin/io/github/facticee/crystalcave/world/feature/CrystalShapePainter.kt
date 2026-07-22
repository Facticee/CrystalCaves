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
        innerThickness: Float,
        middleThickness: Float,
        outerThickness: Float,
        yScale: Float = 1.0f
    ) {
        val outerR = radius + outerThickness
        val middleR = radius + middleThickness
        val innerR = radius + innerThickness
        val fillR = radius

        val range = ceil(outerR).toInt()
        val yRange = ceil(outerR / yScale).toInt()
        val mutable = BlockPos.MutableBlockPos()

        for (x in -range..range) {
            for (y in -yRange..yRange) {
                val worldY = center.y + y
                for (z in -range..range) {
                    val jitter = (random.nextFloat() - 0.5f) * 0.6f
                    val sy = y * yScale
                    val dist = sqrt((x * x).toDouble() + (sy * sy).toDouble() + (z * z).toDouble()).toFloat() + jitter
                    if (dist > outerR) continue

                    mutable.set(center.x + x, worldY, center.z + z)
                    val currentState = level.getBlockState(mutable)
                    if (currentState.`is`(Blocks.BEDROCK)) continue

                    when {
                        dist <= fillR -> if (!currentState.isAir) {
                            level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 3)
                        }
                        dist <= innerR -> {
                            val roll = random.nextFloat()
                            val state: BlockState = when {
                                roll < palette.oreFillChance -> CrystalPalette.pickOre(random, palette)
                                roll < palette.oreFillChance + 0.15f -> palette.altInnerLayer.getState(level, random, mutable)
                                else -> palette.innerLayer.getState(level, random, mutable)
                            }
                            level.setBlock(mutable, state, 3)
                        }
                        dist <= middleR -> level.setBlock(mutable, palette.middleLayer.getState(level, random, mutable), 3)
                        else -> level.setBlock(mutable, palette.outerLayer.getState(level, random, mutable), 3)
                    }
                }
            }
        }
    }

    fun clearFill(
        level: WorldGenLevel,
        center: BlockPos,
        radius: Float,
        yScale: Float = 1.0f
    ) {
        val range = ceil(radius).toInt()
        val yRange = ceil(radius / yScale).toInt()
        val mutable = BlockPos.MutableBlockPos()

        for (x in -range..range) {
            for (y in -yRange..yRange) {
                val worldY = center.y + y
                for (z in -range..range) {
                    val sy = y * yScale
                    val dist = sqrt((x * x).toDouble() + (sy * sy).toDouble() + (z * z).toDouble()).toFloat()
                    if (dist > radius) continue

                    mutable.set(center.x + x, worldY, center.z + z)
                    val currentState = level.getBlockState(mutable)
                    if (currentState.`is`(Blocks.BEDROCK) || currentState.isAir) continue
                    level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 3)
                }
            }
        }
    }

    fun paintShell(
        level: WorldGenLevel,
        random: RandomSource,
        palette: CrystalPalette,
        center: BlockPos,
        radius: Float,
        innerThickness: Float,
        middleThickness: Float,
        outerThickness: Float,
        yScale: Float = 1.0f
    ) {
        val outerR = radius + outerThickness
        val middleR = radius + middleThickness
        val innerR = radius + innerThickness
        val fillR = radius

        val range = ceil(outerR).toInt()
        val yRange = ceil(outerR / yScale).toInt()
        val mutable = BlockPos.MutableBlockPos()

        for (x in -range..range) {
            for (y in -yRange..yRange) {
                val worldY = center.y + y
                for (z in -range..range) {
                    val jitter = (random.nextFloat() - 0.5f) * 0.6f
                    val sy = y * yScale
                    val dist = sqrt((x * x).toDouble() + (sy * sy).toDouble() + (z * z).toDouble()).toFloat() + jitter
                    if (dist > outerR || dist <= fillR) continue

                    mutable.set(center.x + x, worldY, center.z + z)
                    val currentState = level.getBlockState(mutable)
                    // Schon Luft (von clearFill dieser oder einer anderen
                    // überlappenden Kammer) -> niemals wieder zubauen.
                    if (currentState.`is`(Blocks.BEDROCK) || currentState.isAir) continue

                    when {
                        dist <= innerR -> {
                            val roll = random.nextFloat()
                            val state: BlockState = when {
                                roll < palette.oreFillChance -> CrystalPalette.pickOre(random, palette)
                                roll < palette.oreFillChance + 0.15f -> palette.altInnerLayer.getState(level, random, mutable)
                                else -> palette.innerLayer.getState(level, random, mutable)
                            }
                            level.setBlock(mutable, state, 3)
                        }
                        dist <= middleR -> level.setBlock(mutable, palette.middleLayer.getState(level, random, mutable), 3)
                        else -> level.setBlock(mutable, palette.outerLayer.getState(level, random, mutable), 3)
                    }
                }
            }
        }
    }
}