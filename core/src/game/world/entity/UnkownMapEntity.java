package game.world.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import game.world.WorldController;

public class UnkownMapEntity extends MapEntity{
    public UnkownMapEntity(WorldController controller) {
        super(controller);
        invisible = true;
    }

    @Override
    protected Sprite[] loadFrames() {
        return new Sprite[1];
    }

    @Override
    public void collideWith(MapEntity entity) {

    }

    @Override
    public void updateFrame() {

    }
}
