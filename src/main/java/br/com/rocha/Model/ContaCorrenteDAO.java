package br.com.rocha.Model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.rocha.Model.entidades.ContaCorrenteVO;
import br.com.rocha.Model.interfaces.IContaCorrente;
import br.com.rocha.Hibernate.HibernateUtil;

public class ContaCorrenteDAO implements IContaCorrente {
	
	/**
	 * M�todo respons�vel em incluir uma conta corrente.
	 * 
	 * @param contaCorrente
	 * @throws Exception
	 */
	public void incluirContaCorrente(ContaCorrenteVO contaCorrente)
			throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			session.save(contaCorrente);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	/**
	 * M�todo respons�vel em carregar o maior codigo da conta corrente.
	 * 
	 * @return
	 * @throws Exception
	 */
	public Integer carregarMaiorCodigoConta() throws Exception {
		Integer maiorCodigoConta = new Integer(0);
		try {

			Session session = HibernateUtil.currentSession();

			Query query = session
					.createQuery("select max(codigoConta) from ContaCorrenteVO ");

			maiorCodigoConta = (Integer) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return maiorCodigoConta;
	}

	/**
	 * M�todo respons�vel em retornar uma lista de contas que estao na base de
	 * dados.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<ContaCorrenteVO> pesquisarContas(Integer agencia) throws Exception {
		List<ContaCorrenteVO> list = null;
		try {

			Session session = HibernateUtil.currentSession();

			StringBuffer hql = new StringBuffer("select c from ContaCorrenteVO c ");
			
			if (agencia != null) 
				hql.append("where c.agencia = :agencia ");
			
			hql.append("order by c.descricaoConta ");
			
			Query query = session
					.createQuery(hql.toString());
			
			if (agencia != null)
				query.setInteger("agencia", agencia);

			list = (List<ContaCorrenteVO>) query.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return list;
	}

	/**
	 * M�todo respons�vel em retornar uma conta corrente.
	 * 
	 * @param codigoConta
	 * @return
	 * @throws Exception
	 */
	public ContaCorrenteVO carregarContaCorrente(Integer codigoConta)
			throws Exception {
		ContaCorrenteVO conta = null;
		try {
			Session session = HibernateUtil.currentSession();

			Query query = session
					.createQuery("select c from ContaCorrenteVO c where c.codigoConta = :codigoConta ");

			query.setInteger("codigoConta", codigoConta);

			conta = (ContaCorrenteVO) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return conta;
	}

	/**
	 * M�todo respons�vel em excluir uma conta.
	 * 
	 * @param contaCorrente
	 * @throws Exception
	 */
	public void excluirConta(ContaCorrenteVO contaCorrente) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			session.delete(contaCorrente);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * M�todo respons�vel em alterar uma conta corrente.
	 */
	public void alterarConta(ContaCorrenteVO contaCorrente) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			session.update(contaCorrente);

		} catch (Exception e) {
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
	public ContaCorrenteVO carregarContaCorrentePorNumero(Integer numeroConta)
			throws Exception {
		ContaCorrenteVO conta = null;
		try {
			Session session = HibernateUtil.currentSession();

			Query query = session
					.createQuery("select c from ContaCorrenteVO c where c.numeroConta = :numeroConta ");

			query.setInteger("numeroConta", numeroConta);

			conta = (ContaCorrenteVO) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return conta;
	}
}
