package com.certisign.dossie.service;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	public Page<DossieAprovado> pesquisa(Pageable pageable, String numeroPedido, String cxInterna, String cxExterna, String statusAr) {
		
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        Long pedido = 0l;
        if(!numeroPedido.equals("")){
        	pedido = tratarPedido(numeroPedido);
        }
		
		String sb = buildDinamicQuery(pedido, cxInterna, cxExterna, statusAr).toString();
		
		Query query = em.createQuery(sb);
		
		List<DossieAprovado> pedidos = query.getResultList();
		List<DossieAprovado> dossies;
		
		if(pedidos.size() < startItem){
			dossies = Collections.emptyList();			
		}else{
			int toIndex = Math.min(startItem + pageSize, pedidos.size());
			dossies = pedidos.subList(startItem, toIndex);
		}
		
		Page<DossieAprovado> dossiePage = new PageImpl<DossieAprovado>(dossies, PageRequest.of(currentPage, pageSize), dossies.size());

		return dossiePage;

	}

	public Long tratarPedido(String numeroPedido) {

		String pedido = "";
		if (numeroPedido.contains("R") || numeroPedido.contains("T")) {
			String[] parts = numeroPedido.split("-");
			pedido = parts[0];
		}else {
			return Long.parseLong(numeroPedido);
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
		
		StringBuilder sb= new StringBuilder("from tbl_dossie");
		
		if(numeroPedido != 0 || !cxInterna.equals("") || !cxInterna.equals("") || !cxExterna.equals("")){
			sb.append("	d where");			
		}
		if(numeroPedido != 0){
			sb.append(" D.FK_CD_PEDIDO =" + numeroPedido);
		}
		if(!cxInterna.equals("")){
			sb.append(" D.FK_AD_CAIXA_INTERNA =" + cxInterna);
		}
		if(!cxExterna.equals("")){
			sb.append(" D.FK_AD_CAIXA_EXTERNA =" + cxExterna);
		}
		
		return sb;
}
	
	String SQL_SUBLIST = "from tbl_dossie WHERE ROWNUM BETWEEN %d AND %d";
	public Page<DossieAprovado> getAll(Pageable pageable) {
 	    int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<DossieAprovado> list;
        
		String sql = String.format(SQL_SUBLIST, 1, 50);
		Query query = em.createQuery(sql);
		List<DossieAprovado> pedidos = query.getResultList();
		
		if (pedidos.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, pedidos.size());
            list = pedidos.subList(startItem, toIndex);
        }
 
        Page<DossieAprovado> bookPage = new PageImpl<DossieAprovado>(list, PageRequest.of(currentPage, pageSize), pedidos.size());
 
		return bookPage;
	}

}
