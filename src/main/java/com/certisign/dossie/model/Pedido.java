package com.certisign.dossie.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity(name = "pedidos")
@NamedQueries({
	@NamedQuery(name = "PEDIDO.PROCURA_POR_PEDIDO", query = "from pedidos p where p.pedid = :pedid")})
public class Pedido {
	
	public final static String PROCURA_POR_PEDIDO = "PEDIDO.PROCURA_POR_PEDIDO";
	
	@Id
	@Column(name = "PEDID")
	private Long pedid;
	
	@Column(name = "PEDDATA")
	private Timestamp pdData;
	
	@Column(name = "PRDID")
	private String prDid;
	
	@Column(name = "PEDFI")
	private String pedFi;
	
	@Column(name = "PEDSEED")
	private String pedSeed;
	
	@Column(name = "SPEDID")
	private Long spedId;
	
	@Column(name = "ESTPIN1")
	private Long estpin1;

	@Column(name = "USUID")
	private Long usuId;
	
	@Column(name = "PFSCPF")
	private Long pfsCpf;
	
	@Column(name = "PFSNOME")
	private String pfsNome;
	
	@Column(name = "PFSEMAIL")
	private String pfsEmail;
	
	@Column(name = "PFSENDERECO")
	private String pfsEndereco;
	
	@Column(name = "PFSBAIRRO")
	private String pfsBairro;
	
	@Column(name = "PFSMUNICIPIO")
	private String pfsMunicipio;
	
	@Column(name = "PFSUF")
	private String pfSUF;
	
	@Column(name = "PFSPAIS")
	private String pfsPais;
	
	@Column(name = "PFSCEP")
	private Long pfsCep;
	
	@Column(name = "PFSTELEFONE")
	private Long pfsTelefone;

	@Column(name = "PFSDTNASCIMENTO")
	private Timestamp pfsDtNascimento;
	
	@Column(name = "PFSPIS")
	private Long pfsPis;

	@Column(name = "PFSRG")
	private String pfsRG;

	@Column(name = "PFSRGEMISSOR")
	private String pfsRGEmissor;
	
	@Column(name = "PFSRGUF")
	private String pfsRGUF;
	
	@Column(name = "PFSTITELEIT")
	private Long pfsTitEleit;
	
	@Column(name = "PFSTITELEITZONA")
	private Long pfsTitEleitZona;

	@Column(name = "PFSTITELEITSECAO")
	private Long pfsTitEleitSecao;

	@Column(name = "PFSTITELEITMUNICIPIO")
	private String pfsTitEleitMunicipio;
	
	@Column(name = "PFSTITELEITUF")
	private String pfsTitEleitUF;
	
	@Column(name = "PFSPROFISSAO")
	private String pfsProfissao;
	
	@Column(name = "PRDOU1")
	private String prdOu1;
	
	@Column(name = "PRDOU2")
	private String prdOu2;
	
	@Column(name = "PRDOU3")
	private String prdOu3;
	
	@Column(name = "PRDOU4")
	private String prdOu4;
	
	@Column(name = "PRDOU5")
	private String prdOu5;
	
	@Column(name = "PJRCNPJ")
	private Long PjrCnpj;
	
	@Column(name = "PJRRAZAOSOCIAL")
	private String PjrRazaoSocial;
	
	@Column(name = "ORID")
	private Long orId;
	
	@Column(name = "GRPID")
	private String grpId;
	
	@Column(name = "ARID")
	private String arId;
	
	@Column(name = "ORCODIGO")
	private String orCodigo;
	
	@Column(name = "PEDBEMAILHTML")
	private Long pedBeMailHtml;
	
	@Column(name = "PEDBEMAILASSINADO")
	private Long pedBeMailAssinado;
	
	@Column(name = "PEDNUMCARTAO")
	private String pedNumCartao;
	
	@Column(name = "PEDNUMRENOVACAO")
	private Long pedNumRenovacao;
	
	@Column(name = "PFSCEI")
	private Long pfsCei;
	
