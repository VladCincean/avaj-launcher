package ro.academyplus.avaj.simulator.vehicles;

import ro.academyplus.avaj.exception.UnknownWeatherException;
import ro.academyplus.avaj.simulator.WeatherTower;
import ro.academyplus.avaj.util.Logger;

/**
 * Created by vlad on 31/05/2017.
 */
public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.weatherTower = null;
    }

    public void updateConditions() throws UnknownWeatherException {
        String weather = this.weatherTower.getWeather(super.coordinates);
        Coordinates newCoordinates = null;

        switch (weather) {
            case "SUN":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude() + 2,
                        super.coordinates.getLatitude() ,
                        Math.min(super.coordinates.getHeight() + 4, 100)
                );
                Logger.getLogger().log(
                        "Baloon#" + super.name + "(" + super.id + "): Let's enjoy the good weather and take some pics."
                );
                break;
            case "RAIN":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude(),
                        Math.max(super.coordinates.getHeight() - 5, 0)
                );
                Logger.getLogger().log(
                        "Baloon#" + super.name + "(" + super.id + "): Damn you rain! You messed up my baloon."
                );
                break;
            case "FOG":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude(),
                        Math.max(super.coordinates.getHeight() - 3, 0)
                );
                Logger.getLogger().log(
                        "Baloon#" + super.name + "(" + super.id + "): Oh, no! Why is it so foggy?"
                );
                break;
            case "SNOW":
                newCoordinates = new Coordinates(
                        super.coordinates.getLongitude(),
                        super.coordinates.getLatitude() ,
                        super.coordinates.getHeight() - 15
                );
                Logger.getLogger().log(
                        "Baloon#" + super.name + "(" + super.id + "): It's snowing. We're gonna crash."
                );
                break;
            default:
                throw new UnknownWeatherException("Unknown weather: " + weather);
        }

        super.coordinates = newCoordinates;

        if (super.coordinates.getHeight() == 0) {
            Logger.getLogger().log("Baloon#" + super.name + "(" + super.id + ") landing.");
            this.weatherTower.unregister(this);
            Logger.getLogger().log(
                    "Tower says: Baloon#" + super.name + "(" + super.id + ") unregistered from weather tower."
            );
        }
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
        Logger.getLogger().log(
                "Tower says: Baloon#" + super.name + "(" + super.id + ") registered to weather tower."
        );
    }
}
