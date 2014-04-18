package br.com.rocha.Model.interfaces;

import java.util.Date;
import java.util.List;

import br.com.rocha.Model.entidades.LancamentoVO;
import br.com.rocha.Model.entidades.TransferenciaVO;

/**
 * Interface respons�vel pelas assinaturas dos m�todos do DAO de Lan�amentos.
 * 
 * @author Lucas
 * 
 */
public interface ILancamentos {
	
	/**
	 * M�todo respons�vel em incluir um lan�amento.
	 */
	public void incluirLancamentos(LancamentoVO lancamentoVO) throws Exception;
	
	/**
	 * M�todo respons�vel em carregar o maior codigo de lan�amento.
	 */
	public Integer carregarMaiorCodigoLancamento() throws Exception;
	
	/**
	 * M�todo respons�vel em incluir uma transferencia.
	 */
	public void incluirTransferencia(TransferenciaVO transfereciaVO)
			throws Exception;
	
	/**
	 * M�todo respons�vel em carregar o maior codigo de transferencia.
	 */
	public Integer carregarMaiorCodigoTransferencia() throws Exception;
	
	/**
	 * M�todo respons�vel em excluir um lan�amento.
	 */
	public void excluirLancamento(LancamentoVO lancamentoVO) throws Exception;
	
	/**
	 * M�todo respons�vel em excluir uma transferencia.
	 */
	public void excluirTransferencia(TransferenciaVO transferenciaVO)
			throws Exception;
	
	/**
	 * M�todo respons�vel em pesquisar os lan�amentos.
	 */
	public List<LancamentoVO> pesquisarLancamentos() throws Exception;
	
	/**
	 * M�todo respons�vel em carregar um lan�amento.
	 * 
	 * @param codigoLancamento
	 * @return
	 * @throws Exception
	 */
	public LancamentoVO carregarLancamento(Integer codigoLancamento)
			throws Exception;

	/**
	 * M�todo respons�vel em carregar uma transferencia.
	 * 
	 * @param codigoLancamento
	 * @return
	 * @throws Exception
	 */
	public TransferenciaVO carregarTransferencia(Integer codigoTransferencia)
			throws Exception;
	
	/**
	 * Método responsável em carregar o id de um lançamento.
	 * @param numeroContaOrigem
	 * @param numeroContaDestino
	 * @param dataLancamento
	 * @param valor
	 * @return
	 * @throws Exception
	 */
	public Integer carregarCodigoLancamento(Integer numeroContaOrigem,
			Integer numeroContaDestino, Date dataLancamento, Integer valor)
			throws Exception;
}
