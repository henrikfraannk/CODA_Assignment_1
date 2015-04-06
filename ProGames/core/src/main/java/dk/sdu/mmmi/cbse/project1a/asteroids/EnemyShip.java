package dk.sdu.mmmi.cbse.project1a.asteroids;

import dk.sdu.mmmi.cbse.project1a.engine.Body;
import dk.sdu.mmmi.cbse.project1a.engine.Entity;
import dk.sdu.mmmi.cbse.project1a.engine.Health;
import dk.sdu.mmmi.cbse.project1a.engine.Physics;
import dk.sdu.mmmi.cbse.project1a.events.Events;
import playn.core.Image;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

public class EnemyShip extends Entity {

    private int turnDirection = 1;

    public EnemyShip() {

        Image enemyShipImage = assets().getImageSync("images/EnemyShip.png");
        view = graphics().createImageLayer(enemyShipImage);
        view.setOrigin(enemyShipImage.width() / 2f,
                enemyShipImage.height() / 2f);

        body = new Body(this);
        body.x = 360;
        body.y = 280;
        body.radius = enemyShipImage.height() / 2f;

        physics = new Physics(this);
        physics.drag = 0.9;

        health = new Health(this);
        health.hits = 1;

        weapon = new Gun(this);

    }

    @Override
    public void onHurt(Events.HurtEvent hurtEvent) {
        health.hit(hurtEvent.source(), 1);
    }

    @Override
    public void onUpdate(Events.UpdateEvent event) {

        super.onUpdate(event);

        if (Math.random() < 0.05) {
            turnDirection = -turnDirection;
        }

        body.angle += turnDirection * 0.05;

        physics.thrust(Math.random());

        if (Math.random() < 0.02) {
            weapon.fire();
        }
    }
}
