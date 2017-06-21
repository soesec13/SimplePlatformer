package game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import game.world.entity.MapEntity;

/**
 * Created by Sebi on 17/06/2017.
 */
public class WorldCamera extends OrthographicCamera {
    private MapEntity target;
    private float mapWidth,mapHeight;
    public WorldCamera()
    {
        this.setToOrtho(false, 30,20);
    }

    public void setTarget(MapEntity entity)
    {
        target = entity;
    }

    public void setMapSize(float width, float height)
    {
        this.mapHeight = height;
        this.mapWidth  = width;
    }

    public void move (float x, float y)
    {
        this.position.x += x;
        this.position.y += y;
    }

    @Override
    public void update() {
        super.update();
        if(target!=null) {
            float evenX = 0.0f;
            float evenY = 0.0f;

            if ( this.viewportWidth  % 2 != 0 ) evenX = 0.5f;
            if ( this.viewportHeight % 2 != 0 ) evenY = 0.5f;

            this.position.x = target.getBounds().x + target.getBounds().width/2+evenX;
            this.position.y = target.getBounds().y + target.getBounds().height/2+evenY;
            if(position.x + viewportWidth/2 > mapWidth)
            {
                position.x = mapWidth - viewportWidth/2;
            }
            if(position.y + viewportHeight/2 > mapHeight)
            {
                position.y = mapHeight - viewportHeight/2;
            }
            if(position.x - viewportWidth/2 < 0)
            {
                position.x = viewportWidth/2;
            }
            if(position.y - viewportHeight/2 < 0)
            {
                position.y = viewportHeight/2;
            }
        }
    }


}
