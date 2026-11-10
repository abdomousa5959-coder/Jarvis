# Product Requirements Document - Open Jarvis

## 1. Product Name

Open Jarvis

## 2. Vision

An open-source, lightweight Android AI agent that lets users control their Android device using voice or text commands. No root. Pluggable with LLM APIs such as Groq, Gemini, OpenRouter, Ollama, LM Studio, Anthropic, OpenAI, or any OpenAI-compatible endpoint. Uses Graphify for local persistent memory.

## 3. Target Device

- Android 8.0+ (minSDK 26)
- 4GB RAM minimum recommended
- Works on budget phones
- APK distributed via GitHub Releases

## 4. Core Capabilities

1. Voice command input.
2. Text command input through a floating overlay.
3. Open apps by name or intent.
4. Tap, swipe, scroll, and type using AccessibilityService.
5. Read screen content via OCR (ML Kit).
6. Send context to an LLM for decision making.
7. Graphify memory for tasks, contacts, apps, and patterns.
8. Companion CLI bridge for Termux.
9. Local LLM support.
10. Universal LLM adapter for multiple providers.

## 5. App Flow

```
USER INPUT (voice or text)
    ↓
GRAPHIFY MEMORY QUERY
    ↓
LLM BRAIN (selected provider)
    ↓
ACTION EXECUTOR (AccessibilityService)
    ↓
SCREEN VERIFY / RETRY
    ↓
RESULT, TTS, AND MEMORY UPDATE
```

## 6. JSON Action Format

```json
[
  {"action": "open_app", "package": "com.whatsapp", "label": "WhatsApp"},
  {"action": "wait_for", "text": "Chats", "timeout_ms": 3000},
  {"action": "tap", "text": "Search"},
  {"action": "type", "value": "John"},
  {"action": "tap", "text": "John Doe"},
  {"action": "type", "value": "Hello"},
  {"action": "tap", "text": "Send"}
]
```

## 7. Supported Actions

- `open_app`
- `tap`
- `long_press`
- `type`
- `swipe`
- `scroll`
- `press_back`
- `press_home`
- `press_recents`
- `wait_for`
- `screenshot`
- `read_screen`

## 8. LLM Provider System

Supported provider targets include Groq, Google Gemini, OpenRouter, Anthropic Claude, OpenAI, Ollama, LM Studio, custom OpenAI-compatible APIs, and llama.cpp server mode.

## 9. Graphify Memory

Graph stored locally in SQLite via Room.

Node types:
- AppNode
- TaskNode
- ContactNode
- PatternNode
- ProviderNode

## 10. Performance Targets

- Cold start: under 1.5 seconds.
- Idle RAM: under 60MB.
- Base APK size: under 25MB, excluding downloaded models.
- minSDK: 26 (Android 8.0).

## 11. Repository Structure

See the source tree under `app/src/main/java/com/openjarvis/` and supporting docs under `docs/`.
