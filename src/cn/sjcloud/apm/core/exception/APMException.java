package cn.sjcloud.apm.core.exception;

public class APMException extends Exception {

	private static final long serialVersionUID = -8890679550516924875L;

	public APMException() {
		super();
	}

	public APMException(String message, Throwable cause) {
		super(message, cause);
	}

	public APMException(String message) {
		super(message);
	}

	public APMException(Throwable cause) {
		super(cause);
	}

}
