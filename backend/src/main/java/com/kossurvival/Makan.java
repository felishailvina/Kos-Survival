package com.kossurvival;

public class Makan extends Aktivitas {

    @Override
    public void eksekusi(Mahasiswa m) {

        m.setEnergi(m.getEnergi() + 20);
        m.setUang(m.getUang() - 15000);
    }

    @Override
    public String getNamaAktivitas() {
        return "Makan";
    }
}
