package pl.edu.pw.elka.gis.desp.io;

/**
 * An exception thrown when parsing input that contains improperly formatted weighted diagraph.
 */
public class WeightedDiagraphException extends Exception {

    /**
     * Create a new error exception.
     * @param msg error message
     */
    public WeightedDiagraphException(String msg) {
        super(msg);
    }


    /**
     * Create a new error exception.
     * @param msg error message
     * @param cause the cause of the exception
     */
    public WeightedDiagraphException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
