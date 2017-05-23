/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_distinctfilter;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author mansijain
 */
public class BABS_DistinctMapper extends Mapper<Object, Text, Text, Text>{
    private Text station = new Text();
    
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
        
        String splittedValues[] = value.toString().split(",");
        
        station = new Text(splittedValues[1]);
        context.write(station, value);
        
    }
}
