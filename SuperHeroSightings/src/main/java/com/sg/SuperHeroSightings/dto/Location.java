
package com.sg.SuperHeroSightings.dto;
import java.math.BigDecimal;
import java.util.Objects;
/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public class Location {
    private int locationId;
    private String locationName;
    private String locationDescription;
    private String locationAddress;
    private String locationCity;
    private String locationState;
    private String country;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Location() {
    }
    

    public Location(int locationId) {
        this.locationId = locationId;
    }

    
    public int getLocationId() {
        return locationId;
    }
   
    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public String getLocationName() {
        return locationName;
    }
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    public String getLocationDescription() {
        return locationDescription;
    }
    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }
    public String getLocationAddress() {
        return locationAddress;
    }
    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }
    public String getLocationCity() {
        return locationCity;
    }
    public void setLocationCity(String locationCity) {
        this.locationCity = locationCity;
    }
    public String getLocationState() {
        return locationState;
    }
    public void setLocationState(String locationState) {
        this.locationState = locationState;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    public BigDecimal getLatitude() {
        return latitude;
    }
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
    public BigDecimal getLongitude() {
        return longitude;
    }
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.locationId;
        hash = 59 * hash + Objects.hashCode(this.locationName);
        hash = 59 * hash + Objects.hashCode(this.locationDescription);
        hash = 59 * hash + Objects.hashCode(this.locationAddress);
        hash = 59 * hash + Objects.hashCode(this.locationCity);
        hash = 59 * hash + Objects.hashCode(this.locationState);
        hash = 59 * hash + Objects.hashCode(this.country);
        hash = 59 * hash + Objects.hashCode(this.zipCode);
        hash = 59 * hash + Objects.hashCode(this.latitude);
        hash = 59 * hash + Objects.hashCode(this.longitude);
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (!Objects.equals(this.locationName, other.locationName)) {
            return false;
        }
        if (!Objects.equals(this.locationDescription, other.locationDescription)) {
            return false;
        }
        if (!Objects.equals(this.locationAddress, other.locationAddress)) {
            return false;
        }
        if (!Objects.equals(this.locationCity, other.locationCity)) {
            return false;
        }
        if (!Objects.equals(this.locationState, other.locationState)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.zipCode, other.zipCode)) {
            return false;
        }
        if (!Objects.equals(this.latitude, other.latitude)) {
            return false;
        }
        if (!Objects.equals(this.longitude, other.longitude)) {
            return false;
        }
        return true;
    }

  

    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId + ", locationName=" + locationName + ", locationDescription=" + locationDescription + ", locationAddress=" + locationAddress + ", locationCity=" + locationCity + ", locationState=" + locationState + ", country=" + country + ", zipCode=" + zipCode + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

  

}
