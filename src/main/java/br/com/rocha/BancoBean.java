package br.com.rocha;

import java.io.Serializable;

/**
 * Classe respons�vel em ter os atrinutos de Banco na camada View.
 * 
 * @author Lucas
 * 
 */
public class BancoBean implements Serializable {

	private Integer codigoBanco;

	private String nomeBanco;

	public Integer getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

}
