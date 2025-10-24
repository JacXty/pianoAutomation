# 🎹 Proyecto de Automatización QA: Piano Virtual - Himno de la Alegría

## 📝 Descripción

Este proyecto tiene como objetivo automatizar la interacción con el piano virtual en la URL: **`https://www.musicca.com/es/piano`** para validar la correcta reproducción de secuencias de notas (escenarios del "Himno de la Alegría").

La solución se basa en el **Screenplay Pattern** y **Serenity BDD** para garantizar código limpio, legible y la generación de un informe de "Documentación Viva".

---

## 🏛️ Arquitectura y Stack Tecnológico

| Componente | Herramienta/Lenguaje | Versión Clave | Patrón de Diseño / Propósito |
| :--- | :--- | :--- | :--- |
| **Lenguaje** | Java | JDK 17+ | Lenguaje de desarrollo. |
| **Framework** | **Serenity BDD** | `4.2.8` | Generación de reportes de "Documentación Viva". |
| **Patrón** | **Screenplay Pattern** | N/A | Implementación de actores, tareas y preguntas para alta mantenibilidad. |
| **Testing** | **JUnit 4** | `4.13.2` | Motor de ejecución de pruebas (`@Test`, `@RunWith(SerenityRunner.class)`). |
| **WebDriver** | **Selenium** | `4.21.0` | Automatización de la interacción con el navegador Chrome. |
| **Gestor** | **Maven** | `3.6.3+` | Gestión de dependencias y ciclo de vida de la prueba. |

---

## 📂 Estructura del Proyecto y Responsabilidades

La estructura sigue las convenciones de Maven y el **Screenplay Pattern** para separar claramente las responsabilidades:

| Directorio | Archivos Clave | Responsabilidad (Screenplay) | Descripción |
| :--- | :--- | :--- | :--- |
| `src/test/java/test` | `PianoTest.java` | **Escenarios** | Contiene los métodos `@Test` y define las secuencias de notas. Es el punto de partida de la ejecución. |
| `src/test/java/questions` | `LaTeclaEstaMarcada.java` | **Questions (Preguntas)** | Contiene la lógica para validar el estado del sistema  |
| `src/test/java/tasks` | `TocarCancion.java` | **Tasks (Tareas)** | Contiene las acciones de alto nivel que el **Actor** realiza |
| `src/test/java/ui` | `PianoUI.java` | **Targets (Objetivos)** | Define los localizadores (selectores XPath/CSS) de los elementos de la interfaz de usuario (las teclas del piano). |
| `pom.xml` | `pom.xml` | **Configuración** | Archivo de configuración de Maven (dependencias y *plugins* de reporte). |

---

## ⚙️ Requisitos Previos

Para ejecutar el proyecto localmente, necesitas tener instalado:

1.  **Java Development Kit (JDK):** Versión 17 o superior.
2.  **Apache Maven:** Versión 3.6.3 o superior.
3.  **Navegador Chrome:** Requerido para la ejecución del `WebDriver`.

---

## 🚀 Guía de Ejecución
### 1. Inicialización del Proyecto (Primera Vez) ⬇️

Este comando es necesario para descargar **todas las librerías** de Maven Central, compilar el proyecto y prepararlo para la ejecución.

```bash 
mvn clean install        
```

### 2. Comando de Ejecución Estándar y Reporte (Si ya se ejecutó el comando anterior)📊

Este es el comando ayuda  para ejecutar todos los escenarios de prueba y, automáticamente, generar el informe de Serenity BDD. **Abrirá el navegador** durante la ejecución.

```bash
mvn clean verify
```
