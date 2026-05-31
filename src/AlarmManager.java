package src;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AlarmManager {
    private final List<Alarm> alarms;
    private boolean vacationMode;

    public AlarmManager() {
        this.alarms = new ArrayList<>();
        this.vacationMode = false;
    }

    public void addAlarm(Alarm newAlarm) {
        // Validación avanzada obligatoria: Detección de conflictos inteligentes (menos de 3 minutos)
        for (Alarm existingAlarm : alarms) {
            if (existingAlarm.isActive() && hasScheduleOverlap(existingAlarm, newAlarm)) {
                long difference = Math.abs(ChronoUnit.MINUTES.between(existingAlarm.getTime(), newAlarm.getTime()));
                if (difference <= 3) {
                    System.out.println("[⚠️ ALERTA CONFLICTO]: La alarma '" + newAlarm.getLabel() + 
                        "' está muy cercana temporalmente (" + difference + " min) de '" + existingAlarm.getLabel() + "'.");
                }
            }
        }
        alarms.add(newAlarm);
    }

    public boolean deleteAlarm(UUID id) {
        return alarms.removeIf(alarm -> alarm.getId().equals(id));
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public boolean isVacationMode() {
        return vacationMode;
    }

    public void setVacationMode(boolean vacationMode) {
        this.vacationMode = vacationMode;
        System.out.println("[INFO]: Modo vacaciones " + (vacationMode ? "ACTIVADO" : "DESACTIVADO"));
    }

    private boolean hasScheduleOverlap(Alarm a1, Alarm a2) {
        if (a1.getRepetitionDays().isEmpty() || a2.getRepetitionDays().isEmpty()) {
            return true; 
        }
        for (DayOfWeek day : a1.getRepetitionDays()) {
            if (a2.getRepetitionDays().contains(day)) {
                return true;
            }
        }
        return false;
    }
}