package com.nemhh25.core.java.fundamentals;

public abstract class AbstractMagicBlock {

    protected final String dimensionId;

    protected AbstractMagicBlock(String dimensionId) {
        this.dimensionId = dimensionId;
    }

    public abstract boolean canActivate(SimpleBlockState state, String levelName, BlockPos pos);

    public final void activate(SimpleBlockState state, String levelName, BlockPos pos) {
        if(canActivate(state, levelName, pos)) {
            System.out.println("[MagicBlock] Ativado na dimensão " + dimensionId + "em " + pos);
            onActivate(state, levelName, pos);
        }
    }

    protected abstract void onActivate(SimpleBlockState state, String levelName, BlockPos pos);
}
