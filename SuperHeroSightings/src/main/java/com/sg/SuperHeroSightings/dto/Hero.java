package com.sg.SuperHeroSightings.dto;


import java.util.List;
import java.util.Objects;

/*
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public class Hero {

    private int heroId;
    private String heroName;
    private String heroDescription;
    private Superpower superPower;
    private List<OrgStub> organizations;
    private String photoFilename;

    public Hero(int heroId) {
        this.heroId = heroId;
    }
 

    public Hero() {
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroDescription() {
        return heroDescription;
    }

    public void setHeroDescription(String heroDescription) {
        this.heroDescription = heroDescription;
    }

    public Superpower getSuperPower() {
        return superPower;
    }

    public void setSuperPower(Superpower superPower) {
        this.superPower = superPower;
    }

    public List<OrgStub> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrgStub> organizations) {
        this.organizations = organizations;
    }

    public String getPhotoFilename() {
        return photoFilename;
    }

    public void setPhotoFilename(String photoFilename) {
        this.photoFilename = photoFilename;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.heroId;
        hash = 59 * hash + Objects.hashCode(this.heroName);
        hash = 59 * hash + Objects.hashCode(this.heroDescription);
        hash = 59 * hash + Objects.hashCode(this.superPower);
        hash = 59 * hash + Objects.hashCode(this.organizations);
        hash = 59 * hash + Objects.hashCode(this.photoFilename);
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
        final Hero other = (Hero) obj;
        if (this.heroId != other.heroId) {
            return false;
        }
        if (!Objects.equals(this.heroName, other.heroName)) {
            return false;
        }
        if (!Objects.equals(this.heroDescription, other.heroDescription)) {
            return false;
        }
        if (!Objects.equals(this.photoFilename, other.photoFilename)) {
            return false;
        }
        if (!Objects.equals(this.superPower, other.superPower)) {
            return false;
        }
        if (!Objects.equals(this.organizations, other.organizations)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hero{" + "heroId=" + heroId + ", heroName=" + heroName + ", heroDescription=" + heroDescription + ", superPower=" + superPower + ", organizations=" + organizations + ", photoFilename=" + photoFilename + '}';
    }
    


    


}
