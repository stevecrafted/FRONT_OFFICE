package org;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        
        // Récupérer le port depuis la variable d'environnement (Railway utilise $PORT)
        String portEnv = System.getenv("PORT");
        int port = portEnv != null ? Integer.parseInt(portEnv) : 8080;
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        
        // Configurer le contexte
        String contextPath = "";
        String docBase = new File(".").getAbsolutePath();
        Context context = tomcat.addWebapp(contextPath, docBase);
        
        // Ajouter le FrontServlet
        Tomcat.addServlet(context, "FrontServlet", new org.FrontServlet());
        context.addServletMappingDecoded("/*", "FrontServlet");
        
        System.out.println("🚀 Tomcat démarre sur le port " + port);
        
        tomcat.start();
        tomcat.getServer().await();
    }
}