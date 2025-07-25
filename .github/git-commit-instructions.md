# Convenciones de Mensajes de Commit Estrictas

Este documento detalla las convenciones obligatorias para los mensajes de commit en este repositorio. La adhesión a estas directrices es fundamental para mantener un historial de Git limpio, legible y automatizable.

Nos adherimos a la especificación de [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) con algunas adiciones para mayor claridad y uniformidad.

---

## 1. Formato General del Mensaje de Commit

Todos los mensajes de commit DEBEN seguir la siguiente estructura:
[

[

### 1.1. Cabecera (Obligatoria)

La primera línea del mensaje de commit (la cabecera) es **obligatoria** y DEBE seguir un formato estricto:

* **`<tipo>` (Obligatorio):** Describe la naturaleza del cambio. DEBE ser uno de los siguientes:
    * `feat`: Un cambio que introduce una **nueva característica**. (Se mapea a `MINOR` en versionado semántico).
    * `fix`: Un cambio que corrige un **bug**. (Se mapea a `PATCH` en versionado semántico).
    * `build`: Cambios que afectan el sistema de construcción o dependencias externas (ej. herramientas de compilación, librerías).
    * `chore`: Otros cambios que no modifican el código de la aplicación ni los tests (ej. actualizaciones de configuración, scripts varios).
    * `ci`: Cambios en los archivos y scripts de CI (Continuous Integration).
    * `docs`: Cambios en la documentación solamente.
    * `perf`: Un cambio de código que mejora el rendimiento.
    * `refactor`: Un cambio de código que no corrige un bug ni añade una característica, pero mejora la estructura/limpieza.
    * `revert`: Un commit que revierte un commit anterior.
    * `style`: Cambios que no afectan el significado del código (ej. formato, semicolons, espacios en blanco, etc.).
    * `test`: Añadir tests que faltan o corregir tests existentes.

* **`<ámbito>` (Opcional, pero Altamente Recomendado):** Indica la parte del codebase que afecta el cambio. DEBE ir entre paréntesis y en minúsculas.
    * Si un commit afecta a múltiples ámbitos, se pueden usar comas (ej. `feat(auth,profile):`).
    * Si el cambio es global o difícil de acotar, se puede omitir.
    * **Ejemplos Comunes de Ámbitos (Adaptar a tu proyecto):**
        * `domain`
        * `data`
        * `presentation`
        * `home` (para feature `home`)
        * `auth` (para feature `authentication`)
        * `core` (para módulos transversales)
        * `deps` (para dependencias)
        * `build` (para Gradle, configuración de compilación)
        * `di` (para módulos de inyección de dependencias)
        * `test` (para configuración de tests)
        * `docs` (para archivos de documentación)

* **`<descripción_corta>` (Obligatoria):** Una descripción concisa del cambio.
    * DEBE comenzar con una letra minúscula.
    * DEBE estar en tiempo presente, modo imperativo ("añade", "corrige", "mejora", no "añadido", "corregido").
    * DEBE ser concisa (máximo 50-72 caracteres, idealmente).
    * **NO** DEBE terminar con un punto.

**Ejemplos de Cabeceras Válidas:**

* `feat(home): add welcome message to home screen`
* `fix(auth): correct password reset logic for edge cases`
* `docs: update CONTRIBUTING.md with commit guidelines`
* `refactor(product): extract product item composable`
* `chore(deps): update androidx compose material3 to 1.2.0`
* `test(home): add unit tests for HomeViewModel`

---

### 1.2. Cuerpo del Mensaje (Opcional)

* Proporciona una explicación más detallada del cambio, su motivación y el contexto.
* Cada párrafo DEBE tener un ancho de línea de 72 caracteres como máximo.
* Puede incluir una lista de puntos, si es necesario.
* DEBE estar separado de la cabecera por una línea en blanco.
* **Cuándo usarlo:** Para cambios complejos que no pueden explicarse completamente en la descripción corta. Describe el *qué* y el *por qué* del cambio, no el *cómo* (el cómo debe estar claro en el código).

**Ejemplo de Cuerpo:**
feat(settings): allow users to customize notification sounds

This change introduces a new preference screen where users can select
from a predefined list of notification sounds for different event types.

Previously, notification sounds were hardcoded, limiting user
personalization. This feature enhances user experience by providing
more control over alerts.

---

### 1.3. Pie de Página (Opcional)

* DEBE estar separado del cuerpo por una línea en blanco.
* Puede contener referencias a issues de seguimiento de problemas, breaking changes o cualquier metadato adicional.
* **Breaking Changes:** Si el commit introduce un cambio que rompe la compatibilidad (breaking change), DEBE incluir una sección `BREAKING CHANGE:` en el pie de página, seguida de una descripción que explique el cambio y cómo migrar. Esto activará un incremento `MAJOR` en el versionado semántico.
    * **Formato:** `BREAKING CHANGE: <descripción de cómo migrar>`
* **Referencias a Issues:** DEBE incluir referencias a issues relevantes del sistema de seguimiento de problemas (ej. GitHub Issues, Jira).
    * **Formato:** `Closes #123`, `Fixes #456`, `Resolves #789`, `See #1011`.

**Ejemplo de Pie de Página:**
feat(auth): implement new authentication flow

BREAKING CHANGE: The 'login' endpoint now requires an 'application_id'
header. Update all client integrations to include this new header.

Closes #789
Related to #1234

---

## 2. Puntos Adicionales Obligatorios

* **Tamaño del Commit:** Los commits DEBEN ser atómicos y cohesionados, es decir, cada commit DEBE representar un único cambio lógico. Evitar commits masivos que mezclan múltiples funcionalidades o correcciones.
* **Idioma:** Todos los mensajes de commit (tipo, ámbito, descripción, cuerpo, pie de página) **DEBEN estar en inglés.**
* **Revisión:** Antes de hacer `git commit -m "..."`, revisar siempre el mensaje para asegurar que cumple con estas directrices.

---

## 3. Configuración para Herramientas (Opcional)

Para facilitar la adhesión a estas convenciones, se recomienda usar herramientas como:

* **Husky & Commitlint (para proyectos JavaScript/Node.js):** Para validar commits antes de que sean push.
* **Pre-commit hooks personalizados (para cualquier proyecto):** Scripts de Git que validan el mensaje de commit.
* **Plugins de IDE:** Algunos IDEs ofrecen plugins para ayudar a escribir mensajes de commit conformes.

---

**Ejemplo de Commit Completo:**
feat(notifications): add configurable sound options

This feature allows users to select custom notification sounds from a
predefined list in the settings screen. This enhances personalization
and user experience.

Previously, notification sounds were hardcoded, limiting user choice.

Closes #42