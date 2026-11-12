package com.kossurvival.model;

public class KerjaPartTime extends Aktivitas {

    @Override
    public void eksekusi(Mahasiswa m) {
        m.setUang(m.getUang() + 75000);
        m.setEnergi(m.getEnergi() - 25);
        m.setStres(m.getStres() + 10);
    }

    @Override
    public String getNamaAktivitas() {
        return "Kerja Part Time";
    }
}
