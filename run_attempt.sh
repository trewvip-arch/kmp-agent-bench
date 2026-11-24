#!/bin/bash
# 单次 agent 尝试：跑 dsh headless，记录耗时与 JSON 事件
# 用法: run_attempt.sh <task_dir> <round> <prompt_file> <out_dir>
set -u
TASK_DIR="$1"; ROUND="$2"; PROMPT_FILE="$3"; OUT_DIR="$4"
set -a; . "$HOME/.hermes/.env"; set +a
export JAVA_HOME=/opt/homebrew/Cellar/openjdk@21/21.0.13.1/libexec/openjdk.jdk/Contents/Home
unset https_proxy http_proxy all_proxy HTTPS_PROXY HTTP_PROXY
cd "$TASK_DIR" || exit 1
mkdir -p "$OUT_DIR"
START=$(/opt/homebrew/bin/python3 -c 'import time;print(time.time())')
timeout 900 dsh headless --permission-mode workspace-write --approval-policy never \
  --json "$(cat "$PROMPT_FILE")" > "$OUT_DIR/round${ROUND}.events.jsonl" 2> "$OUT_DIR/round${ROUND}.stderr.log"
RC=$?
END=$(/opt/homebrew/bin/python3 -c 'import time;print(time.time())')
/opt/homebrew/bin/python3 -c "print(f'{$END-$START:.1f}')" > "$OUT_DIR/round${ROUND}.seconds"
echo "$RC" > "$OUT_DIR/round${ROUND}.exit"
echo "round $ROUND exit=$RC seconds=$(cat "$OUT_DIR/round${ROUND}.seconds")"
