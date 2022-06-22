/**
* Copyright (c) 2022 Apereo Foundation
* 
* Licensed under the Educational Community License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* 
*             http://opensource.org/licenses/ecl2
* 
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.edf.helloworld.controller;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

import org.sakaiproject.authz.api.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.edf.helloworld.api.HelloWorldService;
import com.edf.helloworld.api.model.Anotacion;
import com.edf.helloworld.api.model.Person;
import com.edf.helloworld.api.model.Subject;
import com.edf.helloworld.service.HelloWorldToolService;

/**
 * MainController
 * 
 * This is the controller used by Spring MVC to handle requests
 * 
 */
@Slf4j
@Controller
public class MainController {

    @Autowired
    private HelloWorldService helloWorldService;

    @Autowired
    private HelloWorldToolService helloWorldToolService;

    private final String INDEX_TEMPLATE = "index";

    @ModelAttribute("userName")
    public String userName() {
        return helloWorldToolService.getCurrentUser().getDisplayName();
    }

    @ModelAttribute("personList")
    public Iterable<Person> personList() {
        return helloWorldService.findAll();
    }

    @ModelAttribute("subjectList")
    public Iterable<Subject> subjectList() {
        return helloWorldService.listAllSubjects();
    }

    @ModelAttribute("subjectCount")
    public long subjectCount() {
        return helloWorldService.countSubjects();
    }

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        log.debug("Accessing the config editor index");
        log.info("We currently have {} persons.", helloWorldService.countPersons());
        String nombreAsignatura = helloWorldToolService.dameNombreDeAsignatura();
        String idAsignatura = helloWorldToolService.getCurrentSiteId();
        model.addAttribute("currentSiteId", idAsignatura);
        model.addAttribute("siteTitle", nombreAsignatura);
        Set<Member> listadoMiembros = helloWorldToolService.damePersonasCurso();
        System.out.println("La lista de miembros.....");
        model.addAttribute("listadoMiembros", listadoMiembros);
        model.addAttribute("anotacion", new Anotacion());
        model.addAttribute("listaAnotaciones", helloWorldService.dameTodasLasAnotaciones());

        // Vamos a comprobar si la asignatura existe
        Subject asignatura = helloWorldService.recuperaAsignatura(idAsignatura).orElse(null);
        if (asignatura == null) {
            Subject newSubject = new Subject();
            newSubject.setContext(idAsignatura);
            newSubject.setTitle(nombreAsignatura);
            helloWorldService.newSubject(newSubject);        	
        } else {
        	System.out.println("La asignatura " + idAsignatura + " ya existe");
        	log.info("La asignatura {} ya existe", idAsignatura);
        }
        return INDEX_TEMPLATE;
    }

    @PostMapping("/add/annotation")
    public String nuevaAnotacion(@ModelAttribute Anotacion anotacion, Model model) {
      String respuesta = MessageFormat.format("He recibido la anotacion {0} de la persona {1} y es visible {2}", anotacion.getAnotacion(), anotacion.getPersona(), anotacion.isVisible());
      System.out.println(respuesta);
      helloWorldService.nuevaAnotacion(anotacion);
      return this.index(model);
    }

    @PostMapping(value="/add/{siteId}")
    public ResponseEntity<Object> add(@PathVariable("siteId") String siteId) {
        log.info("Creating person for site {}", siteId);
        Person p = new Person();
        p.setContext(siteId);
        p.setName(userName());
        helloWorldService.newPerson(p);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value="/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String personId) {
        log.info("Deleting person {}", personId);
        Person person = helloWorldService.findPerson(personId);
        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        helloWorldService.deletePersonById(personId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
