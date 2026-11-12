import java.util.ArrayList;

public class SistemSaveData {

    // ArrayList untuk menyimpan riwayat aktivitas
    private ArrayList<String> riwayatAktivitas;

    // Constructor
    public SistemSaveData() {
        riwayatAktivitas = new ArrayList<>();
    }

    // CREATE: menambahkan catatan aktivitas
    public void tambahLog(String catatan) {
        riwayatAktivitas.add(catatan);
        System.out.println("Log berhasil disimpan!");
    }

    // READ: menampilkan seluruh riwayat
    public void tampilkanRiwayat() {

        System.out.println("\n===== RIWAYAT AKTIVITAS =====");

        if (riwayatAktivitas.isEmpty()) {
            System.out.println("Belum ada aktivitas.");
            return;
        }

        for (int i = 0; i < riwayatAktivitas.size(); i++) {
            System.out.println((i + 1) + ". " + riwayatAktivitas.get(i));
        }

        System.out.println("=============================");
    }

    // UPDATE: mengubah catatan berdasarkan indeks
    public void ubahLog(int indeks, String catatanBaru) {

        if (indeks >= 0 && indeks < riwayatAktivitas.size()) {
            riwayatAktivitas.set(indeks, catatanBaru);
            System.out.println("Log berhasil diperbarui!");
        } else {
            System.out.println("Indeks log tidak ditemukan.");
        }
    }

    // DELETE: menghapus seluruh riwayat
    public void hapusSemuaLog() {
        riwayatAktivitas.clear();
        System.out.println("Seluruh riwayat berhasil dihapus!");
    }

    // Mengambil jumlah catatan
    public int getJumlahLog() {
        return riwayatAktivitas.size();
    }
}
