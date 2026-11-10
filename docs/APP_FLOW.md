# App Flow - Open Jarvis

Open Jarvis follows a five-stage flow from user input to task completion, with verification and recovery.

## 1. User Input

Commands can arrive through voice recognition or the floating text overlay.

## 2. Graphify Memory Query

The app retrieves relevant recent apps, frequent apps, similar tasks, and learned patterns. Only relevant context should be passed to the model.

## 3. LLM Brain

The selected provider receives the command, screen text when available, and memory context. It returns a structured JSON action plan.

## 4. Action Executor

Actions are executed sequentially through Android accessibility APIs. The app verifies important steps, retries recoverable failures, and asks the model for a correction when needed.

## 5. Result

The result is reported through the UI or text-to-speech, and the task outcome is stored locally to improve future context.

## Error Handling

| Error | Handling |
|---|---|
| App not found | Try a package/label match, then ask the user |
| Action fails | Retry once, then request an alternative |
| Timeout | Stop the step and report the timeout |
| Permission denied | Explain which Android permission is needed |
| Provider error | Try a configured fallback provider |

## State Flow

Idle → Parsing → QueryMemory → LLMCall → Executing → Done or Error.

Voice commands use the same execution flow, with speech-to-text at input and text-to-speech for the result.
