package com.edf.helloworld.impl.persistence;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sakaiproject.serialization.BasicSerializableRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edf.helloworld.api.model.Anotacion;
import com.edf.helloworld.api.model.Person;
import com.edf.helloworld.api.persistence.RepositorioAnotaciones;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
public class RepositorioAnotacionesImpl extends BasicSerializableRepository<Anotacion, String> implements RepositorioAnotaciones {

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Anotacion> dameAnotacionesPorPersona(String persona) {
        return startCriteriaQuery().add(Restrictions.eq("persona", persona)).list();
    }

}
