package exception;

import javax.servlet.ServletException;

public class ApplicationException extends ServletException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorMessage;

	public ApplicationException(String errorMessage) {
		this.setErrorMessage(errorMessage);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
