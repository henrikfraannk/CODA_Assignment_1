package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author jcs
 */
@Configuration
public class CollisionConfig {

    @Bean
    @Scope(value = "prototype")
    public IEntityProcessingService createCollisionProcessingService() {
        return new CollisionSystem();
    }
}
