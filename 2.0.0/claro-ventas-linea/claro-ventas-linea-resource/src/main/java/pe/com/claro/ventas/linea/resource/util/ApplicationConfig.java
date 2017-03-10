/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.claro.ventas.linea.resource.util;

import java.util.Set;

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


    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(pe.com.claro.ventas.linea.resource.ClienteResource.class);
        resources.add(ProviderExceptionMapper.class);
    }

}
