package dk.sdu.mmmi.cbse.project1a.asteroids;

import dk.sdu.mmmi.cbse.project1a.engine.IEntity;
import dk.sdu.mmmi.cbse.project1a.engine.Weapon;
import dk.sdu.mmmi.cbse.project1a.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.IEntityListener;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Gun extends Weapon {

    public Gun(IEntity entity) {
        super(entity);
    }

    @Override
    public void fire() {

        Bullet bullet = new Bullet();

        float c = 30.0f;
        float dy = (float) (c * sin(entity.body().angle));
        float dx = (float) (c * cos(entity.body().angle));

        bullet.body().x = entity.body().x + dx;

        bullet.body().y = entity.body().y + dy;

        bullet.body().angle = entity.body().angle;

        bullet.physics().thrust(10.0);

        // events
        for (IEntityListener l : entity.listeners()) {
            l.onCreate(new CreateEvent(this, bullet));
        }

        super.fire();
    }

}
