package br.com.rocha.Control;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import br.com.rocha.BancoBean;

/**
 * Classe responsável pelos testes do UCT003 - Cadastra Banco.
 * 
 * @author Lucas
 * 
 */
public class ControladorBancoTest extends TestCase {
	
	private ControladorBanco controladorBanco;
	
	protected void setUp() {
		this.controladorBanco = new ControladorBanco();
	}
	
	/**
	 * Método responsável pelos testes do cenário 003.01.
	 */
	public void testPesquisaBanco() {
		List<BancoBean> listBancos = new ArrayList<BancoBean>();
		
		try {
			listBancos = this.controladorBanco.pesquisarBancos();
		} catch (Exception e) {			
		}
		
		assertEquals(!listBancos.isEmpty(), true);
	}
}
