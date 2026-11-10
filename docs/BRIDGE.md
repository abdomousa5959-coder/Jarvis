# Open Jarvis CLI Bridge

The Termux CLI bridge allows you to control Open Jarvis from the command line using TCP sockets.

## Quick Start

### Install jarvis.sh

```bash
cp scripts/jarvis.sh $PREFIX/bin/jarvis
chmod +x $PREFIX/bin/jarvis
pkg install socat
```

### Usage

```bash
jarvis "open whatsapp"
jarvis status
jarvis history
jarvis providers
jarvis memory
jarvis --help
```

### Pipe Mode

```bash
cat commands.txt | jarvis
echo "open chrome" | jarvis
```

## Special Commands

| Command | Description |
|---------|-------------|
| `status` | Returns service state, accessibility permission status, active LLM provider |
| `history` | Returns last 10 tasks as JSON array |
| `providers` | Returns LLM provider statistics |
| `memory` | Returns Graphify memory context summary |

## Protocol

### Request Format

```json
{"cmd": "open whatsapp", "requestId": "abc123"}
```

### Response Format

```json
{"requestId": "abc123", "status": "done", "result": "success"}
```

Status values: `progress`, `done`, `error`.

## ADB

```bash
adb forward tcp:5000 tcp:5000
echo '{"cmd":"open chrome","requestId":"1"}' | nc -w 2 127.0.0.1 5000
```

Find the actual port in the app's filesDir.

## Security

The socket bridge is intended for local device use. Anyone with sufficient device access may be able to control the agent. Do not expose it to untrusted networks or forward the port outside the device.

## Troubleshooting

- **Service not running:** Start Open Jarvis and its overlay service.
- **No network tool:** Install `socat` or use `nc`.
- **Connection timeout:** The service may be busy; wait and retry.

## Exit Codes

| Code | Meaning |
|------|---------|
| 0 | Success |
| 1 | Error |