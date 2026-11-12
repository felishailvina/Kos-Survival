package com.kossurvival.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Utility untuk membuat aset Pixel Art secara prosedural (tanpa dependensi file luar)
 * Memastikan estetika Retro Pixel Art ala Super Mario Bros, Citampi Stories,
 * dan Arcade Cabinet 80-an dengan ketajaman sempurna (pixel-crisp).
 */
public class PixelArtRenderer {

    // Helper untuk menggambar grid string matriks pixel
    public static Image createImageFromPattern(String[] pattern, java.util.Map<Character, Color> colorMap, int scale) {
        int height = pattern.length;
        int maxWidth = 0;
        for (String s : pattern) {
            if (s.length() > maxWidth) maxWidth = s.length();
        }
        WritableImage img = new WritableImage(maxWidth * scale, height * scale);
        PixelWriter pw = img.getPixelWriter();

        for (int y = 0; y < height; y++) {
            String row = pattern[y];
            int rowLen = row.length();
            for (int x = 0; x < maxWidth; x++) {
                if (x >= rowLen) continue;
                char c = row.charAt(x);
                Color color = colorMap.getOrDefault(c, Color.TRANSPARENT);
                if (color != null && !color.equals(Color.TRANSPARENT)) {
                    for (int dy = 0; dy < scale; dy++) {
                        for (int dx = 0; dx < scale; dx++) {
                            pw.setColor(x * scale + dx, y * scale + dy, color);
                        }
                    }
                }
            }
        }
        return img;
    }

