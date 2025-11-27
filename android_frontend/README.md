# Simple Notes - Android (Compose, Material 3)

A modern notes application using Jetpack Compose and Material 3 with an MVVM architecture.
No network or external services required. Notes are kept locally in-memory (swap-in Room/DataStore later if needed).

Ocean Professional theme:
- primary #2563EB, secondary/success #F59E0B, error #EF4444
- background #f9fafb, surface #ffffff, text #111827
- rounded corners, subtle shadows, smooth transitions

## Build

```bash
./gradlew :app:assembleDebug
```

## Run

- Connect a device or start an emulator.
- Install and run:
```bash
./gradlew :app:installDebug
```
Search for "Simple Notes".

## Features
- Notes list with FAB to add notes
- Create/edit note screen
- Delete notes
- Sample data for quick visualization
- Material 3, Compose UI, MVVM

## Structure
- app/src/main/kotlin/org/example/app/ui/... UI Composables
- app/src/main/kotlin/org/example/app/viewmodel/... ViewModel
- app/src/main/kotlin/org/example/app/data/... Repository
- app/src/main/kotlin/org/example/app/model/... Models
- app/src/main/kotlin/org/example/app/ui/theme/... Theme

To persist beyond process death, replace NotesRepository with Room/DataStore.