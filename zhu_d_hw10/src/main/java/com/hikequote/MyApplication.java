//package com.hikequote;
//
//import javax.ws.rs.core.Application;
//
//public class MyApplication extends Application {
//
//}


// MyApplication.java
package com.hikequote;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class MyApplication extends ResourceConfig {
    public MyApplication() {
        packages("com.hikequote");
    }
}
