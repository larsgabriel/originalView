package com.certisign.dossie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.certisign.dossie.model.DossieAprovado;

public interface DossieRepository extends JpaRepository<DossieAprovado, Long>{
	
	

}
