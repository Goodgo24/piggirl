package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class ATest1 {
	
	public static void  main(String[] args){
		createProcessEngine1();
	}
	
	
	@Test
	public static void createProcessEngine1(){
	String resUrl="spring-mybatis.xml";
	String resUrl1="activiti.cfg.xml";
//	String name="test1";		
	ProcessEngineConfiguration config=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(resUrl);
	ProcessEngine processEngine=config.buildProcessEngine();
	System.out.println(processEngine);
		
	}
}
