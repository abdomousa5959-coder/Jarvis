# MCP Server — Open Jarvis

## Model Context Protocol (MCP)

MCP lets Open Jarvis connect to external services such as Home Assistant, Notion, and GitHub through standardized tool calls.

## Overview

Examples:

```
"Turn on the living room lights" → MCP: home_assistant.lights.on
"Add task to Notion" → MCP: notion.tasks.create
```

## Supported Servers

| Server | URL | Tools |
|--------|-----|-------|
| Home Assistant | http://homeassistant.local:8123/api/mcp | lights, climate, scripts |
| Notion | https://api.notion.com/v1 | pages, databases, tasks |
| GitHub | https://api.github.com | issues, prs, repos |

## Configuration

### Home Assistant

1. Install the MCP addon in Home Assistant.
2. Get a long-lived access token.
3. Enter the server URL and token in Settings → MCP Servers.

### Notion

1. Create an integration in Notion.
2. Share the database/page with the integration.
3. Configure its API URL and token in Jarvis.

## MCPManager

```kotlin
val mcpManager = MCPManager(context)
mcpManager.serverTemplates.forEach {
    println("${it.name}: ${it.baseUrl}")
}
mcpManager.connect("home_assistant") { result ->
    result.onSuccess { client ->
        // Connected
    }
}
mcpManager.callTool("home_assistant", "lights.on", mapOf(
    "entity_id" to "light.living_room"
))
```

## Security

- Store API keys securely.
- Isolate each server in its own client instance.
- Apply rate limits and request timeouts.
- Review tool arguments before executing actions.