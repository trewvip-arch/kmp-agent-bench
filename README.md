# kmp-agent-bench

《同一个 DeepSeek，Bench 2.0 给 58.9%，我本机 7 个 KMP 任务一次过》的复现工程。

用 [Kotlin Benchmark 2.0](https://blog.jetbrains.com/kotlin/2026/11/kotlin-benchmark-2-0-how-ai-coding-agents-handle-harder-tasks-and-closed-source-code/) 的口径（测试通过才算解决），在本机用 dsh headless 跑 8 个 KMP 方向的典型任务，记录首次通过率、返工轮数、耗时、步数与 token 用量。

## 环境

- macOS 26 / Apple M4
- JDK 21（`JAVA_HOME` 需自行指向）
- Gradle 9.9.2
- Kotlin 2.4.21 + `jvm()` / `iosSimulatorArm64()`
- dsh 0.2.2-rc.5，默认模型 `deepseek-flash`（DeepSeek-V4.1-Flash）

## 结果

| # | 任务 | 类型 | 耗时 | 步数 | 工具调用 | 首次通过 |
|---|---|---|---|---|---|---|
| 1 | 修掉两个文件的编译错误 | 修 bug | 62s | 17 | 16 | 是 |
| 2 | 补 common 层 `formatDuration` + 测试 | 加功能 | 143s | 33 | 53 | 是 |
| 3 | 加 kotlinx-datetime 依赖并实现 `todayUtc` | 加依赖 | 67s | 21 | 31 | 是 |
| 4 | `expect`/`actual` 跨 jvm 与 iOS | 多端 | 72s | 17 | 26 | 是 |
| 5 | 同一需求，只给模糊提示 | 加功能 | 60s | 19 | 21 | 是 |
| 6 | 开 `explicitApi` strict 并补可见性 | 构建配置 | 49s | 20 | 31 | 是 |
| 7 | expect/actual + JDK `Duration` + explicitApi | 复合 | 141s | 28 | 43 | 是 |
| 8 | 需求自相矛盾（陷阱题） | 陷阱 | 442s | 45 | 48 | 否 |

前七题首次通过率 100%，零返工；八次合计 17.3 分钟，中位 72 秒。

## 目录

```
core/                     干净基线工程（4 个源文件）
tasks/task1..task8/       每个任务的初始工程（互不干扰）
tasks/prompts/            每轮给 agent 的原话
tasks/hidden/             判分用的隐藏验收测试（task5 / task7 用）
runs/taskN/round1.*       dsh --json 的逐轮事件流、退出码、耗时
NOTES.md                  复现命令、一手来源、验证边界
```

## 怎么复现

```bash
cd tasks/task2
export JAVA_HOME=/path/to/jdk21
dsh headless --permission-mode workspace-write --approval-policy never \
    --json "$(cat ../prompts/task2_round1.txt)" > ../../runs/task2/round1.events.jsonl
cd ../..
gradle -p tasks/task2 :core:jvmTest      # 判分
```

跑完自己敲一遍 gradle 才算通过——不要只看 agent 的汇报。

task5 与 task7 的隐藏验收：

```bash
cp tasks/hidden/DurationAcceptanceTest.kt tasks/task5/core/src/commonTest/kotlin/bench/
gradle -p tasks/task5 :core:jvmTest --rerun-tasks
```

## 说明

- 任务是自己出的 8 题、单仓库、单模型，不构成统计。
- iOS 侧只验证到 `compileKotlinIosSimulatorArm64`，没在模拟器里跑运行。
- 详细的边界与来源见 `NOTES.md`。
