package br.com.rocha.Action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rocha.ContaBean;
import br.com.rocha.LancamentoBean;
import br.com.rocha.Control.ControladorContaCorrente;
import br.com.rocha.Control.ControladorLancamento;

/**
 * Classe respons�vel em controlar os eventos da tela de lan�amentos.
 * 
 * @author Lucas
 * 
 */
public class LancamentosAction extends HttpServlet {

	/**
	 * M�todo respons�vel em chamar as rotinas necess�rias ap�s a as a��es do
	 * usu�rio.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			String acaoUsuario = req.getParameter("acaoUsuarioLancamento");

			if (acaoUsuario != null) {
				if (acaoUsuario.equals("listaLancamentos")) {
					this.exibirListaLancamentos(req, resp);
				} else if (acaoUsuario.equals("exibirIncluirLancamentos")) {
					this.exibirIncluirLancamentos(req, resp);
				} else if (acaoUsuario.equals("inserirLancamento")) {
					this.inserirLancamento(req, resp);
				} else if (acaoUsuario.equals("monitorarLancamento")) {
					this.exibirMonitorarLancamento(req, resp);
				} else if (acaoUsuario.equals("excluirLancamento")) {
					this.excluirLancamento(req, resp);
				} else if (acaoUsuario.equals("cancelarLancamento")) {
					this.cancelarLancamentos(req, resp);
				} else if (acaoUsuario.equals("monitorarConta")) {
					this.exibirMonitorarContas(req, resp);
				} else if (acaoUsuario.equals("listaContas")) {
					this.exibirListaConta(req, resp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void inserirLancamento(HttpServletRequest req,
			HttpServletResponse resp) {

		String numeroContaOrigem = req
				.getParameter("numeroContaCorrenteOrigem");

		String numeroContaDestino = req
				.getParameter("numeroContaCorrenteDestino");

		String data = req.getParameter("data");

		String valorLancamento = req.getParameter("valorLancamento");

		String tipoCartao = req.getParameter("tipoCartao");

		String descricao = req.getParameter("descricao");

		ControladorLancamento controladorLancamento = new ControladorLancamento();

		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			
			controladorLancamento.incluirLancamentoTransferencia(Integer
					.parseInt(numeroContaOrigem), Integer
					.parseInt(numeroContaDestino), Integer
					.parseInt(valorLancamento), tipoCartao, format.parse(data),
					descricao);

			this.exibirListaLancamentos(req, resp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void exibirIncluirLancamentos(HttpServletRequest req,
			HttpServletResponse resp) {
		try {
			req.getRequestDispatcher("/cadastroLancamento.jsp").forward(req,
					resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cancelarLancamentos(HttpServletRequest req,
			HttpServletResponse resp) {
		List<LancamentoBean> listLancamento = new ControladorLancamento()
				.pesquisarLancamentos();

		req.setAttribute("listLancamento", listLancamento);

		try {
			req.getRequestDispatcher("/listaLancamentos.jsp")
					.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exibirListaLancamentos(HttpServletRequest req,
			HttpServletResponse resp) {
		List<LancamentoBean> listLancamento = new ControladorLancamento()
				.pesquisarLancamentos();

		req.setAttribute("listLancamento", listLancamento);

		try {
			req.getRequestDispatcher("/listaLancamentos.jsp")
					.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exibirMonitorarLancamento(HttpServletRequest req,
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

	private void excluirLancamento(HttpServletRequest req,
			HttpServletResponse resp) {

		String idLancamento = req.getParameter("lancamentoExcluir");

		ControladorLancamento controladorLancamento = new ControladorLancamento();

		try {
			controladorLancamento.excluirLancamentos(Integer
					.parseInt(idLancamento));
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.exibirListaLancamentos(req, resp);
	}
}
