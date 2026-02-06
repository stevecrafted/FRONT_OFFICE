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
        int port = portEnv != null ? Integer.parseInt(portEnv) : 8080;
        
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector();
        tomcat.getConnector().setProperty("address", "0.0.0.0");
        
        String contextPath = "";
        
        // Chercher les JSP dans plusieurs emplacements
        String[] possiblePaths = {
            "src/main/webapp",
            "webapp",
            "/app/webapp",
            "/app/src/main/webapp",
            "."
        };
        
        String docBase = null;
        for (String path : possiblePaths) {
            File testDir = new File(path);
            System.out.println("Test path: " + path + " -> exists: " + testDir.exists());
            if (testDir.exists() && testDir.isDirectory()) {
                docBase = testDir.getAbsolutePath();
                break;
            }
        }
        
        if (docBase == null) {
            docBase = new File(".").getAbsolutePath();
        }
        
        System.out.println("Using docBase: " + docBase);
        
        // Vérifier si le dossier existe
        File docBaseFile = new File(docBase);
        if (!docBaseFile.exists()) {
            System.err.println("ERROR: docBase does not exist: " + docBase);
            // Créer le dossier
            docBaseFile.mkdirs();
        }
        
        // Lister les fichiers JSP trouvés
        System.out.println("Looking for JSP files...");
        findJspFiles(new File(docBase), "");
        
        // addContext
        Context context = tomcat.addContext(contextPath, docBase);
        
        // Configuration du multipart
        context.setAllowCasualMultipartParsing(true);

        // Enregistrer le servlet JSP
        Tomcat.addServlet(context, "jsp", new JspServlet());
        context.addServletMappingDecoded("*.jsp", "jsp");
        context.addServletMappingDecoded("*.jspx", "jsp");
        context.addServletContainerInitializer(new JasperInitializer(), null);

        // Enregistrer le FrontServlet
        Tomcat.addServlet(context, "FrontServlet", new FrontServlet());
        context.addServletMappingDecoded("/", "FrontServlet");
        
        System.out.println("========================================");
        System.out.println("🚀 Application démarrée sur le port " + port);
        System.out.println("📁 Base: " + docBase);
        System.out.println("========================================");
        
        tomcat.start();
        tomcat.getServer().await();
    }
    
    private static void findJspFiles(File dir, String indent) {
        if (dir == null || !dir.exists()) return;
        
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findJspFiles(file, indent + "  ");
                } else if (file.getName().endsWith(".jsp")) {
                    System.out.println(indent + "JSP: " + file.getAbsolutePath());
                }
            }
        }
    }
}
