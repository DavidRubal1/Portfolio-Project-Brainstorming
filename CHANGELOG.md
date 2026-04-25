```markdown
# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Calendar Versioning](https://calver.org/) of
the following form: YYYY.0M.0D.

## 2026.02.05

### Added

- Designed a Piano component
- Designed a Treadmill component
- Designed a Book component

## 2026.02.26

### Added

- Designed a proof of concept for Piano component

### Updated

- Changed design to include time, and the ability to add and remove keys to the keyboard.
## 2026.03.09

### Added

- Designed kernel and enhanced interfaces for Piano component

### Updated

- Changed design to include Key object, methods for getting and setting each field of Key, a getKey kernel method, and a length kernel method
- Changed return type of functions that would have returned a Sequence<Double> to return a Piano.Key

## 2026.03.31

### Added

- Designed abstract class for Piano component

### Updated

- Changed design to include setTime kernel method, to remove isKeyActive secondary method due to overlap in utility with getPressDuration, and to have the return type secondary method getActiveKeys changed to ArrayList<Piano.Key> instead of Piano.Key[].
- Added additional javadoc comments to pianoKernel
- Added assertion statements for secondary method implementations to ensure method contracts are met

## 2026.04.24

### Added

- Designed test suite for Piano component
- Designed two different use cases for Piano component

### Updated

- Changed design to include more clear javadoc comments.
- Reverted the return type change of getActiveKeys (now called activeKeys) back to Piano.Key[], which aliases the original key objects
- Removed the num() method from the Key interface to reduce reuse of the keyPos from the Map representation in Piano1
- Removed the PianoSecondary getter methods that simply wrapped the Simplekey getter methods for simplicity
- Renamed keyNum to keyPos to make its meaning more clear in context of a piano
- Renamed setTime() and setPitch() methods in PianoSecondary to play() and tune() for clarity
- Changed file structure to include component and piano folders that contain all files for the Piano component

```