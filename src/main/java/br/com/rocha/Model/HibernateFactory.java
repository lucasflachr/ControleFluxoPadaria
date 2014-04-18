package br.com.rocha.Model;

import br.com.rocha.Model.interfaces.IBanco;
import br.com.rocha.Model.interfaces.IContaCorrente;
import br.com.rocha.Model.interfaces.ILancamentos;

/**
 * Classe respons�ve� em retornar uma interface de acesso a camada model.
 * 
 * @author Lucas
 * 
 */
public class HibernateFactory {

	public HibernateFactory() {

	}

	public IBanco getIBanco() {
		return new BancoDAO();
	}

	/**
	 * Repons�vel em retornar uma interface de ContaCorrenteDAO.
	 * 
	 * @return
	 */
	public IContaCorrente getIContaCorrente() {
		return new ContaCorrenteDAO();
	}
	
	/**
	 * Respons�vel em retornar uma interface de LancamentoDAO.
	 * 
	 * @return
	 */
	public ILancamentos getILancamentos() {
		return new LancamentoDAO();
	}

}
