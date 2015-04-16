package dk.sdu.mmmi.cbse.expiration;

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
public class ExpirationConfig {

    @Bean
    @Scope(value = "prototype")
    public IEntityProcessingService createExpirationProcessingService() {
        return new ExpirationSystem();
    }
}
