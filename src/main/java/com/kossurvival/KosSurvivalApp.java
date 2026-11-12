package com.kossurvival;

import com.kossurvival.controller.SceneNavigator;
import com.kossurvival.util.PixelArtRenderer;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Entry Point Utama Aplikasi GUI JavaFX "Kos Survival" berbasis arsitektur MVC & FXML.
 */
public class KosSurvivalApp extends Application {

    @Override
    public void init() {
        // Registrasi Font Pixel di tahap awal siklus hidup JavaFX
        SceneNavigator.loadCustomFonts();
    }

    @Override
    public void start(Stage primaryStage) {
        // Set Icon Window (Koin Mario Emas)
        try {
            Image icon = PixelArtRenderer.createCoin(4, 0);
            primaryStage.getIcons().add(icon);
        } catch (Exception ignored) {}

        // Inisialisasi navigasi MVC dan tampilkan layar pertama (Loading View)
        SceneNavigator.initialize(primaryStage);
        SceneNavigator.switchToLoading();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

