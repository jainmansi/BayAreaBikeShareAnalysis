/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_innerjoin_chaining;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author mansijain
 */
public class TripJoin_Mapper2 extends Mapper<Object, Text, ZipCodeDate, Text>{
    
    ZipCodeDate compositeKey = new ZipCodeDate();
    Text outValue = new Text();
    protected void map(Object key, Text value, Context context){
        String separatedInput[] = value.toString().split("\t");
        
        try{
            String zipcode = separatedInput[0];
            String date = separatedInput[1];
            String count = separatedInput[2];
            
            compositeKey.setZipcode(zipcode);
            compositeKey.setDate(date);
            
            outValue.set("A"+count);
            
            context.write(compositeKey, outValue);
            
        }
        catch(Exception e){
            
        }
    }
}
