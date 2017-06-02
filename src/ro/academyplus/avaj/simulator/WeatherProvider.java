package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.simulator.vehicles.Coordinates;

import java.util.Random;

/**
 * Created by vlad on 31/05/2017.
 */
public class WeatherProvider {
    private static WeatherProvider weatherProvider = new WeatherProvider();
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private static int x1 = 1;
    private static int x2 = 1;
    private static int x3 = 1;
    private static Random randomGenerator = new Random();

    private WeatherProvider() {
        weatherProvider = this;
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int index = x1 * coordinates.getLatitude() + x2 * coordinates.getLongitude() + x3 * coordinates.getHeight();
        return weather[index % 4];
    }

    public void changeWeather() {
        x1 = randomGenerator.nextInt(1000);
        x2 = randomGenerator.nextInt(1000);
        x3 = randomGenerator.nextInt(1000);
    }
}
