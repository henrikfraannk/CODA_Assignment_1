package dk.sdu.mmmi.cbse.enemy;

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
public class EnemyConfig {

    @Bean
    @Scope(value = "prototype")
    public IEntityProcessingService createEnemyProcessingService() {
        return new EnemyProcessingService();
    }

    @Bean
    @Scope(value = "prototype")
    public IGamePluginService createEnemyPluginService() {
        return new EnemyPlugin();
    }
}
