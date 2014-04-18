package br.com.rocha.Control;

import java.util.ArrayList;
import java.util.List;

import br.com.rocha.ContaBean;

import junit.framework.TestCase;

/**
 * Classe responsável pelos testes do UCT002 - Cadastra Conta, UCT005 - Monitora Conta e UCT008 - Lista Contas. 
 * @author Lucas
 *
 */
public class ControladorContaCorrenteTest extends TestCase {
	
	private ControladorContaCorrente controladorContaCorrente;
	
	protected void setUp() {
		this.controladorContaCorrente = new ControladorContaCorrente();
	}
	
	/**
	 * Método responsável pelos testes do cenário 002.01.
	 */
	public void testIncluirContaCorrente() {
		
		String statusInclusao = "OK";
		
		try {
			this.controladorContaCorrente.incluirContaCorrente(35, 1, "Conta para testes", 100);
		} catch (Exception e) {
			e.printStackTrace();
			statusInclusao = "ERRO";
		}
		
		assertEquals(statusInclusao, "OK");
		try {
			this.excluirContaTestes(35);
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Exclui a conta após os testes.
	 * @param codigoConta
	 * @throws Exception
	 */
	private void excluirContaTestes(Integer numeroConta) throws Exception {
		
		this.controladorContaCorrente.excluirContaPorNumero(numeroConta);
	}
	
	/**
	 * Método responsável pelos testes do cenário 002.02.
	 */
	public void testIncluirContaSaldoNegativo() {
		String mensagemValidacao = "Preencher os dados corretamente.";
		String mensagemRetorno = "";
		
		try {
			this.controladorContaCorrente.incluirContaCorrente(35, 1, "Conta para testes", -100);
		} catch (Exception e) {			
			mensagemRetorno = e.getMessage();
		}
		
		assertEquals(mensagemValidacao, mensagemRetorno);
	}
	
	/**
	 * Método responsável pelos testes do cenário 002.03.
	 */
	public void testExluirConta() {
		String statusInclusao = "OK";
		
		try {
			this.controladorContaCorrente.incluirContaCorrente(3, 1, "Conta para testes", 100);
			
			this.controladorContaCorrente.excluirContaPorNumero(3);
			
		} catch (Exception e) {
			e.printStackTrace();
			statusInclusao = "ERRO";
		}
		
		assertEquals(statusInclusao, "OK");
	}
	
	/**
	 * Método responsável pelos testes do cenário 002.04.
	 */
	public void testValidacaoDadosIncorretos() {
		String mensagemValidacao = "Dados incorretos.";
		String mensagemRetorno = "";
		
		try {
			this.controladorContaCorrente.incluirContaCorrente(35, 9999, "Conta para testes", 100);
		} catch (Exception e) {			
			mensagemRetorno = e.getMessage();
		}
		
		assertEquals(mensagemValidacao, mensagemRetorno);
	}
	
	/**
	 * Método responsável pelos testes do cenário 002.05.
	 */
	public void testDadosIncompletos() {
		String mensagemValidacao = "Falta dados para o Cadastro.";
		String mensagemRetorno = "";
		
		try {
			this.controladorContaCorrente.incluirContaCorrente(null, 2, "Conta para testes", 100);
		} catch (Exception e) {			
			mensagemRetorno = e.getMessage();
		}
		
		assertEquals(mensagemValidacao, mensagemRetorno);
	}
	
	/**
	 * Método responsável pelos testes do cenário 002.05.
	 */
	public void testConsultaContas() {
		
		List<ContaBean> listPesquisa = null;
		try {
			listPesquisa = this.controladorContaCorrente.pesquisarContas(null);
		} catch (Exception e) {
			listPesquisa = new ArrayList<ContaBean>();
		}
		
		assertEquals(!listPesquisa.isEmpty(), true);
	}
	
	/**
	 * Método responsável pelos testes do cenário 002.05.
	 */
	public void testNenhumaContaCadastrada() {
		String mensagemValidacao = "Nenhuma conta foi cadastrada.";
		String mensagemRetorno = "";
		
		try {
			List<ContaBean> listPesquisa = this.controladorContaCorrente.pesquisarContas(7777);
		} catch (Exception e) {
			mensagemRetorno = e.getMessage();
		}
		
		assertEquals(mensagemValidacao, mensagemRetorno);
		
	}
	
	/**
	 * Método resposável pelos testes do cenário 008.01.
	 */
	public void testContasSomenteAgencia() {
		boolean existeContaOutrasAgencias = false;
		
		try {
			List<ContaBean> listPesquisa = this.controladorContaCorrente.pesquisarContas(1);
			
			for (ContaBean bean : listPesquisa) {
				if (!bean.getAgencia().equals(new Integer(1))) {
					existeContaOutrasAgencias = true;
					break;
				}
			}
		} catch (Exception e) {			
		}
		
		assertEquals(existeContaOutrasAgencias, false);
	}

}
