package com.gurkensalat.osm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
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
