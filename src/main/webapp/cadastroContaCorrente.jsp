<%@page import="br.com.rocha.BancoBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<html>
	<script type="text/javascript">


		function SomenteNumero(e){
		    var tecla=(window.event)?event.keyCode:e.which;
		    if((tecla>47&&  tecla<58)) return true;
		    else{
		        if (tecla==8 || tecla==0) return true;
		        else  return false;
		    }
		}

		function acaoCadastroUsuario(tela) {
			document.getElementById('acaoUsuarioConta').value = tela;
			if (tela == 'inserirConta') {
				if (validaCamposObrigatorios() == true) {
					document.forms[0].submit(); 
				}
			} else {
				document.forms[0].submit();
			}	
		}	

		function validaCamposObrigatorios() {
			if (document.getElementById('numeroContaCorrente').value == "") {
				alert("Campo Número Conta Corrente é obrigatório.");
				return false;
			} else if (document.getElementById('contaCorrente').value == "") {
				alert("Campo Conta Corrente é obrigatório.");
				return false;
			} else if (document.getElementById('banco').value == "") {
				alert("Campo Banco é obrigatório.");
				return false;
			}
			return true;
		}
		
	</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Conta Corrente</title>
<!-- Incluindo o CSS do Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
	<form action="cadastroConta" method="get">
		<input type="hidden" name="acaoUsuarioConta" id="acaoUsuarioConta"/>
		  <center>
			<h2>Cadastro de Conta Corrente</h2>
				
			<div>
				Nº Conta Corrente
				<br>
				<input type="text" name="numeroContaCorrente" id="numeroContaCorrente" maxlength="10" onkeypress="return SomenteNumero(event)">
				<br>
				Conta Corrente
				<br>
				<input type="text" name="contaCorrente" id="contaCorrente" maxlength="20">
				<br>
				Banco<br>
				<select name="banco" id="banco"> 
					<option value="">..</option>  			
					<c:forEach var="banco" items="${listaBanco}">  
						<option value="${banco.codigoBanco}">  
				      		${banco.nomeBanco}  
						</option>  			
				 	</c:forEach>
				</select>  
				
			</div>
			
			<div align="center">
				<button  type="button" class="btn" onclick="javaScript:acaoCadastroUsuario('listaContas');">Cancelar</button>
				<button  type="button" class="btn"  onclick="javaScript:acaoCadastroUsuario('inserirConta');">Salvar</button>
			</div>
		</center>	
	</form>
</body>
</html>