package dk.sdu.mmmi.cbse.core;

import com.decouplink.DisposableList;
import static com.decouplink.Utilities.context;
import dk.sdu.mmmi.cbse.common.data.BehaviourEnum;
import static dk.sdu.mmmi.cbse.common.data.BehaviourEnum.MOVE_DOWN;
import static dk.sdu.mmmi.cbse.common.data.BehaviourEnum.MOVE_UP;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.EntityType;
import static dk.sdu.mmmi.cbse.common.data.EntityType.PLAYER;
import dk.sdu.mmmi.cbse.common.data.GameTime;
import dk.sdu.mmmi.cbse.common.data.Position;
import dk.sdu.mmmi.cbse.common.data.Rotation;
import dk.sdu.mmmi.cbse.common.data.Scale;
import dk.sdu.mmmi.cbse.common.data.View;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Collection;
import playn.core.Game;
import playn.core.GroupLayer;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Keyboard;
import playn.core.PlayN;
import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;
import playn.core.util.Clock;

public class AsteroidsGame extends Game.Default {

    private final Clock.Source clock = new Clock.Source(33);
    private GroupLayer layer;
    private Object world = new Object();
    private Entity player;

    public AsteroidsGame() {
        super(33); // call update every 33ms (30 times per second)
    }

    @Override
    public void init() {

        // Add clock to world context
        context(world).add(GameTime.class, new GameTime());

        // Lookup all Game Plugins using ServiceLoader
        for (IGamePluginService iGamePlugin : getPluginServices()) {
            iGamePlugin.start(world);
        }

        // Keyboard listeners to player
        PlayN.keyboard().setListener(keyboardListener);

        for (Entity entity : context(world).all(Entity.class)) {
            if (context(entity).one(EntityType.class) == PLAYER) {
                this.player = entity;
            }
        }
        // create a group layer to hold everything
        layer = graphics().rootLayer();

        // create and add background image layer
        layer.add(graphics().createImmediateLayer(
                new StarRenderer(clock, world)));
    }

    @Override
    public void update(int delta) {

        clock.update(delta);
        context(world).one(GameTime.class).delta = delta;

        // Process each entity using provided processing services (i.e.,
        // ServiceLoader services)
        for (IEntityProcessingService entityProcessorService : getEntityProcessingServices()) {
            for (Entity e : context(world).all(Entity.class)) {
                entityProcessorService.process(world, e);
            }
        }
    }

    @Override
    public void paint(float alpha) {
        // the background automatically paints itself, so no need to do anything
        // here!
        clock.paint(alpha);

        for (Entity e : context(world).all(Entity.class)) {

            ImageLayer view = context(e).one(ImageLayer.class);

            Position p = context(e).one(Position.class);
            Rotation r = context(e).one(Rotation.class);
            Scale s = context(e).one(Scale.class);

            if (view == null) {
                view = createView(e);
            }
            view.setTranslation(p.x, p.y);
            view.setRotation(r.angle);
            view.setAlpha(1.0f);
            view.setScale(s.x, s.y);

            if (e.isDestroyed()) {
                layer.remove(view);
                context(world).remove(e);
            }
        }
    }

    private ImageLayer createView(Entity entity) {

        View sprite = context(entity).one(View.class);

        String imagePath = sprite.getImageFilePath();

        Image image = assets().getImageSync(imagePath);

        ImageLayer viewLayer = graphics().createImageLayer(image);
        viewLayer.setOrigin(image.width() / 2f, image.height() / 2f);

        context(entity).add(ImageLayer.class, viewLayer);
        layer.add(viewLayer);

        return viewLayer;
    }

    private Collection<? extends IGamePluginService> getPluginServices() {
        return SPILocator.locateAll(IGamePluginService.class);
    }

    private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return SPILocator.locateAll(IEntityProcessingService.class);
    }

    private final Keyboard.Listener keyboardListener = new Keyboard.Listener() {

        private final DisposableList disposables = new DisposableList();

        @Override
        public void onKeyDown(Keyboard.Event event) {
            switch (event.key()) {
                case W:
                    disposables.add(context(player).add(BehaviourEnum.class, MOVE_UP));
                    break;

                case S:
                    disposables.add(context(player).add(BehaviourEnum.class, MOVE_DOWN));
                    break;

                case A:
                    disposables.add(context(player).add(BehaviourEnum.class, BehaviourEnum.MOVE_LEFT));
                    break;

                case D:
                    disposables.add(context(player).add(BehaviourEnum.class, BehaviourEnum.MOVE_RIGHT));
                    break;

                case SPACE:
                    context(player).add(BehaviourEnum.class, BehaviourEnum.SHOOT);
                    break;

                default:
                    break;
            }
        }

        @Override
        public void onKeyTyped(Keyboard.TypedEvent te) {
        }

        @Override
        public void onKeyUp(Keyboard.Event event) {
            disposables.dispose();
        }
    };

}
