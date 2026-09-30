# JavaFX TextField

A JavaFX desktop demo that shows how to build forms with `TextField` controls — from a simple `GridPane` layout to a polished registration UI with CSS styling, entrance animations, and an animated welcome dialog.

## Description

This project demonstrates the fundamentals of JavaFX text fields through two complementary examples:

- **TextFieldExample** (main application) — a modern registration form with first name and last name fields, custom styling, staggered entrance animations, and a modal welcome message on submit.
- **TextFieldUI** — a minimal form using empty `TextField()` constructors laid out in a `GridPane`, useful as a learning baseline.

It is intended as a learning / reference project for JavaFX UI controls, layouts, CSS, and simple animations.

## Features

- Registration form with **First Name** and **Last Name** text fields
- Prompt text (placeholders) on input fields
- External CSS stylesheet for a modern card-based look
- Staggered fade / slide entrance animations on load
- Animated modal welcome dialog after submit
- Graceful fallback display name (`Dear Guest`) when fields are empty
- Secondary basic example (`TextFieldUI`) with `GridPane` layout
- Maven-based build with the OpenJFX Maven plugin

## Technologies

| Technology | Version / Role |
|---|---|
| **Java** | 17+ |
| **JavaFX** | 21.0.5 (`javafx-controls`, `javafx-graphics`) |
| **Maven** | Build & dependency management |
| **JavaFX Maven Plugin** | 0.0.8 — run the app with `mvn javafx:run` |
| **CSS** | Custom stylesheet (`styles.css`) |

## Prerequisites

Before you begin, make sure you have:

- **JDK 17** or later installed (`java -version`)
- **Apache Maven 3.6+** installed (`mvn -version`)
- A desktop environment that can display JavaFX windows

## Installation & Setup

1. **Clone or copy** the project folder:

   ```bash
   cd JavaFX_TextField
   ```

2. **Verify** the project builds:

   ```bash
   mvn clean compile
   ```

Maven will download JavaFX and other dependencies automatically on the first run.

## How to Run

### Main application (modern form)

```bash
mvn javafx:run
```

This launches `application.TextFieldExample` (configured as the main class in `pom.xml`).

### Basic GridPane example

To run the simpler `TextFieldUI` demo instead, either:

**Option A** — temporarily change the main class in `pom.xml`:

```xml
<main.class>application.TextFieldUI</main.class>
```

Then run:

```bash
mvn javafx:run
```

**Option B** — run with an explicit main class:

```bash
mvn javafx:run -Djavafx.mainClass=application.TextFieldUI
```

## Project Structure

```
JavaFX_TextField/
├── pom.xml                          # Maven project configuration
├── README.md                        # This file
└── src/
    └── main/
        ├── java/
        │   └── application/
        │       ├── TextFieldExample.java   # Main app — modern registration UI
        │       └── TextFieldUI.java        # Basic GridPane TextField demo
        └── resources/
            └── application/
                └── styles.css              # Styles for the modern form
```

| File | Purpose |
|---|---|
| `TextFieldExample.java` | Modern registration form, animations, welcome dialog |
| `TextFieldUI.java` | Minimal labels + empty `TextField`s in a `GridPane` |
| `styles.css` | Visual theme (card, fields, button, accent colors) |
| `pom.xml` | Dependencies, Java 17 release, JavaFX plugin config |

## Usage

1. Start the app with `mvn javafx:run`.
2. Enter a **first name** and/or **last name** in the form fields.
3. Click **Submit**.
4. A welcome dialog appears with a personalized greeting (or “Dear Guest” if both fields are empty).
5. Click **Continue** to close the dialog.

### Behavior notes

- Names are trimmed and capitalized for display.
- Fields may be left empty; the dialog still opens with a fallback greeting.
- The main window has a minimum size of 480×560 and opens at 560×620.

## Learning Points

This project is a good reference for:

- Creating and configuring `TextField` controls (empty constructor, prompt text, style classes)
- Building layouts with `VBox`, `StackPane`, and `GridPane`
- Loading external CSS with `scene.getStylesheets().add(...)`
- Using `FadeTransition`, `TranslateTransition`, `ScaleTransition`, and `ParallelTransition`
- Showing a modal dialog with `StageStyle.TRANSPARENT` and `Modality.WINDOW_MODAL`

## License

This project is provided as a learning / demonstration sample. Use and adapt it freely for educational purposes.
