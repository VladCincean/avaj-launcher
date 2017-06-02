package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.exception.UnknownWeatherException;
import ro.academyplus.avaj.simulator.vehicles.Coordinates;

/**
 * Created by vlad on 31/05/2017.
 */
public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() throws UnknownWeatherException {
        WeatherProvider.getProvider().changeWeather();
        super.conditionsChanged();
    }
}
