package com.kossurvival.controller;

import com.kossurvival.model.*;
import com.kossurvival.util.PixelArtRenderer;
import com.kossurvival.util.SoundManager;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller untuk Layar Permainan (Gameplay View).
 * Bertindak sebagai penghubung (Controller) antara Model (GameEngine, Mahasiswa, Aktivitas, SistemSaveData)
 * dengan Tampilan Visual FXML (View).
 */
public class GameplayController implements Initializable {

    @FXML private StackPane centerStack;
    @FXML private ImageView studentRoomView;
    @FXML private ImageView catRoomView;

    @FXML private Label hariLabel;
    @FXML private Label uangLabel;
    @FXML private Label energiLabel;
    @FXML private ProgressBar energiBar;
    @FXML private Label stresLabel;
    @FXML private ProgressBar stresBar;
    @FXML private Label nilaiLabel;
    @FXML private ProgressBar nilaiBar;

    @FXML private ListView<String> logListView;

    private final ObservableList<String> logItems = FXCollections.observableArrayList();
    private final NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
    private GameEngine gameEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.gameEngine = SceneNavigator.getGameEngine();

        // Setup sprite kamar kos
        studentRoomView.setImage(PixelArtRenderer.createStudent(4, 0));
        catRoomView.setImage(PixelArtRenderer.createCat(3, 0));

        logListView.setItems(logItems);

