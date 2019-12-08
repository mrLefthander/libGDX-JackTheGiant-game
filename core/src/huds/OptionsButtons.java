package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lefthander.game.GameMain;

import helpers.GameInfo;
import helpers.GameManager;
import scenes.MainMenu;

public class OptionsButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton backBtn;
    private ImageButton easy, medium, hard;
    private Image sign;

    public OptionsButtons(GameMain game){
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);

        crateAndPositionButtons();
        addAllListeners();

        stage.addActor(backBtn);
        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(hard);
        stage.addActor(sign);

    }

    void crateAndPositionButtons() {
        easy = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Options Menu/Easy.png"))));
        medium = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Options Menu/Medium.png"))));
        hard = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Options Menu/Hard.png"))));
        backBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Options Menu/Back.png"))));

        sign = new Image(new Texture("Buttons/Options Menu/Check Sign.png"));

        easy.setPosition(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 + 40, Align.center);
        medium.setPosition(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 - 40, Align.center);
        hard.setPosition(GameInfo.WIDTH / 2, GameInfo.HEIGHT / 2 - 120, Align.center);
        backBtn.setPosition(17, 17, Align.bottomLeft);
        positionTheSign();
    }

    void addAllListeners() {

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().playClickSound();
                game.setScreen(new MainMenu(game));
            }
        });

        easy.addListener(new ChangeListener() {
                @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeDiff(0);
                GameManager.getInstance().playClickSound();
                sign.setY(easy.getY() + 13);
            }
        });

        medium.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeDiff(1);
                GameManager.getInstance().playClickSound();
                sign.setY(medium.getY() + 13);
            }
        });

        hard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                changeDiff(2);
                GameManager.getInstance().playClickSound();
                sign.setY(hard.getY() + 13);
            }
        });
    }

    void positionTheSign() {
        if(GameManager.getInstance().gameData.isEasyDiff()){
            sign.setPosition(GameInfo.WIDTH / 2f + 76, easy.getY() + 13, Align.bottomLeft);
        } else if(GameManager.getInstance().gameData.isHardDiff()){
            sign.setPosition(GameInfo.WIDTH / 2f + 76, hard.getY() + 13, Align.bottomLeft);
        } else {
            sign.setPosition(GameInfo.WIDTH / 2f + 76, medium.getY() + 13, Align.bottomLeft);
        }

    }

    void changeDiff (int difficulty) {
        switch (difficulty){
            case 0:
                GameManager.getInstance().gameData.setEasyDiff(true);
                GameManager.getInstance().gameData.setMediumDiff(false);
                GameManager.getInstance().gameData.setHardDiff(false);
                break;
            case 1:
                GameManager.getInstance().gameData.setEasyDiff(false);
                GameManager.getInstance().gameData.setMediumDiff(true);
                GameManager.getInstance().gameData.setHardDiff(false);
                break;
            case 2:
                GameManager.getInstance().gameData.setEasyDiff(false);
                GameManager.getInstance().gameData.setMediumDiff(false);
                GameManager.getInstance().gameData.setHardDiff(true);
                break;
        }
        GameManager.getInstance().saveData();
    }

    public Stage getStage() {
        return this.stage;
    }
}
