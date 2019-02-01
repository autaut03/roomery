package net.alexwells.roomery.block;

import net.alexwells.roomery.Roomery;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block {
    protected BlockBase(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public Block setTranslationKey(String key) {
        return super.setTranslationKey(Roomery.MOD_ID + ":" + key);
    }
}
