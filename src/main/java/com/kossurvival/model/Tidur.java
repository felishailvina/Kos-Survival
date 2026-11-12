package com.kossurvival.model;

public class Tidur extends Aktivitas {

    @Override
    public void eksekusi(Mahasiswa m) {
        m.setEnergi(m.getEnergi() + 50);
        m.setStres(m.getStres() - 15);
        m.setHariKe(m.getHariKe() + 1);
    }

    @Override
    public String getNamaAktivitas() {
        return "Tidur";
    }
}
