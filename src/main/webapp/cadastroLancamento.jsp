<%@page contentType="text/html"%>  
<html>
    <head>
        <title>Inicial</title>
        <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
        <link rel="stylesheet" type="text/css" media="screen"
              href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
	
		<script type="text/javascript">
		
			function acaoLancamentoUsuario(tela) {
				document.getElementById('acaoUsuarioLancamento').value = tela;
				if (tela == 'inserirLancamento') {
					if (validaCamposObrigatorios() == true) {
						document.forms[0].submit();
					}
				} else {
					document.forms[0].submit();
				}	 
			}

			function SomenteNumero(e){
			    var tecla=(window.event)?event.keyCode:e.which;
			    if((tecla>47&&  tecla<58)) return true;
			    else{
			        if (tecla==8 || tecla==0) return true;
			        else  return false;
			    }
			}

			function validaCamposObrigatorios() {
				if (document.getElementById('numeroContaCorrenteOrigem').value == "") {
					alert("Campo Número Conta Corrente Origem é obrigatório.");
					return false;
				} else if (document.getElementById('numeroContaCorrenteDestino').value == "") {
					alert("Campo Número Conta Corrente Destino é obrigatório.");
					return false;
				} else if (document.getElementById('data').value == "") {
					alert("Campo Data do Lançamento é obrigatório.");
					return false;
				} else if (document.getElementById('valorLancamento').value == "") {
					alert("Campo Valor do lançamento é obrigatório.");
					return false;
				}
				return true;
			}
		</script>
    </head>
    <body>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
    <center>
        <h2>Cadastro de Conta Corrente</h2>
        <div class="centro" style="width: 800px;  text-align: center; margin-top: 100px;"  >
            <form action="lancamentos" method="get" style="text-align: left">
                	<input type="hidden" name="acaoUsuarioLancamento" id="acaoUsuarioLancamento"/>
                    Nº Conta Corrente Origem
                    <br>
                    <input align="center" type="text" name="numeroContaCorrenteOrigem" id="numeroContaCorrenteOrigem" onkeypress="return SomenteNumero(event)" maxlength="10" style="height: 30px; width: 200px;">
                    <br/>
                    Nº Conta Corrente Destino
                    <br>
                    <input align="center" type="text" name="numeroContaCorrenteDestino" id="numeroContaCorrenteDestino" onkeypress="return SomenteNumero(event)" maxlength="10" style="height: 30px; width: 200px;">
                    <br/>
                    Data do Lançamento
                    <br/>
                    <div id="datetimepicker2" class="input-append date">
                        <input type="text" readonly="true" style="height: 30px; width: 200px;" name="data" id="data"></input>
                        <span class="add-on">
                            <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
                        </span>
                    </div>
                    <br>
                    Valor do Lançamento
                    <br>
                    <input align="center" type="text" name="valorLancamento" id="valorLancamento" maxlength="20" style="height: 30px; width: 200px;">
                    <br>
                    Debito
                    <input type="radio" name="tipoCartao" id="optionsRadios1" value="D" checked style="margin-right: 50px;">
                    Credito
                    <input type="radio" name="tipoCartao" id="optionsRadios1" value="C">
                    <br/><br/>
                    Anotação:
                    <br/>
                    <textarea rows="3" name ="descricao" maxlength="100"></textarea>
            <br/>
            <br/>
                <button  type="button" class="btn" onclick="javaScript:acaoLancamentoUsuario('cancelarLancamento');">Cancelar</button>
				<button  type="button" class="btn"  onclick="javaScript:acaoLancamentoUsuario('inserirLancamento');">Salvar</button>
            </form>
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
