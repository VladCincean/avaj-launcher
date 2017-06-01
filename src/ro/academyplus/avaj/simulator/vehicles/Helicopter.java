package ro.academyplus.avaj.simulator.vehicles;

import ro.academyplus.avaj.simulator.WeatherTower;
import ro.academyplus.avaj.util.Logger;

/**
 * Created by vlad on 31/05/2017.
 */
public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.weatherTower = null;
    }

    public void updateConditions() {
        String weather = this.weatherTower.getWeather(super.coordinates);
        Coordinates newCoordinates = null;

        switch (weather) {
            case "SUN":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude() + 10,
                        super.coordinates.getLatitude(),
                        Math.min(super.coordinates.getHeight() + 2, 100)
                );
                Logger.getLogger().log(
                        "Helicopter#" + super.name + "(" + super.id + "): This is hot."
                );
                break;
            case "RAIN":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude() + 5,
                        super.coordinates.getLatitude(),
                        super.coordinates.getHeight()
                );
                Logger.getLogger().log(
                        "Helicopter#" + super.name + "(" + super.id + "): This could be us. But I don't go out when it's raining."
                );
                break;
            case "FOG":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude() + 1,
                        super.coordinates.getLatitude(),
                        super.coordinates.getHeight()
                );
                Logger.getLogger().log(
                        "Helicopter#" + super.name + "(" + super.id + "): Oh, no! Why is it so foggy?"
                );
                break;
            case "SNOW":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude() ,
                        Math.max(super.coordinates.getHeight() - 12, 0)
                );
                Logger.getLogger().log(
                        "Helicopter#" + super.name + "(" + super.id + "): My rotor is going to freeze."
                );
                break;
            default:
                newCoordinates = super.coordinates;
                break;
        }

        super.coordinates = newCoordinates;

        if (super.coordinates.getHeight() == 0) {
            Logger.getLogger().log("Helicopter#" + super.name + "(" + super.id + ") landing.");
            this.weatherTower.unregister(this);
            Logger.getLogger().log(
                    "Tower says: Helicopter#" + super.name + "(" + super.id + ") unregistered from weather tower."
            );
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().log(
                "Tower says: Helicopter#" + super.name + "(" + super.id + ") registered to weather tower."
        );
    }
}
