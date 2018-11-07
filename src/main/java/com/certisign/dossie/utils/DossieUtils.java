package com.certisign.dossie.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

import com.certisign.dossie.model.DossieAprovado;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DossieUtils {
	
	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("rawtypes")
	public List<DossieAprovado> translateDossiesVO(List responseDossie) throws JsonParseException, JsonMappingException, IOException {

		List<DossieAprovado> dtos = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();

		for (Object o : responseDossie) {
			@SuppressWarnings("unchecked")
			String jsonString = new JSONObject((Map<Object, DossieAprovado>) o).toString();

			DossieAprovado vo = objectMapper.readValue(jsonString, DossieAprovado.class);
			dtos.add(vo);

		}

		return dtos;
	}
	
	@SuppressWarnings("rawtypes")
	public DossieAprovado translateDossie(Object responseDossie) throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();

		@SuppressWarnings("unchecked")
		String jsonString = new JSONObject((Map<Object, DossieAprovado>) responseDossie).toString();

		DossieAprovado vo = objectMapper.readValue(jsonString, DossieAprovado.class);
		
		return vo;
	}
	

	public List<DossieAprovado> translateObjets(List object) throws JsonParseException, JsonMappingException, IOException {

		List<DossieAprovado> dtos = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		
		for (Object o : object) {
			@SuppressWarnings("unchecked")
			String jsonString = new JSONObject((Map<Object, DossieAprovado>) o).toString();

			DossieAprovado vo = objectMapper.readValue(jsonString, DossieAprovado.class);
			dtos.add(vo);

		}

		return dtos;
	}
}
