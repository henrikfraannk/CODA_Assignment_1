/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroids;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.*;
import static dk.sdu.mmmi.cbse.common.data.BehaviourEnum.MOVE_LINEAR;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ASTEROIDS;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author henrikfrank
 */
public class EntityFactory {
    
    public static Entity createAsteroid(){
        ClassLoader cl = Lookup.getDefault().lookup(ClassLoader.class);
        String url = cl.getResource("assets/images/Asteroid.png").toExternalForm();
        
        Entity asteroid = new Entity();
        context(asteroid).add(EntityType.class, ASTEROIDS);
        context(asteroid).add(Health.class, new Health(8));
        context(asteroid).add(BehaviourEnum.class, MOVE_LINEAR);
        context(asteroid).add(View.class, new View(url));
        context(asteroid).add(Position.class, new Position(300,400));
        context(asteroid).add(Rotation.class, new Rotation());
        context(asteroid).add(Velocity.class, new Velocity());
        context(asteroid).add(Scale.class, new Scale());
        context(asteroid).add(Radius.class, new Radius(10));
        return asteroid;
    }
}
