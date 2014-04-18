<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	 
<html>
    <head>
        <title>Inicial</title>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link href="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" media="screen"
              href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
        
        <script type="text/javascript">
		       function acaoLancamentoUsuario(tela) {
				document.getElementById('acaoUsuarioLancamento').value = tela;
				document.forms[0].submit(); 
			}
        </script>      
    </head>
    <body>
        <script src="http://code.jquery.com/jquery-latest.js"></script>

    <center>
        <h2>Monitorar Conta</h2>
        <div class="centro" style="width: 1100px;  text-align: center; margin-top: 100px;"  >
            <ul class="nav nav-tabs" style="margin-bottom: 50px;">
            	 <li class="active">
	                    <a href="javaScript:acaoLancamentoUsuario('monitorarConta');">Contas</a>
	                </li>
	                <li><a href="javaScript:acaoLancamentoUsuario('monitorarLancamento');">Monitorar Conta</a></li>
	                <li><a href="javaScript:acaoLancamentoUsuario('listaContas');">Cadastro</a></li>
            </ul>

            <div style="text-align: left;">
                <form action="lancamentos" method="get" class="bs-docs-example" >
					<input type="hidden" name="acaoUsuarioLancamento" id="acaoUsuarioLancamento"/>
                    <div style="float: left; margin-bottom: 20px ; ">

                        <select class="span3" style="height: 30px; width: 200px;">
                            <option>Selecione a Conta</option>
                            <option>Conta 1</option>
                            <option>Conta 2</option>
                            <option>Conta 3</option>
                            <option>Conta 4</option>
                        </select>
                    </div>
                    <div style="margin-left: 10px; float: left">
                        <input type="checkbox" id="lancFuturos" value="lancFuturos" > Lancamentos Futuros
                    </div>
                    <div style="float: right" >
                        Data inicio:
                        <div id="datetimepicker1" class="input-append date" style="margin-right: 20px">
                            <input type="text" disabled style="height: 30px;"></input>
                            <span class="add-on">
                                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                            </span>
                        </div>
                        Data fim:
                        <div id="datetimepicker2" class="input-append date">
                            <input type="text" disabled style="height: 30px;"></input>
                            <span class="add-on">
                                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                            </span>
                        </div>
                    </div>

                </form>
            </div>
            
            
            <table class="table table-hover" style="text-align: center; ">
                <thead>
                    <tr>
                        <th>Dia</th>
                        <th>Historico</th>
                        <th>Débito</th>
                        <th>Crédito</th>
                        <th>SALDO</th>
                    </tr>
                </thead>
                <tbody>
                	<tr>
	                	<c:forEach var="lancamento" items="${listLancamento}">  
	                        <tr>
		                        <td>${lancamento.dataFormatada} </td>
		                        <td>Transferência </td>
		                        <td>
		                        	<c:if test="${!lancamento.isCredito}">
		                        		${lancamento.valor}
		                        	</c:if>
		                        </td>
		                        <td>
		                        	<c:if test="${lancamento.isCredito}">
		                        		${lancamento.valor}
		                        	</c:if>
		                        </td>
		                        <td>10000 </td>
		                    </tr>    
		               </c:forEach>
		            </tr> 
                </tbody>
            </table>
            <div style="text-align: right;padding-bottom: 9px;  border-bottom: 1px solid #000;">
                Saldo: <input  disabled type="text" value="R$ 1000,00" >
            </div>
            <ul class="nav nav-pills">
                <li><a href="javaScript:acaoLancamentoUsuario('listaLancamentos');">Lançamento</a></li>
                <li><a href="#">Relatório</a></li>
                <li><a href="#"> Cadastro Cheque</a></li>
            </ul>
        </div>

    </center>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="http://cdnjs.cloudflare.com/ajax/libs/jquery/1.8.3/jquery.min.js">
    </script> 
    <script type="text/javascript"
            src="http://netdna.bootstrapcdn.com/twitter-bootstrap/2.2.2/js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
            src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript"
            src="http://tarruda.github.com/bootstrap-datetimepicker/assets/js/bootstrap-datetimepicker.pt-BR.js">
    </script>
    <script type="text/javascript">
        $('#datetimepicker1').datetimepicker({
            format: 'dd/MM/yyyy',
            language: 'pt-BR'
        });
        $('#datetimepicker2').datetimepicker({
            format: 'dd/MM/yyyy',
            language: 'pt-BR'
        });
    </script>
</body>
</html>
