
package com.sg.SuperHeroSightings.dto;
import java.time.LocalDate;
import java.util.Objects;
/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public class Sighting {
    private int SightingId;
    private LocalDate date;
    private Hero hero;
    private Location location;

    public Sighting() {
    }
    
    

    public Sighting(int SightingId) {
        this.SightingId = SightingId;
    }
    
    public int getSightingId() {
        return SightingId;
    }
    public void setSightingId(int SightingId) {
        this.SightingId = SightingId;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Hero getHero() {
        return hero;
    }
    public void setHero(Hero hero) {
        this.hero = hero;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.SightingId;
        hash = 13 * hash + Objects.hashCode(this.date);
        hash = 13 * hash + Objects.hashCode(this.hero);
        hash = 13 * hash + Objects.hashCode(this.location);
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
        final Sighting other = (Sighting) obj;
        if (this.SightingId != other.SightingId) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sighting{" + "SightingId=" + SightingId + ", date=" + date + ", hero=" + hero + ", location=" + location + '}';
    }
    

}
