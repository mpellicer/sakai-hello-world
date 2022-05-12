package com.edf.helloworld.api.persistence;

import com.edf.helloworld.api.model.Subject;

import org.sakaiproject.serialization.SerializableRepository;

public interface SubjectRepository extends SerializableRepository<Subject, String> {

    Iterable<Subject> findOneByTitle(String title);

}
