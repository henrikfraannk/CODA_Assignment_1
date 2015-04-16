package dk.sdu.mmmi.cbse.playersystem;

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
public class PlayerConfig {

    @Bean
    @Scope(value = "prototype")
    public IEntityProcessingService createPlayerProcessingService() {
        return new PlayerControlSystem();
    }

    @Bean
    @Scope(value = "prototype")
    public IGamePluginService createPlayerPluginService() {
        return new EntityPlugin();
    }
}
