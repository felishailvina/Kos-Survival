# 🎓 KOS SURVIVAL - Proyek Akhir PBO
**Sebuah game simulasi manajemen waktu dan sumber daya mahasiswa (Point-and-Click Slice of Life)**

## 📖 Deskripsi Proyek
**Kos Survival** adalah game simulasi kehidupan mahasiswa bergaya *pixel art* yang terinspirasi dari **Citampi Story**. Namun, untuk mengoptimalkan kinerja aplikasi pada perangkat berspesifikasi rendah (Core i3) dan memfokuskan penilaian pada arsitektur Pemrograman Berorientasi Objek (PBO), game ini **TIDAK** menggunakan mekanik berjalan (eksplorasi manual). 

Sebagai gantinya, game ini menggunakan mekanik **Point-and-Click (Instan)**. Pemain menekan tombol aksi (misal: "Ke Kampus"), dan layar akan langsung berpindah (*transition*) ke lokasi yang dituju sambil mengeksekusi logika perubahan status (Uang, Energi, Stres, Nilai Akademik) di belakang layar.

## 🛠️ Spesifikasi Teknis
*   **Bahasa Pemrograman:** Java (Minimum Java 11)
*   **GUI / Antarmuka:** JavaFX
*   **Build Tool / Package Manager:** Maven
*   **Version Control:** Git & GitHub (Menerapkan sistem *Feature Branching*)
*   **Style Visual:** Pixel Art (Dibuat sendiri atau *generate* via AI seperti Leonardo.ai / Bing Image Creator)

---

## 🎮 Mekanisme Permainan (Gameplay)
1.  **Sistem Waktu:** Permainan berjalan hari demi hari (Misal: Hari 1 sampai Hari 30). Waktu akan berganti setiap kali pemain memilih aktivitas "Tidur".
2.  **Manajemen Status:** Pemain harus menyeimbangkan 4 indikator utama (Maksimal 100, Minimal 0):
    *   ⚡ **Energi:** Berkurang saat beraktivitas, bertambah saat makan/tidur.
    *   💰 **Uang:** Berkurang untuk biaya hidup, bertambah dari kerja part-time.
    *   🤯 **Stres:** Bertambah saat belajar/kerja, berkurang saat main game/tidur.
    *   🎓 **Nilai Akademik:** Naik jika sering belajar, turun jika sering main.
3.  **Aktivitas (Point & Click):** Pemain berada di Kamar Kos (Main Hub). Di layar terdapat beberapa tombol menu (Kampus, Minimarket/Kerja, Warung Makan, Main Game, Kasur). Mengklik tombol akan mengubah *background* secara instan dan memicu interaksi.

---

## 🎯 Pemenuhan Syarat Penilaian PBO (Wajib)
Proyek ini dirancang agar lolos kriteria ketat dosen PBO:
1.  **Encapsulation (Enkapsulasi):** Diterapkan pada kelas `Mahasiswa`. Semua indikator (Uang, Energi, dll) bersifat `private` dan hanya bisa diubah melalui `getter/setter` yang memiliki validasi batas angka.
2.  **Abstraction (Abstraksi):** Pembuatan *abstract class* `Aktivitas` dengan *method* abstrak `eksekusi(Mahasiswa m)`.
3.  **Inheritance (Pewarisan):** Kelas seperti `AktivitasKuliah` dan `AktivitasKerja` mewarisi kelas `Aktivitas`.
4.  **Polymorphism (Polimorfisme):** Setiap kelas turunan bebas melakukan *Override* pada method `eksekusi()` dengan rumus kalkulasi yang berbeda-beda.
5.  **CRUD (Create, Read, Update, Delete):** Diterapkan pada fitur **"Buku Harian/Log Aktivitas"** menggunakan `ArrayList`. 
    *   *(Create: Catat aksi. Read: Tampilkan di UI. Update: Modifikasi status saat aksi berjalan. Delete: Hapus riwayat jika game di-restart).*

---

## 🏆 Multiple Endings (Kondisi Tamat)
Setiap selesai melakukan tindakan, sistem akan mengecek kondisi karakter. Terdapat 4 akhir cerita:
1.  **Kalah 1 (Tipes):** Jika `Energi <= 0` atau `Stres >= 100`. (Masuk Rumah Sakit).
2.  **Kalah 2 (Diusir):** Jika `Uang <= 0` sebelum hari terakhir. (Ngemper bawa kerdus).
3.  **Kalah 3 (Drop Out):** Jika `Nilai Akademik <= 0`. (Dapat surat DO).
4.  **Menang (Cumlaude):** Berhasil bertahan hidup sampai Hari ke-30 dengan uang dan energi yang cukup, serta nilai di atas rata-rata. (Lulus pakai Toga).

---

## 👥 Pembagian Tugas Kelompok (3 Orang)

### 🧑‍💻 Orang 1: Programmer OOP & Logika Inti (Backend)
*   **Fokus:** Menulis kelas Java murni, tidak menyentuh kode JavaFX.
*   **Tugas:** 
    *   Membuat *blueprint* `Mahasiswa` (Enkapsulasi).
    *   Membuat kerangka `Aktivitas` (Abstraksi & Polimorfisme).
    *   Membuat sistem CRUD untuk `LogHistory` (Buku Harian).
    *   Membuat `GameEngine` untuk mendeteksi 4 kondisi *Ending*.
*   **Branch Git:** `feature/core-logic`

### 🧑‍🎨 Orang 2: UI/UX & Mekanik JavaFX (Frontend)
*   **Fokus:** Menghubungkan logika Java buatan Orang 1 ke antarmuka visual.
*   **Tugas:**
    *   Mendesain *layout* di JavaFX (menggunakan `StackPane` / `GridPane`).
    *   Membuat dan menyetel warna *Progress Bar* (untuk Energi, Stres, Nilai).
    *   Membuat *event handler* (aksi ketika tombol diklik).
    *   Menampilkan data `LogHistory` (Read CRUD) ke dalam bentuk `ListView` atau teks bergulir di layar.
*   **Branch Git:** `feature/ui-layout`

### 🎬 Orang 3: Manajer Aset Pixel Art & Integrasi Scene
*   **Fokus:** Estetika visual, transisi antar layar, dan audio (jika ada).
*   **Tugas:**
    *   Mencari/Membuat (*prompt* AI) gambar *Background* (Kamar, Kampus, Warteg, Rumah Sakit).
    *   Mencari/Membuat desain *Sprite* Karakter, UI Tombol, dan Ikon (Uang, Petir).
    *   Menulis kode JavaFX untuk mengatur perpindahan latar belakang secara instan saat tombol diklik.
    *   Mengurus transisi perpindahan dari "Main Menu" -> "Gameplay" -> "Result Screen (Endings)".
*   **Branch Git:** `feature/assets-scenes`

---
*Proyek ini diinisiasi menggunakan Maven. Jangan lupa lakukan `git pull` setiap kali akan mulai bekerja agar terhindar dari conflict!*