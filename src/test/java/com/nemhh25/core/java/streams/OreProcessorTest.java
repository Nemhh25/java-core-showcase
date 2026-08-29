package com.nemhh25.core.java.streams;

import static org.assertj.core.api.Assertions.assertThat;

import com.nemhh25.core.java.fundamentals.BlockPos;
import com.nemhh25.core.java.fundamentals.SimpleBlockState;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OreProcessorTest {

  private OreProcessor processor;
  private SimpleBlockState diamondOre;
  private BlockPos pos;

  @BeforeEach
  void setUp() {
    processor = new OreProcessor();
    diamondOre = new SimpleBlockState("diamond_ore", 3);
    pos = new BlockPos(10, 64, -20);
  }

  @Test
  @DisplayName("Deve retornar Optional.empty quando não houver predicados registrados")
  void shouldReturnEmptyWhenNoPredicates() {
    Optional<OreResult> result = processor.process(diamondOre, "overworld", pos);

    assertThat(result).isEmpty();
  }

  @Test
  @DisplayName("Deve retornar OreResult quando pelo menos um predicado for satisfeito")
  void shouldReturnOreResultWhenPredicateMatches() {
    // Registra um predicado que sempre retorna true para blocos com dureza >= 3
    processor.addPredicate((state, level, p) -> state.hardness() >= 3);

    Optional<OreResult> result = processor.process(diamondOre, "overworld", pos);

    assertThat(result).isPresent();
    assertThat(result.get().state()).isEqualTo(diamondOre);
    assertThat(result.get().xp()).isPositive();
  }

  @Test
  @DisplayName("Deve retornar Optional.empty quando nenhum predicado corresponder ao bloco")
  void shouldReturnEmptyWhenPredicateDoesNotMatch() {
    // Registra um predicado que exige dureza >= 5 (diamond_ore tem dureza 3)
    processor.addPredicate((state, level, p) -> state.hardness() >= 5);

    Optional<OreResult> result = processor.process(diamondOre, "overworld", pos);

    assertThat(result).isEmpty();
  }
}
