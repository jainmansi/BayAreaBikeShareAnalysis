/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_customer_type;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Writable;

/**
 *
 * @author mansijain
 */
public class BABS_CustomerType_Writable implements Writable{
    
    private Double subs_count = 0.0;
    private Double cust_count = 0.0;

    public Double getSubs_count() {
        return subs_count;
    }

    public void setSubs_count(Double subs_count) {
        this.subs_count = subs_count;
    }

    public Double getCust_count() {
        return cust_count;
    }

    public void setCust_count(Double cust_count) {
        this.cust_count = cust_count;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeDouble(subs_count);
        out.writeDouble(cust_count);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        subs_count = in.readDouble();
        cust_count = in.readDouble();
    }
    
    @Override
    public String toString(){
        return new StringBuilder().append(subs_count).append("\t").append(cust_count).toString();
    } 
    
}
