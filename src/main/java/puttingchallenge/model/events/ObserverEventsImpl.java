package puttingchallenge.model.events;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that implements an event observer of type {@link ModelEventType}.
 * @param <A>
 */
public class ObserverEventsImpl<A> implements ObserverEvents<A> {

   private final List<A> events = new LinkedList<>();

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
