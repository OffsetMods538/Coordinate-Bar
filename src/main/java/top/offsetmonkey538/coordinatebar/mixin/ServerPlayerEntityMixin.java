package top.offsetmonkey538.coordinatebar.mixin;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static top.offsetmonkey538.coordinatebar.CoordinateBar.*;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    @Inject(
            method = "applyMovementEffects",
            at = @At("HEAD")
    )
    private void updateCoordinateBar(BlockPos pos, CallbackInfo ci) {
        final ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        updatePositionBar(player, player.getBlockPos());
    }
}
