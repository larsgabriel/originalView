			(function($) {
					$.fn.invisible = function() {
						return this.each(function() {
							$(this).css("display", "none");
						});
					};
					$.fn.visible = function() {
						return this.each(function() {
							$(this).css("display", "block");
						});
					};
			}(jQuery));

			$(document).ready(function () {
				var maskCpf = '000.000.000-00';
    			$("#cpf_pesquisa").mask(maskCpf, {reverse: true});
			});
			
			
			function atualizarContador() {
				$.post('/portalcontadoradmin/pesquisacontadoradmin/atualizarContador',
						$('#formEdicaoContador').serialize())
				.done(function() {
					alert('Registro alterado com sucesso!');
					$("#modalEdicao").modal("toggle");
				})
				.fail(function (e){
					alert(e.responseJSON.message);
				});
			}
			
			function selecionarParceiros(cidade) {
				var arParceirosList = $("#contador-arParceiro-select");
				var estadoValue = $("#estadoParceiroId").val();
				var cidadeValue = cidade.value;
				var skinValue = $("#skinSelectId").val();
				var url = "/portalcontadoradmin/pesquisacontadoradmin/selecionarParceiros/" + estadoValue + "/" + cidadeValue + "/" + skinValue;
				
				arParceirosList.empty();
				
				$.getJSON(url, function (data) {
					$.each(data, function (key, entry) {
						arParceirosList.append($('<option></option>').attr('value', entry.pk.idAr).text(entry.descricaoItemSelecao));
					})
				})
			}
			
			function selecionarSkin(skin) {
				var arParceirosList = $("#contador-arParceiro-select");
				var estadoValue = $("#estadoParceiroId").val();
				var cidadeValue = $("#cidadesParceiroId").val();
				var skinValue = skin.value;
				var url = "/portalcontadoradmin/pesquisacontadoradmin/selecionarParceiros/" + estadoValue + "/" + cidadeValue + "/" + skinValue;
				
				arParceirosList.empty();
				
				$.getJSON(url, function (data) {
					$.each(data, function (key, entry) {
						arParceirosList.append($('<option></option>').attr('value', entry.pk.idAr).text(entry.descricaoItemSelecao));
					})
				})
			}

			function selecionarEstado(estado) {
				var cidadesParceiros = $("#cidadesParceiroId");
				var arParceirosList = $("#contador-arParceiro-select");
				var skinValue = $("#skinSelectId").val();
				var url = "/portalcontadoradmin/pesquisacontadoradmin/selecionarEstado/" + estado.value + "/" + skinValue;

				cidadesParceiros.empty();
				arParceirosList.empty();
				
				cidadesParceiros.append('<option value="TODAS" selected="true">--TODAS--</option>');
				cidadesParceiros.prop('selectedIndex', 0);

				$.getJSON(url, function (data) {
					var cidades = data.cidades;
					var listaParceiros = data.listaParceiros;
					  $.each(cidades, function (key, entry) {
						  cidadesParceiros.append($('<option></option>').attr('value', entry).text(entry));
					  })
					  $.each(listaParceiros, function (key, entry) {
						  arParceirosList.append($('<option></option>').attr('value', entry.pk.idAr).text(entry.descricaoItemSelecao));
					  })
				});
			}
			
			function filterByNameParceiro() {
			    var input, filter, select, option, i;
			    input = document.getElementById('contador-arParceiro_filter');
			    filter = input.value.toUpperCase();
			    select = document.getElementById("contador-arParceiro-select");
			    option = select.getElementsByTagName('option');

			    for (i = 0; i < option.length; i++) {
			        if (option[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
			        	option[i].style.display = "";
			        } else {
			        	option[i].style.display = "none";
			        }
			    }
			}
			
			function habilitarDadosBancarios(data) {
				if(data != null && data != undefined) {
					if(data.habilitarDadosBancarios) {
						$("#dados-bancarios").visible();
					} else {
						$("#dados-bancarios").invisible();
					}
				}
			}
			
			function habilitarDadosEmpresa(data) {
				if(data != null && data != undefined) {
					if(data.habilitarDadosEmpresa) {
						$("#dados-empresa").visible();
					} else {
						$("#dados-empresa").invisible();
					}
				}
			}
			
			function habilitarIndicadoParceiro(data) {
				if(data != null && data != undefined) {
					if(data.habilitarIndicadoParceiro) {
						$("#indicadoParceiroId").visible();
					} else {
						$("#indicadoParceiroId").invisible();
					}
				}
			}
			
			function selecionarManterRelacaoParceiro(indicadorParceiro) {
				var indicadorParceiroValue = indicadorParceiro.value;
				var data = {
					habilitarRegistroNaoConheceParceiro : false,
					habilitarAutoridadeRegistroFiltro : false,
					habilitarAutoridadeRegistro : false
				}
				
				if(indicadorParceiroValue == "true") {
					data.habilitarRegistroNaoConheceParceiro = true;
					data.habilitarAutoridadeRegistroFiltro = true;
					data.habilitarAutoridadeRegistro = true;
					
					$('#conheceParceiro2').prop('checked', true);
					habilitarRegistroNaoConheceParceiro(data);
					habilitarAutoridadeRegistroFiltro(data);
					habilitarAutoridadeRegistro(data);
				} else {
					habilitarRegistroNaoConheceParceiro(data);
					habilitarAutoridadeRegistroFiltro(data);
					habilitarAutoridadeRegistro(data);
				}
			}
			
			function habilitarAutoridadeRegistro(data) {
				if(data != null && data != undefined) {
					if(data.habilitarAutoridadeRegistro) {
						$("#autoridade-registro").visible();
					} else {
						$("#autoridade-registro").invisible();
					}
				}
			}
			
			function habilitarAutoridadeRegistroFiltro(data) {
				if(data != null && data != undefined) {
					if(data.habilitarAutoridadeRegistroFiltro) {
						$("#autoridade-registro-filtro").visible();
					} else {
						$("#autoridade-registro-filtro").invisible();
					}
				}
			}
			
			function habilitarRegistroNaoConheceParceiro(data) {
				if(data != null && data != undefined) {
					if(data.habilitarRegistroNaoConheceParceiro) {
						$("#autoridade-registro-nao-conhece-parceiro").visible();
					} else {
						$("#autoridade-registro-nao-conhece-parceiro").invisible();
					}
				}
			}
			
			function selecionarTipoPessoa(tipoPessoa, skin) {
				var url = "/portalcontadoradmin/pesquisacontadoradmin/selecionarTipoPessoa";
				var tipoPessoaValue = tipoPessoa.value;
				var contador = {
					"tipoPessoa" : tipoPessoaValue,
					"nomeSkinGar" : skin
				}
				
				if(tipoPessoaValue == 'PJ') {
					$('#listRelacaoComercial').removeClass("disabled");
					$('#conheceParceiro1').prop('checked', true);
					$('#indicadoParceiro2').prop('checked', true);
					//2 nao
				} else {
					$('#listRelacaoComercial').addClass("disabled");
					$('#indicadoParceiro1').prop('checked', true);
					$('#conheceParceiro2').prop('checked', true);
					//1 sim
				}
				
				$.ajax({
					url : url,
					method : "POST",
					data : JSON.stringify(contador),
					contentType : "application/json",
					success : function(result) {
						habilitarDadosEmpresa(result);
						habilitarDadosBancarios(result);
						
						habilitarRegistroNaoConheceParceiro(result);//voce conhece o seu parceiro      PF: exibe
						habilitarAutoridadeRegistroFiltro(result);//selecione o estado/estado/cidade   PF: exibe
						habilitarAutoridadeRegistro(result);//indique qual/autoridade-registro         PF: exibe
					},
					error : function(e) {
						console.log(e);
					}
				});
			}
			
			function selecionarConheceParceiro(conheceParceiro) {
				var conheceParceiroValue = conheceParceiro.innerText;
				var data = {
					habilitarAutoridadeRegistroFiltro : false
				}
				
				if(conheceParceiroValue == "Sim") {
					habilitarAutoridadeRegistroFiltro(data);
				} else {
					data.habilitarAutoridadeRegistroFiltro = true;
					habilitarAutoridadeRegistroFiltro(data);
				}
				
			}

			function openModalEdicao(cpf) {
				$.ajax({
					url: "/portalcontadoradmin/pesquisacontadoradmin/visualizarContador/" + cpf,
					success: function(data) {
						$("#modalEdicaoHolder").html(data);
						formatarCampos();
						$("#modalEdicao").modal("show");
					}
				});
			}

			function formatarCampos() {
				var maskCpf = '000.000.000-00';
				var maskCnpj = '00.000.000/0000-00';
				var maskCelular = '(99)99999-9999';
				var maskCep = '00000-000';
				
				$("#contador-cpf").val(("00000000000" + $("#contador-cpf").val()).slice(-11));
				$("#contador-cpf").mask(maskCpf, {reverse: true});
    			
				$("#cpfContador").val(("00000000000" + $("#cpfContador").val()).slice(-11));
				$("#cpfContador").mask(maskCpf, {reverse: true});
    			//$("#contador-telefoneCelular").mask(maskCelular, {reverse: true});    			
    			//$("#contador-telefoneFixo").mask(maskCelular, {reverse: true});
				
				$("#endereco-cep").val(("00000000" + $("#endereco-cep").val()).slice(-8));
    			$("#endereco-cep").mask(maskCep, {reverse: true});
    			
				$("#dadosbancarios-cnpj").val(("00000000000000" + $("#dadosbancarios-cnpj").val()).slice(-14));
    			$("#dadosbancarios-cnpj").mask(maskCnpj, {reverse: true});
    			
    			$("#cnpj-dados-bancarios").val(("00000000000000" + $("#cnpj-dados-bancarios").val()).slice(-14));
    			$("#cnpj-dados-bancarios").mask(maskCnpj, {reverse: true});
    			
    			onlyNumbers("crc-numero", "contador-telefoneCelular", "contador-telefoneFixo", "endereco-cep", "endereco-numero");
    			
			}

			function onlyNumbers() {
				for(i = 0; i < arguments.length; i++) {
					$("#"+arguments[i]).keydown(function (e) {
				        // Allow: backspace, delete, tab, escape, enter and .
				        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
				             // Allow: Ctrl+A, Command+A
				            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) || 
				             // Allow: home, end, left, right, down, up
				            (e.keyCode >= 35 && e.keyCode <= 40)) {
				                 // let it happen, don't do anything
				                 return;
				        }
				        // Ensure that it is a number and stop the keypress
				        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
				            e.preventDefault();
				        }
				    });
				}
			}

			function validarCpf() {
				var valueCpf = $("#cpf_pesquisa").val();
				if(valueCpf == null || valueCpf == '' || valueCpf == undefined) {
					alert('Campo CPF deve ser preenchido');
					$("#cpf_pesquisa").focus();
					return false;
				}
				return true;
			}

			function openModalExclusao() {
				$("#modalExclusao").modal("show");
			}

			function excluirContador() {
				var cpf = $("#cpfContador").html();
				$.ajax({
					url: "/portalcontadoradmin/pesquisacontadoradmin/excluirContador/" + cpf,
					error: function(request, status, error) {
						alert(request.responseText);
						}
					}).done(function() {
							alert('Contador excluido com sucesso');
							$("#tabelaContadores tbody").remove();
						}
					);
			}