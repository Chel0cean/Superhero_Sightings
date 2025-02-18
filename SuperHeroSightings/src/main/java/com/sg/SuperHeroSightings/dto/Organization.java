package com.sg.SuperHeroSightings.dto;

import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public class Organization {

    private int organizationId;

    @NotNull(message = "You must select a location")
    private Location location;

    @NotEmpty(message = "You must select at least one hero")
    private List<Hero> heroes;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 45, message = "Name must be fewer than 45 characters")
    private String organizationName;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 255, message = "Description must be fewer than 255 characters")
    private String organizationDescription;

    @Size(max = 65, message = "Email must be fewer than 65 characters")
    private String organizationEmail;

    @Size(max = 15, message = "Phone number must be fewer than 15 characters")
    private String organizationPhone;

    public Organization() {
    }

    public Organization(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String orgnizationName) {
        this.organizationName = orgnizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public String getOrganizationPhone() {
        return organizationPhone;
    }

    public void setOrganizationPhone(String organizationPhone) {
        this.organizationPhone = organizationPhone;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.organizationId;
        hash = 13 * hash + Objects.hashCode(this.location);
        hash = 13 * hash + Objects.hashCode(this.heroes);
        hash = 13 * hash + Objects.hashCode(this.organizationName);
        hash = 13 * hash + Objects.hashCode(this.organizationDescription);
        hash = 13 * hash + Objects.hashCode(this.organizationEmail);
        hash = 13 * hash + Objects.hashCode(this.organizationPhone);
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
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        if (!Objects.equals(this.organizationDescription, other.organizationDescription)) {
            return false;
        }
        if (!Objects.equals(this.organizationEmail, other.organizationEmail)) {
            return false;
        }
        if (!Objects.equals(this.organizationPhone, other.organizationPhone)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.heroes, other.heroes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Organization{" + "organizationId=" + organizationId + ", location=" + location + ", heroes=" + heroes + ", organizationName=" + organizationName + ", organizationDescription=" + organizationDescription + ", organizationEmail=" + organizationEmail + ", organizationPhone=" + organizationPhone + '}';
    }

}
