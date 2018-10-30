package com.certisign.dossie.repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.certisign.dossie.model.DossieAprovado;

@Repository
public interface DossieRepository extends CrudRepository<DossieAprovado, Long> {

	DossieAprovado findByNumeroPedido(Long numeroPedido);
	List<DossieAprovado> findAllByDataInsercaoAfter(Timestamp data);
	List<DossieAprovado> findByCaixaExternaAndCaixaInterna(String caixaexterna, String caixaInterna);
	List<DossieAprovado> findAllByDataUltAtualizacaoAfterAndDataUltAtualizacaoBefore(Timestamp  dataInicial, Timestamp  dataFinal);

}
