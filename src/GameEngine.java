public class GameEngine {

    // Objek utama permainan
    private Mahasiswa mahasiswa;
    private SistemSaveData saveData;

    // Status ending permainan
    private StatusEnding statusEnding;

    // Constructor: inisialisasi permainan
    public GameEngine() {
        mahasiswa = new Mahasiswa();
        saveData = new SistemSaveData();
        statusEnding = StatusEnding.BERLANJUT;

        System.out.println("================================");
        System.out.println("       KOS SURVIVAL DIMULAI     ");
        System.out.println("================================");
        System.out.println("Selamat datang di kehidupan kos!");
    }

    // Menjalankan aktivitas menggunakan polimorfisme
    
    public void jalankanAktivitas(Aktivitas aktivitas) {
    
        // Cegah aktivitas setelah permainan berakhir
        if (statusEnding != StatusEnding.BERLANJUT) {
            System.out.println("Permainan sudah berakhir!");
            return;
        }
    
        // Simpan hari sebelum aktivitas dijalankan
        int hariAktivitas = mahasiswa.getHariKe();
    
        // Jalankan aktivitas sesuai objek turunannya
        aktivitas.eksekusi(mahasiswa);
    
        // Simpan catatan berdasarkan hari aktivitas dilakukan
        String catatan = "Hari ke-" + hariAktivitas
                + " : " + aktivitas.getNamaAktivitas();
    
        saveData.tambahLog(catatan);
    
        // Periksa kondisi ending setelah aktivitas selesai
        statusEnding = cekStatusEnding();
    
        // Tampilkan status terbaru
        mahasiswa.tampilkanStatus();
    
        // Tampilkan ending jika permainan selesai
        if (statusEnding != StatusEnding.BERLANJUT) {
            tampilkanEnding();
        }
    }    

    // Memeriksa empat kondisi ending
    public StatusEnding cekStatusEnding() {

        // KALAH 1: Tipes
        if (mahasiswa.getEnergi() <= 0 ||
            mahasiswa.getStres() >= 100) {

            return StatusEnding.KALAH_TIPES;
        }

        // KALAH 2: Diusir dari kos
        if (mahasiswa.getUang() <= 0 &&
            mahasiswa.getHariKe() < 30) {

            return StatusEnding.KALAH_DIUSIR;
        }

        // KALAH 3: Drop Out
        if (mahasiswa.getNilaiAkademik() <= 0) {

            return StatusEnding.KALAH_DROP_OUT;
        }

        // MENANG: Cumlaude
        if (mahasiswa.getHariKe() == 30 &&
            mahasiswa.getUang() > 0 &&
            mahasiswa.getEnergi() > 0 &&
            mahasiswa.getNilaiAkademik() >= 75) {

            return StatusEnding.MENANG_CUMLAUDE;
        }

        // Jika tidak memenuhi kondisi ending
        return StatusEnding.BERLANJUT;
    }

    // Menampilkan pesan berdasarkan ending
    public void tampilkanEnding() {

        System.out.println("\n================================");
        System.out.println("         HASIL PERMAINAN        ");
        System.out.println("================================");

        switch (statusEnding) {

            case KALAH_TIPES:
                System.out.println("ENDING: KALAH - TIPES");
                System.out.println("Kondisi tubuhmu tidak sanggup bertahan.");
                break;

            case KALAH_DIUSIR:
                System.out.println("ENDING: KALAH - DIUSIR");
                System.out.println("Uangmu habis dan kamu diusir dari kos.");
                break;

            case KALAH_DROP_OUT:
                System.out.println("ENDING: KALAH - DROP OUT");
                System.out.println("Nilai akademikmu mencapai nol.");
                break;

            case MENANG_CUMLAUDE:
                System.out.println("ENDING: MENANG - CUMLAUDE");
                System.out.println("Selamat! Kamu berhasil bertahan 30 hari!");
                break;

            default:
                System.out.println("Permainan masih berlangsung.");
                break;
        }

        System.out.println("================================");
    }

    // Reset permainan untuk memulai dari awal
    public void resetGame() {

        mahasiswa = new Mahasiswa();
        saveData.hapusSemuaLog();
        statusEnding = StatusEnding.BERLANJUT;

        System.out.println("Permainan berhasil di-reset!");
    }

    // Getter untuk integrasi dengan JavaFX
    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public SistemSaveData getSaveData() {
        return saveData;
    }

    public StatusEnding getStatusEnding() {
        return statusEnding;
    }
}
