# Life Canvas Android App

Life Canvas: An innovative Android app empowering users to curate their life's tasks and goals through intuitive organization, priority settings, and seamless synchronization across devices.

## What Features or Page use for this Project
1. **Intro Screen**: where user can see the intro of the app.
2. **Login and Register Screen**: where user can login or register in the app.
3. **Home Screen**: where user can see the home screen of the app.
4. **Add Task Screen**: where user can add the task in the app.
5. **Category Screen**: where user can see the category of the task.
6. **Calendar Screen**: where user can see the calendar of the task.
7. **Profile Screen**: where user can see the profile of the app.
8. **Focus Mode Screen**: where user can see the focus mode of the app.
9. **Settings Screen**: where user can see the settings of the app.
10. **Notification Screen**: where user can see the notification of the app.
11. **Search Screen**: where user can search the task in the app.
12. **Task Screen**: where user can see the task of the app.
13. **Task Detail Screen**: where user can see the task detail of the app.

## We are implementing new features in future updates
1.**Server Driven UI**: where user can see the server driven UI of the app."
2.**AR View**: where user can see the AR view of the app.

## What's Included in this Project

Explore third-party dependencies and documentation in [/documentation](/documentation). Notable inclusions:

- [Ktlint](/documentation/StaticAnalysis.md) for code formatting.
- [Detekt](/documentation/StaticAnalysis.md) for code smells.
- [Git Hooks](/documentation/GitHooks.md) for static analysis checks.
- [GitHub Actions](/documentation/GitHubActions.md) for continuous integration.
- [LeakCanary](https://square.github.io/leakcanary/) for detecting memory leaks.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) and [Room](https://developer.android.com/training/data-storage/room) dependencies (removable via setup.gradle).
- [Paparazzi](https://github.com/cashapp/paparazzi) dependency (removable via setup.gradle).
- [Dokka](https://github.com/Kotlin/dokka) dependency, which document all project and module.
- [Spotless](https://github.com/diffplug/spotless) dependency, which is Keep your code spotless.
- [sortDependencies](https://github.com/square/gradle-dependencies-sorter) dependency, which is Sorts dependencies in build.gradle files.

## Dependency Setup

Dependencies are structured in [/buildscripts](/buildscripts). App module dependencies defined using a Gradle version catalog in [libs.versions.toml](gradle/libs.versions.toml).

## Danger Checks

Uses [Danger](https://danger.systems) for PR checks. See [Dangerfile](Dangerfile). Set up a Danger API key in GitHub secrets for GitHub Actions.

## Templates

Includes [Pull Request Template](/.github/pull_request_template.md) for organized PR descriptions.
