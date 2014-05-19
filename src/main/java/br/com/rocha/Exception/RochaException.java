package br.com.rocha.Exception;

/**
 * Classe responsável pelos erros de negocio da aplicação.
 * @author Lucas
 *
 */
public class RochaException extends Exception {

	private static final long serialVersionUID = -1282869437068222454L;

	public RochaException() {
		super();
	}

	public RochaException(String message, Throwable cause) {
		super(message, cause);
	}

	public RochaException(String message) {
		super(message);
	}

	public RochaException(Throwable cause) {
		super(cause);
	}
		
}
