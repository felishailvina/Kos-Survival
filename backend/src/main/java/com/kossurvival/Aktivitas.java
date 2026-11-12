package com.kossurvival;

public abstract class Aktivitas {

    // Method abstrak yang wajib diimplementasikan
    // oleh seluruh kelas turunannya
    public abstract void eksekusi(Mahasiswa m);

    // Nama aktivitas untuk kebutuhan pencatatan log
    public abstract String getNamaAktivitas();
}
