# ToggleLite

A minimal, zero-dependency feature flag library for Kotlin/JVM.

---

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("org.example:toggle-lite:1.0.0")
}
```

### Gradle (Groovy)

```groovy
implementation 'org.example:toggle-lite:1.0.0'
```

---

## API Reference

| Method | Description |
|--------|-------------|
| `set(key, enabled)` | Sets a flag state |
| `isEnabled(key, default)` | Returns flag state or default |
| `toggle(key)` | Inverts the current flag value |
| `observe(key, listener)` | Subscribes to flag changes, returns unsubscribe function |
| `setAll(config)` | Bulk configuration via Map |
| `getAll()` | Returns immutable copy of all flags |
| `clear()` | Resets all flags and listeners |
| `whenEnabled(key) { }` | Executes block only if flag is enabled |

---

## Usage

### Basic Operations

```kotlin
import org.example.ToggleLite

// Set flags
ToggleLite.set("dark_mode", true)
ToggleLite.set("experimental_feature", false)

// Query state
if (ToggleLite.isEnabled("dark_mode")) {
    enableDarkTheme()
}

// Default value for undefined flags
val beta = ToggleLite.isEnabled("beta_feature", default = true)

// Toggle existing flag
ToggleLite.toggle("dark_mode")
```

### Conditional Execution

```kotlin
ToggleLite.whenEnabled("analytics") {
    trackUserEvent("page_view")
}

val result = ToggleLite.whenEnabled("new_algorithm") {
    computeWithNewMethod(data)
} ?: computeWithLegacyMethod(data)
```

### Reactive Updates

```kotlin
val unsubscribe = ToggleLite.observe("premium_features") { enabled ->
    updateUI(enabled)
}

// Later: stop observing
unsubscribe()
```

### Bulk Configuration

```kotlin
ToggleLite.setAll(mapOf(
    "feature_a" to true,
    "feature_b" to false,
    "feature_c" to true
))

val snapshot = ToggleLite.getAll()
```

---

## Design Principles

- **Singleton pattern**: Global access via `ToggleLite` object
- **Zero dependencies**: No external libraries required
- **Immutable returns**: `getAll()` returns a defensive copy
- **Observer pattern**: Built-in reactivity with cleanup support
- **Null safety**: Leverages Kotlin's type system

---

## Requirements

- Kotlin 1.4+
- JVM 1.8+ (compatible with Java 8 through Java 25+)

---

# ToggleLite (Espa&#241;ol)

Biblioteca minimalista de feature flags para Kotlin/JVM sin dependencias externas.

---

## Instalaci&#243;n

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("org.example:toggle-lite:1.0.0")
}
```

### Gradle (Groovy)

```groovy
implementation 'org.example:toggle-lite:1.0.0'
```

---

## Referencia de API

| M&#233;todo | Descripci&#243;n |
|--------|-------------|
| `set(key, enabled)` | Establece el estado de un flag |
| `isEnabled(key, default)` | Retorna el estado o valor por defecto |
| `toggle(key)` | Invierte el valor actual del flag |
| `observe(key, listener)` | Suscribe a cambios, retorna funci&#243;n para cancelar |
| `setAll(config)` | Configuraci&#243;n masiva mediante Map |
| `getAll()` | Retorna copia inmutable de todos los flags |
| `clear()` | Reinicia todos los flags y listeners |
| `whenEnabled(key) { }` | Ejecuta bloque solo si el flag est&#225; activo |

---

## Uso

### Operaciones B&#225;sicas

```kotlin
import org.example.ToggleLite

// Establecer flags
ToggleLite.set("modo_oscuro", true)
ToggleLite.set("funcion_experimental", false)

// Consultar estado
if (ToggleLite.isEnabled("modo_oscuro")) {
    activarTemaOscuro()
}

// Valor por defecto para flags no definidos
val beta = ToggleLite.isEnabled("funcion_beta", default = true)

// Alternar flag existente
ToggleLite.toggle("modo_oscuro")
```

### Ejecuci&#243;n Condicional

```kotlin
ToggleLite.whenEnabled("analytics") {
    registrarEventoUsuario("vista_pagina")
}

val resultado = ToggleLite.whenEnabled("nuevo_algoritmo") {
    calcularConNuevoMetodo(datos)
} ?: calcularConMetodoAnterior(datos)
```

### Actualizaciones Reactivas

```kotlin
val cancelar = ToggleLite.observe("funciones_premium") { activo ->
    actualizarUI(activo)
}

// Posteriormente: dejar de observar
cancelar()
```

### Configuraci&#243;n Masiva

```kotlin
ToggleLite.setAll(mapOf(
    "funcion_a" to true,
    "funcion_b" to false,
    "funcion_c" to true
))

val snapshot = ToggleLite.getAll()
```

---

## Principios de Dise&#241;o

- **Patr&#243;n Singleton**: Acceso global mediante objeto `ToggleLite`
- **Cero dependencias**: No requiere bibliotecas externas
- **Retornos inmutables**: `getAll()` retorna copia defensiva
- **Patr&#243;n Observer**: Reactividad integrada con soporte para limpieza
- **Null safety**: Aprovecha el sistema de tipos de Kotlin

---

## Requisitos

- Kotlin 1.4+
- JVM 1.8+ (compatible con Java 8 hasta Java 25+)

---

## Licencia

MIT License

Copyright (c) 2025 Jhafet Cánepa