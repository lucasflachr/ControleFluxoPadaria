<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	 
<html>
    <head>
        <title>Inicial</title>

        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        
         <script type="text/javascript">
		       function acaoLancamentoUsuario(tela) {
				document.getElementById('acaoUsuarioLancamento').value = tela;
				if (tela == 'excluirLancamento') {
					if (confirm('Deseja realmente Excluir este Lançamento ?')) {
						document.forms[0].submit();
					}
				} else {
					document.forms[0].submit();
				}
			}

	       function selecionaLancamento(conta) {
				 document.getElementById('lancamentoExcluir').value = conta;
		     }
        </script>  
    </head> 
    <body>
        <script src="http://code.jquery.com/jquery-latest.js"></script>


        <center>
            <h3>Lista Lançamentos</h3>
            <div class="centro" style="width: 1100px;   text-align: center; margin-top: 100px;"  >
                 <form action="lancamentos" method="get" class="bs-docs-example" >
	                <input type="hidden" name="acaoUsuarioLancamento" id="acaoUsuarioLancamento"/>
	                <input type="hidden" name="lancamentoExcluir" id="lancamentoExcluir"/>
	                
	                <table class="table table-hover" style="text-align: center;">
	                    <thead>
	                        <tr>
	                            <th>Selecionar</th>
	                            <th>Valor</th>
	                            <th>Tipo</th>
	                            <th>Data</th>
	                            <th>Conta</th>
	                            <th>Operação</th>	                            
	                        </tr>
	                    </thead>
	                    <tbody>
	                                        	
                        <c:forEach var="lancamento" items="${listLancamento}">  
                            <tr>
                                <td>
                                    <input type="radio" onclick="javaScript:selecionaLancamento('${lancamento.codigoLancamento}');">
                                </td>
                                <td>${lancamento.valor}</td>
                                <td>Transferência </td>
                                <td>${lancamento.dataFormatada} </td>
                                <td>${lancamento.conta} </td>
                                <td>${lancamento.operacaoFormatada} </td>	                                
                            </tr>    
                        </c:forEach>
	                    </tbody>
	                </table>
	                <form class="bs-docs-example" style=" text-align: right; position: relative;
	                      margin: 15px 0;
	                      padding: 39px 19px 14px;">
	                     <button class="btn" type="button" onclick="javaScript:acaoLancamentoUsuario('monitorarLancamento');">Retornar</button>
	                    <button class="btn" type="button" onclick="javaScript:acaoLancamentoUsuario('excluirLancamento');">Excluir</button>
	                    <button class="btn" type="button" onclick="javaScript:acaoLancamentoUsuario('exibirIncluirLancamentos');">Novo</button>
	                </form>
	             </form>   
            </div>

        </center>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
