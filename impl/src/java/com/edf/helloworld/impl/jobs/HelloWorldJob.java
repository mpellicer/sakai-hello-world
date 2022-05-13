package com.edf.helloworld.impl.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.edf.helloworld.api.HelloWorldService;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloWorldJob implements Job {

    @Setter
    private HelloWorldService helloWorldService;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	System.out.println("Yo soy el trabajo del HOLA MUNDO");
    }

}
