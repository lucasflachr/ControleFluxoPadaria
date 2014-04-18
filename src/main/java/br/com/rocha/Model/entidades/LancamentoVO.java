package br.com.rocha.Model.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe respons�vel em representar a entidade de lancamento.
 * 
 * @author Lucas
 *
 */
@Entity
@Table(name = "lancamento")
public class LancamentoVO implements Serializable {

	@Id
	@Column(name = "codigo")
	private Integer codigoLancamento;

	@Column(name = "data")
	private Date data;

	@Column(name = "observacao")
	private String observacao;

	@Column(name = "tipo")
	private String tipo;

	@Column(name = "valor")
	private Integer valor;
	
	@Column(name = "idconta")
	private Integer idConta;
	
	public LancamentoVO() {
	}

	public Integer getCodigoLancamento() {
		return codigoLancamento;
	}

	public void setCodigoLancamento(Integer codigoLancamento) {
		this.codigoLancamento = codigoLancamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}
	
}
