package com.gurkensalat.osm.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
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

    public void copyTo(Contact other)
    {
        other.setPhone(getPhone());
        other.setFax(getFax());
        other.setWebsite(getWebsite());
        other.setEmail(getEmail());
    }
}
