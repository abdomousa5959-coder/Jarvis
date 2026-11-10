# Milestones - Open Jarvis

## Overview

Open Jarvis development is organized into milestones, each building toward a complete Android AI agent.

## M1: Accessibility Service + App Launcher

**Status:** Complete

- AndroidManifest with accessibility permissions.
- JarvisAccessibilityService.
- Basic command parser and floating overlay.
- App launcher and Graphify app logging.
- Settings screen foundation.

## M2: Screenshot + OCR + LLM Integration

- Screenshot capture via MediaProjection.
- ML Kit OCR integration.
- LLMProvider interface and provider implementations.
- ScreenReader and JSON action plans.

## M3: Full Action Executor

- Tap by text, content description, and coordinates.
- Type into focused fields.
- Long press, swipe, scroll, and global navigation actions.
- Wait-for action and retry logic.

## M4: Graphify Memory Integration

- ContactNode and PatternNode.
- Learned edges and similar-task retrieval.
- Context injection and pattern-based suggestions.

## M5: Voice Input and TTS

- Voice activity detection and speech recognition.
- Android text-to-speech.
- Voice activation and spoken results.

## M6: Termux CLI Bridge

- Socket server and JSON request/response protocol.
- Termux companion script.
- Command execution and task history.

## M7: Local Model Support

- Local model integration and downloader.
- Offline mode and cloud/local provider switching.
- Memory-aware model loading.

## M8: Optimization + Public Release

**Status:** Complete in the supplied project notes.

- R8 optimization.
- Memory and startup profiling.
- Documentation and release preparation.

## Timeline Estimate

| Milestone | Estimated Duration |
|-----------|-------------------|
| M1 | 1 week |
| M2 | 2 weeks |
| M3 | 2 weeks |
| M4 | 2 weeks |
| M5 | 2 weeks |
| M6 | 1 week |
| M7 | 2 weeks |
| M8 | 1 week |

**Estimated total: about 13 weeks.**