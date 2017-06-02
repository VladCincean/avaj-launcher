package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.exception.UnknownWeatherException;
import ro.academyplus.avaj.simulator.vehicles.Flyable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 31/05/2017.
 */
public abstract class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
    }

    protected void conditionsChanged() throws UnknownWeatherException {
        List<Flyable> copyOfObservers = new ArrayList<>();
        for (Flyable flyable : observers) {
            copyOfObservers.add(flyable);
        }
        for (Flyable flyable : copyOfObservers) {
            flyable.updateConditions();
        }
    }
}
