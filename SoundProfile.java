package src;

public class SoundProfile {
    private String trackName;
    private int volume;
    private boolean progressiveVolume;

    public SoundProfile(String trackName, int volume, boolean progressiveVolume) {
        this.trackName = trackName;
        setVolume(volume); // Usamos el setter para validar el volumen desde el constructor
        this.progressiveVolume = progressiveVolume;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        // Validación de rango de volumen (Evita estados incoherentes)
        if (volume < 0) {
            this.volume = 0;
        } else if (volume > 100) {
            this.volume = 100;
        } else {
            this.volume = volume;
        }
    }

    public boolean isProgressiveVolume() {
        return progressiveVolume;
    }

    public void setProgressiveVolume(boolean progressiveVolume) {
        this.progressiveVolume = progressiveVolume;
    }
}