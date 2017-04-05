/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.claro.ventas.linea.resource.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.core.Application;

import pe.com.claro.common.resource.exception.ProviderExceptionMapper;

@Singleton
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
  
       resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
       resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
       resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
       resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
       addRestResourceClasses(resources);
        return resources;
    }

    @Override
    public Map<String, Object> getProperties() {
    	String filename = "config.properties";
		return readProperties(filename);
    }
    
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(pe.com.claro.ventas.linea.resource.ClienteResource.class);
        resources.add(ProviderExceptionMapper.class);
    }
    
    
    private Map<String, Object>  readProperties(String fileInClasspath) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileInClasspath);
		
		try {
			Map<String, Object> map = new HashMap();
			Properties properties = new Properties();
			properties.load(is);
			map.putAll(properties.entrySet().stream()
			.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue())));
			return map;
		} catch (IOException e) {
			System.err.println("Could not read properties from file " + fileInClasspath + " in classpath. " + e);
		}
		
		return null;
	}

}
