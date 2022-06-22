package com.edf.helloworld.api.persistence;

import com.edf.helloworld.api.model.Anotacion;

import org.sakaiproject.serialization.SerializableRepository;

public interface RepositorioAnotaciones extends SerializableRepository<Anotacion, String> {
	
    Iterable<Anotacion> dameAnotacionesPorPersona(String persona);

}
