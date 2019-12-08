package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lefthander.game.GameMain;

import helpers.GameInfo;
import helpers.GameManager;
import scenes.MainMenu;

public class HighscoreButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton backBtn;
    private Label scoreLabel, coinLabel;

    public HighscoreButtons(GameMain game){
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());
        stage = new Stage(gameViewport, game.getBatch());

        Gdx.input.setInputProcessor(stage);
        crateAndPositionUIElements();
        stage.addActor(backBtn);
        stage.addActor(scoreLabel);
        stage.addActor(coinLabel);
    }

    void crateAndPositionUIElements() {
        backBtn = new ImageButton(new SpriteDrawable(
                new Sprite(new Texture("Buttons/Options Menu/Back.png"))));
        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("Fonts/blow.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter =
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 50;
        BitmapFont font = generator.generateFont(parameter);

        scoreLabel = new Label(String.valueOf(GameManager.getInstance().gameData.getHighscore()),
                new Label.LabelStyle(font, Color.WHITE));
        coinLabel = new Label(String.valueOf(GameManager.getInstance().gameData.getCoinHighScore()),
                new Label.LabelStyle(font, Color.WHITE));

        backBtn.setPosition(17, 17, Align.bottomLeft);


        scoreLabel.setPosition(GameInfo.WIDTH / 2f - 50, GameInfo.HEIGHT / 2f - 120);
        coinLabel.setPosition(GameInfo.WIDTH / 2f - 50, GameInfo.HEIGHT / 2f - 217);

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameManager.getInstance().playClickSound();
                game.setScreen(new MainMenu(game));
            }
        });



    }

    public Stage getStage() {
        return this.stage;
    }


}
