package dk.sdu.mmmi.cbse.project1a.asteroids;

import dk.sdu.mmmi.cbse.project1a.engine.IEntity;
import dk.sdu.mmmi.cbse.project1a.events.Events;
import playn.core.Game;

public class AsteroidsGame extends Game.Default {

    private AsteroidsWorld world;

    public AsteroidsGame() {
        super(33); // call update every 33ms (30 times per second)
    }

    @Override
    public void init() {

        world = new AsteroidsWorld();

        // add Player
        IEntity ship = new Ship();
        world.onCreate(new Events.CreateEvent(this, ship));

        // add enemy ship
        EnemyShip enemyShip = new EnemyShip();
        world.onCreate(new Events.CreateEvent(this, enemyShip));

        // // add Asteroids
        for (int i = 0; i < 2; i++) {
            IEntity asteroid = new Asteroid();
            world.onCreate(new Events.CreateEvent(this, asteroid));
        }
    }

    @Override
    public void update(int delta) {
        world.update(delta);
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything
        // here!
        world.paint(alpha);
    }
}
