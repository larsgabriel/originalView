package com.certisign.dossie.service;

import java.util.List;
import java.util.Optional;

import com.certisign.dossie.model.DossieAprovado;

public interface DossieService {
		
	DossieAprovado createDossie(DossieAprovado dossie);
	Optional<DossieAprovado> getDossie(Long id);
	DossieAprovado editDossie(DossieAprovado dossie);
	void deleteDossie(DossieAprovado person);
	void deleteDossie(Long id);
	Iterable<DossieAprovado> getAllDossies(int pageNumber, int pageSiz);
	
}
