package com.edf.helloworld.api;

import java.util.Optional;

import com.edf.helloworld.api.model.Anotacion;
import com.edf.helloworld.api.model.Person;
import com.edf.helloworld.api.model.Subject;

/**
 * HelloWorldService is the service that says hi.
 */

public interface HelloWorldService {

    public Person findPerson(String id);

    public Iterable<Person> findAll();

    public Iterable<Person> findPersonsBySite(String siteId);

    public void newPerson(Person person);

    public void savePerson(Person person);

    public void deletePersonById(String personId);

    public void deletePerson(Person person);

    public long countPersons();

    public Iterable<Subject> listAllSubjects();
    
    public long countSubjects();
    
    public void newSubject(Subject subject);
    
    public void deleteSubject(Subject subject);
    
    public Optional<Subject> recuperaAsignatura(String idAsignatura);

    /********* NUEVAS OPERACIONES DE ANOTACIONES *******/    
    public Anotacion encuentraAnotacion(String id);

    public Iterable<Anotacion> dameTodasLasAnotaciones();

    public Iterable<Anotacion> dameAnotacionesPorPersona(String persona);

    public void nuevaAnotacion(Anotacion anotacon);

    public void guardaAnotacion(Anotacion anotacion);

    public void borraAnotacionPorId(String id);

    public void borraAnotacion(Anotacion anotacion);

    public long totalAnotaciones();
}
