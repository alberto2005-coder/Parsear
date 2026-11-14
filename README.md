# Parsear

[![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Swing](https://img.shields.io/badge/GUI-Swing-007396)](https://docs.oracle.com/javase/tutorial/uiswing/)
[![JSON](https://img.shields.io/badge/JSON-org.json-000000?logo=json&logoColor=white)](https://github.com/stleary/JSON-java)
[![MIT](https://img.shields.io/badge/Licencia-MIT-green)](LICENSE)

[Español](#español) · [English](#english)

> Desarrollado por **[Alberto Ortiz](https://github.com/alberto2005-coder/)** · [albertoortiz.is-a.dev](https://albertoortiz.is-a.dev/)

---

## Español

### Índice

- [Descripción](#descripción)
- [Características](#características)
- [Requisitos](#requisitos)
- [Compilación y ejecución](#compilación-y-ejecución)
- [Uso](#uso)
- [Arquitectura modular](#arquitectura-modular)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Formato de salida](#formato-de-salida)
- [Archivos de ejemplo](#archivos-de-ejemplo)
- [Limitaciones conocidas](#limitaciones-conocidas)
- [Licencia](#licencia)

### Descripción

Herramienta de escritorio en **Java** con interfaz **Swing** para leer y parsear archivos **JSON**, **XML** y **CSV** desde una única ventana. El usuario elige el formato en un desplegable, selecciona un archivo y el resultado se muestra formateado en un área de texto.

El objetivo del proyecto es demostrar un diseño **modular orientado a objetos**: una interfaz `IParser` común y una implementación independiente por formato.

### Características

- **Interfaz Swing**: ventana de 700 × 500, selector de formato, botón de apertura y área de resultados con scroll.
- **Diseño modular**: `JsonParser`, `XmlParser` y `CsvParser` implementan `IParser`; la GUI solo conoce la interfaz.
- **JSON** mediante la librería `org.json`, incluida en `lib/org.json.jar`.
- **XML** con el parser DOM nativo del JDK.
- **CSV** con un parser propio, sin dependencias.
- **Manejo de errores visible**: si el archivo no se puede leer o parsear, la ventana muestra el detalle en lugar de fallar en silencio.
- **Archivos de prueba** incluidos para los tres formatos.
- La interfaz está **solo en español**.

### Requisitos

- **JDK 11** o superior (`Files.readString` requiere Java 11).
- La librería `lib/org.json.jar`, ya incluida en el repositorio.
- Opcional: un IDE como IntelliJ IDEA, Eclipse o VS Code con el paquete de extensiones de Java.

Comprueba tu versión con:

```bash
java -version
javac -version
```

### Compilación y ejecución

1. **Clona el repositorio:**

   ```bash
   git clone https://github.com/alberto2005-coder/Parsear.git
   cd Parsear
   ```

2. **Compila** desde la raíz del proyecto:

   ```bash
   javac -cp "lib/*" -d bin src/*.java
   ```

3. **Ejecuta** la clase principal `Main`:

   ```bash
   # Linux / macOS
   java -cp "bin:lib/*" Main

   # Windows
   java -cp "bin;lib/*" Main
   ```

El separador del *classpath* es `:` en Linux y macOS, y `;` en Windows. Si usas PowerShell, las comillas dobles evitan que el shell expanda el asterisco.

También puedes abrir la carpeta en tu IDE, marcar `src` como carpeta de fuentes, añadir `lib/org.json.jar` al *classpath* y ejecutar `Main.java`.

> [!NOTE]
> Todas las clases están en el **paquete por defecto** (no hay sentencias `package`), por eso los comandos anteriores funcionan desde `src` sin especificar un nombre cualificado.

### Uso

1. Ejecuta la aplicación: aparece la ventana «Herramienta de Parseo de Archivos (Modular)».
2. Elige **JSON**, **XML** o **CSV** en el desplegable.
3. Pulsa **«Seleccionar Archivo y Parsear»**.
4. Selecciona un archivo desde el diálogo del sistema.
5. El resultado aparece en el área central; si algo falla, se muestra un bloque `--- ERROR ---` con el mensaje de la excepción.

El formato del desplegable es el que decide el parser: la extensión del archivo **no** se valida. Si eliges JSON y abres un CSV, el error lo dará el parser de JSON.

### Arquitectura modular

```text
Main (JFrame)
   │  elige el tipo y lee el archivo con Files.readString
   ▼
parseData(content, type)
   │  switch sobre "JSON" / "XML" / "CSV"
   ▼
IParser  ◄── JsonParser
         ◄── XmlParser
         ◄── CsvParser
   │
   ▼
String formateado → JTextArea
```

La interfaz es mínima:

```java
public interface IParser {
    String parse(String content) throws Exception;
}
```

Añadir un formato nuevo consiste en crear una clase que implemente `IParser`, añadir su opción al array `parserTypes` de `Main` y un `case` en `parseData`.

### Estructura del proyecto

```text
Parsear/
├── lib/
│   └── org.json.jar          # Dependencia JSON
├── src/
│   ├── IParser.java          # Interfaz común de parseo
│   ├── Main.java             # Punto de entrada y ventana Swing
│   ├── JsonParser.java       # Implementación JSON
│   ├── XmlParser.java        # Implementación XML con DOM
│   ├── CsvParser.java        # Implementación CSV
│   ├── LectorCSV.java        # Demo de consola, no usada por la GUI
│   └── archivos pruebas/
│       ├── prueba.json
│       ├── prueba.xml
│       └── prueba.csv
├── .gitignore
├── LICENSE
└── README.md
```

`LectorCSV` tiene su propio `main` y procesa un CSV escrito directamente en el código; sirve como ejemplo didáctico y **no** se usa desde la interfaz.

### Formato de salida

**JSON** (objeto):

```text
Detectado Objeto JSON Principal:
  - Clave: id              | Valor: 101
  - Clave: nombre          | Valor: Ana
```

**JSON** (array): indica el número de elementos y recorre cada objeto. Los valores anidados se imprimen con `toString()`, en una sola línea.

**XML**:

```text
Elemento Raíz: <configuracion>

Nodos Hijos Directos:
  - <servidor>: 192.168.1.1
  - <puerto>: 8080
```

Solo se listan los **hijos directos** de la raíz y se usa su `textContent`, así que los elementos anidados se aplanan en el texto del padre.

**CSV**:

```text
Detectado CSV con 3 columnas.
Cabeceras: SKU | Producto | Stock
-------------------------------------------------
Fila 1:
  - SKU            : A-001
  - Producto       : Teclado Mecanico
  - Stock          : 35
```

La primera línea se toma como cabecera; las líneas vacías se ignoran.

### Archivos de ejemplo

| Archivo | Contenido |
|---|---|
| `src/archivos pruebas/prueba.json` | Array de dos empleados con `id`, `nombre`, `departamento` y `activa` |
| `src/archivos pruebas/prueba.xml` | Configuración con servidor, puerto, modo y usuario |
| `src/archivos pruebas/prueba.csv` | Inventario con SKU, producto y stock |

La carpeta tiene un espacio en el nombre: al seleccionar los archivos desde la GUI no hay problema, pero evita usarla en scripts de shell sin comillas.

### Limitaciones conocidas

- **CSV sin comillas**: separa por `,` y por saltos de línea, sin soportar campos entrecomillados, comas dentro de valores ni saltos de línea internos. Tampoco valida que todas las filas tengan el mismo número de columnas.
- **XML sin atributos ni profundidad**: solo muestra los hijos directos de la raíz y su texto agregado; no lista atributos ni conserva la jerarquía.
- **XML sin protección XXE**: `DocumentBuilderFactory` se usa con la configuración por defecto. No abras XML de fuentes no confiables; para ello, desactiva `external-general-entities` y `external-parameter-entities`, y activa `FEATURE_SECURE_PROCESSING`.
- **Codificación implícita**: `Files.readString` usa UTF-8, pero `XmlParser` vuelve a convertir el texto a bytes con la codificación por defecto de la plataforma. En archivos con acentos y un *default charset* distinto de UTF-8 puede haber diferencias.
- **Sin validación de extensión**: manda el formato elegido en el desplegable, no la extensión del archivo.
- **Carga completa en memoria**: el archivo entero se lee como `String`; los archivos muy grandes pueden agotar la memoria.
- **Sin pruebas automatizadas** ni sistema de construcción (Maven/Gradle): la compilación es manual con `javac`.
- **Interfaz solo en español**, aunque este README sea bilingüe.

### Licencia

Distribuido bajo la **Licencia MIT**. Consulta [LICENSE](LICENSE). © 2026 Alberto Ortiz.

---

## English

### Table of contents

- [Overview](#overview)
- [Features](#features)
- [Requirements](#requirements)
- [Build and run](#build-and-run)
- [Usage](#usage)
- [Modular architecture](#modular-architecture)
- [Project structure](#project-structure)
- [Output format](#output-format)
- [Sample files](#sample-files)
- [Known limitations](#known-limitations)
- [License](#license)

### Overview

A **Java** desktop tool with a **Swing** interface for reading and parsing **JSON**, **XML** and **CSV** files from a single window. You pick the format from a drop-down, choose a file, and the formatted result appears in a text area.

The project is meant to demonstrate a **modular object-oriented design**: a shared `IParser` interface and one independent implementation per format.

### Features

- **Swing UI**: 700 × 500 window with a format selector, an open button and a scrollable results area.
- **Modular design**: `JsonParser`, `XmlParser` and `CsvParser` implement `IParser`; the GUI only knows the interface.
- **JSON** through the `org.json` library, bundled as `lib/org.json.jar`.
- **XML** with the JDK's native DOM parser.
- **CSV** through a small hand-written parser with no dependencies.
- **Visible error handling**: if a file cannot be read or parsed, the window shows the details instead of failing silently.
- **Sample files** for all three formats.
- The UI is **Spanish only**.

### Requirements

- **JDK 11** or later (`Files.readString` requires Java 11).
- `lib/org.json.jar`, already included in the repository.
- Optional: an IDE such as IntelliJ IDEA, Eclipse or VS Code with the Java extension pack.

Check your version with:

```bash
java -version
javac -version
```

### Build and run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/alberto2005-coder/Parsear.git
   cd Parsear
   ```

2. **Compile** from the project root:

   ```bash
   javac -cp "lib/*" -d bin src/*.java
   ```

3. **Run** the `Main` class:

   ```bash
   # Linux / macOS
   java -cp "bin:lib/*" Main

   # Windows
   java -cp "bin;lib/*" Main
   ```

The classpath separator is `:` on Linux and macOS, and `;` on Windows. In PowerShell, the double quotes stop the shell from expanding the asterisk.

You can also open the folder in your IDE, mark `src` as a source folder, add `lib/org.json.jar` to the classpath and run `Main.java`.

> [!NOTE]
> All classes are in the **default package** (there are no `package` statements), which is why the commands above work from `src` without a qualified name.

### Usage

1. Run the application: the “Herramienta de Parseo de Archivos (Modular)” window opens.
2. Choose **JSON**, **XML** or **CSV** in the drop-down.
3. Click **“Seleccionar Archivo y Parsear”** (Select File and Parse).
4. Pick a file from the system dialog.
5. The result appears in the central area; on failure you get a `--- ERROR ---` block with the exception message.

The drop-down decides the parser: the file extension is **not** validated. If you select JSON and open a CSV, the JSON parser is what reports the error.

### Modular architecture

```text
Main (JFrame)
   │  picks the type and reads the file with Files.readString
   ▼
parseData(content, type)
   │  switch over "JSON" / "XML" / "CSV"
   ▼
IParser  ◄── JsonParser
         ◄── XmlParser
         ◄── CsvParser
   │
   ▼
formatted String → JTextArea
```

The interface is minimal:

```java
public interface IParser {
    String parse(String content) throws Exception;
}
```

Adding a format means writing a class that implements `IParser`, adding its label to `Main`'s `parserTypes` array and a `case` in `parseData`.

### Project structure

```text
Parsear/
├── lib/
│   └── org.json.jar          # JSON dependency
├── src/
│   ├── IParser.java          # Shared parsing interface
│   ├── Main.java             # Entry point and Swing window
│   ├── JsonParser.java       # JSON implementation
│   ├── XmlParser.java        # XML implementation using DOM
│   ├── CsvParser.java        # CSV implementation
│   ├── LectorCSV.java        # Console demo, unused by the GUI
│   └── archivos pruebas/
│       ├── prueba.json
│       ├── prueba.xml
│       └── prueba.csv
├── .gitignore
├── LICENSE
└── README.md
```

`LectorCSV` has its own `main` and processes a CSV hard-coded in the source; it is a teaching example and is **not** called from the UI.

### Output format

**JSON** (object):

```text
Detectado Objeto JSON Principal:
  - Clave: id              | Valor: 101
  - Clave: nombre          | Valor: Ana
```

**JSON** (array): reports the element count and walks each object. Nested values are printed with `toString()` on a single line.

**XML**:

```text
Elemento Raíz: <configuracion>

Nodos Hijos Directos:
  - <servidor>: 192.168.1.1
  - <puerto>: 8080
```

Only the **direct children** of the root are listed, using `textContent`, so nested elements are flattened into their parent's text.

**CSV**:

```text
Detectado CSV con 3 columnas.
Cabeceras: SKU | Producto | Stock
-------------------------------------------------
Fila 1:
  - SKU            : A-001
  - Producto       : Teclado Mecanico
  - Stock          : 35
```

The first line is treated as the header; blank lines are skipped.

### Sample files

| File | Contents |
|---|---|
| `src/archivos pruebas/prueba.json` | Array of two employees with `id`, `nombre`, `departamento` and `activa` |
| `src/archivos pruebas/prueba.xml` | Configuration with server, port, mode and user |
| `src/archivos pruebas/prueba.csv` | Inventory with SKU, product and stock |

The folder name contains a space: selecting the files through the GUI is fine, but quote the path in shell scripts.

### Known limitations

- **CSV without quoting**: splits on `,` and newlines, so quoted fields, commas inside values and embedded newlines are unsupported. It also does not check that every row has the same number of columns.
- **XML without attributes or depth**: only the root's direct children and their aggregated text are shown; attributes and hierarchy are dropped.
- **XML without XXE protection**: `DocumentBuilderFactory` uses its defaults. Do not open XML from untrusted sources; disable `external-general-entities` and `external-parameter-entities` and enable `FEATURE_SECURE_PROCESSING` first.
- **Implicit encoding**: `Files.readString` uses UTF-8, but `XmlParser` converts the text back to bytes with the platform default charset. Files with accents can differ when that default is not UTF-8.
- **No extension validation**: the drop-down selection wins over the actual file extension.
- **Whole file in memory**: the file is read into a single `String`, so very large files can exhaust the heap.
- **No automated tests** and no build tool (Maven/Gradle): compilation is manual with `javac`.
- **Spanish-only UI**, even though this README is bilingual.

### License

Distributed under the **MIT License**. See [LICENSE](LICENSE). © 2026 Alberto Ortiz.
