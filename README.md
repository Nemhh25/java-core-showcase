# java-core-showcase

[![CI](https://github.com/nemhh25/java-core-showcase/actions/workflows/ci.yml/badge.svg)](https://github.com/nemhh25/java-core-showcase/actions/workflows/ci.yml)
[![Java](https://img.shields.io/badge/Java-21-blue.svg)](https://openjdk.org/projects/jdk/21/)
[![Gradle](https://img.shields.io/badge/Gradle-9.6-green.svg)](https://gradle.org/releases/)

> **Fundamentos Java para desenvolvimento profissional de mods Minecraft**  
> Parte da trilha de estudos: **Fase 0 → Fase 1 (Forge) → Fase 2 (Mod Portfolio) → Capstone**

---

## 🎯 Objetivo

Demonstrar domínio dos fundamentos Java modernos necessários para desenvolvimento profissional de mods Minecraft (Forge/NeoForge/Fabric), incluindo:

- Programação funcional (Streams, Optionals, Lambdas)
- Concorrência segura (CompletableFuture, ExecutorService)
- Testes unitários profissionais (JUnit 5 + AssertJ)
- Qualidade de código automatizada (Spotless, Checkstyle)
- CI/CD com GitHub Actions

---

## 📁 Estrutura do Projeto

```
src/main/java/com/nemhh25/core/java/
├── fundamentals/     # Interfaces, Records, Abstract Classes, Template Method
├── streams/          # Streams API, Optional, Records, Predicates
└── concurrency/      # CompletableFuture, ExecutorService, ThreadFactory

src/test/java/com/nemhh25/core/java/
├── streams/          # Testes do OreProcessor (3 cenários)
└── concurrency/      # Testes do AsyncDataFetcher (assincronidade, cleanup)
```

---

## 🧠 Conceitos Demonstrados

### `fundamentals/`
| Arquivo | Conceito |
|---------|----------|
| `SimpleBlockState`, `BlockPos` | **Records** — dados imutáveis, `equals/hashCode/toString` automáticos |
| `BlockPredicate` | **@FunctionalInterface** — Single Abstract Method para lambdas |
| `IToolModifier` | **Interface com `default method`** — evolução compatível (padrão em APIs Forge) |
| `AbstractMagicBlock` | **Classe abstrata + Template Method** — estado protegido (`protected final`), construtor protegido, fluxo fixo (`final`) com hook abstrato |

### `streams/`
- `OreProcessor` — **Streams API** (`stream().filter().findFirst().map()`), **Optional** para representar "pode não ter resultado", **Records** para retorno tipado
- `OreProcessorTest` — 3 testes cobrindo: lista vazia, predicate casa, predicate não casa
- **Bug real corrigido**: `hashCode() % n` pode ser negativo → `Math.abs()` garante XP positivo

### `concurrency/`
- `AsyncDataFetcher` — **CompletableFuture.supplyAsync()**, **ExecutorService** com `ThreadFactory` customizado (daemon, named threads), tratamento de exceções com `.exceptionally()`
- `AsyncDataFetcherTest` — valida assincronicidade, cleanup no `@AfterEach`, execução em thread separada

---

## ⚙️ Tecnologias & Ferramentas

| Categoria | Ferramenta |
|-----------|------------|
| Language | Java 21 (LTS) |
| Build | Gradle 9.6 (Groovy DSL) |
| Testes | JUnit 5.11 + AssertJ 3.26 |
| Formatação | Spotless 6.25 (Google Java Format) |
| Análise Estática | Checkstyle 10.21 |
| CI/CD | GitHub Actions (Ubuntu, Temurin JDK 21) |

---

## 🚀 Como Executar

```bash
# Clone o repositório
git clone https://github.com/nemhh25/java-core-showcase.git
cd java-core-showcase

# Build completo (compila, testa, formata, checkstyle)
./gradlew check spotlessCheck

# Apenas testes
./gradlew test

# Aplicar formatação automática
./gradlew spotlessApply
```

---

## 📊 Qualidade de Código

- ✅ **100% dos testes passando**
- ✅ **Zero warnings Checkstyle**
- ✅ **Formatação Google Java Format enforçada**
- ✅ **CI/CD automatizado** (roda em todo push/PR)

---

## 📚 Próximas Fases

| Fase | Projeto | Foco |
|------|---------|------|
| **1** | `forge-foundation` | Setup Forge 1.20.2, DeferredRegister, Data Components, Networking |
| **2** | `ore-excavation-plus` | Primeiro mod de portfólio completo (3x3 mining, config, particles) |
| **3** | `arcane-core` | Capabilities, Custom Entities+AI, BlockEntities+Screens, Database |
| **4** | `runic-dungeons` | Capstone: Dungeons procedural, RPG systems, Party, Boss phases |

---

## 👨‍💻 Autor

**Nemhh25** — Desenvolvedor Java / Minecraft Modder  
[GitHub](https://github.com/nemhh25) • [LinkedIn](https://linkedin.com/in/nemhh25)

---

## 📄 Licença

Este projeto está sob licença MIT. Veja [LICENSE](LICENSE) para detalhes.