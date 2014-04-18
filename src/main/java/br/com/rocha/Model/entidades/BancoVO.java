package br.com.rocha.Model.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe respons�vel em representar a entidade de banco.
 * 
 * @author Lucas
 * 
 */
@Entity
@Table(name = "banco")
public class BancoVO implements Serializable {

	@Id
	@Column(name = "id_banco")
	private Integer idBanco;

	@Column(name = "nome")
	private String nomeBanco;

	@Column(name = "code_banco")
	private Integer codigoBanco;
		
	public BancoVO() {
	}

	public Integer getIdBanco() {
		return idBanco;
	}

	public void setIdBanco(Integer idBanco) {
		this.idBanco = idBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public Integer getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

}
