package com.kossurvival.controller;

import com.kossurvival.util.PixelArtRenderer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller untuk Layar Loading (Loading View) bergaya Citampi Stories.
 * Menampilkan suasana perkotaan Indonesia, karakter berjalan, dan progress bar oranye emas.
 */
public class LoadingController implements Initializable {

    @FXML private ImageView cityBgView;
    @FXML private ProgressBar progressBar;
    @FXML private Label statusLabel;
    @FXML private Label tipLabel;
    @FXML private javafx.scene.text.Text titleKos;
    @FXML private javafx.scene.text.Text titleSurvival;

    private int tipIndex = 0;
    private Timeline tipLoop;

    private final String[] TIPS_CITAMPI = {
        "Tips: Sedia tolak angin & obat maag di laci kosan sebelum terserang tipes!",
        "Tips: Makan di Warteg lebih hemat dan bergizi seimbang daripada kafe hits.",
        "Tips: Jangan lupa bayar uang kosan ke Ibu Kos tepat waktu agar aman tenteram.",
        "Tips: Kucing oren kosan sangat ramah bila kamu memberinya sedikit ikan asin.",
        "Tips: Seimbangkan waktu belajar, kerja part-time, dan tidur untuk meraih Cumlaude!"
    };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Enforce font pixel berukuran besar pada judul
        if (titleKos != null) {
            titleKos.setFont(SceneNavigator.getPixelFont(86));
        }
        if (titleSurvival != null) {
            titleSurvival.setFont(SceneNavigator.getPixelFont(58));
        }

        // 1. Latar Belakang Perkotaan Citampi Stories
        if (cityBgView != null) {
            cityBgView.setImage(PixelArtRenderer.createCitampiCityBackground(960, 540));
        }

        // 2. Mulai Animasi Progress & Tips
        startAnimations();
    }

    private void startAnimations() {
        // Rotasi tips anak kos bergaya Citampi
        tipLoop = new Timeline(new KeyFrame(Duration.millis(1800), e -> {
            tipIndex = (tipIndex + 1) % TIPS_CITAMPI.length;
            tipLabel.setText(TIPS_CITAMPI[tipIndex]);
        }));
        tipLoop.setCycleCount(Animation.INDEFINITE);
        tipLoop.play();

        // Timeline Progress Loading Bar
        Timeline progressTimeline = new Timeline();

        KeyValue kv0 = new KeyValue(progressBar.progressProperty(), 0.0);
        KeyFrame kf0 = new KeyFrame(Duration.ZERO, kv0);

        KeyValue kv1 = new KeyValue(progressBar.progressProperty(), 0.45);
        KeyFrame kf1 = new KeyFrame(Duration.millis(1200), kv1);

        KeyValue kv2 = new KeyValue(progressBar.progressProperty(), 0.80);
        KeyFrame kf2 = new KeyFrame(Duration.millis(2300), kv2);

        KeyValue kv3 = new KeyValue(progressBar.progressProperty(), 1.0);
        KeyFrame kf3 = new KeyFrame(Duration.millis(3400), kv3);

        progressBar.progressProperty().addListener((obs, oldVal, newVal) -> {
            int pct = (int) Math.round(newVal.doubleValue() * 100);
            statusLabel.setText("Menuju Kehidupan Kos... " + pct + "%");
        });

        progressTimeline.getKeyFrames().addAll(kf0, kf1, kf2, kf3);
        progressTimeline.setOnFinished(e -> {
            Timeline wait = new Timeline(new KeyFrame(Duration.millis(400), evt -> {
                tipLoop.stop();
                SceneNavigator.switchToMainMenu();
            }));
            wait.play();
        });

        progressTimeline.play();
    }
}
