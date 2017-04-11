/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_customer_type;

import java.io.IOException;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author mansijain
 */
public class BABS_Customer_Type_Reducer extends Reducer<IntWritable, Text, IntWritable, BABS_CustomerType_Writable>{
    
    private double total = 0;
    private BABS_CustomerType_Writable outVal = new BABS_CustomerType_Writable();
    private double subs_count = 0;
    private double cust_count = 0;
    
    
    protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException
    {
        
        for(Text val : values){
            String v = val.toString();
            total++;
            if(v.equalsIgnoreCase("subscriber")){
                subs_count++;
            }
            if(v.equalsIgnoreCase("customer")){
                cust_count++;
            }
        }
        
        
        
        outVal.setSubs_count((subs_count/total)*100);
        outVal.setCust_count((cust_count/total)*100);
        
        context.write(key, outVal);
    }
}
