/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_innerjoin_chaining;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;


/**
 *
 * @author mansijain
 */
public class ZipCodeDate implements WritableComparable<ZipCodeDate>{

    String zipcode;
    String date;

    public ZipCodeDate() {
        zipcode = "0";
        date = "0";
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    @Override
    public void write(DataOutput d) throws IOException {
        WritableUtils.writeString(d, zipcode);
        WritableUtils.writeString(d, date);
    }

    @Override
    public void readFields(DataInput di) throws IOException {
        zipcode = WritableUtils.readString(di);
        date = WritableUtils.readString(di);
    }
    
    public String toString(){
        return (new StringBuilder().
                append(zipcode).
                append("\t").
                append(date).
                toString());
    }

    @Override
    public int compareTo(ZipCodeDate zd) {
        if(zd == null){
            return 0;
        }
        int intcnt = zipcode.compareTo(zd.zipcode);
        return intcnt == 0 ? date.compareTo(zd.date) : intcnt;
    }
    
}
