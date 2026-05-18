package dev.doctor4t.mariposa;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.BiomeParameterTargets;
import com.terraformersmc.biolith.api.biome.sub.Criterion;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import dev.doctor4t.mariposa.index.MariposaBlocks;
import dev.doctor4t.mariposa.index.MariposaItems;
import dev.doctor4t.mariposa.index.MariposaWorldgenFeatures;
import dev.doctor4t.mariposa.world.gen.MariposaBiomes;
import dev.doctor4t.mariposa.world.gen.MariposaSurfaceRuleData;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mariposa implements ModInitializer {
    public static final String MOD_ID = "mariposa";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id(String string) {
        return Identifier.of(MOD_ID, string);
    }

    @Override
    public void onInitialize() {
        MariposaItems.initialize();
        MariposaBlocks.initialize();
        MariposaWorldgenFeatures.initialize();
        initializeWorldgen();
    }

    private static void initializeWorldgen() {
        // Weights are kept intentionally low as we add minimal biomes
        BiomePlacement.addSubOverworld(BiomeKeys.OLD_GROWTH_PINE_TAIGA, MariposaBiomes.REDWOOD_FOREST, CriterionBuilder.valueMin(BiomeParameterTargets.CONTINENTALNESS, 0.5f));
        BiomePlacement.addSubOverworld(BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, MariposaBiomes.REDWOOD_FOREST, nearReadwood());
        BiomePlacement.addSubOverworld(BiomeKeys.TAIGA, MariposaBiomes.REDWOOD_FOREST, nearReadwood());

        // Register our surface rules
        SurfaceGeneration.addOverworldSurfaceRules(id("redwoods"), MariposaSurfaceRuleData.makeRules());
    }

    private static Criterion nearReadwood() {
        return CriterionBuilder.allOf(
                CriterionBuilder.valueMin(BiomeParameterTargets.CONTINENTALNESS, 0.5f),
                CriterionBuilder.neighbor(BiomeKeys.OLD_GROWTH_PINE_TAIGA),
                CriterionBuilder.NEAR_BORDER
        );
    }
}