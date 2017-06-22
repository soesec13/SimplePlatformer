package game.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import game.game.MyGame;
import game.util.Clock;

/**
 * Created by Sebi on 22/06/2017.
 */
public class GameStage extends Stage {
    private final MyGame game;
    public final Clock clock;

    public GameStage(MyGame game) {
        this.game = game;
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        clock = new Clock(skin);
        addActor(clock);
        clock.start();
    }



    public void dispose() {

    }
}
