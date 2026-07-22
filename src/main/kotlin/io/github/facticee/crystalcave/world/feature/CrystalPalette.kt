package io.github.facticee.crystalcave.world.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

data class CrystalPalette(
    val innerLayer: BlockStateProvider,
    val altInnerLayer: BlockStateProvider,
    val middleLayer: BlockStateProvider,
    val outerLayer: BlockStateProvider,
    val primaryOres: List<BlockState>,
    val accentOres: List<BlockState>,
    val accentChance: Float,
    val oreFillChance: Float
) {
    companion object {
        val CODEC: Codec<CrystalPalette> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.CODEC.fieldOf("inner_layer_provider").forGetter(CrystalPalette::innerLayer),
                BlockStateProvider.CODEC.fieldOf("alternate_inner_layer_provider").forGetter(CrystalPalette::altInnerLayer),
                BlockStateProvider.CODEC.fieldOf("middle_layer_provider").forGetter(CrystalPalette::middleLayer),
                BlockStateProvider.CODEC.fieldOf("outer_layer_provider").forGetter(CrystalPalette::outerLayer),
                BlockState.CODEC.listOf().fieldOf("primary_ores").forGetter(CrystalPalette::primaryOres),
                BlockState.CODEC.listOf().fieldOf("accent_ores").forGetter(CrystalPalette::accentOres),
                Codec.FLOAT.fieldOf("accent_chance").forGetter(CrystalPalette::accentChance),
                Codec.FLOAT.fieldOf("ore_fill_chance").forGetter(CrystalPalette::oreFillChance)
            ).apply(instance, ::CrystalPalette)
        }

        // verhältnis main ore zu sekundärem ore
        fun pickOre(random: RandomSource, palette: CrystalPalette): BlockState {
            return if (palette.accentOres.isNotEmpty() && random.nextFloat() < palette.accentChance) {
                palette.accentOres[random.nextInt(palette.accentOres.size)]
            } else {
                palette.primaryOres[random.nextInt(palette.primaryOres.size)]
            }
        }
    }
}
