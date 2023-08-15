package top.offsetmonkey538.coordinatebar;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.boss.BossBarManager;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.offsetmonkey538.coordinatebar.command.CoordinateBarCommand;

public class CoordinateBar implements ModInitializer {
	public static final String MOD_ID = "coordinate-bar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final Identifier POSITION_BAR = id("position_bar");

	@Override
	public void onInitialize() {
		// Do stuff

		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> CoordinateBarCommand.register(dispatcher)));
	}

	public static Identifier positionBarId(ServerPlayerEntity player) {
		return POSITION_BAR.withSuffixedPath("_" + player.getUuidAsString());
	}

	public static void addPositionBar(ServerPlayerEntity player) {
		final MinecraftServer server = player.getServer();
		if (server == null) return;
		final BossBarManager manager = server.getBossBarManager();

		CommandBossBar positionBar = manager.add(positionBarId(player), Text.empty());
		positionBar.addPlayer(player);
		positionBar.setVisible(true);
	}

	public static void removePositionBar(ServerPlayerEntity player) {
		final MinecraftServer server = player.getServer();
		if (server == null) return;
		final BossBarManager manager = server.getBossBarManager();

		manager.remove(manager.get(positionBarId(player)));
	}

	public static void updatePositionBar(ServerPlayerEntity player, BlockPos pos) {
		final MinecraftServer server = player.getServer();
		if (server == null) return;
		final BossBarManager manager = server.getBossBarManager();

		CommandBossBar positionBar = manager.get(positionBarId(player));
		if (positionBar == null) return;

		positionBar.setName(Text.of(String.format("X: %s Y: %s Z: %s", pos.getX(), pos.getY(), pos.getZ())));
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
