package dk.sdu.mmmi.cbse.project1a.asteroids;

import dk.sdu.mmmi.cbse.project1a.engine.IEntity;
import dk.sdu.mmmi.cbse.project1a.events.Events;
import dk.sdu.mmmi.cbse.project1a.events.Events.CreateEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.DestroyEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.EntityListenerAdapter;
import dk.sdu.mmmi.cbse.project1a.events.Events.RenderEvent;
import dk.sdu.mmmi.cbse.project1a.events.Events.UpdateEvent;
import java.util.ArrayList;
import java.util.List;
import playn.core.GroupLayer;
import static playn.core.PlayN.graphics;
import playn.core.util.Clock;

public class AsteroidsWorld extends EntityListenerAdapter {

    private final List<IEntity> entities = new ArrayList<IEntity>();
    private final GroupLayer layer;
    private final Clock.Source clock = new Clock.Source(33);

    public AsteroidsWorld() {

        // create a group layer to hold everything
        layer = graphics().rootLayer();

        // create and add background image layer
        layer.add(graphics().createImmediateLayer(new StarRenderer(clock)));
    }

    public void update(int delta) {
        clock.update(delta);

        for (int i = 0; i < entities.size(); i++) {
            for (int j = i + 1; j < entities.size(); j++) {
                IEntity source = entities.get(i);
                IEntity target = entities.get(j);

                if (target != null && testCollision(source, target)) {
                    target.onHurt(new Events.HurtEvent(source, target));
                    source.onHurt(new Events.HurtEvent(target, source));
                }
            }
        }

        for (IEntity e : new ArrayList<IEntity>(entities)) {
            e.onUpdate(new UpdateEvent(delta));
        }
    }

    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything
        // here!
        clock.paint(alpha);

        for (IEntity e : entities) {
            e.onRender(new RenderEvent(alpha));
        }
    }

    private boolean testCollision(IEntity source, IEntity target) {
        float dx;
        float dy;

        dx = source.body().x - target.body().x;
        dy = source.body().y - target.body().y;

        double dist = Math.sqrt((dx * dx) + (dy * dy));
        boolean isCollision = dist <= source.body().radius
                + target.body().radius;

        if (isCollision) {
            System.out.println(String.format(
                    "%s hits %s, dist=%s, totalRadius=%s", source, target,
                    dist, source.body().radius
                    + target.body().radius));
        }

        return isCollision;
    }

    @Override
    public void onCreate(CreateEvent createEvent) {
        IEntity e = createEvent.entity();
        e.addEntityListener(this);
        entities.add(e);
        layer.add(e.view());
    }

    @Override
    public void onDestroy(DestroyEvent destroyEvent) {
        IEntity entityDestroyed = destroyEvent.entity();
        entities.remove(entityDestroyed);
        layer.remove(entityDestroyed.view());
        entityDestroyed.onDestroyed();
    }
}
