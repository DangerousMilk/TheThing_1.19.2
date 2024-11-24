package net.realfancymoo.thething.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class IndustrialBarrelBlockEntity extends BlockEntity {

    private int progress;
    private static final int MAX_PROGRESS = 100;

    public static final Component CONTAINER_TITLE = Component.translatable("container.industrialbarrel");

    private final ItemStackHandler inventory = new ItemStackHandler(54);
    private final LazyOptional<IItemHandlerModifiable> optional = LazyOptional.of(() -> this.inventory);

    public IndustrialBarrelBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.INDUSTRIAL_BARREL_BLOCK_ENTITY.get(), blockPos, blockState);
    }

    public void Tick(){
        if(this.level.isClientSide()) return;

        progress++;
        if(progress >= MAX_PROGRESS)
        {
            progress = 0;
            var server = this.level.getServer();

            for (ServerPlayer player : server.getPlayerList().getPlayers())
            {
                player.displayClientMessage(Component.literal("holes"), false);
            }
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.inventory.deserializeNBT(nbt.getCompound("inventory"));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.put("inventory", this.inventory.serializeNBT());
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        this.optional.invalidate();
    }
}
