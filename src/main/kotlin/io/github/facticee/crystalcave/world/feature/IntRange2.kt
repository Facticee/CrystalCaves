package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

data class IntRange2(val min: Int, val max: Int) {
    companion object {
        val CODEC: Codec<IntRange2> = RecordCodecBuilder.create { instance ->
            instance.group(
                Codec.INT.fieldOf("min").forGetter(IntRange2::min),
                Codec.INT.fieldOf("max").forGetter(IntRange2::max)
            ).apply(instance, ::IntRange2)
        }
    }

    fun sample(random: net.minecraft.util.RandomSource): Int {
        return if (max <= min) min else random.nextIntBetweenInclusive(min, max)
    }
}
