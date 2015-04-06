package dk.sdu.mmmi.cbse.project1a.asteroids;

import dk.sdu.mmmi.cbse.project1a.engine.Body;
import dk.sdu.mmmi.cbse.project1a.engine.Entity;
import dk.sdu.mmmi.cbse.project1a.engine.Physics;
import dk.sdu.mmmi.cbse.project1a.events.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.IEntityListener;
import dk.sdu.mmmi.cbse.project1a.events.Events.UpdateEvent;
import playn.core.Image;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

public class Bullet extends Entity {

    private int age;

    public Bullet() {

        Image bulletImage = assets().getImageSync("images/Bullet.png");
        view = graphics().createImageLayer(bulletImage);
        view.setOrigin(bulletImage.width() / 2f, bulletImage.height() / 2f);

        body = new Body(this);
        body.radius = 2;

        physics = new Physics(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bullet@");
        sb.append(this.hashCode());
        sb.append("={x=");
        sb.append(body.x);
        sb.append(",y=");
        sb.append(body.y);
        sb.append(", a=");
        sb.append(body.angle);
        sb.append("}");
        return sb.toString();
    }

    @Override
    public void onUpdate(UpdateEvent event) {

        super.onUpdate(event);

        age++;
        if (age > 20) {
            view.setAlpha(view.alpha() - 0.2f);
        }
        if (age > 25) {
            for (IEntityListener listener : listenerList) {
                listener.onDestroy(new DestroyEvent(Bullet.this));
            }
            // remove listener
            listenerList.clear();
        }
    }
}
