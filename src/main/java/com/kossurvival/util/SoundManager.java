package com.kossurvival.util;

/**
 * Sound Manager telah dinonaktifkan sepenuhnya sesuai permintaan pengguna (Silent Mode).
 */
public class SoundManager {

    private static boolean soundEnabled = false;

    public static void playTone(double frequency, int durationMs, double volume) {
        // Suara dinonaktifkan
    }

    public static void playHoverSound() {
        // Suara dinonaktifkan
    }

    public static void playClickSound() {
        // Suara dinonaktifkan
    }

    public static void playCoinSound() {
        // Suara dinonaktifkan
    }

    public static void playGameOverSound() {
        // Suara dinonaktifkan
    }

    public static void setSoundEnabled(boolean enabled) {
        soundEnabled = enabled;
    }

    public static boolean isSoundEnabled() {
        return false;
    }
}

