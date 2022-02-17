package com.edf.helloworld.impl.persistence;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sakaiproject.serialization.BasicSerializableRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edf.helloworld.api.model.Person;
import com.edf.helloworld.api.persistence.HelloWorldRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
public class HelloWorldRepositoryImpl extends BasicSerializableRepository<Person, String> implements HelloWorldRepository {

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Person> findPersonsBySite(String siteId) {
        return startCriteriaQuery().add(Restrictions.eq("context", siteId)).list();
    }

}
