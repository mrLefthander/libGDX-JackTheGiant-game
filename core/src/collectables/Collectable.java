package collectables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class Collectable extends Sprite {

    private World world;
    private Fixture fixture;
    private String name;
    private Body body;

    public Collectable(World world, String name){
        super(new Texture("Collectables/" + name + ".png"));
        this.world = world;
        this.name = name;

    }

    void createCollectableBody(){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(getX() / GameInfo.PPM,
                getY() / GameInfo.PPM);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth() / 2f) / GameInfo.PPM,
                (getHeight() / 2f) / GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.filter.categoryBits = GameInfo.COLLECTABLE;
        fixtureDef.isSensor = true;

        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(name);

        shape.dispose();


    }

    public void setCollectablePosition(float x, float y){
        setPosition(x, y);
        createCollectableBody();
    }
  /*  public void updateCollectable() {
        setPosition(body.getPosition().x * GameInfo.PPM, body.getPosition().y * GameInfo.PPM);
    }
    */
    public  void changeFilter() {
        Filter filter = new Filter();
        filter.categoryBits = GameInfo.DESTROYED;
        fixture.setFilterData(filter);
    }

    public Fixture getFixture() {
        return fixture;
    }

}
