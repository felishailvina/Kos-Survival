package com.kossurvival;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Inisialisasi game
        GameEngine game = new GameEngine();

        boolean berjalan = true;

        while (berjalan) {

            // Berhenti jika sudah mendapatkan ending
            if (game.getStatusEnding() != StatusEnding.BERLANJUT) {
                break;
            }

            System.out.println("\n===== MENU KOS SURVIVAL =====");
            System.out.println("1. Kuliah dan Mengerjakan Tugas");
            System.out.println("2. Kerja Part Time");
            System.out.println("3. Bermain Game");
            System.out.println("4. Tidur");
            System.out.println("5. Makan");
            System.out.println("6. Lihat Riwayat");
            System.out.println("7. Lihat Status");
            System.out.println("8. Reset Permainan");
            System.out.println("0. Keluar");
            System.out.print("Pilih aktivitas: ");

            int pilihan;

            try {
                pilihan = Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Masukkan angka yang valid!");
                continue;
            }

            // Memilih aktivitas
            switch (pilihan) {

                case 1:
                    game.jalankanAktivitas(new KuliahDanTugas());
                    break;

                case 2:
                    game.jalankanAktivitas(new KerjaPartTime());
                    break;

                case 3:
                    game.jalankanAktivitas(new MainGame());
                    break;

                case 4:
                    game.jalankanAktivitas(new Tidur());
                    break;

                case 5:
                    game.jalankanAktivitas(new Makan());
                    break;

                case 6:
                    game.getSaveData().tampilkanRiwayat();
                    break;
                    
                case 7:
                    game.getMahasiswa().tampilkanStatus();
                    break;
                    
                case 8:
                    game.resetGame();
                    break;
                
                case 0:
                    berjalan = false;
                    System.out.println("Terima kasih sudah bermain!");
                    break;

                default:
                    System.out.println("Pilihan tidak tersedia!");
                    break;
            }
        }

        input.close();
    }
}
