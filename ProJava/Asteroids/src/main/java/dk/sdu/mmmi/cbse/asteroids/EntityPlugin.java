/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author henrikfrank
 */

package dk.sdu.mmmi.cbse.asteroids;

import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import static dk.sdu.mmmi.cbse.asteroids.EntityFactory.createAsteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class EntityPlugin implements IGamePluginService{
    
    DisposableList entities = new DisposableList();

    @Override
    public void start(Object world) {
        
        // Add entities to the world
        Entity e = createAsteroid();
        Link<Entity> el = context(world).add(Entity.class, e);
        entities.add(el);
    }

    @Override
    public void stop() {
        // Remove entities
        entities.dispose();
    }
    
}
