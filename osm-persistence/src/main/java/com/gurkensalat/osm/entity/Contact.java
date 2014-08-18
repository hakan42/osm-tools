package com.gurkensalat.osm.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact
{
    @Column(name = "PHONE", length = 80)
    private String phone;

    @Column(name = "FAX", length = 80)
    private String fax;

    @Column(name = "WEBSITE", length = 80)
    private String website;

    @Column(name = "EMAIL", length = 80)
    private String email;

    public Contact()
    {
    }

    public void copyTo(Contact other)
    {
        other.setPhone(getPhone());
        other.setFax(getFax());
        other.setWebsite(getWebsite());
        other.setEmail(getEmail());
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getWebsite()
    {
        return website;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String toString()
    {
        return (new ToStringBuilder(this))
                .append("phone", phone)
                .append("fax", fax)
                .append("website", website)
                .append("email", email)
                .toString();
    }
}
