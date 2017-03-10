

package pe.com.claro.common.resource.exception;

public class NotFoundException extends ApiException {
  /**
	 * 
	 */
	private static final long serialVersionUID = 8992570382210399390L;
private int code;
  public NotFoundException (int code, String msg) {
    super(code, msg);
    this.code = code;
  }
}
