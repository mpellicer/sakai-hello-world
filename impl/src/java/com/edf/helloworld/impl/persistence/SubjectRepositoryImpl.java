package com.edf.helloworld.impl.persistence;

import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.sakaiproject.serialization.BasicSerializableRepository;
import org.springframework.transaction.annotation.Transactional;

import com.edf.helloworld.api.model.Subject;
import com.edf.helloworld.api.persistence.SubjectRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
public class SubjectRepositoryImpl extends BasicSerializableRepository<Subject, String> implements SubjectRepository {

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Subject> findOneByTitle(String title) {
        return startCriteriaQuery().add(Restrictions.eq("title", title)).list();
    }

}