        // Update tampilan pertama kali dari Model
        updateView();
    }

    public void updateView() {
        Mahasiswa m = gameEngine.getMahasiswa();

        hariLabel.setText("HARI KE-" + m.getHariKe() + " / 30");
        uangLabel.setText(rupiahFormat.format(m.getUang()));

        energiBar.setProgress(m.getEnergi() / 100.0);
        energiLabel.setText("⚡ Energi: " + m.getEnergi() + "/100");

        stresBar.setProgress(m.getStres() / 100.0);
        stresLabel.setText("🤯 Stres: " + m.getStres() + "/100");

        nilaiBar.setProgress(m.getNilaiAkademik() / 100.0);
        nilaiLabel.setText("🎓 Nilai Akademik: " + m.getNilaiAkademik() + "/100");

        // Sinkronisasi data CRUD Read langsung dari SistemSaveData (Model)
        SistemSaveData saveData = gameEngine.getSaveData();
        ArrayList<String> riwayat = saveData.getRiwayatAktivitas();
        logItems.setAll(riwayat);
        if (!logItems.isEmpty()) {
            logListView.scrollTo(logItems.size() - 1);
        }

        // Evaluasi kondisi tamat (Multiple Endings)
        StatusEnding ending = gameEngine.getStatusEnding();
        if (ending != StatusEnding.BERLANJUT) {
            showEndingBanner(ending);
        }
    }

    @FXML
    private void handleKuliah() {
        SoundManager.playClickSound();
        gameEngine.jalankanAktivitas(new KuliahDanTugas());
        updateView();
    }

    @FXML
    private void handleKerja() {
        SoundManager.playClickSound();
        gameEngine.jalankanAktivitas(new KerjaPartTime());
        updateView();
    }

    @FXML
    private void handleMakan() {
        SoundManager.playClickSound();
        gameEngine.jalankanAktivitas(new Makan());
        updateView();
    }

    @FXML
    private void handleGame() {
        SoundManager.playClickSound();
        gameEngine.jalankanAktivitas(new MainGame());
        updateView();
    }

    @FXML
    private void handleTidur() {
        SoundManager.playClickSound();
        gameEngine.jalankanAktivitas(new Tidur());
        updateView();
    }

    @FXML
    private void handleResetGame() {
        SoundManager.playClickSound();
        gameEngine.resetGame();
        centerStack.getChildren().removeIf(node -> "ENDING_BANNER".equals(node.getId()));
        updateView();
    }

    @FXML
    private void handleBackToMenu() {
        SoundManager.playHoverSound();
        SceneNavigator.switchToMainMenu();
    }

    private void showEndingBanner(StatusEnding ending) {
        // Cegah duplikasi banner jika sudah muncul
        centerStack.getChildren().removeIf(node -> "ENDING_BANNER".equals(node.getId()));

        VBox banner = new VBox(14);
        banner.setId("ENDING_BANNER");
        banner.setAlignment(Pos.CENTER);
        banner.setMaxSize(550, 320);
        banner.setStyle(
            "-fx-background-color: #1a1a2e;" +
            "-fx-border-width: 4px;" +
            "-fx-border-radius: 12px;" +
            "-fx-background-radius: 12px;" +
            "-fx-padding: 24px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 20, 0, 0, 10);"
        );

        Label title = new Label("PERMAINAN BERAKHIR");
        title.setFont(SceneNavigator.getPixelFont(16));
        title.setStyle("-fx-effect: dropshadow(one-pass-box, #000, 0, 0, 3, 3);");
        title.setTextFill(Color.web("#f1c40f"));

        String descText = "";
        Color bannerBorder = Color.web("#e74c3c");

        switch (ending) {
            case KALAH_TIPES:
                title.setText("💀 KALAH: TERKENA TIPES");
                descText = "Kondisi tubuhmu ambruk karena kelelahan atau stres berlebih.\nKamu harus dilarikan ke Rumah Sakit!";
                SoundManager.playGameOverSound();
                break;
            case KALAH_DIUSIR:
                title.setText("📦 KALAH: DIUSIR IBU KOS");
                descText = "Uangmu habis total sebelum hari ke-30!\nIbu kos menggembok pintu dan kamu terpaksa ngemper bawa kardus.";
                SoundManager.playGameOverSound();
                break;
            case KALAH_DROP_OUT:
                title.setText("📄 KALAH: DROP OUT (DO)");
                descText = "Nilai akademikmu anjlok mencapai 0!\nSurat peringatan dan pemecatan dari kampus tiba.";
                SoundManager.playGameOverSound();
                break;
            case MENANG_CUMLAUDE:
                title.setText("🎓 MENANG: LULUS CUMLAUDE!");
                title.setTextFill(Color.web("#2ecc71"));
                descText = "Selamat! Kamu berhasil bertahan 30 hari penuh tantangan\ndengan nilai akademik luar biasa dan toga wisuda!";
                bannerBorder = Color.web("#2ecc71");
                SoundManager.playCoinSound();
                break;
            default:
                break;
        }

        banner.setStyle(banner.getStyle() + "-fx-border-color: " + toRgbHex(bannerBorder) + ";");

        Label desc = new Label(descText);
        desc.setFont(SceneNavigator.getPixelFont(10));
        desc.setStyle("-fx-line-spacing: 5px;");
        desc.setTextFill(Color.WHITE);
        desc.setWrapText(true);
        desc.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        HBox btnBox = new HBox(16);
        btnBox.setAlignment(Pos.CENTER);

        Button btnPlayAgain = new Button("🔄 MAIN LAGI");
        btnPlayAgain.getStyleClass().add("pixel-btn-start");
        btnPlayAgain.setStyle("-fx-font-size: 11px; -fx-padding: 10px 18px;");
        btnPlayAgain.setOnAction(e -> handleResetGame());

        Button btnMenu = new Button("🏠 MENU UTAMA");
        btnMenu.getStyleClass().add("pixel-btn-secondary");
        btnMenu.setStyle("-fx-font-size: 11px; -fx-padding: 10px 18px;");
        btnMenu.setOnAction(e -> handleBackToMenu());

        btnBox.getChildren().addAll(btnPlayAgain, btnMenu);
        banner.getChildren().addAll(title, desc, btnBox);

        banner.setOpacity(0.0);
        centerStack.getChildren().add(banner);

        FadeTransition ft = new FadeTransition(Duration.millis(350), banner);
        ft.setToValue(1.0);
        ft.play();
    }

    private String toRgbHex(Color c) {
        return String.format("#%02X%02X%02X",
            (int) (c.getRed() * 255),
            (int) (c.getGreen() * 255),
            (int) (c.getBlue() * 255));
    }
}

