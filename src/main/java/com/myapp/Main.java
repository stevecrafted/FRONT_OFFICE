package com.myapp;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.servlet.JasperInitializer;
import org.apache.jasper.servlet.JspServlet;
import org.FrontServlet;
import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {
        
        // Koyeb fournit la variable PORT
        String portEnv = System.getenv("PORT");
        int port = (portEnv != null) ? Integer.parseInt(portEnv) : 8084;
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        tomcat.getConnector().setProperty("address", "0.0.0.0");
        
        // Chemin webapp (fonctionne en local ET dans Docker)
        String docBase = "src/main/webapp";
        File webappDir = new File(docBase);
        
        if (!webappDir.exists()) {
            System.err.println("ERROR: Webapp directory not found: " + docBase);
            System.err.println("Current directory: " + new File(".").getAbsolutePath());
            System.exit(1);
        }
        
        System.out.println("Webapp directory: " + webappDir.getAbsolutePath());
        
        // Configurer le contexte
        Context context = tomcat.addContext("", webappDir.getAbsolutePath());
        context.setAllowCasualMultipartParsing(true);
        
        // Initializer JSP
        context.addServletContainerInitializer(new JasperInitializer(), null);
        
        // Servlet JSP
        Tomcat.addServlet(context, "jsp", new JspServlet());
        context.addServletMappingDecoded("*.jsp", "jsp");
        
        // FrontServlet
        Tomcat.addServlet(context, "FrontServlet", new FrontServlet());
        context.addServletMappingDecoded("/", "FrontServlet");
        
        System.out.println("========================================");
        System.out.println("🚀 Application démarrée");
        System.out.println("📡 Port: " + port);
        System.out.println("📁 Webapp: " + webappDir.getAbsolutePath());
        System.out.println("========================================");
        
        tomcat.start();
        tomcat.getServer().await();
    }
}