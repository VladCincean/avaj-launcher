package ro.academyplus.avaj.exception;

/**
 * Created by vlad on 02/06/2017.
 */
public class UnknownWeatherException extends AvajLauncherException {
    public UnknownWeatherException() {
        super();
    }

    public UnknownWeatherException(String message) {
        super(message);
    }
}
