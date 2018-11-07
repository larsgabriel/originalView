package com.certisign.dossie.model;

public class FormRegistro {

	String numeroPedido;
	String cxExterna;
	String cxInterna;
	Long pedidoTratado;

	public Long getPedidoTratado() {
		return pedidoTratado;
	}
	public void setPedidoTratado(Long pedidoTratado) {
		this.pedidoTratado = pedidoTratado;
	}
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getCxExterna() {
		return cxExterna;
	}
	public void setCxExterna(String cxExterna) {
		this.cxExterna = cxExterna;
	}
	public String getCxInterna() {
		return cxInterna;
	}
	public void setCxInterna(String cxInterna) {
		this.cxInterna = cxInterna;
	}
	
	
}
