# 🤖 Smart Alarm System - Lógica de Negocio OO

Este repositorio contiene el diseño y la implementación modular de la lógica interna para un sistema de alarma inteligente avanzado, emulando las capacidades operativas de los teléfonos inteligentes modernos. Desarrollado exclusivamente en **Java** bajo un entorno sin interfaz gráfica.

---

## 🎯 Objetivos del Proyecto
* **Análisis de Requisitos Extensivos:** Desacoplamiento total entre las entidades de configuración de datos y el motor del planificador.
* **Encapsulación Rígida:** Restricción de acceso mediante visibilidad privada (`private`) en atributos clave con validaciones consistentes en mutadores.
* **Estructuras de Datos Eficientes:** Uso de colecciones `Set` para prevenir inconsistencias en las repeticiones semanales.

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

classDiagram
    class Alarm {
        -UUID id
        -LocalTime time
        -String label
        -Set~DayOfWeek~ repetitionDays
        -SoundProfile sound
        -AlarmCategory category
        -boolean active
        +Alarm(LocalTime, String, Set~DayOfWeek~, SoundProfile, AlarmCategory)
        +isScheduledForDay(DayOfWeek) boolean
        +toggleActive() void
    }
    class AlarmManager {
        -List~Alarm~ alarms
        -boolean vacationMode
        +addAlarm(Alarm) void
        +deleteAlarm(UUID) boolean
        +setVacationMode(boolean) void
        -hasScheduleOverlap(Alarm, Alarm) boolean
    }
    class AlarmScheduler {
        -AlarmManager manager
        +checkAlarms(LocalTime, DayOfWeek) void
        -triggerAlarm(Alarm) void
    }
    class SnoozeManager {
        -int snoozeDurationMinutes
        -int snoozeCount
        +calculateNextSnooze(LocalTime) LocalTime
        +resetSnooze() void
    }
    class MathChallenge {
        -int correctAnswer
        -String challengeExpression
        +generateChallenge() void
        +verifyAnswer(int) boolean
    }
    class SoundProfile {
        -String trackName
        -int volume
        -boolean progressiveVolume
        +setVolume(int) void
    }
    class AlarmCategory <<enumeration>> {
        WORK
        STUDY
        SPORT
        MEDICINE
    }

    AlarmManager "1" *-- "0..*" Alarm : administra
    Alarm --> SoundProfile : usa
    Alarm --> AlarmCategory : clasifica
    AlarmScheduler --> AlarmManager : consulta

Principio de Responsabilidad Única (SOLID - SRP): Se ha estructurado el dominio aislando responsabilidades. La clase Alarm funciona como una entidad pura contenedora de estado. La gestión del almacenamiento y el algoritmo de control de colisiones horarias quedan relegados a AlarmManager. A su vez, la evaluación cronológica se desacopla en AlarmScheduler, impidiendo que las entidades de datos dependan del motor de ejecución temporal.

Relaciones y Ciclo de Vida: Se aplica una relación de composición (1 a 0..*) desde AlarmManager hacia Alarm mediante el operador *--. Esto responde a que las alarmas carecen de sentido operativo en el dominio si el gestor de alarmas central es destruido. Las entidades de audio (SoundProfile) y clasificación (AlarmCategory) se vinculan por asociación simple, modularizando de forma correcta propiedades estéticas y taxonómicas.

Encapsulación Defensiva: Todos los atributos del sistema usan visibilidad privada (-). Los mutadores públicos (+) incluyen reglas de control perimetral; por ejemplo, el método SoundProfile.setVolume(int) valida los límites previniendo que el volumen adopte valores incoherentes fuera del rango entre 0 y 100%.

graph LR
    Usuario((Usuario))
    
    Usuario --> CU1(Configurar Nueva Alarma)
    Usuario --> CU2(Desactivar Alarma Activa)
    Usuario --> CU3(Posponer Alarma - Snooze)
    Usuario --> CU4(Activar Modo Vacaciones)
    
    CU2 -.->|include| CU5(Resolver Reto Matemático)
    CU3 -.->|include| CU5

Relaciones de Inclusión (Include): Se justifica la relación de inclusión (include) hacia el caso de uso secundario "Resolver Reto Matemático" debido a que las especificaciones de comportamiento del sistema imponen de manera estricta y automatizada superar el desafío aritmético antes de permitir que una alerta sonora activa sea silenciada permanentemente o pospuesta temporalmente.
