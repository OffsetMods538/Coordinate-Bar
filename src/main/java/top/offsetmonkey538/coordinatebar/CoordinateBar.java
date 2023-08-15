package top.offsetmonkey538.coordinatebar;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.BossBarManager;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.entity.boss.ServerBossBar;
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

	public static final Map<ServerPlayerEntity, ServerBossBar> POSITION_BAR_MAP = new HashMap<>();

	@Override
	public void onInitialize() {
		// Do stuff

		CommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess, environment) -> CoordinateBarCommand.register(dispatcher)));
	}

	public static void addPositionBar(ServerPlayerEntity player) {
		ServerBossBar positionBar = new ServerBossBar(Text.empty(), BossBar.Color.WHITE, BossBar.Style.PROGRESS);
		positionBar.addPlayer(player);
		positionBar.setVisible(true);

		POSITION_BAR_MAP.put(player, positionBar);
	}

	public static void removePositionBar(ServerPlayerEntity player) {
		if (!POSITION_BAR_MAP.containsKey(player)) return;
		POSITION_BAR_MAP.get(player).clearPlayers();
		POSITION_BAR_MAP.remove(player);
	}

	public static void updatePositionBar(ServerPlayerEntity player, BlockPos pos) {
		if (!POSITION_BAR_MAP.containsKey(player)) return;
		POSITION_BAR_MAP.get(player).setName(Text.of(String.format("X: %s Y: %s Z: %s", pos.getX(), pos.getY(), pos.getZ())));
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
