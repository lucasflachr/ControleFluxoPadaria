<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<html>
	<script type="text/javascript">
		function acaoUsuario(tela) {
			document.getElementById('acaoUsuarioConta').value = tela;
			document.forms[0].submit(); 
		}
	</script>
    <head>
        <title>Inicial</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    </head>
    <body>
    	<form action="cadastroConta" method="get">
        <script src="http://code.jquery.com/jquery-latest.js"></script>
		<input type="hidden" name="acaoUsuarioConta" id="acaoUsuarioConta"/>
	    <center>
	        <h2>Contas</h2>
	        <div class="centro" style="width: 1100px;    text-align: center; margin-top: 100px;"  >
	            <ul class="nav nav-tabs">
	                <li class="active">
	                    <a href="javaScript:acaoUsuario('monitorarConta');">Contas</a>
	                </li>
	                <li><a href="javaScript:acaoUsuario('monitorarLancamento');">Monitorar Conta</a></li>
	                <li><a href="javaScript:acaoUsuario('listaContas');">Cadastro</a></li>
	            </ul>
	            <table class="table table-hover" style="text-align: center;">
	                <thead>
	                    <tr>
	                        <th>Conta Corrente</th>
	                        <th>Agencia</th>
	                        <th>Banco</th>
	                        <th>Saldo</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<tr>
		                	<c:forEach var="conta" items="${listContas}">  
		                        <tr>
			                        <td>${conta.descricaoConta} ${conta.numeroConta} </td>
			                        <td>${conta.agencia} </td>
			                        <td>${conta.agencia} </td>
			                        <td>${conta.saldo} </td>
			                    </tr>    
			               </c:forEach>
			            </tr> 
	                </tbody>
	            </table>
	        </div>
	    </center>
	    <!-- Incluindo o JavaScript do Bootstrap -->
	    <script src="js/bootstrap.min.js">
		    
	    </script>
	</form>    
</body>
</html>
