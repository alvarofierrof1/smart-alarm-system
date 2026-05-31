package src;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Alarm {
    private final UUID id;
    private LocalTime time;
    private String label;
    private Set<DayOfWeek> repetitionDays;
    private SoundProfile sound;
    private AlarmCategory category;
    private boolean active;

    public Alarm(LocalTime time, String label, Set<DayOfWeek> repetitionDays, SoundProfile sound, AlarmCategory category) {
        this.id = UUID.randomUUID(); // Identificador único automático
        this.time = time;
        this.label = label;
        this.repetitionDays = repetitionDays != null ? new HashSet<>(repetitionDays) : new HashSet<>();
        this.sound = sound;
        this.category = category;
        this.active = true; // Por defecto nace activa
    }

    // Método de negocio para verificar si debe sonar un día específico
    public boolean isScheduledForDay(DayOfWeek day) {
        // Si el conjunto está vacío, asumimos que es una alarma de "un solo uso" (suena el día que coincida la hora)
        return repetitionDays.isEmpty() || repetitionDays.contains(day);
    }

    // Getters y Setters (Encapsulación)
    public UUID getId() { return id; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public Set<DayOfWeek> getRepetitionDays() { return repetitionDays; }
    public void setRepetitionDays(Set<DayOfWeek> repetitionDays) { this.repetitionDays = repetitionDays; }

    public SoundProfile getSound() { return sound; }
    public void setSound(SoundProfile sound) { this.sound = sound; }

    public AlarmCategory getCategory() { return category; }
    public void setCategory(AlarmCategory category) { this.category = category; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    
    public void toggleActive() { this.active = !this.active; }
}