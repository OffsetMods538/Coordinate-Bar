package top.offsetmonkey538.coordinatebar.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import top.offsetmonkey538.coordinatebar.CoordinateBar;

import static net.minecraft.server.command.CommandManager.*;

public class CoordinateBarCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("coordinate-bar")
                        .then(literal("enable").executes(CoordinateBarCommand::enableCommand))
                        .then(literal("disable").executes(CoordinateBarCommand::disableCommand))
        );
    }

    private static int enableCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        final ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
        final BlockPos pos = player.getBlockPos();

        CoordinateBar.addPositionBar(player);
        CoordinateBar.updatePositionBar(player, pos);

        return 1;
    }

    private static int disableCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        final ServerPlayerEntity player = context.getSource().getPlayerOrThrow();

        CoordinateBar.removePositionBar(player);

        return 1;
    }
}
