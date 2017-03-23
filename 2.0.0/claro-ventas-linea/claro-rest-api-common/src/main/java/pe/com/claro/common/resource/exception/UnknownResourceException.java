package pe.com.claro.common.resource.exception;

public class UnknownResourceException extends FunctionalException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnknownResourceException(ExceptionType type) {
        super(type);
    }
    
    public UnknownResourceException(ExceptionType type, String message) {
        super(type, message);
    }     
    
}
