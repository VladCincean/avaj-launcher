package ro.academyplus.avaj.simulator;

import ro.academyplus.avaj.exception.AvajLauncherException;
import ro.academyplus.avaj.exception.ScenarioFileException;
import ro.academyplus.avaj.simulator.vehicles.AircraftFactory;
import ro.academyplus.avaj.simulator.vehicles.Flyable;
import ro.academyplus.avaj.util.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 31/05/2017.
 */
public class Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java ro.academyplus.avaj.simulator.Simulator scenario.txt");
            System.exit(0);
        }

        try {
            File file = new File(args[0]);
            if (!file.exists()) {
                System.out.println("File " + args[0] + " does not exist.");
                System.exit(1);
            }
            if (file.isDirectory()) {
                System.out.println("File " + args[0] + " is a actually directory, not a regular file.");
                System.exit(1);
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = 0;
                try {
                    simulations = Integer.parseInt(line.split(" ")[0]);
                } catch (NumberFormatException e) {
                    throw new ScenarioFileException("Invalid Scenario file: integer expected.");
                }
                if (simulations < 0) {
                    System.out.println("Invalid simulation count " + simulations);
                    System.exit(1);
                }
                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.split(" ");

                    if (tokens.length != 5) {
                        throw new ScenarioFileException("Invalid scenario file: aircraft description should be TYPE NAME LONGITUDE LATITUDE HEIGHT");
                    }

                    try {
                        Flyable flyable = AircraftFactory.newAircraft(
                                tokens[0],
                                tokens[1],
                                Integer.parseInt(tokens[2]),
                                Integer.parseInt(tokens[3]),
                                Integer.parseInt(tokens[4])
                        );
                        flyables.add(flyable);
                    } catch (NumberFormatException e) {
                        throw new ScenarioFileException("Invalid Scenario file: integer expected.");
                    }
                }

                for (Flyable flyable : flyables) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= simulations; i++) {
                    weatherTower.changeWeather();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file " + args[0]);
        } catch (IOException e) {
            System.out.println("There was an error while reading the file " + args[0]);
        } catch (AvajLauncherException e) {
            System.out.println("AvajLauncherException caught: " + e.getMessage());
        } finally {
            Logger.getLogger().close();
        }
    }
}
