 

package org.seedstack.samples.catalog.domain.model.product;

import org.seedstack.business.domain.BaseValueObject;

import javax.persistence.Embeddable;
 
@Embeddable
public class Price extends BaseValueObject {
    private float amount;
    private String currency;

    protected Price() {
    }
 
       @param amount   the amount
       @param currency the currency
      
    public Price(float amount, String currency) {
        if (amount < 0) {
            throw new IllegalArgumentException("The price amount can't be negative: " + amount);
        }
        this.amount = amount;
        this.currency = currency;
    }

     
       @return the amount
      
    public float getAmount() {
        return amount;
    }

     
      @return the currency
      
    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}
