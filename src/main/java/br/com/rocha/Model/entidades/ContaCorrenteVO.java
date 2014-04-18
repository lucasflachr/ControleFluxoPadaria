package br.com.rocha.Model.entidades;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe respons�vel em mapear a tabela de conta corrente.
 * 
 * @author Lucas
 * 
 */
@Entity
@Table(name = "conta_corrrente")
public class ContaCorrenteVO implements Serializable {
	
	@Id
	@Column(name = "codigo")
	private Integer codigoConta;

	@Column(name = "agencia")
	private Integer agencia;

	@Column(name = "conta")
	private Integer numeroConta;
	
	@Column(name = "descricao")
	private String descricaoConta;
	
	@Column(name = "saldo")
	private Integer saldo;
	
	public ContaCorrenteVO() {
	}

	public Integer getCodigoConta() {
		return codigoConta;
	}

	public void setCodigoConta(Integer codigoConta) {
		this.codigoConta = codigoConta;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
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

}
