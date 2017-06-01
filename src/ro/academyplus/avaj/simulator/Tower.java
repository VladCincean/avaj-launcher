package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.vehicles.Flyable;

import java.util.List;

/**
 * Created by vlad on 31/05/2017.
 */
public abstract class Tower {
    private List<Flyable> observers;

    public void register(Flyable flyable) {
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }
}