    // 1. Mario Super Mushroom (Jamur Merah)
    public static Image createMushroom(int scale) {
        String[] pattern = {
            ".....RRRRRR.....",
            "...RRRRRRRRRR...",
            "..RRWWWRRRWWWR..",
            ".RRWWWWWRWWWWWR.",
            ".RRWWWWWRWWWWWR.",
            "RRRWWWWWRWWWWWRR",
            "RRRRWWWRRRWWWRRR",
            "RRRRRRRRRRRRRRRR",
            "..KKKKKKKKKKKK..",
            "..KWWKWWWWKWWK..",
            "..KWKBWWWWKBWK..",
            "..KWKBWWWWKBWK..",
            "..KWWKWWWWKWWK..",
            "..KWWWWWWWWWWK..",
            "...KKKKKKKKKK...",
            "................"
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('R', Color.web("#e52521")); // Mario Red
        map.put('W', Color.web("#ffffff")); // White
        map.put('K', Color.web("#fcd8a8")); // Skin/cream stem
        map.put('B', Color.web("#000000")); // Black eye
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 2. Mario Question Block [?]
    public static Image createQuestionBlock(int scale, boolean flash) {
        String[] pattern = {
            "YYYYYYYYYYYYYYYY",
            "YWWWWWWWWWWWWWWY",
            "YWYYYYYYYYYYYYDY",
            "YWY..KKKKKK..YDY",
            "YWY.KK....KK.YDY",
            "YWY.....KKKK.YDY",
            "YWY....KKKK..YDY",
            "YWY....KKKK..YDY",
            "YWY....KKKK..YDY",
            "YWY..........YDY",
            "YWY....KKKK..YDY",
            "YWY....KKKK..YDY",
            "YWY..........YDY",
            "YWDDDDDDDDDDDDDY",
            "YDDDDDDDDDDDDDDY",
            "YYYYYYYYYYYYYYYY"
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        Color mainYellow = flash ? Color.web("#fff0a0") : Color.web("#fca010");
        map.put('Y', Color.web("#804000")); // Border brown
        map.put('W', Color.web("#ffe070")); // Highlight yellow
        map.put('D', Color.web("#b86800")); // Shadow brown-orange
        map.put('K', Color.web("#402000")); // Question mark dark
        map.put('.', mainYellow);

        return createImageFromPattern(pattern, map, scale);
    }

    // 3. Kucing Belang Tiga / Calico Cat (Kucing Kampung Citampi Story)
    public static Image createCat(int scale, int frame) {
        String tail1 = frame % 2 == 0 ? "..." : ".O.";
        String tail2 = frame % 2 == 0 ? "..O" : ".O.";

        String[] pattern = {
            "....................",
            "...OO......BB.......",
            "..OWWO....BWWB......",
            "..OWWWO..BWWWB......",
            "..OWWWWWWWWWWB......",
            "..OWKWOWWWKBWB......",
            "..OWKWOWWWKBWB......",
            "...OWWWWWWWWPO......",
            "...OWWPWWWWWO.......",
            "....WWWWWWWWO.......",
            "...OWWWWWWWWW.......",
            "..OOWWBBWWWWWO......",
            ".OOWWWBBWWWWWO......",
            ".OOWWWWWWWWWWO.." + tail1,
            ".OOWWWWWWWWWWO.." + tail2,
            "..OOWWBBWWWWWO.O....",
            "...WWWWWWWWWWOO.....",
            "...WW.WW...WW.......",
            "...KK.KK...KK.......",
            "...................."
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('W', Color.web("#f5f5f5")); // Putih bulu
        map.put('O', Color.web("#e67e22")); // Oranye Calico
        map.put('B', Color.web("#2c3e50")); // Hitam belang
        map.put('K', Color.web("#111111")); // Mata hitam
        map.put('P', Color.web("#ff9999")); // Hidung pink
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 4. Karakter Mahasiswa Anak Kos (Inspirasi Citampi & Karakter di foto referensi)
    public static Image createStudent(int scale, int frame) {
        int footOffset = (frame % 2 == 0) ? 0 : 1;
        String leg1 = (footOffset == 0) ? "....BBDD..BBDD.." : ".....BBD..BBD...";
        String leg2 = (footOffset == 0) ? "....BBDD..BBDD.." : "....BBD....BBD..";

        String[] pattern = {
            ".....HHHHHH.....",
            "....HHHHHHHHH...",
            "...HHHHHHHHHHH..",
            "...HHSSSHHHSS...",
            "...HSSKSSKSSS...",
            "....SSSSSSSSS...",
            "....SSSPPPSSS...",
            ".....SSSSSSS....",
            "....CCCCCCCC....",
            "...CCCCCCCCCC...",
            "...CCCGGGCCCC...",
            "..ACCCGGGCCCCA..",
            "..ACCCGGGCCCCA..",
            "..ACCCCCCCCCCA..",
            "...CCCCCCCCCC...",
            "....BB....BB....",
            leg1,
            leg2,
            "....SS....SS....",
            "....KK....KK...."
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('H', Color.web("#8b4513")); // Rambut coklat acak-acakan
        map.put('S', Color.web("#ffd1a4")); // Warna kulit muka
        map.put('K', Color.web("#1f2421")); // Mata hitam
        map.put('P', Color.web("#e08080")); // Bibir/pipi
        map.put('C', Color.web("#2980b9")); // Baju biru kasual (sesuai foto referensi)
        map.put('G', Color.web("#ffffff")); // Kaos dalaman putih
        map.put('A', Color.web("#ffd1a4")); // Tangan
        map.put('B', Color.web("#2c3e50")); // Celana jeans gelap
        map.put('D', Color.web("#1a252f")); // Bayangan jeans
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 5. Koin Emas Pixel (Mario Coin)
    public static Image createCoin(int scale, int frame) {
        String[] pattern;
        int step = frame % 4;
        if (step == 0) {
            pattern = new String[]{
                "...YYYYYY...",
                "..YWWWWWWY..",
                ".YWYYYYYYWY.",
                ".YWY.$$.YWY.",
                "YWY..$$.YWY.",
                "YWY..$$.YWY.",
                "YWY..$$.YWY.",
                "YWY..$$.YWY.",
                ".YWY.$$.YWY.",
                ".YWYYYYYYWY.",
                "..YWWWWWWY..",
                "...YYYYYY..."
            };
        } else if (step == 1 || step == 3) {
            pattern = new String[]{
                "....YYYY....",
                "...YWWWWY...",
                "..YWYYYYWY..",
                "..YW.$.YWY..",
                "..YW.$.YWY..",
                "..YW.$.YWY..",
                "..YW.$.YWY..",
                "..YW.$.YWY..",
                "..YW.$.YWY..",
                "..YWYYYYWY..",
                "...YWWWWY...",
                "....YYYY...."
            };
        } else {
            pattern = new String[]{
                ".....YY.....",
                "....YWWY....",
                "....YYYY....",
                "....YYYY....",
                "....YYYY....",
                "....YYYY....",
                "....YYYY....",
                "....YYYY....",
                "....YYYY....",
                "....YYYY....",
                "....YWWY....",
                ".....YY....."
            };
        }
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('Y', Color.web("#c47e00")); // Golden outline
        map.put('W', Color.web("#ffeb60")); // Bright gold highlight
        map.put('$', Color.web("#e69500")); // Dollar/sign dark gold
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 6. Awan Pixel (Super Mario Cloud)
    public static Image createCloud(int scale) {
        String[] pattern = {
            "........WWWWWWWW........",
            ".....WWWWWWWWWWWWWW.....",
            "..WWWWWWWWWWWWWWWWWWWW..",
            ".WWWWWWWWWWWWWWWWWWWWWW.",
            "WWWWWWWWWWWWWWWWWWWWWWWW",
            "WWWWWWWWWWWWWWWWWWWWWWWW",
            "WWWWWWWWWWWWWWWWWWWWWWWW",
            ".SSSSSSSSSSSSSSSSSSSSSS.",
            "..SSSSSSSSSSSSSSSSSSSS.."
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('W', Color.web("#ffffff")); // Putih awan
        map.put('S', Color.web("#cde8f5")); // Bayangan biru awan
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 7. Bola Basket Pixel (Dekorasi sudut kanan atas di foto referensi)
    public static Image createBasketball(int scale) {
        String[] pattern = {
            ".....OOOOO.....",
            "...OOBBBOOOO...",
            "..OOBOOOBOOOO..",
            ".OOBOOOOOBOOOO.",
            ".OOBBBBBBBBOOO.",
            "OOBOOOOOOOOBOOO",
            "OOBOOOOOOOOBOOO",
            "OOBBBBBBBBBBBOO",
            "OOBOOOOOOOOBOOO",
            "OOBOOOOOOOOBOOO",
            ".OOBBBBBBBBOOO.",
            ".OOBOOOOOBOOOO.",
            "..OOBOOOBOOOO..",
            "...OOBBBOOOO...",
            ".....OOOOO....."
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('O', Color.web("#e65100")); // Orange bola
        map.put('B', Color.web("#212121")); // Garis hitam bola
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 8. Bola Voli Pixel (Dekorasi sudut kiri bawah di foto referensi)
    public static Image createVolleyball(int scale) {
        String[] pattern = {
            ".....YYYYY.....",
            "...YYWWWWYYYY..",
            "..YYWWBWWYYYYY.",
            ".YYWWBBWWYYYYYY",
            ".YYWWBWWBBBBBYY",
            "YYWWBBWWBBBBBBY",
            "WWWWBBWWBBBBBBY",
            "WWWWBBWWBBBBBBY",
            "WWWWWWWWBBBBBBY",
            "WWWWWWWWBBBBBBY",
            ".WWWWWWWBBBBBBY",
            ".WWWWWWWBBBBBBY",
            "..WWWWWWBBBBBY.",
            "...WWWWWYYYYY..",
            ".....YYYYY....."
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('Y', Color.web("#fbc02d")); // Kuning
        map.put('W', Color.web("#ffffff")); // Putih
        map.put('B', Color.web("#1976d2")); // Biru
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 9. Piala Emas & Hati Merah (Dekorasi sudut kanan bawah di foto referensi)
    public static Image createTrophyWithHeart(int scale) {
        String[] pattern = {
            "........HH.HH...",
            ".......HHHHHHH..",
            ".......HHHHHHH..",
            "........HHHHH...",
            ".........HHH....",
            "...YYYYYYYYY....",
            "..YWWWWWWWWWY...",
            ".YWWYYYYYYWWWY..",
            ".YWWYYYYYYWWWY..",
            "..YWWYYYYWWWY...",
            "...YYYYYYYYY....",
            ".....YYYYY......",
            ".....YYYYY......",
            "....YYYYYYY.....",
            "...YYYYYYYYY....",
            "................"
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('Y', Color.web("#f57f17")); // Emas piala
        map.put('W', Color.web("#fff59d")); // Kilau piala
        map.put('H', Color.web("#e53935")); // Hati merah
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 10. Tile Tanah & Rumput Mario Bros
    public static Image createGrassTile(int width, int height) {
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw = img.getPixelWriter();

        Color grassLight = Color.web("#60bb46");
        Color grassDark = Color.web("#42872e");
        Color dirtLight = Color.web("#a05c30");
        Color dirtDark = Color.web("#753c16");
        Color dirtPebble = Color.web("#54280b");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (y < 8) {
                    if (y % 2 == 0 && (x + y) % 4 == 0) {
                        pw.setColor(x, y, grassLight);
                    } else {
                        pw.setColor(x, y, grassDark);
                    }
                } else if (y < 12) {
                    if ((x + y) % 3 == 0) {
                        pw.setColor(x, y, grassDark);
                    } else {
                        pw.setColor(x, y, dirtDark);
                    }
                } else {
                    int blockX = (x / 8) % 2;
                    int blockY = (y / 8) % 2;
                    if ((x % 8 == 0 || y % 8 == 0)) {
                        pw.setColor(x, y, dirtDark);
                    } else if ((x + y * 7) % 11 == 0) {
                        pw.setColor(x, y, dirtPebble);
                    } else if (blockX == blockY) {
                        pw.setColor(x, y, dirtLight);
                    } else {
                        pw.setColor(x, y, Color.web("#8e5028"));
                    }
                }
            }
        }
        return img;
    }

    // 11. Karakter Mahasiswa Terbang ala Superman (Sesuai Gambar Inspo Loading Screen)
    public static Image createFlyingStudent(int scale, int frame) {
        // Jubah merah berkibar di belakang (kiri)
        String c1 = (frame % 2 == 0) ? "RRRRR" : ".RRRR";
        String c2 = (frame % 2 == 0) ? ".RRRR" : "RRRRR";

        String[] pattern = {
            ".........HHHHH.........",
            "........HHHHHHH........",
            c1 + "...SSSKSSKSSSF.....",
            c2 + "...SSSSSSSSSSF.....",
            ".RRRRR...BBBBBBBAAAAA..",
            "..RRRRR..BBYBBDDAAAAA..",
            "...RRRR..BBBBBBDAAAAA..",
            "........JJJJJJJD.......",
            "........RR..RR........."
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('H', Color.web("#1e1e1e")); // Rambut hitam rapi
        map.put('S', Color.web("#ffd1a4")); // Muka
        map.put('K', Color.web("#111111")); // Mata
        map.put('F', Color.web("#ffd1a4")); // Tangan mengepal maju ke depan
        map.put('B', Color.web("#0984e3")); // Kostum biru Superman/Mahasiswa
        map.put('Y', Color.web("#f1c40f")); // Logo kuning di dada
        map.put('D', Color.web("#0652dd")); // Bayangan biru gelap
        map.put('A', Color.web("#ffd1a4")); // Lengan maju
        map.put('J', Color.web("#2c3e50")); // Celana
        map.put('R', Color.web("#d63031")); // Jubah & sepatu merah berkibar
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 12. Kucing Calico Terbang ala Wonder Woman (Sesuai Gambar Inspo Loading Screen)
    public static Image createFlyingCat(int scale, int frame) {
        String c1 = (frame % 2 == 0) ? "OOOOO" : ".OOOO";
        String c2 = (frame % 2 == 0) ? ".OOOO" : "OOOOO";

        String[] pattern = {
            ".........OO...BB.......",
            "........OWWO.BWWB......",
            c1 + "...OWWKKWKBWFA.....",
            c2 + "...OWWWWWWWPFA.....",
            ".EEEEE...RRRRRRRAAAAA..",
            "..EEEEE..RRYRRDDAAAAA..",
            "...EEEE..RRRRRRDAAAAA..",
            "........BBBBBBBD.......",
            "........WW..WW........."
        };
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('W', Color.web("#f5f5f5")); // Putih bulu
        map.put('O', Color.web("#e67e22")); // Belang oranye
        map.put('B', Color.web("#2c3e50")); // Belang hitam
        map.put('K', Color.web("#111111")); // Mata
        map.put('P', Color.web("#ff9999")); // Hidung pink
        map.put('F', Color.web("#f5f5f5")); // Cakar kucing maju ke depan
        map.put('A', Color.web("#f5f5f5")); // Tangan cakar
        map.put('R', Color.web("#e84393")); // Kostum merah Wonder Cat
        map.put('Y', Color.web("#f1c40f")); // Sabuk emas
        map.put('D', Color.web("#b71540")); // Bayangan kostum
        map.put('E', Color.web("#f39c12")); // Selendang jubah emas berkibar
        map.put('.', Color.TRANSPARENT);

        return createImageFromPattern(pattern, map, scale);
    }

    // 13. Blok Kotak Kuning dengan Simbol Matematika / Status (Sesuai Inspo Gambar: +, -, ×, ÷)
    public static Image createSymbolBlock(String symbol, int scale) {
        String[] pattern;
        if ("+".equals(symbol)) {
            pattern = new String[]{
                "YYYYYYYYYYYYYYYY",
                "YWWWWWWWWWWWWWWY",
                "YWYYYYYYYYYYYYDY",
                "YWY..........YDY",
                "YWY....KK....YDY",
                "YWY....KK....YDY",
                "YWY..KKKKKK..YDY",
                "YWY..KKKKKK..YDY",
                "YWY....KK....YDY",
                "YWY....KK....YDY",
                "YWY..........YDY",
                "YWDDDDDDDDDDDDDY",
                "YDDDDDDDDDDDDDDY",
                "YYYYYYYYYYYYYYYY"
            };
        } else if ("-".equals(symbol)) {
            pattern = new String[]{
                "YYYYYYYYYYYYYYYY",
                "YWWWWWWWWWWWWWWY",
                "YWYYYYYYYYYYYYDY",
                "YWY..........YDY",
                "YWY..........YDY",
                "YWY..........YDY",
                "YWY..KKKKKK..YDY",
                "YWY..KKKKKK..YDY",
                "YWY..........YDY",
                "YWY..........YDY",
                "YWY..........YDY",
                "YWDDDDDDDDDDDDDY",
                "YDDDDDDDDDDDDDDY",
                "YYYYYYYYYYYYYYYY"
            };
        } else if ("x".equalsIgnoreCase(symbol) || "×".equals(symbol)) {
            pattern = new String[]{
                "YYYYYYYYYYYYYYYY",
                "YWWWWWWWWWWWWWWY",
                "YWYYYYYYYYYYYYDY",
                "YWY..........YDY",
                "YWY..KK..KK..YDY",
                "YWY...KKKK...YDY",
                "YWY....KK....YDY",
                "YWY...KKKK...YDY",
                "YWY..KK..KK..YDY",
                "YWY..........YDY",
                "YWY..........YDY",
                "YWDDDDDDDDDDDDDY",
                "YDDDDDDDDDDDDDDY",
                "YYYYYYYYYYYYYYYY"
            };
        } else { // Division "÷"
            pattern = new String[]{
                "YYYYYYYYYYYYYYYY",
                "YWWWWWWWWWWWWWWY",
                "YWYYYYYYYYYYYYDY",
                "YWY..........YDY",
                "YWY....KK....YDY",
                "YWY..........YDY",
                "YWY..KKKKKK..YDY",
                "YWY..KKKKKK..YDY",
                "YWY..........YDY",
                "YWY....KK....YDY",
                "YWY..........YDY",
                "YWDDDDDDDDDDDDDY",
                "YDDDDDDDDDDDDDDY",
                "YYYYYYYYYYYYYYYY"
            };
        }
        java.util.Map<Character, Color> map = new java.util.HashMap<>();
        map.put('Y', Color.web("#804000")); // Border brown
        map.put('W', Color.web("#ffe070")); // Highlight yellow
        map.put('D', Color.web("#b86800")); // Shadow brown-orange
        map.put('K', Color.web("#402000")); // Simbol hitam kecoklatan
        map.put('.', Color.web("#fca010")); // Warna kuning emas utama blok

        return createImageFromPattern(pattern, map, scale);
    }

    // 14. Judul 3D Pixel Block "KOS SURVIVAL" / "SUPER MATH" (Gaya Inspo Gambar)
    public static Image create3DPixelTitle(int scale) {
        String[] pattern = {
            "........................................................",
            ".KK..KK...OOOO...SSSSSS.................................",
            ".KK.KK...OO..OO..SS.....................................",
            ".KKKK....OO..OO...SSSS..................................",
            ".KK.KK...OO..OO......SS.................................",
            ".KK..KK...OOOO...SSSSSS.................................",
            "........................................................",
            ".SSSSSS.UU...UU.RRRRR..VV...VV.II.VV...VV...AAA...LL....",
            ".SS.....UU...UU.RR..RR.VV...VV.II.VV...VV..AA.AA..LL....",
            "..SSSS..UU...UU.RRRRR...VV.VV..II..VV.VV..AAAAAAA.LL....",
            ".....SS.UU...UU.RR.RR....VVV...II...VVV...AA...AA.LL....",
            ".SSSSSS..UUUUU..RR..RR....V....II....V....AA...AA.LLLLLL",
            "........................................................"
        };
        int height = pattern.length;
        int width = pattern[0].length();

        // Buat kanvas dengan efek 3D block:
        // Layer 1: Bayangan 3D coklat tua diekstrusi ke kanan-bawah (+2, +2)
        // Layer 2: Muka huruf oranye-emas terang
        // Layer 3: Highlight putih/kuning di sudut kiri-atas
        int extrusion = 2;
        WritableImage img = new WritableImage((width + extrusion) * scale, (height + extrusion) * scale);
        PixelWriter pw = img.getPixelWriter();

        Color colHighlight = Color.web("#fff3a8");
        Color colFace = Color.web("#f59e0b");
        Color colDark = Color.web("#d97706");
        Color colShadow = Color.web("#78350f");
        Color colOutline = Color.web("#451a03");

        for (int y = 0; y < height; y++) {
            String row = pattern[y];
            for (int x = 0; x < width; x++) {
                char c = row.charAt(x);
                if (c != '.') {
                    // Gambar ekstrusi bayangan 3D (turun 2px, geser 2px)
                    for (int ex = 1; ex <= extrusion; ex++) {
                        for (int dy = 0; dy < scale; dy++) {
                            for (int dx = 0; dx < scale; dx++) {
                                int px = (x + ex) * scale + dx;
                                int py = (y + ex) * scale + dy;
                                pw.setColor(px, py, colShadow);
                            }
                        }
                    }
                }
            }
        }

        // Gambar muka huruf dan highlight
        for (int y = 0; y < height; y++) {
            String row = pattern[y];
            for (int x = 0; x < width; x++) {
                char c = row.charAt(x);
                if (c != '.') {
                    boolean isTop = (y == 0 || pattern[y - 1].charAt(x) == '.');
                    boolean isLeft = (x == 0 || row.charAt(x - 1) == '.');
                    Color fillColor = (isTop || isLeft) ? colHighlight : ((y % 2 == 0) ? colFace : colDark);

                    for (int dy = 0; dy < scale; dy++) {
                        for (int dx = 0; dx < scale; dx++) {
                            int px = x * scale + dx;
                            int py = y * scale + dy;
                            // Garis tepi outline
                            if (dy == 0 || dx == 0) {
                                pw.setColor(px, py, isTop || isLeft ? colHighlight : colOutline);
                            } else {
                                pw.setColor(px, py, fillColor);
                            }
                        }
                    }
                }
            }
        }
        return img;
    }

    // 15. Segmented Pixel Loading Bar (Sesuai Persis dengan Inspo Gambar Supermath)
    public static Image createSegmentedLoadingBar(int width, int height, double progress) {
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw = img.getPixelWriter();

        Color borderWhite = Color.web("#ffffff");
        Color bgDark = Color.web("#0e2b17"); // Latar track hijau sangat gelap
        Color fillBright = Color.web("#00e676"); // Hijau terang menyala
        Color fillHighlight = Color.web("#b9f6ca"); // Highlight putih-hijau di atas
        Color fillShadow = Color.web("#00b348"); // Bayangan hijau di bawah
        Color segmentGap = Color.web("#071a0e"); // Garis pemisah antar segmen

        int borderThickness = 4;
        int numSegments = 16;
        int activeSegments = (int) Math.round(progress * numSegments);

        // 1. Gambar latar track gelap dan border putih tebal
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x < borderThickness || x >= width - borderThickness ||
                    y < borderThickness || y >= height - borderThickness) {
                    pw.setColor(x, y, borderWhite);
                } else {
                    pw.setColor(x, y, bgDark);
                }
            }
        }

        // 2. Gambar segmen-segmen hijau (brick segments)
        int innerWidth = width - (borderThickness * 2);
        int innerHeight = height - (borderThickness * 2);
        double segWidth = (double) innerWidth / numSegments;

        for (int s = 0; s < activeSegments; s++) {
            int startX = borderThickness + (int) (s * segWidth);
            int endX = borderThickness + (int) ((s + 1) * segWidth);

            for (int x = startX; x < endX; x++) {
                for (int y = borderThickness; y < borderThickness + innerHeight; y++) {
                    if (x == endX - 1) {
                        // Garis pemisah pixel hitam antar kotak
                        pw.setColor(x, y, segmentGap);
                    } else if (y - borderThickness < 4) {
                        // Kilau atas segmen
                        pw.setColor(x, y, fillHighlight);
                    } else if (borderThickness + innerHeight - y <= 4) {
                        // Bayangan bawah segmen
                        pw.setColor(x, y, fillShadow);
                    } else {
                        // Hijau cerah
                        pw.setColor(x, y, fillBright);
                    }
                }
            }
        }

        return img;
    }

    // 16. Siluet Horizon Awan Pixel Bertingkat (Stepped Pixel Cloud Horizon)
    public static Image createPixelCloudHorizon(int width, int height) {
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw = img.getPixelWriter();

        Color colLayer1 = Color.web("#86b8fc");
        Color colLayer2 = Color.web("#a7cdfc");
        Color colLayer3 = Color.web("#cbe0fc");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int blockX = x / 16;
                int h1 = 30 + ((blockX * 7) % 15);
                int h2 = 20 + ((blockX * 11) % 12);
                int h3 = 10 + ((blockX * 13) % 8);

                if (height - y < h3) {
                    pw.setColor(x, y, colLayer3);
                } else if (height - y < h2) {
                    pw.setColor(x, y, colLayer2);
                } else if (height - y < h1) {
                    pw.setColor(x, y, colLayer1);
                } else {
                    pw.setColor(x, y, Color.TRANSPARENT);
                }
            }
        }
        return img;
    }

    // 17. Bendera Indonesia Pixel Berbingkai (Sesuai Ikon Kiri Atas Citampi Stories)
    public static Image createIndonesianFlag(int width, int height) {
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw = img.getPixelWriter();

        Color colBorderDark = Color.web("#8c470a");
        Color colBorderLight = Color.web("#f5b041");
        Color colRed = Color.web("#d63031");
        Color colWhite = Color.web("#ffffff");

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x == 0 || x == width - 1 || y == 0 || y == height - 1) {
                    pw.setColor(x, y, colBorderDark);
                } else if (x == 1 || y == 1) {
                    pw.setColor(x, y, colBorderLight);
                } else if (y < height / 2) {
                    pw.setColor(x, y, colRed);
                } else {
                    pw.setColor(x, y, colWhite);
                }
            }
        }
        return img;
    }

    // 18. Latar Belakang Perkotaan Citampi Stories (Dense Indonesian Cityscape with Power Lines & Trees)
    public static Image createCitampiCityBackground(int width, int height) {
        WritableImage img = new WritableImage(width, height);
        PixelWriter pw = img.getPixelWriter();

        // 1. Langit Biru Cerulean
        Color skyTop = Color.web("#3d92da");
        Color skyBottom = Color.web("#6cb1ea");
        for (int y = 0; y < height; y++) {
            double ratio = (double) y / height;
            Color c = skyTop.interpolate(skyBottom, ratio);
            for (int x = 0; x < width; x++) {
                pw.setColor(x, y, c);
            }
        }

        // 2. Awan Putih Pixel di Sudut Kanan Atas (Sesuai Screenshot Citampi)
        Color cloudWhite = Color.web("#ffffff");
        Color cloudShadow = Color.web("#c4e1f7");
        for (int x = 620; x < width; x++) {
            int cloudHeight = 40 + (int) (Math.sin((x - 620) * 0.03) * 25) + ((x % 8 < 4) ? 4 : 0);
            for (int y = 0; y < cloudHeight && y < height; y++) {
                if (cloudHeight - y < 8) {
                    pw.setColor(x, y, cloudShadow);
                } else {
                    pw.setColor(x, y, cloudWhite);
                }
            }
        }

        // 3. Gedung Bertingkat Jauh (Apartemen Slate Blue)
        Color towerDark = Color.web("#547288");
        Color towerMid = Color.web("#66879e");
        Color towerLight = Color.web("#7a9bb2");
        Color winGlass = Color.web("#cce6f6");
        Color winFrame = Color.web("#3d5668");
        Color winLit = Color.web("#ffeb99");

        // Menara 1 (Kiri)
        drawCityTower(pw, 20, 190, 80, height, towerDark, winGlass, winFrame, winLit);
        drawCityTower(pw, 110, 240, 60, height, towerMid, winGlass, winFrame, winLit);
        drawCityTower(pw, 180, 290, 70, height, towerLight, winGlass, winFrame, winLit);
        // Menara 2 (Tengah & Kanan Jauh)
        drawCityTower(pw, 420, 220, 90, height, towerDark, winGlass, winFrame, winLit);
        drawCityTower(pw, 520, 260, 70, height, towerMid, winGlass, winFrame, winLit);
        drawCityTower(pw, 820, 180, 140, height, towerLight, winGlass, winFrame, winLit);

        // 4. Bangunan Hijau Lumut Kiri (Ruko Stucco Hijau)
        Color greenBuilding = Color.web("#4b5b46");
        Color greenBuildingDark = Color.web("#374433");
        for (int x = 0; x < 240; x++) {
            for (int y = 200; y < height; y++) {
                pw.setColor(x, y, (x % 32 == 0) ? greenBuildingDark : greenBuilding);
            }
        }

        // AC Outdoor Unit pada Gedung Hijau (x: 95, y: 235)
        drawAcUnit(pw, 95, 235);

        // Kanopi Seng / Awning Hijau Tua di bawah AC
        Color awningColor = Color.web("#2c3e50");
        for (int x = 0; x < 180; x++) {
            for (int y = 300; y < 350; y++) {
                pw.setColor(x, y, (x % 6 < 3) ? awningColor : Color.web("#1e272e"));
            }
        }

        // 5. Bangunan Tengah (Ruko Bata Coklat)
        Color brickMid = Color.web("#64554b");
        Color brickDark = Color.web("#4c3f37");
        for (int x = 230; x < 610; x++) {
            for (int y = 170; y < height; y++) {
                pw.setColor(x, y, ((x / 12 + y / 8) % 2 == 0) ? brickMid : brickDark);
            }
        }

        // 6. Gedung Kos Modern di Sisi Kanan (Gedung Utama Citampi)
        Color kosWall = Color.web("#544b47");
        Color kosLedge = Color.web("#9c938d");
        for (int x = 600; x < width; x++) {
            for (int y = 170; y < height; y++) {
                if (y == 170 || y == 171 || y == 172 || y == 290 || y == 291 || y == 410 || y == 411) {
                    pw.setColor(x, y, kosLedge); // Lintel / Pembatas lantai beton abu-abu
                } else {
                    pw.setColor(x, y, kosWall);
                }
            }
        }

        // Jendela Modern Besar dengan Pantulan Garis Diagonal (3 Lantai x 3 Jendela)
        for (int floor = 0; floor < 3; floor++) {
            int winY = 190 + (floor * 120);
            for (int col = 0; col < 3; col++) {
                int winX = 640 + (col * 105);
                drawModernWindow(pw, winX, winY, 65, 80);
            }
        }

        // 7. Pohon Rindang Hijau di Depan Gedung Kanan (Sesuai Persis dengan Citampi)
        drawLushPixelTree(pw, 730, 310, 110, 220);

        // 8. Gudang Atap Seng Kiri Bawah (Corrugated Zinc Roof)
        Color zincLight = Color.web("#9aa8b2");
        Color zincDark = Color.web("#6c7d88");
        for (int x = 0; x < 290; x++) {
            for (int y = 345; y < 540; y++) {
                if (y < 460) {
                    // Garis-garis seng vertikal
                    pw.setColor(x, y, (x % 8 < 4) ? zincLight : zincDark);
                } else {
                    // Dinding bata bawah
                    pw.setColor(x, y, Color.web("#7a4128"));
                }
            }
        }

        // 9. Tiang Listrik & Kabel Listrik Semrawut Melintang (Khas Indonesia & Citampi Stories)
        drawTelephonePoleAndWires(pw, width, height);

        return img;
    }

    private static void drawCityTower(PixelWriter pw, int startX, int startY, int w, int h,
                                      Color wallCol, Color winGlass, Color winFrame, Color winLit) {
        for (int x = startX; x < startX + w; x++) {
            for (int y = startY; y < h; y++) {
                int relX = x - startX;
                int relY = y - startY;

                if (relX % 16 >= 4 && relX % 16 <= 12 && relY % 18 >= 4 && relY % 18 <= 12) {
                    if (relX % 16 == 4 || relX % 16 == 12 || relY % 18 == 4 || relY % 18 == 12) {
                        pw.setColor(x, y, winFrame);
                    } else {
                        // Sebagian jendela menyala kuning
                        boolean isLit = (relX * 7 + relY * 13) % 17 == 0;
                        pw.setColor(x, y, isLit ? winLit : winGlass);
                    }
                } else {
                    pw.setColor(x, y, wallCol);
                }
            }
        }
    }

    private static void drawAcUnit(PixelWriter pw, int startX, int startY) {
        Color acBody = Color.web("#e4e7eb");
        Color acVent = Color.web("#2f3640");
        for (int x = startX; x < startX + 38; x++) {
            for (int y = startY; y < startY + 26; y++) {
                int rx = x - startX;
                int ry = y - startY;
                if (rx == 0 || rx == 37 || ry == 0 || ry == 25) {
                    pw.setColor(x, y, Color.web("#718093"));
                } else if (rx >= 16 && rx <= 32 && ry >= 6 && ry <= 20) {
                    // Kipas pendingin
                    boolean isGrill = (ry % 3 == 0) || (rx == 24);
                    pw.setColor(x, y, isGrill ? acVent : acBody);
                } else {
                    pw.setColor(x, y, acBody);
                }
            }
        }
    }

    private static void drawModernWindow(PixelWriter pw, int startX, int startY, int w, int h) {
        Color frameCol = Color.web("#837a75");
        Color glassCol = Color.web("#5b7b99");
        Color glareCol = Color.web("#b4d5ee");

        for (int x = startX; x < startX + w; x++) {
            for (int y = startY; y < startY + h; y++) {
                int rx = x - startX;
                int ry = y - startY;

                if (rx < 4 || rx >= w - 4 || ry < 4 || ry >= h - 4 || rx == w / 2) {
                    pw.setColor(x, y, frameCol);
                } else {
                    // Pantulan garis kaca diagonal miring
                    int diag = (rx + ry);
                    if (diag >= 35 && diag <= 42 || diag >= 65 && diag <= 72) {
                        pw.setColor(x, y, glareCol);
                    } else {
                        pw.setColor(x, y, glassCol);
                    }
                }
            }
        }
    }

    private static void drawLushPixelTree(PixelWriter pw, int centerX, int startY, int w, int h) {
        Color trunkCol = Color.web("#4e342e");
        Color leaf1 = Color.web("#43a047"); // Daun cerah
        Color leaf2 = Color.web("#2e7d32"); // Daun sedang
        Color leaf3 = Color.web("#1b5e20"); // Daun gelap
        Color leaf4 = Color.web("#0e3d16"); // Daun bayangan pekat

        // Batang pohon
        int trunkX = centerX + 10;
        for (int y = startY + 90; y < startY + h; y++) {
            for (int x = trunkX - 6; x < trunkX + 6; x++) {
                pw.setColor(x, y, trunkCol);
            }
        }

        // Kerumunan daun bulat-bulat bertingkat khas Citampi
        int[][] clumps = {
            {centerX - 10, startY + 20, 26},
            {centerX - 24, startY + 45, 30},
            {centerX + 15, startY + 40, 28},
            {centerX - 15, startY + 80, 36},
            {centerX + 18, startY + 85, 34},
            {centerX - 5, startY + 115, 38}
        };

        for (int[] clump : clumps) {
            int cx = clump[0];
            int cy = clump[1];
            int r = clump[2];

            for (int dy = -r; dy <= r; dy++) {
                for (int dx = -r; dx <= r; dx++) {
                    int distSq = dx * dx + dy * dy;
                    if (distSq <= r * r) {
                        int px = cx + dx;
                        int py = cy + dy;
                        if (px >= 0 && px < 960 && py >= 0 && py < 540) {
                            if (dy < -r / 3) {
                                pw.setColor(px, py, leaf1);
                            } else if (dy < r / 5) {
                                pw.setColor(px, py, leaf2);
                            } else if (dy < r / 2) {
                                pw.setColor(px, py, leaf3);
                            } else {
                                pw.setColor(px, py, leaf4);
                            }
                        }
                    }
                }
            }
        }
    }

    private static void drawTelephonePoleAndWires(PixelWriter pw, int w, int h) {
        int poleX = 370;
        Color poleCol = Color.web("#2d3436");
        Color cableCol = Color.web("#111111");

        // Tiang kayu/beton
        for (int y = 250; y < h; y++) {
            for (int x = poleX - 3; x <= poleX + 3; x++) {
                pw.setColor(x, y, poleCol);
            }
        }
        // Palang horizontal tiang
        for (int y = 270; y <= 274; y++) {
            for (int x = poleX - 30; x <= poleX + 30; x++) {
                pw.setColor(x, y, poleCol);
            }
        }

        // Kabel listrik 1: Lengkung catenary dari kiri atas ke tiang lalu ke kanan
        drawCatenaryWire(pw, 0, 320, poleX, 360, w, 395, 24, cableCol);

        // Kabel listrik 2: Lengkung bawah melewati belakang tombol
        drawCatenaryWire(pw, 0, 360, poleX, 420, w, 425, 32, cableCol);

        // Kabel listrik 3: Lengkung melintang gedung
        drawCatenaryWire(pw, 120, 440, 520, 465, w, 460, 20, cableCol);
    }

    private static void drawCatenaryWire(PixelWriter pw, int x0, int y0, int xMid, int yMid, int x1, int y1, int sag, Color col) {
        // Gambar garis melengkung santai
        for (int x = x0; x < x1; x++) {
            double t = (double) (x - x0) / (x1 - x0);
            double baseLinear = y0 + (y1 - y0) * t;
            // Lengkung parabolik sag
            double curve = Math.sin(t * Math.PI) * sag;
            int y = (int) Math.round(baseLinear + curve);

            if (y >= 0 && y < 540 && x >= 0 && x < 960) {
                pw.setColor(x, y, col);
                if (y + 1 < 540) pw.setColor(x, y + 1, col); // Ketebalan 2px
            }
        }
    }
}
