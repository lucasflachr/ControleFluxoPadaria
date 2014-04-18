package br.com.rocha;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe respons�vel em representar os dados de conta corrente na camada view.
 * @author Lucas
 *
 */
public class ContaBean implements Serializable {
	
	private Integer numeroConta;
	
	private Integer agencia;
	
	private String descricaoConta;
	
	private Integer saldo;
	
	private Integer idConta;

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public String getDescricaoConta() {
		return descricaoConta;
	}

	public void setDescricaoConta(String descricaoConta) {
		this.descricaoConta = descricaoConta;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

}
