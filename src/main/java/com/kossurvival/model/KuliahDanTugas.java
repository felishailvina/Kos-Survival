package com.kossurvival.model;

public class KuliahDanTugas extends Aktivitas {

    @Override
    public void eksekusi(Mahasiswa m) {
        m.setEnergi(m.getEnergi() - 20);
        m.setStres(m.getStres() + 15);
        m.setNilaiAkademik(m.getNilaiAkademik() + 10);
    }

    @Override
    public String getNamaAktivitas() {
        return "Kuliah dan Mengerjakan Tugas";
    }
}
