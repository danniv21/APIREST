package pe.com.claro.common.resource.exception;

public class BadUsageException extends ApiException {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   private int code;
     public BadUsageException (int code, String msg) {
       super(code, msg);
       this.code = code;
     }    
}
