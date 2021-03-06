package puttingchallenge.model.events;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class that implements an event observer.
 * @param <A>
 */
public class ObserverEventsImpl<A> implements ObserverEvents<A> {

   private final List<A> events = new CopyOnWriteArrayList<>();;

   /**
    * {@inheritDoc}
    */
   @Override
   public void sendModelEvents(final List<A> types) {
       this.events.addAll(types);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public List<A> getEvents() {
       final List<A> copy = List.copyOf(this.events);
       this.events.clear();
       return copy;
   }
}