	@Column(name = "PJRCEI")
	private Long pjrCei;
	
	@Column(name = "PEDVALOR")
	private Long pedValor;
		
	@Column(name = "PEDINTEGRADO")
	private Integer pedIntegrado;
	
	@Column(name = "DATA_PGTO")
	private Timestamp dataPgto;
	
	@Column(name = "PEDBLOQUEIODATA")
	private Long pedBloqueioData;
	
	@Column(name = "PEDNUMTENTATIVAS")
	private Long pedNumTentativas;
	
	@Column(name = "PEDTIPOEMISSAO")
	private String pedTipoEmissao;
	
	@Column(name = "PEDTIPOVALIDACAO")
	private String pedTipoValidacao;
	
	@Column(name = "DATANOTIFICACAO")
	private Integer dataNotificacao;
	
	@Column(name = "CD_MOV_CLIENTE")
	private String cdMovCliente;
	
	@Column(name = "CD_ENVIO_EMAIL_PED_CORP")
	private Long cdEnvioEmailPedCorp;
	
	@Column(name = "IC_BLOQUEIO_FRAUDE")
	private Long icBloqueioFraude;
	
	@Column(name = "IC_ANALISE_SEGURANCA")
	private Long icAnaliseSeguranca;
	
	@Column(name = "IC_TERMO_BIOMETRIA")
	private Long icTermoBiometria;

	public Long getPedid() {
		return pedid;
	}

	public void setPedid(Long id) {
		this.pedid = id;
	}

	public Timestamp getPdData() {
		return pdData;
	}

	public void setPdData(Timestamp pdData) {
		this.pdData = pdData;
	}

	public String getPrDid() {
		return prDid;
	}

	public void setPrDid(String prDid) {
		this.prDid = prDid;
	}

	public String getPedFi() {
		return pedFi;
	}

	public void setPedFi(String pedFi) {
		this.pedFi = pedFi;
	}

	public String getPedSeed() {
		return pedSeed;
	}

	public void setPedSeed(String pedSeed) {
		this.pedSeed = pedSeed;
	}

	public Long getSpedId() {
		return spedId;
	}

	public void setSpedId(Long spedId) {
		this.spedId = spedId;
	}

	public Long getEstpin1() {
		return estpin1;
	}

	public void setEstpin1(Long estpin1) {
		this.estpin1 = estpin1;
	}

	public Long getUsuId() {
		return usuId;
	}

	public void setUsuId(Long usuId) {
		this.usuId = usuId;
	}

	public Long getPfsCpf() {
		return pfsCpf;
	}

	public void setPfsCpf(Long pfsCpf) {
		this.pfsCpf = pfsCpf;
	}

	public String getPfsNome() {
		return pfsNome;
	}

	public void setPfsNome(String pfsNome) {
		this.pfsNome = pfsNome;
	}

	public String getPfsEmail() {
		return pfsEmail;
	}

	public void setPfsEmail(String pfsEmail) {
		this.pfsEmail = pfsEmail;
	}

	public String getPfsEndereco() {
		return pfsEndereco;
	}

	public void setPfsEndereco(String pfsEndereco) {
		this.pfsEndereco = pfsEndereco;
	}

	public String getPfsBairro() {
		return pfsBairro;
	}

	public void setPfsBairro(String pfsBairro) {
		this.pfsBairro = pfsBairro;
	}

	public String getPfsMunicipio() {
		return pfsMunicipio;
	}

	public void setPfsMunicipio(String pfsMunicipio) {
		this.pfsMunicipio = pfsMunicipio;
	}

	public String getPfSUF() {
		return pfSUF;
	}

	public void setPfSUF(String pfSUF) {
		this.pfSUF = pfSUF;
	}

	public String getPfsPais() {
		return pfsPais;
	}

	public void setPfsPais(String pfsPais) {
		this.pfsPais = pfsPais;
	}

	public Long getPfsCep() {
		return pfsCep;
	}

	public void setPfsCep(Long pfsCep) {
		this.pfsCep = pfsCep;
	}

