package br.com.rocha.Model.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Classe respons�vel em representar a entidade de transferencia.
 * 
 * @author Lucas
 * 
 */
@Entity
@Table(name = "transferencia")
public class TransferenciaVO implements Serializable {

	@Id
	@Column(name = "id_transferencia")
	private Integer codigo;

	@Column(name = "agencia")
	private Integer agencia;

	@Column(name = "conta_origem")
	private Integer contaOrigem;

	@Column(name = "conta_destino")
	private Integer contaDestino;

	@Column(name = "data")
	private Date data;

	@Column(name = "valor")
	private Integer valor;
	
	public TransferenciaVO() {
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Integer contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public Integer getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(Integer contaDestino) {
		this.contaDestino = contaDestino;
	}

}
