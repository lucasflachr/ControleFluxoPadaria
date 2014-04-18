package br.com.rocha.Action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.rocha.ContaBean;
import br.com.rocha.Control.ControladorContaCorrente;

/**
 * Classe respons�vel em controlar os eventos da tela principal.
 * 
 * @author Lucas
 * 
 */
public class MainAction extends HttpServlet {

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acaoUsuario = request.getParameter("acaoUsuario");

		if (acaoUsuario != null) {
			if (acaoUsuario.equals("conta")) {
				try {
					List<ContaBean> listContas = new ControladorContaCorrente()
							.pesquisarContas(null);
					request.setAttribute("listContas", listContas);

					request.getRequestDispatcher("/monitorarConta.jsp")
							.forward(request, response);
					// response.sendRedirect("listaConta.jsp");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else
			response.sendRedirect("main.jsp");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acaoUsuario = request.getParameter("acaoUsuario");

		if (acaoUsuario != null) {
			if (acaoUsuario.equals("conta")) {
				request.getRequestDispatcher("/listaConta.jsp");
			}
		}
	}
}
