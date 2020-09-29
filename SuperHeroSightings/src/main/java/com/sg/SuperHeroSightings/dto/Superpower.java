package com.sg.SuperHeroSightings.dto;

import java.util.Objects;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */

public class Superpower {

    public Superpower() {
    }
    
    public Superpower(int superPowerId) {
        this.superPowerId = superPowerId;
    }

    public Superpower(String superPowerName) {
        this.superPowerName = superPowerName;
    }
    

    private int superPowerId;
    private String superPowerName;

    public int getSuperPowerId() {
        return superPowerId;
    }

    public void setSuperPowerId(int superPowerId) {
        this.superPowerId = superPowerId;
    }

    public String getSuperPowerName() {
        return superPowerName;
    }

    public void setSuperPowerName(String superPowerName) {
        this.superPowerName = superPowerName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.superPowerId;
        hash = 79 * hash + Objects.hashCode(this.superPowerName);
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
        final Superpower other = (Superpower) obj;
        if (this.superPowerId != other.superPowerId) {
            return false;
        }
        if (!Objects.equals(this.superPowerName, other.superPowerName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Superpower{" + "superPowerId=" + superPowerId + ", superPowerName=" + superPowerName + '}';
    }
    
    
}
