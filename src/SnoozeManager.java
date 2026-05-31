package src;

import java.time.LocalTime;

public class SnoozeManager {
    private final int snoozeDurationMinutes;
    private int snoozeCount;

    public SnoozeManager() {
        this.snoozeDurationMinutes = 5; // Duración predeterminada de posposición
        this.snoozeCount = 0;
    }

    // Calcula el momento exacto en el que volverá a sonar la alarma
    public LocalTime calculateNextSnooze(LocalTime currentTime) {
        snoozeCount++;
        LocalTime nextTime = currentTime.plusMinutes(snoozeDurationMinutes);
        System.out.println("⏳ [SNOOZE]: Alarma pospuesta por " + snoozeCount + "ª vez. Volverá a sonar a las " + nextTime);
        return nextTime;
    }

    public int getSnoozeCount() {
        return snoozeCount;
    }

    public void resetSnooze() {
        this.snoozeCount = 0;
    }
}