package top.offsetmonkey538.positionbar.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import top.offsetmonkey538.positionbar.PositionBar;

import static net.minecraft.server.command.CommandManager.*;

public class PositionBarCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("position-bar")
                        .then(literal("enable").executes(PositionBarCommand::enableCommand))
                        .then(literal("disable").executes(PositionBarCommand::disableCommand))
        );
        dispatcher.register(
                literal("pos-bar")
                        .then(literal("enable").executes(PositionBarCommand::enableCommand))
                        .then(literal("disable").executes(PositionBarCommand::disableCommand))
        );
    }

    private static int enableCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        final ServerPlayerEntity player = context.getSource().getPlayerOrThrow();
        final BlockPos pos = player.getBlockPos();

        PositionBar.addPositionBar(player);
        PositionBar.updatePositionBar(player, pos);

        return 1;
    }

    private static int disableCommand(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        final ServerPlayerEntity player = context.getSource().getPlayerOrThrow();

        PositionBar.removePositionBar(player);

        return 1;
    }
}
