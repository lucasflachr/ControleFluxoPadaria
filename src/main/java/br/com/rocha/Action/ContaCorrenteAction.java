package br.com.rocha.Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rocha.BancoBean;
import br.com.rocha.ContaBean;
import br.com.rocha.LancamentoBean;
import br.com.rocha.Control.ControladorBanco;
import br.com.rocha.Control.ControladorContaCorrente;
import br.com.rocha.Control.ControladorLancamento;
import br.com.rocha.Model.entidades.BancoVO;

/**
 * Classe respons�vel em controlar os eventos da tela de conta corrente.
 * 
 * @author Lucas
 * 
 */
public class ContaCorrenteAction extends HttpServlet {

	/**
	 * M�todo respons�vel em chamar as rotinas necess�rias ap�s a as a��es do
	 * usu�rio.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			String acaoUsuario = req.getParameter("acaoUsuarioConta");

			if (acaoUsuario != null) {
				if (acaoUsuario.equals("listaContas")) {
					this.exibirListaConta(req, resp);
				} else if (acaoUsuario.equals("exibirCadastroConta")) {
					this.exibirCadastrarConta(req, resp);
				} else if (acaoUsuario.equals("inserirConta")) {
					this.inserirConta(req, resp);
				} else if (acaoUsuario.equals("monitorarConta")) {
					this.exibirMonitorarContas(req, resp);
				} else if (acaoUsuario.equals("excluirConta")) {
					this.excluirConta(req, resp);
				} else if (acaoUsuario.equals("monitorarLancamento")) {
					this.exibirMonitorarLancamento(req, resp);
				} else if (acaoUsuario.equals("monitorarLancamento")) {
					this.exibirMonitorarLancamento(req, resp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * M�todo respons�vel em exibir a tela de inclus�o de conta corrente.
	 * 
	 * @param req
	 * @param resp
	 */
	private void exibirCadastrarConta(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			List<BancoBean> listBancoBen;
			listBancoBen = new ControladorBanco().pesquisarBancos();
			req.setAttribute("listaBanco", listBancoBen);

			req.getRequestDispatcher("/cadastroContaCorrente.jsp").forward(req,
					resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo respons�vel em exibir a tela de lista de contas.
	 * 
	 * @param req
	 * @param resp
	 */
	private void exibirListaConta(HttpServletRequest req,
			HttpServletResponse resp) {

		List<ContaBean> listContas;
		try {
			listContas = new ControladorContaCorrente().pesquisarContas(null);
			req.setAttribute("listContas", listContas);

			req.getRequestDispatcher("/listaContas.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo respons�vel em chamar a rotina de inser��o de conta.
	 * 
	 * @param req
	 * @param resp
	 */
	private void inserirConta(HttpServletRequest req, HttpServletResponse resp) {

		String numeroConta = req.getParameter("numeroContaCorrente");

		String descricaoConta = req.getParameter("contaCorrente");

		String banco = req.getParameter("banco");

		ControladorContaCorrente controladorCC = new ControladorContaCorrente();

		try {
			controladorCC.incluirContaCorrente(Integer.parseInt(numeroConta),
					Integer.parseInt(banco), descricaoConta, 0);

			this.exibirListaConta(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo respons�vel em exibir a tela de monitoramento de contas.
	 * 
	 * @param req
	 * @param resp
	 */
	private void exibirMonitorarContas(HttpServletRequest req,
			HttpServletResponse resp) {

		List<ContaBean> listContas;
		try {
			listContas = new ControladorContaCorrente().pesquisarContas(null);
			req.setAttribute("listContas", listContas);

			req.getRequestDispatcher("/monitorarConta.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo respons�vel em chamar a exclus�o de uma conta.
	 * 
	 * @param req
	 * @param resp
	 */
	private void excluirConta(HttpServletRequest req, HttpServletResponse resp) {

		String idConta = req.getParameter("contaExcluir");

		ControladorContaCorrente controleContaCorrente = new ControladorContaCorrente();

		try {
			controleContaCorrente.excluirConta(Integer.parseInt(idConta));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.exibirListaConta(req, resp);
	}

	/**
	 * M�todo respons�vel em exibir a tela de monitoramento de lan�amentos.
	 * 
	 * @param req
	 * @param resp
	 */
	public void exibirMonitorarLancamento(HttpServletRequest req,
			HttpServletResponse resp) {
		List<LancamentoBean> listLancamento = new ControladorLancamento()
				.pesquisarLancamentos();

		req.setAttribute("listLancamento", listLancamento);

		try {
			req.getRequestDispatcher("/monitoraLancamentos.jsp").forward(req,
					resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
