package com.certisign.dossie.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.certisign.dossie.model.DossieAprovado;
import java.lang.String;

@Repository
public interface DossieRepository extends CrudRepository<DossieAprovado, Long>{
		
	  List<DossieAprovado> findByCaixaInternaAndCaixaInterna(String caixaexterna, String caixaInterna);
	
	  List<DossieAprovado> findByNumeroPedido(Long numeroPedido);

}
