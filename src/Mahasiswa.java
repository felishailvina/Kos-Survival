public class Mahasiswa {

    // Atribut private untuk enkapsulasi
    private int uang;
    private int energi;
    private int stres;
    private int nilaiAkademik;
    private int hariKe;

    // Constructor: kondisi awal permainan
    public Mahasiswa() {
        this.uang = 1500000;
        this.energi = 100;
        this.stres = 0;
        this.nilaiAkademik = 50;
        this.hariKe = 1;
    }

    // Membatasi nilai indikator antara 0-100
    private int validasiIndikator(int nilai) {
        return Math.max(0, Math.min(100, nilai));
    }

    // Getter dan setter uang
    public int getUang() {
        return uang;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    // Getter dan setter energi
    public int getEnergi() {
        return energi;
    }

    public void setEnergi(int energi) {
        this.energi = validasiIndikator(energi);
    }

    // Getter dan setter stres
    public int getStres() {
        return stres;
    }

    public void setStres(int stres) {
        this.stres = validasiIndikator(stres);
    }

    // Getter dan setter nilai akademik
    public int getNilaiAkademik() {
        return nilaiAkademik;
    }

    public void setNilaiAkademik(int nilaiAkademik) {
        this.nilaiAkademik = validasiIndikator(nilaiAkademik);
    }

    // Getter dan setter hari
    public int getHariKe() {
        return hariKe;
    }

    public void setHariKe(int hariKe) {
        this.hariKe = Math.max(1, hariKe);
    }

    // Menampilkan seluruh status pemain
    public void tampilkanStatus() {
        System.out.println("\n===== STATUS MAHASISWA =====");
        System.out.println("Hari             : " + hariKe);
        System.out.println("Uang             : Rp" + uang);
        System.out.println("Energi           : " + energi);
        System.out.println("Stres            : " + stres);
        System.out.println("Nilai Akademik   : " + nilaiAkademik);
        System.out.println("============================");
    }
}
