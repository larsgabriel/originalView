package com.certisign.dossie.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.certisign.dossie.model.DossieAprovado;
import com.certisign.dossie.repository.DossieRepository;

public class DossieServiceImplementation implements DossieService{
	
	@Autowired
	private DossieRepository dossieRepository;
	
	@Override
	public DossieAprovado createDossie(DossieAprovado dossie) {
		return dossieRepository.save(dossie);
	}

	@Override
	public Optional<DossieAprovado> getDossie(Long id) {
		return dossieRepository.findById(id);
	}

	@Override
	public DossieAprovado editDossie(DossieAprovado dossie) {
		return dossieRepository.save(dossie);
	}

	@Override
	public void deleteDossie(DossieAprovado dossie) {
		dossieRepository.delete(dossie);
	}

	@Override
	public void deleteDossie(Long id) {
		dossieRepository.deleteById(id);
		
	}

	@Override
	public Iterable<DossieAprovado> getAllDossies(int pageNumber, int pageSize) {
		return dossieRepository.findAll();
	}

}
