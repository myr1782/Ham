package cn.sjcloud.apm.core.exception;

public class APMRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -8461878930980631479L;

	public APMRuntimeException() {
		super();
	}

	public APMRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public APMRuntimeException(String message) {
		super(message);
	}

	public APMRuntimeException(Throwable cause) {
		super(cause);
	}

}
