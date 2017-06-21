package game.screens.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import game.game.MyGame;
import game.world.WorldController;

import java.util.Collection;

/**
 * Created by Sebi on 21/06/2017.
 */
public class LevelSelectStage extends Stage {
    private final Table levelTable;
    private final WorldController controller;
    private final MyGame game;

    public LevelSelectStage(Collection<String> levelNames, final WorldController controller, final MyGame game) {
        super();
        this.controller = controller;
        this.game = game;
        final ClickListener listener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                load(event.getListenerActor().getName());
            }
        };
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        levelTable = new Table(skin);


        for(String name:levelNames)
        {
            TextButton b = new TextButton(extractMapName(name),skin);
            b.setName(name);
            b.addListener(listener);
            levelTable.add(b).pad(10).fillY().align(Align.top);
            levelTable.row();
        }

        setPositions();

        this.addActor(levelTable);
    }

    public void setPositions()
    {
        levelTable.setPosition(
                Gdx.graphics.getWidth() /2-levelTable.getWidth() /2,
                Gdx.graphics.getHeight()/2-levelTable.getHeight()/2);
    }

    private void load(String name)
    {
        this.controller.loadMap(name);
        this.game.transitTo(game.gameScreen);
    }

    private static String extractMapName(String path)
    {
        String[] parts = path.split("/");
        String fileName = parts[parts.length-1];
        String withoutFileExtension = fileName.split("\\.")[0];
        return withoutFileExtension;
    }
}
