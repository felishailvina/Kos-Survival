#!/bin/bash
# ==============================================================================
# KOS SURVIVAL - RUNNER SCRIPT
# Game Simulasi Mahasiswa (Point & Click) Berbasis JavaFX & Pixel Art
# ==============================================================================

# Deteksi JAVA_HOME jika belum terdefinisi
if [ -z "$JAVA_HOME" ]; then
    if [ -d "/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home" ]; then
        export JAVA_HOME="/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home"
    elif [ -x "/usr/libexec/java_home" ]; then
        export JAVA_HOME=$(/usr/libexec/java_home)
    fi
fi

if [ -n "$JAVA_HOME" ]; then
    export PATH="$JAVA_HOME/bin:$PATH"
fi

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR" || exit 1

echo "========================================================"
echo " 🎓 KOS SURVIVAL - POINT & CLICK LIFE SIMULATION"
echo " Menjalankan Game JavaFX (Retro Pixel & Arcade Style)..."
echo "========================================================"

mvn -o javafx:run

