package src;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println("====================================================");
        System.out.println("🤖 SMART ALARM SYSTEM - SIMULACIÓN DE LOGICA DE NEGOCIO");
        System.out.println("====================================================\n");

        // 1. Inicialización de Gestores
        AlarmManager manager = new AlarmManager();
        AlarmScheduler scheduler = new AlarmScheduler(manager);
        SnoozeManager snoozeManager = new SnoozeManager();
        MathChallenge mathChallenge = new MathChallenge();
        Scanner scanner = new Scanner(System.in);

        // 2. Configuración de Días de Repetición (Lunes a Viernes)
        Set<DayOfWeek> weekdays = new HashSet<>();
        weekdays.add(DayOfWeek.MONDAY);
        weekdays.add(DayOfWeek.TUESDAY);
        weekdays.add(DayOfWeek.WEDNESDAY);
        weekdays.add(DayOfWeek.THURSDAY);
        weekdays.add(DayOfWeek.FRIDAY);

        // 3. Crear Perfiles de Sonido
        SoundProfile corporateSound = new SoundProfile("Echoes of Morning", 80, true); // Circadiano activo
        SoundProfile sportSound = new SoundProfile("Eye of the Tiger", 95, false);

        // 4. Creación e Inserción de Alarmas
        System.out.println("--- [PASO 1]: Registrando alarmas del sistema ---");
        Alarm alarm1 = new Alarm(LocalTime.of(7, 0), "Despertador Trabajo", weekdays, corporateSound, AlarmCategory.WORK);
        manager.addAlarm(alarm1);
        
        // 5. DEMOSTRACIÓN AVANZADA: Detección inteligente de conflictos horarias (Mismo día, a 2 min de diferencia)
        Alarm alarmConflictive = new Alarm(LocalTime.of(7, 2), "Entrenamiento Gym", weekdays, sportSound, AlarmCategory.SPORT);
        manager.addAlarm(alarmConflictive); 
        System.out.println();

        // 6. SIMULACIÓN DEL MOTOR DE TIEMPO (Suena a las 07:00 AM un Lunes)
        System.out.println("--- [PASO 2]: Iniciando Ticker del Reloj (Simulación Lunes 07:00 AM) ---");
        LocalTime simulatedClock = LocalTime.of(7, 0);
        DayOfWeek simulatedDay = DayOfWeek.MONDAY;
        
        // El Scheduler revisa si hay alguna alarma para esta hora y día exactos
        scheduler.checkAlarms(simulatedClock, simulatedDay);

        // 7. FLUJO INTERACTIVO DE DETENCIÓN DE ALARMA MEDIANTE RETO MATEMÁTICO
        System.out.println("\n--- [PASO 3]: La alarma requiere resolver un Reto Matemático para apagarse ---");
        mathChallenge.generateChallenge();
        boolean challengeSolved = false;

        while (!challengeSolved) {
            System.out.println("\n📢 PANTALLA: " + mathChallenge.getChallengeExpression());
            System.out.print("Introduce tu respuesta (O teclea '0' para solicitar SNOOZE/Posponer): ");
            
            try {
                int userAnswer = scanner.nextInt();
                
                if (userAnswer == 0) {
                    // El usuario pide posponer la alarma usando el SnoozeManager
                    LocalTime newDisparalTime = snoozeManager.calculateNextSnooze(simulatedClock);
                    System.out.println("💤 Sistema en silencio temporal.");
                    break; // Cortamos la simulación aquí ya que fue pospuesta con éxito
                } else {
                    // Verificamos si la respuesta matemática es la correcta
                    challengeSolved = mathChallenge.verifyAnswer(userAnswer);
                }
            } catch (Exception e) {
                System.out.println("⚠️ Por favor, introduce un número entero válido.");
                scanner.next(); // Limpiar el búfer del scanner
            }
        }

        // 8. DEMOSTRACIÓN AVANZADA: Modo Vacaciones activo
        System.out.println("\n--- [PASO 4]: Demostración de Modo Vacaciones ---");
        manager.setVacationMode(true);
        System.out.println("Simulando que el reloj avanza al siguiente día a las 07:00 AM...");
        // Aunque coincida la hora exacta, al estar en modo vacaciones el Scheduler no imprimirá nada
        scheduler.checkAlarms(LocalTime.of(7, 0), DayOfWeek.TUESDAY);
        System.out.println("🏁 [FIN DE LA SIMULACIÓN]: Ninguna alarma sonó gracias al Modo Vacaciones activo.");
        System.out.println("====================================================");
        
        scanner.close();
    }
}
