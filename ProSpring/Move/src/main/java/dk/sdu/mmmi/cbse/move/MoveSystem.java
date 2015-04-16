package dk.sdu.mmmi.cbse.move;

import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Velocity;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

/**
 *
 * @author jcs
 */
public class MoveSystem implements IEntityProcessingService {

    @Override
    public void process(Object world, Entity entity) {

        Position pos = context(entity).one(Position.class);
        Velocity velocity = context(entity).one(Velocity.class);

        pos.x += velocity.vectorX;
        pos.y += velocity.vectorY;
    }

}
