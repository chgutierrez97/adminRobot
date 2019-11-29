/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.accusy.robotpro.robotadmin.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author MGIAccusys
 */
public class ListaPersonaDTO {
    private List<Persona> PersonaList;

    

    public List<Persona> getPersonaList() {
        return PersonaList;
    }

    public void setPersonaList(List<Persona> PersonaList) {
        this.PersonaList = PersonaList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.PersonaList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ListaPersonaDTO other = (ListaPersonaDTO) obj;
        if (!Objects.equals(this.PersonaList, other.PersonaList)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonaList{" + "PersonaList=" + PersonaList + '}';
    } 

    
}
