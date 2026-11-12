package com.kossurvival.controller;

import com.kossurvival.model.GameEngine;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Controller Navigasi (Scene Navigator) dalam arsitektur MVC.
 * Bertanggung jawab memuat file FXML (View) dan mengatur perpindahan antar layar.
 */
public class SceneNavigator {

    public static final int WINDOW_WIDTH = 960;
    public static final int WINDOW_HEIGHT = 540;

    private static Stage primaryStage;
    private static StackPane rootContainer;
    private static GameEngine gameEngine;

    public static void initialize(Stage stage) {
        primaryStage = stage;
        gameEngine = new GameEngine();

        // 1. Registrasi Font Retro Arcade Pixel 'Press Start 2P' & 'Silkscreen'
        loadCustomFonts();

        rootContainer = new StackPane();
        rootContainer.setStyle("-fx-background-color: #3b8fd9;");

        Scene scene = new Scene(rootContainer, WINDOW_WIDTH, WINDOW_HEIGHT);

        // Load stylesheet global
        try {
            String css = SceneNavigator.class.getResource("/css/style.css").toExternalForm();
            scene.getStylesheets().add(css);
        } catch (Exception e) {
            System.err.println("Gagal memuat CSS style.css: " + e.getMessage());
        }

        primaryStage.setScene(scene);
        primaryStage.setTitle("Kos Survival");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void loadCustomFonts() {
        try {
            // Load via external URL string untuk registrasi CSS JavaFX
            var pUrl = SceneNavigator.class.getResource("/fonts/PressStart2P-Regular.ttf");
            if (pUrl != null) {
                Font.loadFont(pUrl.toExternalForm(), 16);
            }
            try (InputStream pStream = SceneNavigator.class.getResourceAsStream("/fonts/PressStart2P-Regular.ttf")) {
                if (pStream != null) {
                    Font.loadFont(pStream, 16);
                }
            }

            var sUrl = SceneNavigator.class.getResource("/fonts/Silkscreen-Regular.ttf");
            if (sUrl != null) {
                Font.loadFont(sUrl.toExternalForm(), 16);
            }
            try (InputStream sStream = SceneNavigator.class.getResourceAsStream("/fonts/Silkscreen-Regular.ttf")) {
                if (sStream != null) {
                    Font.loadFont(sStream, 16);
                }
            }
        } catch (Exception e) {
            System.err.println("Warning: Gagal mendaftarkan custom font: " + e.getMessage());
        }
    }

    public static Font getPixelFont(double size) {
        try {
            Font f = Font.font("Press Start 2P", size);
            if (f != null && "Press Start 2P".equalsIgnoreCase(f.getFamily())) {
                return f;
            }
            try (InputStream is = SceneNavigator.class.getResourceAsStream("/fonts/PressStart2P-Regular.ttf")) {
                if (is != null) {
                    return Font.loadFont(is, size);
                }
            }
        } catch (Exception ignored) {}
        return Font.font("Monospaced", size);
    }

    public static GameEngine getGameEngine() {
        if (gameEngine == null) {
            gameEngine = new GameEngine();
        }
        return gameEngine;
    }

    public static void setGameEngine(GameEngine engine) {
        gameEngine = engine;
    }

    public static void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
            Parent view = loader.load();

            // Efek Transisi Cross-Fade halus
            view.setOpacity(0.0);
            rootContainer.getChildren().add(view);

            FadeTransition fadeIn = new FadeTransition(Duration.millis(350), view);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.setOnFinished(e -> {
                // Bersihkan layer sebelumnya setelah transisi selesai
                rootContainer.getChildren().removeIf(node -> node != view && !isModal(node));
            });
            fadeIn.play();

        } catch (Throwable e) {
            System.err.println("Gagal memuat FXML dari path: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static void showModal(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
            Parent modal = loader.load();
            modal.setId("MODAL_OVERLAY");
            modal.setOpacity(0.0);

            rootContainer.getChildren().add(modal);

            FadeTransition ft = new FadeTransition(Duration.millis(250), modal);
            ft.setToValue(1.0);
            ft.play();
        } catch (Throwable e) {
            System.err.println("Gagal memuat Modal FXML: " + fxmlPath);
            e.printStackTrace();
        }
    }

    public static void closeModal() {
        rootContainer.getChildren().removeIf(node -> "MODAL_OVERLAY".equals(node.getId()));
    }

    private static boolean isModal(javafx.scene.Node node) {
        return "MODAL_OVERLAY".equals(node.getId());
    }

    public static void switchToLoading() {
        loadView("/views/loading.fxml");
    }

    public static void switchToMainMenu() {
        loadView("/views/main_menu.fxml");
    }

    public static void switchToGameplay() {
        loadView("/views/gameplay.fxml");
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}

