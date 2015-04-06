package dk.sdu.mmmi.cbse.enemy;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import static dk.sdu.mmmi.cbse.enemy.EntityFactory.createEnemyShip;

public class EntityPlugin implements IGamePluginService {

    DisposableList entities = new DisposableList();

    @Override
    public void start(Object world) {

        // Add entities to the world
        Entity e = createEnemyShip();
        Link<Entity> el = context(world).add(Entity.class, e);
        entities.add(el);
    }

    @Override
    public void stop() {
        // Remove entities
        entities.dispose();
    }

}
