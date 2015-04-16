/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroids;

import com.decouplink.Context;
import com.decouplink.DisposableList;
import com.decouplink.Link;
import static com.decouplink.Utilities.context;
import static dk.sdu.mmmi.cbse.asteroids.EntityFactory.createAsteroid;
import dk.sdu.mmmi.cbse.common.data.BehaviourEnum;
import static dk.sdu.mmmi.cbse.common.data.BehaviourEnum.HIT;
import static dk.sdu.mmmi.cbse.common.data.BehaviourEnum.MOVE_LINEAR;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ASTEROIDS;
import dk.sdu.mmmi.cbse.common.data.Health;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.View;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.project1a.engine.Body;

/**
 *
 * @author henrikfrank
 */
public class AsteroidProcessingService implements IEntityProcessingService{
    
    private float direction = (float) 180.0;
    private Object entities;

    @Override
    public void process(Object world, Entity entity) {
        Context entityCtx = context(entity);
        Rotation rotation = context(entity).one(Rotation.class);
        Velocity velocity = context(entity).one(Velocity.class);
        Position position = context(entity).one(Position.class);
        Scale scale = context(entity).one(Scale.class);
        Health health = context(entity).one(Health.class);
        Body body = context(entity).one(Body.class);
            
        if (entityCtx.one(EntityType.class).equals(ASTEROIDS)) {

            for (BehaviourEnum behaviour : entityCtx.all(BehaviourEnum.class)) {

                if (behaviour.equals(MOVE_LINEAR)) {

                    rotation.angle = direction;

                    velocity.vectorX = (float)Math.sin(rotation.angle);
                    velocity.vectorY = (float)Math.cos(rotation.angle);
                }
                if (behaviour.equals(HIT)) {

                    Health h = entityCtx.one(Health.class);

                    h.addDamage(1);

                    if (!h.isAlive()) {
                        entity.setDestroyed(true);
                    }
                    
                    if(h.getHealth() == 5){
                        
                        scale.x = (float) 0.5;
                        scale.y = (float) 0.5;
                            
                        DisposableList entities = new DisposableList();
                        
                        Entity e = createAsteroid();
                        Link<Entity> el = context(world).add(Entity.class, e);
                        entities.add(el);

                        context(e).one(Position.class).x = position.x+50;
                        context(e).one(Position.class).y = position.y+50;
                        context(e).one(Scale.class).x = scale.x;
                        context(e).one(Scale.class).y = scale.y;
                        context(e).one(Health.class).addDamage(3);
                    }

                    context(entity).remove(behaviour);
                }
            }
        }
    }
    
}
