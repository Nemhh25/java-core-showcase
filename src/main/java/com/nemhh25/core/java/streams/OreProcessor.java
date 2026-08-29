package com.nemhh25.core.java.streams;

import com.nemhh25.core.java.fundamentals.BlockPos;
import com.nemhh25.core.java.fundamentals.BlockPredicate;
import com.nemhh25.core.java.fundamentals.SimpleBlockState;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OreProcessor {

  private final List<BlockPredicate> predicates = new ArrayList<>();

  public void addPredicate(BlockPredicate predicate) {
    this.predicates.add(predicate);
  }

  public Optional<OreResult> process(SimpleBlockState state, String levelName, BlockPos pos) {
    return predicates.stream()
        .filter(p -> p.test(state, levelName, pos))
        .findFirst()
        .map(
            matchedPredicate ->
                new OreResult(
                    state,
                    Math.abs(levelName.hashCode()) % 10 + 1, // FIX: Math.abs garante XP positivo
                    Math.random() < 0.05));
  }

  public int getPredicateCount() {
    return predicates.size();
  }
}
