package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

public class GameManager {
    private static final GameManager ourInstance = new GameManager();

    public GameData gameData;
    private Json json = new Json();
    private FileHandle fileHandle = Gdx.files.local("bin/GameData.json");
    public boolean gameStartedFromMainMenu, isPaused = true;
    public int lifeScore, coinScore, score;
    private Music music;
    private Sound clickSound;

    private GameManager() {
    }

    public void initializeGameData() {
        if(!fileHandle.exists()) {
            gameData = new GameData();
            gameData.setHighscore(0);
            gameData.setCoinHighScore(0);
            gameData.setEasyDiff(false);
            gameData.setMediumDiff(true);
            gameData.setHardDiff(false);
            gameData.setMusicOn(true);
            saveData();
        } else {
            loadData();
        }
    }

    public void saveData() {
        if(gameData != null) {
            fileHandle.writeString(Base64Coder.encodeString(json.prettyPrint(gameData)), false);
        }
    }

    public void loadData() {
        gameData = json.fromJson(GameData.class, Base64Coder.decodeString(fileHandle.readString()));
    }

    public void checkForNewHighscores() {
        if(gameData.getHighscore() < score) {
            gameData.setHighscore(score);
        }

        if(gameData.getCoinHighScore() < coinScore) {
            gameData.setCoinHighScore(coinScore);
        }
        saveData();
    }

    public void playMusic() {
        if(music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Background.mp3"));
        }
        if(!music.isPlaying()){
            music.play();
            music.setLooping(true);
        }
        if(clickSound == null) {
            clickSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/Click Sound.wav"));
        }
    }

    public void stopMusic() {
        if(music.isPlaying()) {
            music.stop();
            music.dispose();

        }
        if(music != null) {
            music = null;
        }
        if(clickSound != null) {
            clickSound.dispose();
            clickSound = null;
        }
    }

    public void setMusicVolume(float volume){
        music.setVolume(volume);
    }

    public void playClickSound() {
        if(gameData.isMusicOn()){
            clickSound.play();
        }
    }

    public static GameManager getInstance() {
        return ourInstance;
    }

}
