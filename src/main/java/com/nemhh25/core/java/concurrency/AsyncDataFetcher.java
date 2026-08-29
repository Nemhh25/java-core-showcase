package com.nemhh25.core.java.concurrency;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsyncDataFetcher {

  private final ExecutorService ioExecutor =
      Executors.newSingleThreadExecutor(
          r -> {
            Thread t = new Thread(r, "mod-io-%d");
            t.setDaemon(true);
            return t;
          });

  // Simula busca assíncrona (banco de dados, API externa, arquivo)
  public CompletableFuture<Optional<String>> fetchPlayerData(UUID uuid) {
    return CompletableFuture.supplyAsync(
            () -> {
              // TODO: implementação bloqueante real aqui (JDBC, HTTP, Files.read)
              try {
                Thread.sleep(50); // simula latência
              } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
              }
              return Optional.of("data-" + uuid);
            },
            ioExecutor)
        .exceptionally(
            ex -> {
              System.err.println(
                  "[AsyncDataFetcher] Falha ao buscar " + uuid + ": " + ex.getMessage());
              return Optional.empty();
            });
  }

  public void shutdown() {
    ioExecutor.shutdown();
  }
}
