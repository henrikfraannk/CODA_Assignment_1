package dk.sdu.mmmi.cbse.updater;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author jcs
 */
@Configuration
public class UpdaterConfig {

    @Bean
    public UpdaterImpl createUpdater() {
        UpdaterImpl u = new UpdaterImpl();
        u.start();
        return u;
    }
}
