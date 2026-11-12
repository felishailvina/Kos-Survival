package com.kossurvival.controller;

import com.kossurvival.util.PixelArtRenderer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller untuk Layar Menu Utama (Main Menu View) bergaya Citampi Stories.
 * Mengintegrasikan latar belakang perkotaan Indonesia pixel art, tombol oranye beveled,
 * dan navigasi point & click.
 */
public class MainMenuController implements Initializable {

    @FXML private ImageView cityBgView;
    @FXML private ImageView flagIconView;
    @FXML private Button btnStart;
    @FXML private Button btnLoad;
    @FXML private Button btnCredits;
    @FXML private Button btnTutorial;
    @FXML private javafx.scene.text.Text titleKos;
    @FXML private javafx.scene.text.Text titleSurvival;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Enforce font pixel berukuran ekstra besar & eyecatching
        if (titleKos != null) {
            titleKos.setFont(SceneNavigator.getPixelFont(82));
        }
        if (titleSurvival != null) {
            titleSurvival.setFont(SceneNavigator.getPixelFont(56));
        }

        // 1. Gambar Ilustrasi Kota Perkampungan Citampi Stories (960 x 540)
        if (cityBgView != null) {
            cityBgView.setImage(PixelArtRenderer.createCitampiCityBackground(960, 540));
        }

        // 2. Ikon Bendera Indonesia
        if (flagIconView != null) {
            flagIconView.setImage(PixelArtRenderer.createIndonesianFlag(36, 24));
        }
    }

    @FXML
    private void handleStartGame() {
        SceneNavigator.switchToGameplay();
    }

    @FXML
    private void handleShowInstructions() {
        SceneNavigator.showModal("/views/instructions_modal.fxml");
    }

    @FXML
    private void handleShowCredits() {
        SceneNavigator.showModal("/views/instructions_modal.fxml");
    }

    @FXML
    private void handleFlagClick() {
        // Toggle atau info bahasa
    }
}
