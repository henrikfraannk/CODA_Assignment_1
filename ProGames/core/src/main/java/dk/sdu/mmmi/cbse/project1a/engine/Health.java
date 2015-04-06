package dk.sdu.mmmi.cbse.project1a.engine;

import dk.sdu.mmmi.cbse.project1a.events.Events;
import dk.sdu.mmmi.cbse.project1a.events.Events.IEntityListener;

public class Health {

    private final IEntity entity;
    public Integer hits;

    public Health(IEntity entity) {
        this.entity = entity;
    }

    public void hit(IEntity source, Integer damage) {
        hits -= damage;

        if (hits <= 0) {
            for (IEntityListener l : entity.listeners()) {
                l.onDestroy(new Events.DestroyEvent(this.entity));
            }
        }
    }
}
