package ro.academyplus.avaj.simulator.vehicles;

import ro.academyplus.avaj.exception.UnknownWeatherException;
import ro.academyplus.avaj.simulator.WeatherTower;
import ro.academyplus.avaj.util.Logger;

/**
 * Created by vlad on 31/05/2017.
 */
public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.weatherTower = null;
    }

    public void updateConditions() throws UnknownWeatherException {
        String weather = this.weatherTower.getWeather(super.coordinates);
        Coordinates newCoordinates = null;

        switch (weather) {
            case "SUN":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude() + 10,
                        super.coordinates.getHeight() + 2
                );
                Logger.getLogger().log(
                        "JetPlane#" + super.name + "(" + super.id + "): Bright sun."
                );
                break;
            case "RAIN":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude() + 5,
                        super.coordinates.getHeight()
                );
                Logger.getLogger().log(
                        "JetPlane#" + super.name + "(" + super.id + "): It's raining. Better watch out for lightings."
                );
                break;
            case "FOG":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude() + 1,
                        super.coordinates.getHeight()
                );
                Logger.getLogger().log(
                        "JetPlane#" + super.name + "(" + super.id + "): The fog is killing me. Request permission to land."
                );
                break;
            case "SNOW":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude() ,
                        super.coordinates.getHeight() + 7
                );
                Logger.getLogger().log(
                        "JetPlane#" + super.name + "(" + super.id + "): OMG! Winter is coming!"
                );
                break;
            default:
                throw new UnknownWeatherException("Unknown weather: " + weather);
        }

        super.coordinates = newCoordinates;

        if (super.coordinates.getHeight() == 0) {
            Logger.getLogger().log("JetPlane#" + super.name + "(" + super.id + ") landing.");
            this.weatherTower.unregister(this);
            Logger.getLogger().log(
                    "Tower says: JetPlane#" + super.name + "(" + super.id + ") unregistered from weather tower."
            );
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().log(
                "Tower says: JetPlane#" + super.name + "(" + super.id + ") registered to weather tower."
        );
    }
}
