package com.edf.helloworld.api.persistence;

import com.edf.helloworld.api.model.Person;
import org.sakaiproject.serialization.SerializableRepository;

public interface HelloWorldRepository extends SerializableRepository<Person, String> {

    Iterable<Person> findPersonsBySite(String siteId);

}
