package br.com.rocha.Control;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;
import br.com.rocha.LancamentoBean;

/**
 * Classe responsável pelos testes do UCT-004 - Realiza Lançamentos.
 * 
 * @author Lucas
 * 
 */
public class ControladorLancamentoTest extends TestCase {

	private ControladorLancamento controladorLancamento;

	protected void setUp() {
		this.controladorLancamento = new ControladorLancamento();
	}

	/**
	 * Método responsável pelos testes do cenário 004.01.
	 */
	public void testIncluirLancamento() {
		String statusInclusao = "OK";
		try {
			this.controladorLancamento.incluirLancamentoTransferencia(22, 888,
					10, "D", new Date(), "Inclusão de um lançamento.");
		} catch (Exception e) {
			statusInclusao = "ERRO";
		}

		try {
			this.limparLancamentos(22, 888, new Date(), 10);
		} catch (Exception e) {
		}
		
		assertEquals(statusInclusao, "OK");
	}

	/**
	 * Método responsável em limpar o lançamento após um teste.
	 * 
	 * @param numeroContaOrigem
	 * @param numeroContaDestino
	 * @param dataLancamento
	 * @param valor
	 * @throws Exception
	 */
	private void limparLancamentos(Integer numeroContaOrigem,
			Integer numeroContaDestino, Date dataLancamento, Integer valor)
			throws Exception {
		this.controladorLancamento
				.excluirLancamentos(this.controladorLancamento
						.carregarCodigoLancamento(numeroContaOrigem,
								numeroContaDestino, dataLancamento, valor));
	}

	/**
	 * Método responsável pelos testes do cenário 004.02.
	 */
	public void testDadosIncompletos() {
		String mensagemValidacao = "Primeiro complete os dados do Lancamento.";
		String mensagemRetorno = "";

		try {
			this.controladorLancamento.incluirLancamentoTransferencia(22, 888,
					null, "D", new Date(), "Inclusão de um lançamento.");
		} catch (Exception e) {
			mensagemRetorno = e.getMessage();
		}

		assertEquals(mensagemRetorno, mensagemValidacao);
	}

	/**
	 * Método responsável pelos testes do cenário 004.03.
	 */
	public void testExclusaoLancamento() {
		String statusExclusao = "OK";

		try {
			this.controladorLancamento.incluirLancamentoTransferencia(677, 422,
					20, "D", new Date(), "Inclusão de um lançamento.");

			this.controladorLancamento
					.excluirLancamentos(this.controladorLancamento
							.carregarCodigoLancamento(677, 422, new Date(), 20));
		} catch (Exception e) {
			statusExclusao = e.getMessage();
		}

		assertEquals(statusExclusao, "OK");
	}

	/**
	 * Método responsável pelos testes do cenário 004.04.
	 */
	public void testDadosInvalidos() {
		String mensagemValidacao = "Dados incorretos ou nao preenchidos.";
		String mensagemRetorno = "";

		try {
			this.controladorLancamento.incluirLancamentoTransferencia(22, 888,
					-10, "D", new Date(), "Inclusão de um lançamento.");
		} catch (Exception e) {
			mensagemRetorno = e.getMessage();
		}

		assertEquals(mensagemRetorno, mensagemValidacao);
	}

	/**
	 * Método responsável pelos testes do cenário 004.05.
	 */
	public void testDataLancamentoMenor() {
		String mensagemValidacao = "Os dados nao foram preenchidos corretamente.";
		String mensagemRetorno = "";

		try {

			Calendar dataMenosUmDia = Calendar.getInstance();
			dataMenosUmDia.add(Calendar.DAY_OF_MONTH, -1);

			this.controladorLancamento.incluirLancamentoTransferencia(22, 888,
					10, "D", dataMenosUmDia.getTime(),
					"Inclusão de um lançamento.");
		} catch (Exception e) {
			mensagemRetorno = e.getMessage();
		}

		assertEquals(mensagemRetorno, mensagemValidacao);
	}
	
	/**
	 * Método responsável pelos testes do cenário 004.06.
	 */
	public void testPesquisaLancamento() {
		
		List<LancamentoBean> listLancamentos =  this.controladorLancamento.pesquisarLancamentos();
		
		assertEquals(!listLancamentos.isEmpty(), true);
	}
}
