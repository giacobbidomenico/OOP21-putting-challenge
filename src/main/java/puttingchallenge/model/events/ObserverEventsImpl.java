package puttingchallenge.model.events;

import java.util.LinkedList;
import java.util.List;

/**
 * Class that implements an event observer of type {@link ModelEventType}.
 */
public class ObserverEventsImpl implements ObserverEvents {

   private final List<ModelEventType> events = new LinkedList<>();

   /**
    * {@inheritDoc}
    */
   @Override
   public void notifyEvents(final List<ModelEventType> types) {
       this.events.clear();
       this.events.addAll(types);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public List<ModelEventType> getEvents() {
       return this.events;
   }
}
