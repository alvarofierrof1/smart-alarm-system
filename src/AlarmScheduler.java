package src;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class AlarmScheduler {
    private final AlarmManager manager;

    public AlarmScheduler(AlarmManager manager) {
        this.manager = manager;
    }

    public void checkAlarms(LocalTime simulatedTime, DayOfWeek simulatedDay) {
        if (manager.isVacationMode()) {
            return; // Si estamos en vacaciones, el motor no hace sonar nada
        }

        for (Alarm alarm : manager.getAlarms()) {
            if (alarm.isActive() && alarm.isScheduledForDay(simulatedDay)) {
                if (alarm.getTime().getHour() == simulatedTime.getHour() &&
                    alarm.getTime().getMinute() == simulatedTime.getMinute()) {
                    triggerAlarm(alarm);
                }
            }
        }
    }

    private void triggerAlarm(Alarm alarm) {
        System.out.println("\n🔔 [¡RIIING!] Alarma sonando: " + alarm.getLabel().toUpperCase());
        System.out.println("🎵 Sonido: " + alarm.getSound().getTrackName() + " | Volumen: " + alarm.getSound().getVolume() + "%");
        System.out.println("📁 Categoría: " + alarm.getCategory());
        
        if (alarm.getSound().isProgressiveVolume()) {
            System.out.println("🌅 [MODO CIRCADIANO]: Incrementando volumen gradualmente...");
        }
    }
}