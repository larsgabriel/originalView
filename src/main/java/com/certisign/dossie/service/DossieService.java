package com.certisign.dossie.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.certisign.dossie.model.DossieAprovado;
import com.certisign.dossie.repository.DossieRepository;

@Service
public class DossieService {
	
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	DossieRepository repository;

	public DossieAprovado getDossie(String numeroPedido, String cxInterna, String cxExterna) {

		DossieAprovado dossie = new DossieAprovado();
		
		try {
			String tipoTermo = tratarTermo(numeroPedido);
			Long pedido = tratarPedido(numeroPedido);
			dossie = findPedido(pedido);
			dossie.setNumeroPedido(pedido);
			dossie.setIdStatusPedido("ESTOCADO");
			dossie.setTipoTermo(tipoTermo);
			dossie.setCaixaExterna(cxExterna);
			dossie.setCaixaInterna(cxInterna);

		} catch (Exception e) {
			
		}

		return dossie;

	}

	public DossieAprovado findPedido(Long numeroPedido) {
		DossieAprovado pedidos = (DossieAprovado) repository.findByNumeroPedido(numeroPedido);
		return pedidos;
	}

	public List<DossieAprovado> findPedidoByNumeroPedido(String cxInterna, String cxExterna) {
		List<DossieAprovado> pedidos = repository.findByCaixaInternaAndCaixaInterna(cxExterna, cxInterna);
		return pedidos;
	}

	public List<DossieAprovado> pesquisa(String numeroPedido, String cxInterna, String cxExterna, String statusAr) {
		
		Long pedido = tratarPedido(numeroPedido);
		String sb = buildDinamicQuery(pedido, cxInterna, cxExterna, statusAr).toString();
		
		Query query = em.createQuery(sb);
		List<DossieAprovado> pedidos = query.getResultList();

		return pedidos;

	}

	public Long tratarPedido(String numeroPedido) {

		String pedido = "";
		if (numeroPedido.contains("R") || numeroPedido.contains("T")) {
			String[] parts = numeroPedido.split("-");
			pedido = parts[0];
		}
		
		return Long.parseLong(pedido);
	}

	public String tratarTermo(String numeroPedido) {

		String termo = "";
		if (numeroPedido.contains("R") || numeroPedido.contains("T")) {
			String[] parts = numeroPedido.split("-");
			termo = parts[1];
		}
		return termo;
	}
	
	public StringBuilder buildDinamicQuery(Long numeroPedido, String cxInterna, String cxExterna, String statusAr){
		
		StringBuilder sb= new StringBuilder("SELECT * FROM TBL_DOSSIE D");
		
		if(numeroPedido != null || !cxInterna.equals("") || !cxInterna.equals("") || !cxExterna.equals("")){
			sb.append("WHERE");			
		}
		if(!numeroPedido.equals("")){
			sb.append("D.FK_CD_PEDIDO =" + numeroPedido);
		}
		if(!cxInterna.equals("")){
			sb.append("D.FK_AD_CAIXA_INTERNA =" + cxInterna);
		}
		if(!cxExterna.equals("")){
			sb.append("D.FK_AD_CAIXA_EXTERNA =" + cxExterna);
		}
		
		return sb;
}
	
	String SQL_SUBLIST = "from tbl_dossie WHERE ROWNUM BETWEEN %d AND %d";
	public List<DossieAprovado> getAll() {
		String sql = String.format(SQL_SUBLIST, 1, 10);
		Query query = em.createQuery(sql);
		List<DossieAprovado> pedidos = query.getResultList();		
		return pedidos;
	}

}
