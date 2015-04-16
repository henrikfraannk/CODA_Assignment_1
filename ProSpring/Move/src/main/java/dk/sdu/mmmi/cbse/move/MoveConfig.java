package dk.sdu.mmmi.cbse.move;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author jcs
 */
@Configuration
public class MoveConfig {

    @Bean
    @Scope(value = "prototype")
    public IEntityProcessingService createMoveProcessingService() {
        return new MoveSystem();
    }
}
