# Security — Open Jarvis

## Security Model

Open Jarvis has a defense-in-depth security architecture focusing on user privacy and device safety.

### Core Principles

1. Data stays on device where possible.
2. Use least-privilege permissions.
3. Apply multiple security layers.
4. Make device actions visible to the user.

## Permissions

| Permission | Purpose | Risk |
|------------|---------|------|
| Accessibility Service | Read screen and interact with UI | High |
| Overlay | Floating widget | Medium |
| Microphone | Voice input | High |
| Storage | Local model files | Medium |
| Network | LLM APIs | Medium |

## Privacy Protections

- Graphify database is local SQLite.
- API keys are intended to be stored in EncryptedSharedPreferences.
- Review notification access and block sensitive apps.
- Screenshots and clipboard data may contain private information.

## Security Features

### Prompt Injection Guard

The prompt sanitizer checks suspicious instruction patterns such as "ignore instructions", "jailbreak", and "DAN mode".

### Provider Fallback

If a configured LLM provider fails, the provider fallback logic may try another provider and surface an error.

### Socket Authentication

The bridge contains a UID allowlist. Verify the implementation on the target Android version before relying on it as an authorization boundary.

## Data Handling

Potentially stored data includes task history, app usage patterns, user preferences, and Graphify memory nodes. Treat screen content, notifications, voice input, and clipboard contents as sensitive.

## Best Practices

1. Review all permissions before granting them.
2. Keep API keys private.
3. Test commands with low-risk actions first.
4. Require confirmation before sending messages, making purchases, or performing other consequential actions.
5. Do not run untrusted skills or prompts.
6. Avoid exposing local bridge ports to untrusted networks.

## Reporting Security Issues

Review the project maintainers' current contact details before reporting vulnerabilities.