# 🤖 Smart Alarm System - Lógica de Negocio OO

Este repositorio contiene el diseño y la implementación modular de la lógica interna para un sistema de alarma inteligente avanzado, emulando las capacidades operativas de los teléfonos inteligentes modernos. Desarrollado exclusivamente en **Java** bajo un entorno sin interfaz gráfica.

---

## 🎯 Objetivos del Proyecto
* **Análisis de Requisitos Extensivos:** Desacoplamiento total entre las entidades de configuración de datos y el motor del planificador.
* **Encapsulación Rígida:** Restricción de acceso mediante visibilidad privada (`private`) en atributos clave con validaciones consistentes en mutadores.
* **Estructuras de Datos Eficientes:** Uso de colecciones `Set` para prevenir inconsistencies en las repeticiones semanales.

---

## 📁 Estructura de Directorios
```text
smart-alarm-system/
├── src/
│   ├── Alarm.java
│   ├── AlarmCategory.java
│   ├── AlarmManager.java
│   ├── AlarmScheduler.java
│   ├── Main.java
│   ├── MathChallenge.java
│   ├── SnoozeManager.java
│   └── SoundProfile.java
├── docs/
├── tests/
└── README.md