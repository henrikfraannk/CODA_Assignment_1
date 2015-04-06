package dk.sdu.mmmi.cbse.project1a.events;

import dk.sdu.mmmi.cbse.project1a.engine.IEntity;
import java.util.EventListener;

public interface Events {

    class DestroyEvent {

        private final IEntity destroyedEntity;

        public DestroyEvent(IEntity destroyedEntity) {
            this.destroyedEntity = destroyedEntity;
        }

        public IEntity entity() {

            return this.destroyedEntity;
        }
    }

    class CreateEvent {

        private final IEntity createdEntity;
        private final Object eventSource;

        public CreateEvent(Object eventSource, IEntity createdEntity) {
            this.eventSource = eventSource;
            this.createdEntity = createdEntity;
        }

        public IEntity entity() {
            return this.createdEntity;
        }

        public Object eventSource() {
            return eventSource;
        }
    }

    class HurtEvent {

        private final IEntity target;
        private final IEntity source;

        public HurtEvent(IEntity source, IEntity target) {
            this.source = source;
            this.target = target;
        }

        public IEntity source() {
            return source;
        }

        public IEntity entity() {
            return target;
        }
    }

    class UpdateEvent {

        private final int delta;

        public UpdateEvent(int delta) {
            this.delta = delta;
        }

        public int delta() {
            return delta;
        }

    }

    class RenderEvent {

        public RenderEvent(float alpha) {
        }
    }

    interface IEntityListener extends EventListener {

        void onDestroy(DestroyEvent e);

        void onDestroyed();

        void onCreate(CreateEvent e);

        void onHurt(HurtEvent hurtEvent);

    }

    interface IEngineListener extends EventListener {

        void onUpdate(UpdateEvent event);

        void onRender(RenderEvent event);
    }

    class EntityListenerAdapter implements IEntityListener {

        @Override
        public void onDestroy(DestroyEvent destroyEvent) {
        }

        @Override
        public void onCreate(CreateEvent createEvent) {
        }

        @Override
        public void onHurt(HurtEvent hurtEvent) {
        }

        @Override
        public void onDestroyed() {
        }
    }

    class EngineListenerAdapter implements IEngineListener {

        @Override
        public void onUpdate(UpdateEvent event) {
        }

        @Override
        public void onRender(RenderEvent event) {
        }

    }
}
