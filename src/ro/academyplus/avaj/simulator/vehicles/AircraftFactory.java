package ro.academyplus.avaj.simulator.vehicles;

import ro.academyplus.avaj.exception.UnknownAircraftTypeException;

/**
 * Created by vlad on 31/05/2017.
 */
public abstract class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws UnknownAircraftTypeException {
        Flyable flyable = null;

        switch (type) {
            case "Baloon":
                flyable = new Baloon(name, new Coordinates(longitude, latitude, height));
                break;
            case "JetPlane":
                flyable = new JetPlane(name, new Coordinates(longitude, latitude, height));
                break;
            case "Helicopter":
                flyable = new Helicopter(name, new Coordinates(longitude, latitude, height));
                break;
            default:
                flyable = null;
                break;
        }

        if (flyable == null) {
            throw new UnknownAircraftTypeException("Unknown aircraft type: " + type);
        }

        return flyable;
    }
}
