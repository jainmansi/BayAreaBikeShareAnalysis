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
public class TripCount_Mapper1 extends Mapper<Object, Text, ZipCodeDate, IntWritable> {

    ZipCodeDate compositeKey = new ZipCodeDate();
    IntWritable one = new IntWritable(1);

    @Override
    protected void map(Object key, Text value, Context context) {
        String separatedValues[] = value.toString().split(",");
        
        if (!separatedValues[1].equalsIgnoreCase("Duration")) {
            
            try{
                String date = separatedValues[2].trim().split(" ")[0];
                String zipcode = separatedValues[10].trim();
                compositeKey.setZipcode(zipcode);
                compositeKey.setDate(date);
                
                context.write(compositeKey, one);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            
        }

    }
}
