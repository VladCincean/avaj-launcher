package ro.academyplus.avaj.exception;

/**
 * Created by vlad on 02/06/2017.
 */
public class UnknownAircraftNameException extends AvajLauncherException {
    public UnknownAircraftNameException() {
        super();
    }

    public UnknownAircraftNameException(String message) {
        super(message);
    }
}
