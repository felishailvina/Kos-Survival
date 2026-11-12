package com.kossurvival;

public class MainGame extends Aktivitas {

    @Override
    public void eksekusi(Mahasiswa m) {
        m.setStres(m.getStres() - 30);
        m.setEnergi(m.getEnergi() - 10);
        m.setNilaiAkademik(m.getNilaiAkademik() - 5);
    }

    @Override
    public String getNamaAktivitas() {
        return "Bermain Game";
    }
}
