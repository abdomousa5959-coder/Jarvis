# App Builder — Open Jarvis

## Overview

App Builder Mode lets users generate simple Android apps from natural language descriptions. Say "build me a [type] app" and Jarvis creates a complete project.

## How It Works

1. User describes desired app.
2. Jarvis analyzes requirements.
3. Generates Android project structure.
4. Creates necessary files.
5. Provides build instructions.

## Requirements

- Termux installed with Gradle.
- About 2GB free storage.
- Android SDK.

## Usage

### From Overlay

```
User: "build me a calculator app"
Jarvis: "What features should the calculator have?"
User: "Basic math, plus minus multiply divide"
Jarvis: [Generates project]
```

### From Termux CLI

```bash
jarvis build "flashlight app with sos pattern"
```

## Generated Project Structure

```
MyApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/myapp/
│   │   │   ├── MainActivity.kt
│   │   │   └── ...
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   └── drawable/
│   │   └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## Supported App Types

| Type | Features |
|------|--------|
| Calculator | Buttons, operations, display |
| Flashlight | Camera torch, SOS pattern |
| Notes | CRUD notes, local storage |
| Timer | Countdown, alarm |
| Converter | Unit conversions |
| Quiz | Questions, scoring |

## Build APK

```bash
cd ~/jarvis-builds/myapp
./gradlew assembleDebug
```

APK location: `app/build/outputs/apk/debug/app-debug.apk`.

## Limitations

- No backend/server features.
- Simple UI only.
- Local storage only.
- Maximum about 10 screens.