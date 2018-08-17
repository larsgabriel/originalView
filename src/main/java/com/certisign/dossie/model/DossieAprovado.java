package com.certisign.dossie.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "tbl_dossie")
public class DossieAprovado {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "dossie_seq", sequenceName = "dossie_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dossie_seq")
	@Column(name = "PK_AD_DOSSIE")
	private Long id;
	
    @JoinColumn(name="PEDID")
	@Column(name = "FK_CD_PEDIDO")
	private String numeroPedido;

	@Column(name = "CD_AR")
	private String ar;

	@Column(name = "CD_POSTO")
	private String posto;

	@Column(name = "FK_AD_CAIXA_EXTERNA")
	private String caixaExterna;

	@Column(name = "FK_AD_CAIXA_INTERNA")
	private String caixaInterna;

	@Column(name = "FK_TP_TERMO")
	private String tipoTermo;

	@Column(name = "DT_APROVACAO")
	private Timestamp dataAprovacao;

	@Column(name = "DT_EXPIRACAO_CERT")
	private Timestamp dataExpiracao;

	@Column(name = "DT_SAIDA_AR")
	private Timestamp dataSaidaAr;

	@Column(name = "DT_RECEBIMENTO")
	private Timestamp dataRecebimento;

	@Column(name = "DT_REGISTRO")
	private Timestamp dataRegistro;

	@Column(name = "DT_RESGATE")
	private Timestamp dataResgate;

	@Column(name = "ID_STATUS_AR")
	private Integer statusAr;

	@Column(name = "ID_STATUS_PEDIDO")
	private Integer idStatusPedido;

	@Column(name = "ID_LEGADO")
	private Integer idLegado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(String numeroPedido) {
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

	public Integer getStatusAr() {
		return statusAr;
	}

	public void setStatusAr(Integer statusAr) {
		this.statusAr = statusAr;
	}

	public Integer getIdStatusPedido() {	
		return idStatusPedido;
	}

	public void setIdStatusPedido(Integer idStatusPedido) {
		this.idStatusPedido = idStatusPedido;
	}

	public Integer getIdLegado() {
		return idLegado;
	}

	public void setIdLegado(Integer idLegado) {
		this.idLegado = idLegado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
