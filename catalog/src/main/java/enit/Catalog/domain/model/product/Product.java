 

package org.seedstack.samples.catalog.domain.model.product;

import org.hibernate.annotations.Type;
import org.seedstack.business.data.DataSet;
import org.seedstack.business.domain.BaseAggregateRoot;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

 
@Entity
@DataSet(group = "catalog", name = "product")
public class Product extends BaseAggregateRoot<String> {
    @Id
    private String name;
    private URI picture;
    private Price pricing;
    @Type(type = "text")
    private String description;
    @ElementCollection
    private Set<String> tags = new HashSet<String>();
    @ElementCollection
    private Set<String> details = new HashSet<String>();
    @ElementCollection
    private Set<String> related = new HashSet<String>();

    protected Product() {
    }

 
 
    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public URI getPicture() {
        return picture;
    }

    public void setPicture(URI picture) {
        this.picture = picture;
    }

    public Price getPricing() {
        return pricing;
    }

    public void setPricing(Price pricing) {
        this.pricing = pricing;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void tag(String tag) {
        this.tags.add(tag);
    }

    public Set<String> getDetails() {
        return details;
    }

    public void addDetails(String details) {
        this.details.add(details);
    }

    public Set<String> getRelated() {
        return related;
    }

    public void addRelated(String related) {
        this.related.add(related);
    }

    @Override
    public String getId() {
        return this.name;
    }
}