	public Long getPfsTelefone() {
		return pfsTelefone;
	}

	public void setPfsTelefone(Long pfsTelefone) {
		this.pfsTelefone = pfsTelefone;
	}

	public Timestamp getPfsDtNascimento() {
		return pfsDtNascimento;
	}

	public void setPfsDtNascimento(Timestamp pfsDtNascimento) {
		this.pfsDtNascimento = pfsDtNascimento;
	}

	public Long getPfsPis() {
		return pfsPis;
	}

	public void setPfsPis(Long pfsPis) {
		this.pfsPis = pfsPis;
	}

	public String getPfsRG() {
		return pfsRG;
	}

	public void setPfsRG(String pfsRG) {
		this.pfsRG = pfsRG;
	}

	public String getPfsRGEmissor() {
		return pfsRGEmissor;
	}

	public void setPfsRGEmissor(String pfsRGEmissor) {
		this.pfsRGEmissor = pfsRGEmissor;
	}

	public String getPfsRGUF() {
		return pfsRGUF;
	}

	public void setPfsRGUF(String pfsRGUF) {
		this.pfsRGUF = pfsRGUF;
	}

	public Long getPfsTitEleit() {
		return pfsTitEleit;
	}

	public void setPfsTitEleit(Long pfsTitEleit) {
		this.pfsTitEleit = pfsTitEleit;
	}

	public Long getPfsTitEleitZona() {
		return pfsTitEleitZona;
	}

	public void setPfsTitEleitZona(Long pfsTitEleitZona) {
		this.pfsTitEleitZona = pfsTitEleitZona;
	}

	public Long getPfsTitEleitSecao() {
		return pfsTitEleitSecao;
	}

	public void setPfsTitEleitSecao(Long pfsTitEleitSecao) {
		this.pfsTitEleitSecao = pfsTitEleitSecao;
	}

	public String getPfsTitEleitMunicipio() {
		return pfsTitEleitMunicipio;
	}

	public void setPfsTitEleitMunicipio(String pfsTitEleitMunicipio) {
		this.pfsTitEleitMunicipio = pfsTitEleitMunicipio;
	}

	public String getPfsTitEleitUF() {
		return pfsTitEleitUF;
	}

	public void setPfsTitEleitUF(String pfsTitEleitUF) {
		this.pfsTitEleitUF = pfsTitEleitUF;
	}

	public String getPfsProfissao() {
		return pfsProfissao;
	}

	public void setPfsProfissao(String pfsProfissao) {
		this.pfsProfissao = pfsProfissao;
	}

	public String getPrdOu1() {
		return prdOu1;
	}

	public void setPrdOu1(String prdOu1) {
		this.prdOu1 = prdOu1;
	}

	public String getPrdOu2() {
		return prdOu2;
	}

	public void setPrdOu2(String prdOu2) {
		this.prdOu2 = prdOu2;
	}

	public String getPrdOu3() {
		return prdOu3;
	}

	public void setPrdOu3(String prdOu3) {
		this.prdOu3 = prdOu3;
	}

	public String getPrdOu4() {
		return prdOu4;
	}

	public void setPrdOu4(String prdOu4) {
		this.prdOu4 = prdOu4;
	}

	public String getPrdOu5() {
		return prdOu5;
	}

	public void setPrdOu5(String prdOu5) {
		this.prdOu5 = prdOu5;
	}

	public Long getPjrCnpj() {
		return PjrCnpj;
	}

	public void setPjrCnpj(Long pjrCnpj) {
		PjrCnpj = pjrCnpj;
	}

	public String getPjrRazaoSocial() {
		return PjrRazaoSocial;
	}

	public void setPjrRazaoSocial(String pjrRazaoSocial) {
		PjrRazaoSocial = pjrRazaoSocial;
	}

	public Long getOrId() {
		return orId;
	}

	public void setOrId(Long orId) {
		this.orId = orId;
	}

	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

	public String getOrCodigo() {
		return orCodigo;
	}

