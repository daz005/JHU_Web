package com.hikequote;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

/**
 * MyApplication class configures the Jersey RESTful web service.
 * It sets the base URI path for all REST endpoints and specifies the package to scan for resources.
 */
@ApplicationPath("/api")
public class MyApplication extends ResourceConfig {
    
    /**
     * Constructor for MyApplication.
     * Configures the package to scan for Jersey resources, providers, and features.
     */
    public MyApplication() {
        // Specify the package to scan for RESTful resources
        packages("com.hikequote");
    }
}
