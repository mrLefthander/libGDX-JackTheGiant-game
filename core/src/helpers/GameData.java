package helpers;

public class GameData {

    private int highscore;
    private int coinHighScore;
    private boolean easyDiff, mediumDiff, hardDiff;
    private boolean musicOn;


    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public int getCoinHighScore() {
        return coinHighScore;
    }

    public void setCoinHighScore(int coinHighScore) {
        this.coinHighScore = coinHighScore;
    }

    public boolean isEasyDiff() {
        return easyDiff;
    }

    public void setEasyDiff(boolean easyDiff) {
        this.easyDiff = easyDiff;
    }

    public boolean isMediumDiff() {
        return mediumDiff;
    }

    public void setMediumDiff(boolean mediumDiff) {
        this.mediumDiff = mediumDiff;
    }

    public boolean isHardDiff() {
        return hardDiff;
    }

    public void setHardDiff(boolean hardDiff) {
        this.hardDiff = hardDiff;
    }

    public boolean isMusicOn() {
        return musicOn;
    }

    public void setMusicOn(boolean musicOn) {
        this.musicOn = musicOn;
    }
}
