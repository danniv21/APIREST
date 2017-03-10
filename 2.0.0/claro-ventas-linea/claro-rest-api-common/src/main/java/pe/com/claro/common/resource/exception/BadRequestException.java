

package pe.com.claro.common.resource.exception;

public class BadRequestException extends ApiException{
  /**
	 * 
	 */
	private static final long serialVersionUID = -7482288873992395827L;
private int code;
  public BadRequestException (int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
