package ro.academyplus.avaj.simulator.vehicles;

import ro.academyplus.avaj.exception.UnknownWeatherException;
import ro.academyplus.avaj.simulator.WeatherTower;

/**
 * Created by vlad on 31/05/2017.
 */
public interface Flyable {

    void updateConditions() throws UnknownWeatherException;

    void registerTower(WeatherTower weatherTower);
}
