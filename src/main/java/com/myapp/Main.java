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
        
        String portEnv = System.getenv("PORT");
        int port = portEnv != null ? Integer.parseInt(portEnv) : 8081;
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        tomcat.getConnector().setProperty("address", "0.0.0.0");
        
        // NE PAS utiliser addWebapp (qui charge web.xml)
        // Utiliser addContext à la place
        String contextPath = "";
        String docBase = new File("src/main/webapp").getAbsolutePath();
        
        if (!new File(docBase).exists()) {
            docBase = new File(".").getAbsolutePath();
        }
        
        // addContext au lieu de addWebapp
        Context context = tomcat.addContext(contextPath, docBase);
        
        // Configuration du multipart
        context.setAllowCasualMultipartParsing(true);

        // Enregistrer le servlet JSP (nécessaire avec addContext)
        Tomcat.addServlet(context, "jsp", new JspServlet());
        context.addServletMappingDecoded("*.jsp", "jsp");
        context.addServletMappingDecoded("*.jspx", "jsp");
        context.addServletContainerInitializer(new JasperInitializer(), null);

        // Enregistrer le FrontServlet manuellement (pas de web.xml)
        Tomcat.addServlet(context, "FrontServlet", new FrontServlet());
        context.addServletMappingDecoded("/", "FrontServlet");
        
        System.out.println("========================================");
        System.out.println("🚀 Application démarrée sur le port " + port);
        System.out.println("📁 Base: " + docBase);
        System.out.println("========================================");
        
        tomcat.start();
        tomcat.getServer().await();
    }
}
