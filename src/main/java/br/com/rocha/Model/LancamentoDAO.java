package br.com.rocha.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.rocha.Model.entidades.LancamentoVO;
import br.com.rocha.Model.entidades.TransferenciaVO;
import br.com.rocha.Model.interfaces.ILancamentos;
import br.com.rocha.Hibernate.HibernateUtil;

/**
 * Classe respons�vel pela intera��o no banco de dados do caso de uso de
 * lan�amentos.
 * 
 * @author Lucas
 * 
 */
public class LancamentoDAO implements ILancamentos {

	/**
	 * M�todo respons�vel em incluir um lan�amento.
	 */
	public void incluirLancamentos(LancamentoVO lancamentoVO) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			session.save(lancamentoVO);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * M�todo respons�vel em carregar o maior codigo de lan�amento.
	 */
	public Integer carregarMaiorCodigoLancamento() throws Exception {
		Integer maiorCodigoLancamento = new Integer(0);
		try {

			Session session = HibernateUtil.currentSession();

			Query query = session
					.createQuery("select max(codigoLancamento) from LancamentoVO ");

			maiorCodigoLancamento = (Integer) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return maiorCodigoLancamento;
	}

	/**
	 * M�todo respons�vel em incluir uma transferencia.
	 */
	public void incluirTransferencia(TransferenciaVO transfereciaVO)
			throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			session.save(transfereciaVO);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * M�todo respons�vel em carregar o maior codigo de transferencia.
	 */
	public Integer carregarMaiorCodigoTransferencia() throws Exception {
		Integer maiorCodigoLancamento = new Integer(0);
		try {

			Session session = HibernateUtil.currentSession();

			Query query = session
					.createQuery("select max(codigo) from TransferenciaVO ");

			maiorCodigoLancamento = (Integer) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return maiorCodigoLancamento;
	}

	/**
	 * M�todo respons�vel em excluir um lan�amento.
	 */
	public void excluirLancamento(LancamentoVO lancamentoVO) throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			session.delete(lancamentoVO);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * M�todo respons�vel em excluir uma transferencia.
	 */
	public void excluirTransferencia(TransferenciaVO transferenciaVO)
			throws Exception {
		try {
			Session session = HibernateUtil.currentSession();

			session.delete(transferenciaVO);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * M�todo respons�vel em pesquisar os lan�amentos.
	 */
	public List<LancamentoVO> pesquisarLancamentos() throws Exception {
		List<LancamentoVO> list = new ArrayList<LancamentoVO>();
		try {

			Session session = HibernateUtil.currentSession();

			Query query = session.createQuery("select l from LancamentoVO l ");

			list = (List<LancamentoVO>) query.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}

	/**
	 * M�todo respons�vel em carregar um lan�amento.
	 * 
	 * @param codigoLancamento
	 * @return
	 * @throws Exception
	 */
	public LancamentoVO carregarLancamento(Integer codigoLancamento)
			throws Exception {
		LancamentoVO lanc = null;
		try {

			Session session = HibernateUtil.currentSession();

			Query query = session
					.createQuery("select l from LancamentoVO l where l.codigoLancamento = :codigoLancamento ");

			query.setInteger("codigoLancamento", codigoLancamento);

			lanc = (LancamentoVO) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return lanc;
	}
	
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
			throws Exception {
		Integer codigoLancamento = null;
		try {

			Session session = HibernateUtil.currentSession();

			StringBuilder hql = new StringBuilder(
					"select l.codigoLancamento from LancamentoVO l, ");
			hql.append("TransferenciaVO t, ContaCorrenteVO c ");
			hql.append("where c.numeroConta = :numeroContaOrigem ");
			hql.append("and t.contaOrigem = c.codigoConta ");
			hql.append("and t.contaDestino = (select c2.codigoConta from ContaCorrenteVO c2 where c2.numeroConta = :numeroContaDestino) ");
			hql.append("and l.idConta = c.codigoConta ");
			hql.append("and t.data = :dataLancamento ");
			hql.append("and l.data = :dataLancamento ");
			hql.append("and t.valor = :valor ");
			hql.append("and l.valor = :valor ");

			Query query = session
					.createQuery(hql.toString());

			query.setInteger("numeroContaOrigem", numeroContaOrigem);
			query.setInteger("numeroContaDestino", numeroContaDestino);
			query.setInteger("valor", valor);
			query.setDate("dataLancamento", dataLancamento);

			codigoLancamento = (Integer) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return codigoLancamento;
	}

	/**
	 * M�todo respons�vel em carregar uma transferencia.
	 * 
	 * @param codigoLancamento
	 * @return
	 * @throws Exception
	 */
	public TransferenciaVO carregarTransferencia(Integer codigoTransferencia)
			throws Exception {
		TransferenciaVO trans = null;
		try {

			Session session = HibernateUtil.currentSession();

			Query query = session
					.createQuery("select t from TransferenciaVO t where t.codigo = :codigoTransferencia ");

			query.setInteger("codigoTransferencia", codigoTransferencia);

			trans = (TransferenciaVO) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return trans;
	}

}
