package top.offsetmonkey538.coordinatebar;

import java.util.HashMap;
import java.util.Map;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.boss.BossBarManager;
import net.minecraft.entity.boss.CommandBossBar;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoordinateBar implements ModInitializer {
	public static final String MOD_ID = "coordinate-bar";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Map<ServerPlayerEntity, CommandBossBar> POSITION_BAR_MAP = new HashMap<>();

	@Override
	public void onInitialize() {
		// Do stuff

		ServerPlayConnectionEvents.JOIN.register(((handler, sender, server) -> {
			if (server == null) return;

			final ServerPlayerEntity player = handler.getPlayer();
			final BlockPos pos = player.getBlockPos();
			final BossBarManager bossBarManager = server.getBossBarManager();


			addPositionBar(player, bossBarManager);
			updatePositionBar(player, pos);
		}));
	}

	public static void addPositionBar(ServerPlayerEntity player, BossBarManager bossBarManager) {
		CommandBossBar positionBar = bossBarManager.add(id("position_bar"), Text.empty());
		positionBar.addPlayer(player.getUuid());

		POSITION_BAR_MAP.put(player, positionBar);
	}

	public static void removePositionBar(ServerPlayerEntity player) {
		POSITION_BAR_MAP.remove(player);
	}

	public static CommandBossBar getPositionBar(ServerPlayerEntity player) {
		return POSITION_BAR_MAP.get(player);
	}

	public static void updatePositionBar(ServerPlayerEntity player, BlockPos pos) {
		getPositionBar(player).setName(Text.of(String.format("X: %s Y: %s Z: %s", pos.getX(), pos.getY(), pos.getZ())));
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
