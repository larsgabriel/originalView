package com.certisign.dossie.model;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "tbl_dossie")
public class DossieAprovado {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name = "dossie_seq", sequenceName = "dossie_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dossie_seq")
	@Column(name = "PK_AD_DOSSIE")
	private Long id;
	
	@Column(name = "CD_PEDIDO")
	private Long numeroPedido;

	@Column(name = "CD_AR")
	private String ar;

	@Column(name = "CD_POSTO")
	private String posto;

	@Column(name = "CD_CAIXA_EXTERNA")
	private String caixaExterna;

	@Column(name = "CD_CAIXA_INTERNA")
	private String caixaInterna;

	@Column(name = "TIPO_TERMO")
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

	@Column(name = "STATUS_AR")
	private String statusAr;

	@Column(name = "STATUS_PEDIDO")
	private String statusPedido;

	@Column(name = "ID_LEGADO")
	private Long idLegado;
	
	@Column(name = "NR_CPF")
	private Long cpf;
	
	@Column(name = "NR_CNPJ")
	private Long cnpj;
	
	@Column(name = "CD_USUARIO")
	private String cdUsuario;
	
	@Column(name = "DT_INSERCAO")
	private Timestamp dataInsercao;
	
	@Column(name = "DT_ULT_ATUALIZACAO")
	private Timestamp dataUltAtualizacao;
		

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

	public void setStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
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

	public String getStatusPedido() {	
		return statusPedido;
	}

	public void setIdStatusPedido(String statusPedido) {
		this.statusPedido = statusPedido;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
