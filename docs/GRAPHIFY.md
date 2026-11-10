# Graphify Memory System - Open Jarvis

## Overview

Graphify is Open Jarvis's persistent memory system built on SQLite via Room DB. It tracks app usage, tasks, contacts, and learned patterns locally.

## Architecture

GraphifyDB (Room) stores AppNode, TaskNode, ContactNode, PatternNode, and relationship Edge records.

## Node Types

- **AppNode:** package name, label, last-used timestamp, and use count.
- **TaskNode:** command, result, timestamp, and provider used.
- **ContactNode:** contact identity and optional contact details.
- **PatternNode:** learned behavior sequence, confidence, and last update.

## Edge Types

| Edge | Meaning |
|---|---|
| UsedBy | App used by a task |
| OpenedAfter | App opened after another app |
| ResultedIn | Task led to another task |
| MentionedIn | Contact mentioned in a task |
| LearnedFrom | Pattern derived from app history |

## Query Patterns

Room DAOs support recent apps, most-used apps, similar tasks, and app-sequence relationships. The repository builds a concise memory context for injection into LLM prompts.

## Pattern Learning

On successful task completion, the task is logged and recent app sequences are analyzed. Repeated sequences increase edge weights and pattern confidence. Stale patterns can decay over time.

## Performance and Security

- Data remains local in SQLite.
- No cloud synchronization is required.
- Provider credentials should be stored using encrypted preferences.
- Contact indexing should be opt-in.
- Database indexes support timestamp and frequently queried fields.

## Future Extensions

Potential additions include graph visualization, optional encrypted backup, cross-device sync, and natural-language memory queries.
