package DAOs;

import org.glassfish.grizzly.utils.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ExceptionDAO extends Exception {
    public ExceptionDAO(String message) {
        super(message);
    }
}
