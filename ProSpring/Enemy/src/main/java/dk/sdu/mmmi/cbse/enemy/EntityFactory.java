package dk.sdu.mmmi.cbse.enemy;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.BehaviourEnum;
import static dk.sdu.mmmi.cbse.common.data.BehaviourEnum.MOVE_RANDOM;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.ENEMY;
import dk.sdu.mmmi.cbse.common.data.Health;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Radius;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.data.View;

public class EntityFactory {

    public static Entity createEnemyShip() {
        Entity enemyShip = new Entity();
        context(enemyShip).add(EntityType.class, ENEMY);
        context(enemyShip).add(Health.class, new Health(5));
        context(enemyShip).add(BehaviourEnum.class, MOVE_RANDOM);
        context(enemyShip).add(View.class, new View("images/EnemyShip.png"));
        context(enemyShip).add(Position.class, new Position(160, 80));
        context(enemyShip).add(Rotation.class, new Rotation());
        context(enemyShip).add(Velocity.class, new Velocity(1, 1));
        context(enemyShip).add(Scale.class, new Scale());
        context(enemyShip).add(Radius.class, new Radius(10));
        return enemyShip;
    }

}
