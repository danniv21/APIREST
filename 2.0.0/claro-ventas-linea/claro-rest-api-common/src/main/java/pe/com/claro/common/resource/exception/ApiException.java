
package pe.com.claro.common.resource.exception;

public class ApiException extends RuntimeException{
  /**
	 * 
	 */
	private static final long serialVersionUID = -4463545099205456203L;
private int code;
  public ApiException (int code, String msg) {
    super(msg);
    this.code = code;
  }
}
