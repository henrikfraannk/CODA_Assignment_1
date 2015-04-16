package dk.sdu.mmmi.cbse.asteroids;

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
public class AsteroidConfig {

    @Bean
    @Scope(value = "prototype")
    public IEntityProcessingService createAsteroidProcessingService() {
        return new AsteroidProcessingService();
    }

    @Bean
    @Scope(value = "prototype")
    public IGamePluginService createAsteroidPluginService() {
        return new AsteroidPlugin();
    }
}
