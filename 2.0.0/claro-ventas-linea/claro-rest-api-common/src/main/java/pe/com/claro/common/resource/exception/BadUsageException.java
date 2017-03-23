package pe.com.claro.common.resource.exception;

public class BadUsageException extends FunctionalException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadUsageException(ExceptionType type) {
        super(type);
    }
    
    public BadUsageException(ExceptionType type, String message) {
        super(type, message);
    }     
    
}
