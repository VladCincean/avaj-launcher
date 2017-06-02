package ro.academyplus.avaj.simulator.vehicles;

/**
 * Created by vlad on 31/05/2017.
 */
public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = Math.max(longitude, 0);
        this.latitude = Math.max(latitude, 0);
        this.height = Math.max(Math.min(height, 100), 0);
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }
}
