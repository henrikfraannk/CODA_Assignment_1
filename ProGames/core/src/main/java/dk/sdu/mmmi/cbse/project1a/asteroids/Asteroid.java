package dk.sdu.mmmi.cbse.project1a.asteroids;

import dk.sdu.mmmi.cbse.project1a.engine.Body;
import dk.sdu.mmmi.cbse.project1a.engine.Entity;
import dk.sdu.mmmi.cbse.project1a.engine.Health;
import dk.sdu.mmmi.cbse.project1a.engine.Physics;
import dk.sdu.mmmi.cbse.project1a.events.Events;
import dk.sdu.mmmi.cbse.project1a.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.HurtEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.IEntityListener;
import playn.core.Image;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

public class Asteroid extends Entity {

    private static final int MIN_ASTEROID_SIZE = 20;
    private static final double Y_START_POS = 600.0;
    private static final double X_START_POS = 800.0;

    public Asteroid() {

        Image asteroidImage = assets().getImageSync("images/Asteroid.png");
        view = graphics().createImageLayer(asteroidImage);

        view.setOrigin(asteroidImage.width() / 2f, asteroidImage.height() / 2f);
        view.setSize(asteroidImage.width(), asteroidImage.height());

        body = new Body(this);
        body.x = (float) (Math.random() * X_START_POS);
        body.y = (float) (Math.random() * Y_START_POS);
        body.radius = asteroidImage.width() / 2f;

        physics = new Physics(this);

        // A little bit random velocity
        physics.velocityX = (Math.random() * 10) - 5;
        physics.velocityY = (Math.random() * 10) - 5;

        health = new Health(this);
        health.hits = 2;
    }

    @Override
    public void onHurt(HurtEvent event) {

        // Resize
        view.setSize(0.5f * view.width(), 0.5f * view.height());
        body.radius = view.width() / 2f;

        if (body.radius < MIN_ASTEROID_SIZE) {
            for (IEntityListener listener : listenerList) {
                listener.onDestroy(new Events.DestroyEvent(this));
            }
            return;
        } else {
            health.hit(event.source(), 1);
        }

        // Create new Asteroid if the asteroid itself is damaged
        Asteroid newaAsteroid = new Asteroid();
        newaAsteroid.body.x = (float) (body.x + (Math.random() * X_START_POS));
        newaAsteroid.body.y = (float) (body.y + (Math.random() * Y_START_POS));
        newaAsteroid.body.radius = body.radius;
        newaAsteroid.view.setSize(view.width(), view.height());

        // Publish event
        for (IEntityListener listener : listenerList) {
            listener.onCreate(new CreateEvent(this, newaAsteroid));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Asteroid@");
        sb.append(this.hashCode());
        sb.append("={x=");
        sb.append(body.x);
        sb.append(",y=");
        sb.append(body.y);
        sb.append(", a=");
        sb.append(body.angle);
        sb.append(", r=");
        sb.append(body.radius);
        sb.append("}");
        return sb.toString();
    }
}
