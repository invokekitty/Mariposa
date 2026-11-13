package dev.doctor4t.mariposa.common;

import com.terraformersmc.biolith.api.biome.BiomePlacement;
import com.terraformersmc.biolith.api.biome.sub.CriterionBuilder;
import com.terraformersmc.biolith.api.surface.SurfaceGeneration;
import dev.doctor4t.mariposa.common.init.*;
import dev.doctor4t.mariposa.common.world.level.levelgen.MariposaSurfaceRuleData;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biomes;

public class Mariposa implements ModInitializer {
	public static final String MOD_ID = "mariposa";

	public static Identifier id(String string) {
		return Identifier.fromNamespaceAndPath(MOD_ID, string);
	}

	@Override
	public void onInitialize() {
		MariposaItems.initialize();
		MariposaBlocks.initialize();
		MariposaEntityTypes.initialize();
		MariposaFeatureConfigurations.initialize();
		setupWorldgen();
	}

	private static void setupWorldgen() {
		BiomePlacement.replaceOverworld(Biomes.TAIGA, MariposaBiomes.REDWOOD_FOREST, 0.18);
		BiomePlacement.addSubOverworld(Biomes.SNOWY_TAIGA, MariposaBiomes.SNOWY_REDWOOD_FOREST, CriterionBuilder.alternate(MariposaBiomes.REDWOOD_FOREST, Biomes.TAIGA));
		SurfaceGeneration.addOverworldSurfaceRules(Identifier.fromNamespaceAndPath("mariposa", "rules"), MariposaSurfaceRuleData.makeRules());
	}
}
