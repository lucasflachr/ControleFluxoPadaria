package br.com.rocha.Control;

import java.util.ArrayList;
import java.util.List;

import br.com.rocha.ContaBean;
import br.com.rocha.Model.HibernateFactory;
import br.com.rocha.Model.entidades.ContaCorrenteVO;
import br.com.rocha.Exception.RochaException;
import br.com.rocha.Hibernate.ContaCorrenteSingletonSequence;
import br.com.rocha.Hibernate.HibernateUtil;

/**
 * Classe respons�vel em controlar os cen�rios do caso de uso de conta corrente.
 * 
 * @author Lucas
 * 
 */
public class ControladorContaCorrente {

	/**
	 * M�todo respons�vel em incluir uma conta corrente.
	 * 
	 * @param numeroConta
	 * @param codigoBanco
	 * @param descricao
	 * @throws Exception
	 */
	public void incluirContaCorrente(Integer numeroConta, Integer codigoBanco,
			String descricao, Integer saldo) throws RochaException, Exception {

		try {
			HibernateUtil.currentTransaction();
			
			ContaCorrenteVO contaCorrente = new ContaCorrenteVO();

			contaCorrente.setCodigoConta(ContaCorrenteSingletonSequence
					.getInstance().getSequenceConta());
			contaCorrente.setNumeroConta(numeroConta);
			contaCorrente.setAgencia(codigoBanco);
			contaCorrente.setDescricaoConta(descricao);			
			contaCorrente.setSaldo(saldo);
			
			this.validarDadosInclusaoConta(contaCorrente);
			
			HibernateFactory hibernateFactory = new HibernateFactory();

			hibernateFactory.getIContaCorrente().incluirContaCorrente(
					contaCorrente);

			HibernateUtil.commitTransaction();
			
		} catch (RochaException e) {
			HibernateUtil.rollbackTransaction();
			throw e;
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
			throw e;
		}

	}
	
	/**
	 * Método responsável em validar os campos de conta corrente.
	 * @param numeroConta
	 * @param codigoBanco
	 * @param descricao
	 * @param saldo
	 * @throws Exception
	 */
	private void validarDadosInclusaoConta(ContaCorrenteVO contaCorrentoVO) throws RochaException, Exception {
		
		if (contaCorrentoVO.getSaldo() == null || contaCorrentoVO.getSaldo().compareTo(0) < 0) {
			throw new RochaException("Preencher os dados corretamente.");
		}
		
		if (contaCorrentoVO.getAgencia() == null || contaCorrentoVO.getAgencia().compareTo(9999) == 0) {
			throw new RochaException("Dados incorretos.");
		}
		
		if (contaCorrentoVO.getNumeroConta() == null) {
			throw new RochaException("Faltam dados para o Cadastro.");
		}
	}

	/**
	 * M�todo respons�vel em retornar o maior id da conta.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer carregarMaiorCodigoConta() throws Exception {
		HibernateFactory hibernateFactory = new HibernateFactory();
		return hibernateFactory.getIContaCorrente().carregarMaiorCodigoConta();
	}

	/**
	 * M�todo respons�vel em retornar uma lista com as contas cadastradas.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ContaBean> pesquisarContas(Integer agencia) throws RochaException, Exception {

		List<ContaBean> list = new ArrayList<ContaBean>();

		try {
			HibernateFactory hibernateFactory = new HibernateFactory();

			List<ContaCorrenteVO> listContaVO = hibernateFactory
					.getIContaCorrente().pesquisarContas(agencia);

			if (listContaVO != null && !listContaVO.isEmpty()) {
				ContaBean bean = null;
				for (ContaCorrenteVO contaVO : listContaVO) {
					bean = new ContaBean();
					bean.setIdConta(contaVO.getCodigoConta());
					bean.setNumeroConta(contaVO.getNumeroConta());
					bean.setAgencia(contaVO.getAgencia());
					bean.setDescricaoConta(contaVO.getDescricaoConta());
					bean.setSaldo(contaVO.getSaldo() != null ? contaVO
							.getSaldo() : 0);
					list.add(bean);
				}
			} else {
				throw new RochaException("Nenhuma conta foi cadastrada.");
			}
		} catch (RochaException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return list;
	}

	/**
	 * M�todo respons�vel em excluir uma conta.
	 * 
	 * @param codigoConta
	 * @throws Exception
	 */
	public void excluirConta(Integer codigoConta) throws Exception {
		try {
			HibernateUtil.currentTransaction();

			HibernateFactory hibernateFactory = new HibernateFactory();

			ContaCorrenteVO contaCorrenteVO = hibernateFactory
					.getIContaCorrente().carregarContaCorrente(codigoConta);

			hibernateFactory.getIContaCorrente().excluirConta(contaCorrenteVO);

			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * M�todo respons�vel em carregar um conta.
	 * 
	 * @param codigoConta
	 * @return
	 * @throws Exception
	 */
	public ContaCorrenteVO carregarContaCorrente(Integer codigoConta)
			throws Exception {
		HibernateFactory hibernateFactory = new HibernateFactory();
		return hibernateFactory.getIContaCorrente().carregarContaCorrente(
				codigoConta);
	}

	/**
	 * M�todo respons�vel em alterar uma conta corrente.
	 * 
	 * @param contaCorrente
	 * @throws Exception
	 */
	public void alterarConta(ContaCorrenteVO contaCorrente) throws Exception {
		HibernateFactory hibernateFactory = new HibernateFactory();
		hibernateFactory.getIContaCorrente().alterarConta(contaCorrente);
	}
	
	/**
	 * Método responsável em excluir uma conta por numero.
	 * @param numeroConta
	 * @throws Exception
	 */
	public void excluirContaPorNumero(Integer numeroConta) throws Exception {
		try {
			HibernateUtil.currentTransaction();

			HibernateFactory hibernateFactory = new HibernateFactory();

			ContaCorrenteVO contaCorrenteVO = this.carregarContaPorNumero(numeroConta);

			hibernateFactory.getIContaCorrente().excluirConta(contaCorrenteVO);

			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * M�todo respons�vel em retornar uma conta corrente.
	 * 
	 * @param numeroConta
	 * @return
	 * @throws Exception
	 */
	public ContaCorrenteVO carregarContaPorNumero(Integer numeroConta)
			throws Exception {
		HibernateFactory hibernateFactory = new HibernateFactory();
		return hibernateFactory.getIContaCorrente()
				.carregarContaCorrentePorNumero(numeroConta);
	}

}
