package br.com.rocha;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe respons�vel em ter os atrinutos de Banco na camada View.
 * 
 * @author Leandro
 * 
 */
public class LancamentoBean implements Serializable {

	private Integer codigoLancamento;   
	private Date data;
	private String tipo;
	private Integer valor;
	private Integer conta;
	private Integer agencia;

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

	public Integer getConta() {
		return conta;
	}

	public void setConta(Integer conta) {
		this.conta = conta;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}
	
	public String getDataFormatada() {
		SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy");
		return simple.format(this.data);
	}
	
	public String getOperacaoFormatada() {
		if (this.tipo.equals("C"))
			return "Cr�dito";
		else
			return "D�bito";
	}
	
	public boolean getIsCredito() {
		return this.tipo.equals("C") ? true : false;
	}
	
}
