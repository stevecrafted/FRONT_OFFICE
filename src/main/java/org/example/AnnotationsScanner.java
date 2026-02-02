package org.example;

import org.reflections.Reflections;

import org.example.Sprintdeux.*;
import org.example.sprintdeuxbis.AnnotationContoller;

import org.reflections.scanners.Scanners;
import java.lang.reflect.Method;
import java.util.Set;

public class AnnotationsScanner {

    public static void main(String[] args) {

        // Maka ny reflections object izay mikaroka ao amin'ny package org.example
        Reflections reflections = new Reflections(
                "org.example",
                Scanners.MethodsAnnotated, 
                Scanners.TypesAnnotated);

        // Alaina daolo izay AnnotationMethode ao anaty package org.example
        AnnotationsScanner an = new AnnotationsScanner();
        /**
         * Sprint deux
         */
        an.getAnnotationMethode(reflections);

        /**
         * Sprint deux bis
         */
        an.getAnnotationController(reflections);
    }

    public void getAnnotationController(Reflections reflections) {
        System.out.println("scan des Annotation Controller dans : " + reflections);

        Set<Class<?>> annotatedMethods = reflections.getTypesAnnotatedWith(AnnotationContoller.class);
        System.out.println(annotatedMethods.size());

        for (Class<?> maClass : annotatedMethods) {
            AnnotationContoller annotation = maClass.getAnnotation(AnnotationContoller.class);
            System.out.println("Classe : "
                    + maClass.getSimpleName() + " avec Url : " + annotation.value());
        }
    }

    public void getAnnotationMethode(Reflections reflections) {
        System.out.println("scan des Methode Controller dans : " + reflections);

        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(AnnotationMethod.class);
        System.out.println(annotatedMethods.size());

        for (Method method : annotatedMethods) {
            AnnotationMethod annotation = method.getAnnotation(AnnotationMethod.class);
            System.out.println("Method : "
                    + method.getName() + " avec Url : " + annotation.value());
        }
    }
}
