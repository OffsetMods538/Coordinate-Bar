package top.offsetmonkey538.coordinatebar;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordinateBar implements ModInitializer {
	public static final String MOD_ID = "coordinate-bar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// Do stuff
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
