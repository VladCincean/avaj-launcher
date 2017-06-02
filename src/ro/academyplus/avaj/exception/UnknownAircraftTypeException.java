package ro.academyplus.avaj.exception;

/**
 * Created by vlad on 02/06/2017.
 */
public class UnknownAircraftTypeException extends AvajLauncherException {
    public UnknownAircraftTypeException() {
        super();
    }

    public UnknownAircraftTypeException(String message) {
        super(message);
    }
}
