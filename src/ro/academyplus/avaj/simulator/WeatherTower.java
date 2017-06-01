package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.vehicles.Coordinates;
import ro.academyplus.avaj.weather.WeatherProvider;

/**
 * Created by vlad on 31/05/2017.
 */
public class WeatherTower extends Tower {

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() {
        this.conditionsChanged();
    }
}
