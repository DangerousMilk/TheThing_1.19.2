package net.realfancymoo.thething.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.realfancymoo.thething.block.entity.IndustrialBarrelBlockEntity;
import org.jetbrains.annotations.Nullable;

public class IndustrialBarrelBlock extends Block implements EntityBlock {
    public IndustrialBarrelBlock(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new IndustrialBarrelBlockEntity(blockPos, blockState);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
       if(!level.isClientSide())
       {
           if(level.getBlockEntity(blockPos) instanceof IndustrialBarrelBlockEntity industrialBarrel)
           {
               SimpleMenuProvider provider = new SimpleMenuProvider(,IndustrialBarrelBlockEntity.CONTAINER_TITLE);
               NetworkHooks.openScreen((ServerPlayer)player, provider, blockPos);
           }
       }

       return InteractionResult.sidedSuccess(!level.isClientSide());
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> blockEntityType) {
        return level.isClientSide() ? null : ($0, $1, $2, blockEntity) -> {
            if(blockEntity instanceof IndustrialBarrelBlockEntity industrialBarrel)
            {
                industrialBarrel.Tick();
            }
        };
    }
}
