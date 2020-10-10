
package com.sg.SuperHeroSightings.dto;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author Chelsea, Karma, Mohammed, Patrick
 */
public class OrgStub {
    private int organizationId;
    private String organizationName;

    public OrgStub() {
    }
    
    

    public OrgStub(int organizationId) {
        this.organizationId = organizationId;
    }

    public int getOrganizationId() {
        return organizationId;
    }
    
    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
   
    
    public String getOrganizationName() {
        return organizationName;
    }
    public void setOrganizationName(String orgnizationName) {
        this.organizationName = orgnizationName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.organizationId;
        hash = 71 * hash + Objects.hashCode(this.organizationName);
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
        final OrgStub other = (OrgStub) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.organizationName, other.organizationName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrgStub{" + "organizationId=" + organizationId + ", organizationName=" + organizationName + '}';
    }
    

}
