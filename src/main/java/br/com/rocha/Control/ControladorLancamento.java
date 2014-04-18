package br.com.rocha.Control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rocha.LancamentoBean;
import br.com.rocha.Model.HibernateFactory;
import br.com.rocha.Model.entidades.ContaCorrenteVO;
import br.com.rocha.Model.entidades.LancamentoVO;
import br.com.rocha.Model.entidades.TransferenciaVO;
import br.com.rocha.Hibernate.HibernateUtil;

/**
 * Classe respons�vel em controlar as a��es do caso de uso de lan�amentos.
 * 
 * @author Lucas
 * 
 */
public class ControladorLancamento {

	private Date dataAtual = new Date();

	/**
	 * M�todo respons�vel em incluir um lan�amento
	 * 
	 * @param codigoContaOrigem
	 * @param codigoContaDestino
	 * @param valorLancamento
	 * @param tipo
	 * @param dataLancamento
	 * @param observacao
	 * @throws Exception
	 */
	public void incluirLancamentoTransferencia(Integer numeroContaOrigem,
			Integer numeroContaDestino, Integer valorLancamento, String tipo,
			Date dataLancamento, String observacao) throws Exception {
		try {
			HibernateUtil.currentTransaction();
			ControladorContaCorrente controladorCC = new ControladorContaCorrente();

			this.validacaoCamposLancamento(numeroContaOrigem,
					numeroContaDestino, valorLancamento, tipo, dataLancamento,
					observacao);

			ContaCorrenteVO contaOrigem = controladorCC
					.carregarContaPorNumero(numeroContaOrigem);
			ContaCorrenteVO contaDestino = controladorCC
					.carregarContaPorNumero(numeroContaDestino);

			HibernateFactory hibernateFactory = new HibernateFactory();

			LancamentoVO lancamentoVO = new LancamentoVO();
			Integer codigoLancamento = hibernateFactory.getILancamentos()
					.carregarMaiorCodigoLancamento();

			if (codigoLancamento == null)
				codigoLancamento = new Integer(0);

			lancamentoVO.setCodigoLancamento(++codigoLancamento);
			lancamentoVO.setData(dataLancamento);
			lancamentoVO.setIdConta(contaOrigem.getCodigoConta());
			lancamentoVO.setObservacao(observacao);
			lancamentoVO.setTipo(tipo);
			lancamentoVO.setValor(valorLancamento);

			hibernateFactory.getILancamentos().incluirLancamentos(lancamentoVO);

			TransferenciaVO transferenciaVO = new TransferenciaVO();
			Integer codigoTransferencia = hibernateFactory.getILancamentos()
					.carregarMaiorCodigoTransferencia();
			if (codigoTransferencia == null) {
				codigoTransferencia = new Integer(0);
			}
			transferenciaVO.setCodigo(++codigoTransferencia);
			transferenciaVO.setAgencia(contaOrigem.getAgencia());
			transferenciaVO.setContaOrigem(contaOrigem.getCodigoConta());
			transferenciaVO.setContaDestino(contaDestino.getCodigoConta());
			transferenciaVO.setData(dataLancamento);
			transferenciaVO.setValor(valorLancamento);

			hibernateFactory.getILancamentos().incluirTransferencia(
					transferenciaVO);

			Integer valorSaldoOrigem = contaOrigem.getSaldo() != null ? contaOrigem
					.getSaldo() : new Integer(0);
			Integer valorSaldoDestino = contaDestino.getSaldo() != null ? contaDestino
					.getSaldo() : new Integer(0);

			// quando � debito
			if (tipo.equals("D")) {
				contaOrigem.setSaldo(valorSaldoOrigem - valorLancamento);
				contaDestino.setSaldo(valorSaldoDestino + valorLancamento);
			} else {
				contaOrigem.setSaldo(valorSaldoOrigem + valorLancamento);
				contaDestino.setSaldo(valorSaldoDestino - valorLancamento);
			}

			controladorCC.alterarConta(contaOrigem);
			controladorCC.alterarConta(contaDestino);

			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Método responsável em validar os campos para inclusão de um lançamento.
	 * 
	 * @param numeroContaOrigem
	 * @param numeroContaDestino
	 * @param valorLancamento
	 * @param tipo
	 * @param dataLancamento
	 * @param observacao
	 * @throws Exception
	 */
	private void validacaoCamposLancamento(Integer numeroContaOrigem,
			Integer numeroContaDestino, Integer valorLancamento, String tipo,
			Date dataLancamento, String observacao) throws Exception {

		if (numeroContaOrigem == null || numeroContaDestino == null
				|| valorLancamento == null || tipo == null
				|| dataLancamento == null)
			throw new Exception("Primeiro complete os dados do Lancamento.");
		else if (valorLancamento.compareTo(0) <= 0)
			throw new Exception("Dados incorretos ou nao preenchidos.");
		else if (dataLancamento.before(this.dataAtual))
			throw new Exception("Os dados nao foram preenchidos corretamente.");

	}

	/**
	 * M�todo respons�vel em pesquisas os lan�amentos da padaria.
	 * 
	 * @return
	 */
	public List<LancamentoBean> pesquisarLancamentos() {
		List<LancamentoBean> list = new ArrayList<LancamentoBean>();

		try {
			List<LancamentoVO> listLancamentoVO = new HibernateFactory()
					.getILancamentos().pesquisarLancamentos();
			ControladorContaCorrente controladorCC = new ControladorContaCorrente();

			if (listLancamentoVO != null) {
				LancamentoBean bean = null;
				ContaCorrenteVO contaCorrente = null;
				for (LancamentoVO lancamentoVO : listLancamentoVO) {
					bean = new LancamentoBean();
					bean.setCodigoLancamento(lancamentoVO.getCodigoLancamento());
					contaCorrente = controladorCC
							.carregarContaCorrente(lancamentoVO.getIdConta());
					bean.setConta(contaCorrente.getNumeroConta());
					bean.setAgencia(contaCorrente.getAgencia());
					bean.setData(lancamentoVO.getData());
					bean.setTipo(lancamentoVO.getTipo());
					bean.setValor(lancamentoVO.getValor());
					list.add(bean);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * M�todo respons�vel em excluir um lan�amento.
	 * 
	 * @param codigoLancamento
	 * @throws Exception
	 */
	public void excluirLancamentos(Integer codigoLancamento) throws Exception {
		try {
			HibernateUtil.currentTransaction();

			HibernateFactory hibernateFactory = new HibernateFactory();

			LancamentoVO lancamentoVO = hibernateFactory.getILancamentos()
					.carregarLancamento(codigoLancamento);

			TransferenciaVO transferenciaVO = hibernateFactory
					.getILancamentos().carregarTransferencia(codigoLancamento);

			ControladorContaCorrente controladorCC = new ControladorContaCorrente();

			ContaCorrenteVO contaOrigem = controladorCC
					.carregarContaCorrente(transferenciaVO.getContaOrigem());
			ContaCorrenteVO contaDestino = controladorCC
					.carregarContaCorrente(transferenciaVO.getContaDestino());

			Integer valorSaldoOrigem = contaOrigem.getSaldo() != null ? contaOrigem
					.getSaldo() : new Integer(0);
			Integer valorSaldoDestino = contaDestino.getSaldo() != null ? contaDestino
					.getSaldo() : new Integer(0);

			// quando � debito
			if (lancamentoVO.getTipo().equals("D")) {
				contaOrigem
						.setSaldo(valorSaldoOrigem + lancamentoVO.getValor());
				contaDestino.setSaldo(valorSaldoDestino
						- lancamentoVO.getValor());
			} else {
				contaOrigem
						.setSaldo(valorSaldoOrigem - lancamentoVO.getValor());
				contaDestino.setSaldo(valorSaldoDestino
						+ lancamentoVO.getValor());
			}

			hibernateFactory.getILancamentos().excluirLancamento(lancamentoVO);

			hibernateFactory.getILancamentos().excluirTransferencia(
					transferenciaVO);

			controladorCC.alterarConta(contaOrigem);
			controladorCC.alterarConta(contaDestino);

			HibernateUtil.commitTransaction();
		} catch (Exception e) {
			HibernateUtil.rollbackTransaction();
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Método responsável em carregar o id de um lançamento.
	 * 
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

		return new HibernateFactory().getILancamentos()
				.carregarCodigoLancamento(numeroContaOrigem,
						numeroContaDestino, dataLancamento, valor);

	}

}
