package game.world;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import game.world.entity.MapEntity;

import java.util.ArrayList;

/**
 * Created by Sebi on 18/06/2017.
 */
public class CollisionHandler {
    private final ArrayList<game.world.entity.MapEntity> bodies = new ArrayList<MapEntity>();
    private WorldMap map;
    private final Pool<Rectangle> rectPool = new Pool<Rectangle>() {
        @Override
        protected Rectangle newObject() {
            return new Rectangle();
        }
    };
    private Array<Rectangle> tiles = new Array<Rectangle>();

    public void register(game.world.entity.MapEntity entity)
    {
        bodies.add(entity);
    }

    public void setMap(WorldMap map) {
        this.map = map;
    }

    public void update()
    {
        detectMapCollision();
        detectEntityCollision();
    }

    private void detectEntityCollision() {
        MapEntity entity1,entity2;
        for(int i = 0;i<bodies.size();i++)
        {
            entity1 = bodies.get(i);
            for(int j = 0;j<bodies.size();j++)
            {
                if(i == j) //Colliding with oneself is no advised
                {
                    continue;
                }
                entity2 = bodies.get(j);
                if(entity1.getBounds().overlaps(entity2.getBounds()))
                {
                    entity1.collideWith(entity2);
                }
            }
        }
    }

    private void detectMapCollision() {
        for(game.world.entity.MapEntity entity: bodies)
        {
            if(entity.isIgnoresTiles())
                continue;
            Vector2 velocity = entity.getVelocity();
            Rectangle bounds = entity.getBounds();
            Rectangle detection = rectPool.obtain();
            detection.set(bounds.x,bounds.y+velocity.y*WorldController.delta,bounds.width,bounds.height);

            //Detection Area
            float startX,startY,endX,endY;

            //Collide Horizontal
            float optionalHeight = velocity.y > 0 ? bounds.height:0;
            startY = endY = detection.y + optionalHeight;
            startX = detection.x;
            endX = detection.x+detection.width;

            getTiles(startX,startY,endX,endY,tiles);

            for(Rectangle tile:tiles)
            {
                if(detection.overlaps(tile))
                {
                    float velocityAdjustment = (velocity.y > 0? 0.5f:0);
                    entity.collideHorizontal((int) (bounds.y+velocityAdjustment));//+1-bounds.height
                    break;
                }
            }
            //Collide Vertical
            detection.set(bounds.x+velocity.x*WorldController.delta,bounds.y,bounds.width,bounds.height);
            float optionalWidth = velocity.x > 0 ? bounds.width:0;
            startX = endX = detection.x + optionalWidth;

            startY = detection.y;
            endY = detection.y + detection.height;
            getTiles(startX,startY,endX,endY,tiles);
            for(Rectangle tile:tiles)
            {
                if(detection.overlaps(tile))
                {
                    float velocityAdjustement = (velocity.x > 0? 0.5f:0);
                    entity.collideVertical((int) (bounds.x+velocityAdjustement));
                    break;
                }
            }
            rectPool.free(detection);
        }
    }

    private void getTiles(float sX, float sY, float eX, float eY, Array<Rectangle> tiles)
    {
        /*
        int startX = (int) (sX/32);
        int startY = (int) (sY/32);
        int endX = (int) (eX/32);
        int endY = (int) (eY/32);
        */
        int startX = (int) (sX);
        int startY = (int) (sY);
        int endX = (int) (eX);
        int endY = (int) (eY);
        rectPool.freeAll(tiles);
        tiles.clear();
        //System.out.format("Getting %d %d %d %d\n",startX,startY,endX,endY);
        for(int y = startY; y<=endY;y++)
        {
            for(int x=startX; x <= endX; x++)
            {
                TiledMapTileLayer.Cell cell = map.getCell(x,y);
                if(cell != null)
                {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x,y,1,1);
                    tiles.add(rect);
                }
            }
        }
    }


    public void clear() {
        bodies.clear();
    }
}
