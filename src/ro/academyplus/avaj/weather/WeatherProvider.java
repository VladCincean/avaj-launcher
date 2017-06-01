package ro.academyplus.avaj.weather;

import ro.academyplus.avaj.simulator.vehicles.Coordinates;

/**
 * Created by vlad on 31/05/2017.
 */
public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
        weatherProvider = this;
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        // TODO: this...
        return null;
    }
}
