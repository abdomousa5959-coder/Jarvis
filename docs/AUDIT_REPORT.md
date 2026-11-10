# Open Jarvis — Repository Audit Report

## Security and Reliability Review

This report records the main areas reviewed before release. Findings should be rechecked against the current implementation before treating any item as resolved.

## Critical and High-Priority Areas

1. **Accessibility node lifecycle:** AccessibilityNodeInfo objects can become stale after screen transitions. Reacquire nodes before acting and guard operations against stale references.
2. **Concurrent task execution:** AgentCore should serialize device-control tasks with a coroutine Mutex to prevent overlapping action sequences.
3. **Coroutine lifecycle:** Avoid GlobalScope. Bind jobs to a service, ViewModel, or explicitly managed CoroutineScope and cancel them when the owner stops.
4. **LLM response validation:** Validate JSON structure, action names, required fields, and argument lengths before executing any model-generated action.
5. **Bitmap lifecycle:** Release screenshot images and recycle temporary bitmaps when no longer needed.
6. **Nullable action arguments:** Validate required fields for each action before dispatch.

## Security Areas

1. **Prompt injection:** Treat screen text, notifications, clipboard contents, and user-provided data as untrusted input. Delimit and sanitize context before including it in prompts.
2. **Local socket authentication:** Restrict the CLI bridge to the intended local caller and validate every request.
3. **Notification privacy:** Exclude financial and other sensitive applications, and let users disable notification processing.
4. **Credential storage:** Store API keys in encrypted storage and never log them.
5. **Risky actions:** Require confirmation before destructive, financial, or externally visible actions.

## Operational Hardening

- Apply request and connection timeouts.
- Handle provider rate limits and server errors.
- Avoid logging private message bodies or credentials.
- Use bounded retries with backoff.
- Add tests for malformed model output and failed accessibility actions.
- Verify battery and memory impact of polling services.

## Release Checklist

- Review Android manifest permissions and exported components.
- Verify AccessibilityService disclosure and user-controlled activation.
- Test on supported Android versions.
- Run unit and instrumentation tests.
- Build both debug and release variants.
- Inspect the final APK and dependency report.
