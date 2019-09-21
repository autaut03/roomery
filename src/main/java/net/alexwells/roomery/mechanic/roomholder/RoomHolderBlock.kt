package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.util.getTile
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.item.BlockItemUseContext
import net.minecraft.item.ItemStack
import net.minecraft.state.BooleanProperty
import net.minecraft.state.DirectionProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.Direction
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.storage.loot.LootContext
import net.minecraft.world.storage.loot.LootParameters
import net.minecraftforge.fml.network.NetworkHooks

object RoomHolderBlock : Block(
    Block.Properties
        .create(Material.IRON)
        .hardnessAndResistance(2f)
        .sound(SoundType.METAL)
) {
    // Sadly, we have to declare properties inside another object.
    // This is because if you declare them directly, Block()'s
    // constructor will call createBlockState(), which
    // will try to access these properties and get null.
    // These won't be initialized before Block()'s constructor
    // ends what it does.
    object Properties {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val ACTIVE: BooleanProperty = BooleanProperty.create("active")
    }

    init {
        registryName = ROOM_HOLDER_RESOURCE
        defaultState = stateContainer.baseState
            .with(Properties.FACING, Direction.NORTH)
            .with(Properties.ACTIVE, false)
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        super.fillStateContainer(builder)

        builder.add(Properties.FACING, Properties.ACTIVE)
    }

    override fun hasTileEntity(state: BlockState?) = true

    override fun createTileEntity(state: BlockState?, world: IBlockReader?) = RoomHolderTileEntity()

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
        return defaultState.blockState.with(Properties.FACING, context.placementHorizontalFacing.opposite)
    }

    override fun getDrops(state: BlockState, context: LootContext.Builder): MutableList<ItemStack> {
        val drops = super.getDrops(state, context)

        val tile = context.get(LootParameters.BLOCK_ENTITY)
        if (tile is RoomHolderTileEntity && tile.roomCard != null) {
            drops.add(tile.roomCard)
        }

        return drops
    }

    override fun onBlockActivated(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockRayTraceResult
    ): Boolean {
        // If sneaking, let the block be placed
        if (player.isSneaking) {
            return false
        }

        // If world is client sided, don't place a block
        if (world.isRemote) {
            return true
        }

        val tile = world.getTile<RoomHolderTileEntity>(pos) ?: return false

        // todo: send chunk preview
        NetworkHooks.openGui(player as ServerPlayerEntity, tile, pos)

        return true
    }
}
