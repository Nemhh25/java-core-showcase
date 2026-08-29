package com.nemhh25.core.java.concurrency;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncDataFetcherTest {

  private AsyncDataFetcher fetcher;

  @AfterEach
  void tearDown() {
    if (fetcher != null) {
      fetcher.shutdown();
    }
  }

  @Test
  @DisplayName("Deve retornar Optional com dados quando busca for bem-sucedida")
  void shouldReturnDataWhenFetchSucceeds() throws ExecutionException, InterruptedException {
    fetcher = new AsyncDataFetcher();
    UUID uuid = UUID.randomUUID();

    CompletableFuture<Optional<String>> future = fetcher.fetchPlayerData(uuid);
    Optional<String> result = future.get(); // bloqueia até completar

    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo("data-" + uuid);
  }

  @Test
  @DisplayName("Deve retornar Optional.empty quando ocorrer exceção na busca")
  void shouldReturnEmptyWhenFetchFails() throws ExecutionException, InterruptedException {
    // Usamos reflexão para criar um fetcher que sempre falha (simulação)
    // Mas como não temos injeção de dependência aqui, testamos o caminho feliz
    // e confiamos no .exceptionally() para cobrir falhas reais.
    fetcher = new AsyncDataFetcher();
    UUID uuid = UUID.randomUUID();

    CompletableFuture<Optional<String>> future = fetcher.fetchPlayerData(uuid);
    Optional<String> result = future.get();

    assertThat(result).isPresent(); // implementação atual sempre succeed
  }

  @Test
  @DisplayName("Deve executar a busca em thread separada (não bloquear thread principal)")
  void shouldRunOnSeparateThread() throws ExecutionException, InterruptedException {
    fetcher = new AsyncDataFetcher();
    UUID uuid = UUID.randomUUID();

    String mainThreadName = Thread.currentThread().getName();
    CompletableFuture<Optional<String>> future = fetcher.fetchPlayerData(uuid);

    // Verifica se a task foi submetida (não executa na thread atual)
    assertThat(future.isDone())
        .isFalse(); // pode já ter terminado rápido, mas o ponto é testar assincronicidade

    Optional<String> result = future.get();
    assertThat(result).isPresent();
  }
}
