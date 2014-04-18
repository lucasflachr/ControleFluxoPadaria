<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<html>
	<script type="text/javascript">
		 function acaoUsuario(tela) {
				document.getElementById('acaoUsuarioConta').value = tela;
				if (tela == 'excluirConta') {
					if (confirm('Deseja realmente Excluir esta Conta ?')) {
						document.forms[0].submit(); 
					}					
				} else {
					document.forms[0].submit(); 
				}
				
		 }	
		 
		 function selecionaConta(conta) {
			 document.getElementById('contaExcluir').value = conta;
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
		<input type="hidden" name="contaExcluir" id="contaExcluir"/>
	    <center>
	        <h2>Contas</h2>
	        <div class="centro" style="width: 1100px;    text-align: center; margin-top: 100px;"  >
	            <table class="table table-hover" style="text-align: center;">
	                <thead>
	                    <tr>
	                    	<th>Excluir</th>
	                        <th>Conta</th>
	                        <th>Número</th>
	                        <th>Banco</th>
	                    </tr>
	                </thead>
	                <tbody>
	                	<tr>
		                	<c:forEach var="conta" items="${listContas}">  
		                        <tr>
		                        	<td>
		                        		<input type="radio" onclick="javaScript:selecionaConta('${conta.idConta}');">
		                        	</td>
			                        <td>${conta.descricaoConta}</td>
			                        <td>${conta.numeroConta} </td>
			                        <td>${conta.agencia} </td>
			                    </tr>    
			               </c:forEach>
			            </tr> 
	                </tbody>
	            </table>
	            <div align="right">
	            	<button  type="button"  class="btn" onclick="javaScript:acaoUsuario('monitorarConta');">Retornar</button>
					<button  type="button"  class="btn" onclick="javaScript:acaoUsuario('excluirConta');">Excluir</button>
					<button  type="button"  class="btn" onclick="javaScript:acaoUsuario('exibirCadastroConta');">Novo</button>
	            </div>
	        </div>
	    </center>
	    <!-- Incluindo o JavaScript do Bootstrap -->
	    <script src="js/bootstrap.min.js">
	    </script>
	</form>    
</body>
</html>
