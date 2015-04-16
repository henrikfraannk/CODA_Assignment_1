package dk.sdu.mmmi.cbse.updater;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author jcs
 */
public class UpdaterImpl implements ApplicationContextAware {

    private AnnotationConfigApplicationContext appContext;
    private final ScheduledExecutorService executor;

    public UpdaterImpl() {
        this.executor = Executors.newScheduledThreadPool(1);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = (AnnotationConfigApplicationContext) applicationContext;
    }

    public void start() {
        this.executor.scheduleAtFixedRate(updateTask, 10, 10, TimeUnit.SECONDS);
    }

    private final Runnable updateTask = new Runnable() {

        @Override
        public void run() {
            System.out.println("Scanning for new components...");

            //TODO: Run through all jar in plugins dir and add them dynamically to classpath
            //String addonClasspath = "/some/path/not/already/on/classpath";
            //ClassLoaderUtil.addFileToClassPath(addonClasspath);
            appContext.scan("dk.sdu");
            appContext.refresh();
        }
    };

}
