package com.certisign.dossie.model;

import java.sql.Timestamp;

public class DossieAprovado {

	private Long id;
	private Long numeroPedido;
	private String ar;
	private String posto;
	private String caixaExterna;
	private String caixaInterna;
	private String tipoTermo;
	private Timestamp dataAprovacao;
	private Timestamp dataExpiracao;
	private Timestamp dataSaidaAr;
	private Timestamp dataRecebimento;
	private Timestamp dataRegistro;
	private Timestamp dataResgate;
	private String statusAr;
	private String statusCertisign;
	private Long idLegado;
	private Long cpf;
	private Long cnpj;
	private String cdUsuario;
	private Timestamp dataInsercao;
	private Timestamp dataUltAtualizacao;

	public String getStatusCertisign() {
		return statusCertisign;
	}

	public void setStatusCertisign(String statusCertisign) {
		this.statusCertisign = statusCertisign;
	}

	public Timestamp getDataInsercao() {
		return dataInsercao;
	}

	public void setDataInsercao(Timestamp dataInsercao) {
		this.dataInsercao = dataInsercao;
	}

	public Timestamp getDataUltAtualizacao() {
		return dataUltAtualizacao;
	}

	public void setDataUltAtualizacao(Timestamp dataUltAtualizacao) {
		this.dataUltAtualizacao = dataUltAtualizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public String getAr() {
		return ar;
	}

	public void setAr(String ar) {
		this.ar = ar;
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
		this.posto = posto;
	}

	public String getCaixaExterna() {
		return caixaExterna;
	}

	public void setCaixaExterna(String caixaExterna) {
		this.caixaExterna = caixaExterna;
	}

	public String getCaixaInterna() {
		return caixaInterna;
	}

	public void setCaixaInterna(String caixaInterna) {
		this.caixaInterna = caixaInterna;
	}

	public String getTipoTermo() {
		return tipoTermo;
	}

	public void setTipoTermo(String tipoTermo) {
		this.tipoTermo = tipoTermo;
	}

	public Timestamp getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Timestamp dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public Timestamp getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(Timestamp dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public Timestamp getDataSaidaAr() {
		return dataSaidaAr;
	}

	public void setDataSaidaAr(Timestamp dataSaidaAr) {
		this.dataSaidaAr = dataSaidaAr;
	}

	public Timestamp getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Timestamp dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public Timestamp getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(Timestamp dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public Timestamp getDataResgate() {
		return dataResgate;
	}

	public void setDataResgate(Timestamp dataResgate) {
		this.dataResgate = dataResgate;
	}

	public String getStatusAr() {
		return statusAr;
	}

	public void setStatusAr(String statusAr) {
		this.statusAr = statusAr;
	}

	public Long getIdLegado() {
		return idLegado;
	}

	public void setIdLegado(Long idLegado) {
		this.idLegado = idLegado;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
}
