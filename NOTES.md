# 验证边界与复现信息

## 复现命令

```bash
cd ~/wechat/labs/agentbench-kmp/tasks/taskN
dsh headless --permission-mode workspace-write --approval-policy never \
  --json "$(cat ../prompts/taskN_round1.txt)" > ../../runs/taskN/round1.events.jsonl
```

判分：`gradle :core:jvmTest`；task8 另加 `gradle :core:compileKotlinIosSimulatorArm64`。
task5 / task7 用 `tasks/hidden/DurationAcceptanceTest.kt` 做隐藏验收。

## 一手来源

- JetBrains 博客 Kotlin Benchmark 2.0（2026-11-23）：任务构成、30 分钟限时、断网红线、各配置分数
  https://blog.jetbrains.com/kotlin/2026/11/kotlin-benchmark-2-0-how-ai-coding-agents-handle-harder-tasks-and-closed-source-code/
- 本机 dsh 0.2.2-rc.5 默认模型 deepseek-flash（DeepSeek-V4.1-Flash），`dsh --profile headless --dump-config` 可见

## 文章里删掉的一节（不进发布稿与飞书）

## 这次没验到的部分

说清楚边界，免得读者拿我的数字去下判断。

**iOS 只验证到编译。** 两个 iOS 相关的任务，我确认了 `compileKotlinIosSimulatorArm64` 通过，没在模拟器里跑过。编译过不等于跑对，这一点上周写 Compose 体积那篇时已经吃过一次教训（plist 少一个 key，编译完全没事，启动直接崩）。

**任务是我自己出的。** 八题、一个仓库、一个模型，不构成统计。True positive 和 false positive 都可能存在，尤其是第 5 题那种「它猜的正好是我想的」。

**没有成本数据。** dsh 的 token 用量我记了（第八题单次 4 万输入、3 万输出，缓存读 180 万），但我没算钱，因为本地路由的价格跟榜单的 OpenCode 不是一套。

**也没测更难的那一半。** 更难的 KMP 任务是「让这个 iOS 界面在真机上不闪退」「把这个 expect 换成 cinterop 绑定」，这类要设备、要交互、要肉眼判断的，我这次的框架测不了。

![判据：有没有东西能立刻判对错](/Users/hermes/wechat/kmp-agent-bench-figs/fig4.png)

## 原始数据

`runs/task1..8/round1.{events.jsonl,exit,seconds}`
