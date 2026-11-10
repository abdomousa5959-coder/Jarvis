# Open Jarvis — Android AI Agent

Open Jarvis is an Android automation project built with Kotlin. It combines an accessibility service, a natural-language agent, configurable LLM providers, local task history, voice input/output, OCR, and a Termux socket bridge.

> The project is still being imported and checked. The feature descriptions below reflect the supplied source archive, not a verified release build.

## Main components

- **Agent:** interprets commands and produces action plans.
- **Accessibility:** opens apps, reads visible UI text, taps controls, and enters text.
- **LLM providers:** configurable remote and local model adapters.
- **Graphify memory:** Room database for task history, app usage, contacts, and learned patterns.
- **Voice:** Android speech recognition and text-to-speech.
- **Vision:** ML Kit OCR for extracting visible text from screenshots.
- **Skills:** JSON templates for common tasks.
- **Termux bridge:** local socket interface for command-line requests.
- **Screen watcher:** rule-based screen and battery monitoring.

## Requirements

- Android 8.0 (API 26) or later.
- Android Studio / Gradle with JDK 17.
- Accessibility and overlay permissions for device automation.
- An LLM API key for cloud inference, or a compatible local model server.

## Build

Open the project in Android Studio and sync Gradle. From a terminal with the Android SDK configured:

```bash
./gradlew assembleDebug
```

The debug APK is normally generated at:

```
app/build/outputs/apk/debug/app-debug.apk
```

## Setup

1. Install the APK.
2. Enable the Open Jarvis Accessibility Service in Android Settings.
3. Grant overlay permission if you want the floating interface.
4. Configure an LLM provider in Settings, or configure a supported local model endpoint.
5. Start the assistant and test with a low-risk command first.

## Termux bridge

The project includes a companion script under `scripts/jarvis.sh`. The bridge implementation and protocol notes are under `app/src/main/java/com/openjarvis/bridge/` and `docs/BRIDGE.md`.

## Security notes

Accessibility access is powerful: the service can read screen content and interact with apps. Review every permission before enabling it. Keep API keys private, use confirmation for sensitive actions, and do not run untrusted skills or prompts without inspection.

## Documentation

- [Skills format](docs/SKILLS.md)
- [Bridge protocol](docs/BRIDGE.md)
- [Graphify memory](docs/GRAPHIFY.md)
- [Provider configuration](docs/PROVIDERS.md)
- [Security notes](docs/SECURITY.md)
- [Release notes](RELEASE_NOTES.md)

## License

MIT — see [LICENSE](LICENSE).
