package puttingchallenge.model.events;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class that implements an event observer of type {@link ModelEventType}.
 * @param <A>
 */
public class ObserverEventsImpl<A> implements ObserverEvents<A> {

   private final List<A> events = new CopyOnWriteArrayList<>();;

   /**
    * {@inheritDoc}
    */
   @Override
   public void notifyEvents(final List<A> types) {
       this.events.clear();
       this.events.addAll(types);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public List<A> getEvents() {
       return this.events;
   }
}
