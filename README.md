# 📝 Herramienta de Parseo de Archivos (Modular) / File Parsing Tool

<div align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java"/>
  <img src="https://img.shields.io/badge/Swing_GUI-blue?style=for-the-badge" alt="Swing GUI"/>
  <img src="https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white" alt="JSON"/>
  <img src="https://img.shields.io/badge/XML-FF6600?style=for-the-badge&logo=xml&logoColor=white" alt="XML"/>
  <img src="https://img.shields.io/badge/CSV-1D365D?style=for-the-badge&logo=csv&logoColor=white" alt="CSV"/>
</div>
<br>

<p align="center">
  🌐 <b><a href="#-español">Lea esto en Español</a></b> | 
  🌐 <b><a href="#-english">Read this in English</a></b>
</p>

---

## 🇪🇸 Español

### 📖 Índice
1. [Acerca del Proyecto](#-acerca-del-proyecto)
2. [Características](#-características)
3. [Requisitos Previos](#-requisitos-previos)
4. [Instalación y Ejecución](#-instalación-y-ejecución)
5. [Estructura del Proyecto](#-estructura-del-proyecto)
6. [Cómo utilizarlo](#-cómo-utilizarlo)

### 📌 Acerca del Proyecto
Esta es una herramienta desarrollada en **Java** con una interfaz gráfica nativa mediante **Swing**. Permite leer y parsear distintos formatos de archivos de datos (`.json`, `.xml`, `.csv`) de forma modular, aplicando patrones de diseño orientados a objetos.

### ✨ Características
- Interfaz gráfica limpia y adaptativa.
- **Sistema Modular:** Utiliza una interfaz `IParser` para implementar dinámicamente el manejo de distintos formatos.
- Soporte para **JSON** (vía `org.json`).
- Soporte para **XML** nativo.
- Soporte para **CSV** nativo.

### ⚙️ Requisitos Previos
- **Java Development Kit (JDK)** 11 o superior.
- Un IDE como Visual Studio Code, IntelliJ IDEA o Eclipse.

### 🚀 Instalación y Ejecución
1. Clona este repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   ```
2. Asegúrate de que la librería ubicada en `lib/org.json.jar` esté incluida en el *ClassPath* de tu proyecto.
3. Compila y ejecuta el archivo principal:
   ```bash
   javac -cp "lib/*" src/*.java -d bin
   java -cp "bin;lib/*" Main
   ```
   *(Nota: si usas un IDE, simplemente ejecuta `Main.java`)*

### 📁 Estructura del Proyecto
```text
Parsear/
│
├── lib/                 # Librerías externas (Ej. org.json.jar)
├── src/                 # Código fuente en Java
│   ├── IParser.java     # Interfaz principal de parseo
│   ├── Main.java        # Punto de entrada y GUI (Swing)
│   ├── JsonParser.java  # Implementación para archivos JSON
│   ├── XmlParser.java   # Implementación para archivos XML
│   ├── CsvParser.java   # Implementación para archivos CSV
│   └── archivos pruebas/# Archivos de ejemplo para testear
│
└── .gitignore           # Exclusiones de Git
```

### 💡 Cómo utilizarlo
1. Ejecuta la aplicación.
2. Selecciona en el menú desplegable el formato que deseas parsear (JSON, XML o CSV).
3. Haz clic en **"Seleccionar Archivo y Parsear"**.
4. Elige un archivo válido desde tu ordenador.
5. Los resultados formateados se mostrarán en la gran caja de texto central.

<br>
<hr>
<br>

## 🇬🇧 English

### 📖 Table of Contents
1. [About the Project](#-about-the-project)
2. [Features](#-features)
3. [Prerequisites](#-prerequisites)
4. [Installation & Execution](#-installation--execution)
5. [Project Structure](#-project-structure)
6. [How to Use](#-how-to-use)

### 📌 About the Project
This is a tool developed in **Java** featuring a native Graphical User Interface (GUI) built with **Swing**. It allows users to read and parse different data file formats (`.json`, `.xml`, `.csv`) following a modular object-oriented design approach.

### ✨ Features
- Clean and responsive graphical interface.
- **Modular System:** Uses an `IParser` interface to dynamically implement the handling of different formats.
- Support for **JSON** (via `org.json`).
- Support for native **XML**.
- Support for native **CSV**.

### ⚙️ Prerequisites
- **Java Development Kit (JDK)** 11 or higher.
- An IDE like Visual Studio Code, IntelliJ IDEA, or Eclipse.

### 🚀 Installation & Execution
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/your-repository.git
   ```
2. Make sure the library located at `lib/org.json.jar` is included in your project's *ClassPath*.
3. Compile and run the main file:
   ```bash
   javac -cp "lib/*" src/*.java -d bin
   java -cp "bin;lib/*" Main
   ```
   *(Note: If using an IDE, simply run `Main.java`)*

### 📁 Project Structure
```text
Parsear/
│
├── lib/                 # External libraries (e.g., org.json.jar)
├── src/                 # Java source code
│   ├── IParser.java     # Main parsing interface
│   ├── Main.java        # Entry point and GUI (Swing)
│   ├── JsonParser.java  # Implementation for JSON files
│   ├── XmlParser.java   # Implementation for XML files
│   ├── CsvParser.java   # Implementation for CSV files
│   └── archivos pruebas/# Sample files for testing
│
└── .gitignore           # Git exclusions
```

### 💡 How to Use
1. Run the application.
2. Select the format you want to parse from the dropdown menu (JSON, XML, or CSV).
3. Click on the **"Seleccionar Archivo y Parsear"** (Select File and Parse) button.
4. Choose a valid file from your local machine.
5. The nicely formatted results will be displayed in the large central text area.

---
<div align="center">
  <i>Desarrollado con ❤️ para aprender y compartir. / Developed with ❤️ to learn and share.</i>
</div>
