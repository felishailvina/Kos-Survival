package com.kossurvival.controller;

import com.kossurvival.util.SoundManager;
import javafx.fxml.FXML;

/**
 * Controller untuk Modal Dialog Petunjuk & Ending.
 */
public class InstructionsModalController {

    @FXML
    private void handleCloseModal() {
        SoundManager.playClickSound();
        SceneNavigator.closeModal();
    }
}

