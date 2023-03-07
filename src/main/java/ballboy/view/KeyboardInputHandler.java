package ballboy.view;

import ballboy.model.GameEngine;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

class KeyboardInputHandler {
    private final GameEngine model;
    private boolean left = false;
    private boolean right = false;
    private final Set<KeyCode> pressedKeys = new HashSet<>();

//    private Map<String, MediaPlayer> sounds = new HashMap<>();

    KeyboardInputHandler(GameEngine model) {
        this.model = model;

        // TODO (longGoneUser): Is there a better place for this code?
        // TODO (bobbob): Move sound choice/production into the model before alpha is released to the new devs
        // TODO (bobbob): Just commenting this out while I debug my driver - don't forget to revert this before anyone
        // else sees this
//        URL mediaUrl = getClass().getResource("/jump.wav");
//        String jumpURL = mediaUrl.toExternalForm();
//
//        Media sound = new Media(jumpURL);
//        MediaPlayer mediaPlayer = new MediaPlayer(sound);
//        sounds.put("jump", mediaPlayer);
    }

    void handlePressed(KeyEvent keyEvent) {
        if (pressedKeys.contains(keyEvent.getCode())) {
            return;
        }
        pressedKeys.add(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.UP)) {
            if (model.boostHeight()) {
//                MediaPlayer jumpPlayer = sounds.get("jump");
//                jumpPlayer.stop();
//                jumpPlayer.play();
            }
        }

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = true;
        } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = true;
        } else {
            return;
        }

        if (left) {
            model.moveLeft();
        } else {
            model.moveRight();
        }
    }

    void handleReleased(KeyEvent keyEvent) {
        pressedKeys.remove(keyEvent.getCode());

        if (keyEvent.getCode().equals(KeyCode.LEFT)) {
            left = false;
        } else if (keyEvent.getCode().equals(KeyCode.RIGHT)) {
            right = false;
        } else {
            return;
        }

        if (!(right || left)) {
            model.dropHeight();
        } else if (right) {
            model.moveRight();
        } else {
            model.moveLeft();
        }
    }
}
