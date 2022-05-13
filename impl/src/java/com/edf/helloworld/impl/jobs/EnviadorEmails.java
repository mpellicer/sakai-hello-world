package com.edf.helloworld.impl.jobs;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.sakaiproject.db.api.SqlService;
import org.sakaiproject.email.api.EmailService;

import com.edf.helloworld.api.HelloWorldService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnviadorEmails implements Job {

    @Setter
    private HelloWorldService helloWorldService;

    @Setter
    private EmailService emailService;

    @Setter
    SqlService sqlService;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	System.out.println("ES EL JOB DE ENVIO DE MAILS\n\n\n*******************************");
    	long totalPersonas = helloWorldService.countPersons();
    	long totalSubjects = helloWorldService.countSubjects();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String currentDate = sdf.format(new Date());
    	List<String> sessionCount = sqlService.dbRead("select count(*) from sakai_session where session_start like '"+currentDate+"%';");
    	String correo = MessageFormat.format("Tengo en total {0} personas y {1} asignaturas, en el día de hoy ha habido {2}", totalPersonas, totalSubjects, sessionCount.get(0));
    	System.out.println(correo);
    	System.out.println("\n\n\n*******************************");
    	List listaVacia = new ArrayList<>();    	
    	try {
    		emailService.send("noreply@unap.cl", "mm@mm.com", "Envio diario anotaciones", correo, null, null, listaVacia);
    	} catch (Exception ex) {
    		System.out.println("Error enviado email.");    		
    	}
    	
    	
    }

}
