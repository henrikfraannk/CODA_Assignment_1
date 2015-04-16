package dk.sdu.mmmi.cbse.java;

import dk.sdu.mmmi.cbse.core.AsteroidsGame;
import playn.core.PlayN;
import playn.java.JavaPlatform;

public class AsteroidsGameJava {

    public static void main(String[] args) {
        JavaPlatform.Config config = new JavaPlatform.Config();
        // use config to customize the Java platform, if needed
        JavaPlatform.register(config);
        PlayN.run(new AsteroidsGame());
    }
}
