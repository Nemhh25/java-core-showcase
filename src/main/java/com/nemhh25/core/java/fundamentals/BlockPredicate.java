package com.nemhh25.core.java.fundamentals;

@FunctionalInterface
public interface BlockPredicate {
  boolean test(SimpleBlockState state, String levelName, BlockPos pos);
}