	public void setOrCodigo(String orCodigo) {
		this.orCodigo = orCodigo;
	}

	public Long getPedBeMailHtml() {
		return pedBeMailHtml;
	}

	public void setPedBeMailHtml(Long pedBeMailHtml) {
		this.pedBeMailHtml = pedBeMailHtml;
	}

	public Long getPedBeMailAssinado() {
		return pedBeMailAssinado;
	}

	public void setPedBeMailAssinado(Long pedBeMailAssinado) {
		this.pedBeMailAssinado = pedBeMailAssinado;
	}

	public String getPedNumCartao() {
		return pedNumCartao;
	}

	public void setPedNumCartao(String pedNumCartao) {
		this.pedNumCartao = pedNumCartao;
	}

	public Long getPedNumRenovacao() {
		return pedNumRenovacao;
	}

	public void setPedNumRenovacao(Long pedNumRenovacao) {
		this.pedNumRenovacao = pedNumRenovacao;
	}

	public Long getPfsCei() {
		return pfsCei;
	}

	public void setPfsCei(Long pfsCei) {
		this.pfsCei = pfsCei;
	}

	public Long getPjrCei() {
		return pjrCei;
	}

	public void setPjrCei(Long pjrCei) {
		this.pjrCei = pjrCei;
	}

	public Long getPedValor() {
		return pedValor;
	}

	public void setPedValor(Long pedValor) {
		this.pedValor = pedValor;
	}

	public Integer getPedIntegrado() {
		return pedIntegrado;
	}

	public void setPedIntegrado(Integer pedIntegrado) {
		this.pedIntegrado = pedIntegrado;
	}

	public Timestamp getDataPgto() {
		return dataPgto;
	}

	public void setDataPgto(Timestamp dataPgto) {
		this.dataPgto = dataPgto;
	}

	public Long getPedBloqueioData() {
		return pedBloqueioData;
	}

	public void setPedBloqueioData(Long pedBloqueioData) {
		this.pedBloqueioData = pedBloqueioData;
	}

	public Long getPedNumTentativas() {
		return pedNumTentativas;
	}

	public void setPedNumTentativas(Long pedNumTentativas) {
		this.pedNumTentativas = pedNumTentativas;
	}

	public String getPedTipoEmissao() {
		return pedTipoEmissao;
	}

	public void setPedTipoEmissao(String pedTipoEmissao) {
		this.pedTipoEmissao = pedTipoEmissao;
	}

	public String getPedTipoValidacao() {
		return pedTipoValidacao;
	}

	public void setPedTipoValidacao(String pedTipoValidacao) {
		this.pedTipoValidacao = pedTipoValidacao;
	}

	public Integer getDataNotificacao() {
		return dataNotificacao;
	}

	public void setDataNotificacao(Integer dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}

	public String getCdMovCliente() {
		return cdMovCliente;
	}

	public void setCdMovCliente(String cdMovCliente) {
		this.cdMovCliente = cdMovCliente;
	}

	public Long getCdEnvioEmailPedCorp() {
		return cdEnvioEmailPedCorp;
	}

	public void setCdEnvioEmailPedCorp(Long cdEnvioEmailPedCorp) {
		this.cdEnvioEmailPedCorp = cdEnvioEmailPedCorp;
	}

	public Long getIcBloqueioFraude() {
		return icBloqueioFraude;
	}

	public void setIcBloqueioFraude(Long icBloqueioFraude) {
		this.icBloqueioFraude = icBloqueioFraude;
	}

	public Long getIcAnaliseSeguranca() {
		return icAnaliseSeguranca;
	}

	public void setIcAnaliseSeguranca(Long icAnaliseSeguranca) {
		this.icAnaliseSeguranca = icAnaliseSeguranca;
	}

	public Long getIcTermoBiometria() {
		return icTermoBiometria;
	}

	public void setIcTermoBiometria(Long icTermoBiometria) {
		this.icTermoBiometria = icTermoBiometria;
	}
	
	
}
