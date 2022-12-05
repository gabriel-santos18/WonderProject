package net.redewonder.rankup.managers;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import javax.annotation.Nonnull;
import java.util.Random;

public class WorldManager extends ChunkGenerator {

    @Override
    @Nonnull
    public ChunkData generateChunkData(@Nonnull World world, @Nonnull Random random, int x, int z, @Nonnull BiomeGrid biome) {
        return createChunkData(world);
    }
}
