package org.example.Model;

import java.util.ArrayList;
import java.util.List;

public class Employe {
    String name;
    private List<Departement> departement;

    public Employe() {
        this.departement = new ArrayList<>();
    }

    public List<Departement> getDepartement() {
        return departement;
    }

    public void setDepartement(List<Departement> Departement) {
        this.departement = Departement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
