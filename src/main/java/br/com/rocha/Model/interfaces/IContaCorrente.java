package br.com.rocha.Model.interfaces;

/**
 * Interface respons�vel em representar o DAO de Conta corrente.
 */
import java.util.List;

import br.com.rocha.Model.entidades.ContaCorrenteVO;

public interface IContaCorrente {

	/**
	 * M�todo respons�vel em incluir uma conta corrente.
	 * 
	 * @param contaCorrente
	 * @throws Exception
	 */
	public void incluirContaCorrente(ContaCorrenteVO contaCorrente)
			throws Exception;

	/**
	 * M�todo respons�vel em carregar o maior codigo da conta corrente.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer carregarMaiorCodigoConta() throws Exception;

	/**
	 * M�todo respons�vel em retornar uma lista de contas que estao na base de
	 * dados.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ContaCorrenteVO> pesquisarContas(Integer agencia) throws Exception;

	/**
	 * M�todo respons�vel em retornar uma conta corrente.
	 * 
	 * @param codigoConta
	 * @return
	 * @throws Exception
	 */
	public ContaCorrenteVO carregarContaCorrente(Integer codigoConta)
			throws Exception;

	/**
	 * M�todo respons�vel em excluir uma conta.
	 * 
	 * @param contaCorrente
	 * @throws Exception
	 */
	public void excluirConta(ContaCorrenteVO contaCorrente) throws Exception;

	/**
	 * M�todo respons�vel em alterar uma conta corrente.
	 * 
	 * @param contaCorrente
	 * @throws Exception
	 */
	public void alterarConta(ContaCorrenteVO contaCorrente) throws Exception;

	/**
	 * M�todo respons�vel em retornar uma conta corrente.
	 * 
	 * @param numeroConta
	 * @return
	 * @throws Exception
	 */
	public ContaCorrenteVO carregarContaCorrentePorNumero(Integer numeroConta)
			throws Exception;
}
