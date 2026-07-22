package io.github.facticee.crystalcave.world.feature

import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.ceil
import kotlin.math.sqrt

/**
 * Erste Version der Bau-Werkzeuge für Crystal-Features.
 */
object CrystalShapePainter {

    fun paintChamber(
        level: WorldGenLevel,
        random: RandomSource,
        palette: CrystalPalette,
        center: BlockPos,
        radius: Float,
        thickness: Float,
        yScale: Float = 1.0f
    )  {
        val outerR = radius + thickness
        val fillR = radius

        val range = ceil(outerR).toInt()
        val mutable = BlockPos.MutableBlockPos()

        for (x in -range..range) {
            for (y in -range..range) {
                val worldY = center.y + y
                for (z in -range..range) {
                    val dist = sqrt((x * x + y * y + z * z).toDouble()).toFloat()
                    if (dist > outerR) continue

                    mutable.set(center.x + x, worldY, center.z + z)
                    if (level.getBlockState(mutable).`is`(Blocks.BEDROCK)) continue

                    if (dist <= fillR) {
                        level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 3)
                    } else {
                        val state = palette.innerLayer.getState(level, random, mutable)
                        level.setBlock(mutable, state, 3)
                    }
                }
            }
        }
    }

    /** Simple Tunnel-Verbindung zwischen zwei Punkten */
    fun paintTunnel(
        level: WorldGenLevel,
        random: RandomSource,
        palette: CrystalPalette,
        from: BlockPos,
        to: BlockPos,
        radius: Float
    ) {
        val dx = (to.x - from.x).toDouble()
        val dy = (to.y - from.y).toDouble()
        val dz = (to.z - from.z).toDouble()
        val length = sqrt(dx * dx + dy * dy + dz * dz)
        val steps = ceil(length * 1.5).toInt().coerceAtLeast(1)
        val mutable = BlockPos.MutableBlockPos()
        val r = ceil(radius).toInt()

        for (s in 0..steps) {
            val t = s.toDouble() / steps
            val cx = from.x + dx * t
            val cy = from.y + dy * t
            val cz = from.z + dz * t

            for (ox in -r..r) {
                for (oy in -r..r) {
                    for (oz in -r..r) {
                        val dist = sqrt((ox * ox + oy * oy + oz * oz).toDouble()).toFloat()
                        if (dist > radius) continue

                        mutable.set((cx + ox).toInt(), (cy + oy).toInt(), (cz + oz).toInt())
                        if (level.getBlockState(mutable).`is`(Blocks.BEDROCK)) continue

                        if (dist <= radius * 0.6f) {
                            level.setBlock(mutable, Blocks.AIR.defaultBlockState(), 3)
                        } else {
                            val state = palette.innerLayer.getState(level, random, mutable)
                            level.setBlock(mutable, state, 3)
                        }
                    }
                }
            }
        }
    }

    fun paintPillar(
        level: WorldGenLevel,
        random: RandomSource,
        palette: CrystalPalette,
        baseCenter: BlockPos,
        height: Int,
        radius: Float
    ) {
        val r = ceil(radius).toInt()
        val mutable = BlockPos.MutableBlockPos()

        for (h in 0 until height) {
            val worldY = baseCenter.y + h
            for (ox in -r..r) {
                for (oz in -r..r) {
                    val dist = sqrt((ox * ox + oz * oz).toDouble()).toFloat()
                    if (dist > radius) continue

                    mutable.set(baseCenter.x + ox, worldY, baseCenter.z + oz)
                    if (level.getBlockState(mutable).`is`(Blocks.BEDROCK)) continue

                    level.setBlock(mutable, CrystalPalette.pickOre(random, palette), 3)
                }
            }
        }
    }
}