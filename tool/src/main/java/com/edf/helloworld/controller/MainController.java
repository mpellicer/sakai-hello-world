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
import com.edf.helloworld.api.model.Person;
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

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        log.debug("Accessing the config editor index");
        log.info("We currently have {} persons.", helloWorldService.countPersons());
        model.addAttribute("currentSiteId", helloWorldToolService.getCurrentSiteId());
        return INDEX_TEMPLATE;
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
