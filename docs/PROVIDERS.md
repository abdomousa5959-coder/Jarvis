# LLM Providers - Open Jarvis

## Overview

Open Jarvis uses a universal adapter to connect multiple language-model providers through a common interface. OpenAI-compatible endpoints can be added with a small provider implementation.

## Supported Providers

- Groq
- Google Gemini
- OpenRouter
- Anthropic Claude
- OpenAI
- Ollama
- Custom OpenAI-compatible endpoints
- Local llama.cpp integration

## Provider Architecture

AgentCore sends prompts through UniversalAdapter, which selects the configured provider and normalizes its response.

## Adding a Custom Provider

1. Implement the LLMProvider interface.
2. Build the provider-specific request body.
3. Send requests through the shared HTTP client.
4. Parse successful responses and convert API errors into useful exceptions.
5. Register the provider in UniversalAdapter.
6. Add its name and settings to the Settings UI.
7. Test success, authentication failure, rate limiting, and network failure.

## Configuration

Provider settings include provider name, base URL, encrypted API key, and model name. Local providers may not require an API key.

## Error Handling

| Error | Handling |
|---|---|
| Invalid API key | Prompt user to verify credentials |
| Rate limit | Back off or try another configured provider |
| Network error | Report offline state |
| Model not found | Explain model selection issue |
| Server error | Retry safely or use fallback provider |

Never hard-code API keys in source code or commit secrets to the repository.
