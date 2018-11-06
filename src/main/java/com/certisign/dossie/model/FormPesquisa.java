package com.certisign.dossie.model;

public class FormPesquisa {
	
	private String numeroPedido;
	private String cxInterna;
	private String cxExterna;
	private String statusAr;
	private String statusCertisign;
	private Integer page;
	private Integer size;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public String getCxInterna() {
		return cxInterna;
	}
	public void setCxInterna(String cxInterna) {
		this.cxInterna = cxInterna;
	}
	public String getCxExterna() {
		return cxExterna;
	}
	public void setCxExterna(String cxExterna) {
		this.cxExterna = cxExterna;
	}
	public String getStatusAr() {
		return statusAr;
	}
	public void setStatusAr(String statusAr) {
		this.statusAr = statusAr;
	}
	public String getStatusCertisign() {
		return statusCertisign;
	}
	public void setStatusCertisign(String statusCertisign) {
		this.statusCertisign = statusCertisign;
	}
	
	
	
}
