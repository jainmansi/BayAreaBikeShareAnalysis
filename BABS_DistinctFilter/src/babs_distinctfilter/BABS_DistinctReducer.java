/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_distinctfilter;

import java.io.IOException;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author mansijain
 */
public class BABS_DistinctReducer extends Reducer<Text, Text, Text, NullWritable>{
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for(Text val: values){
            context.write(val, NullWritable.get());
            
        }
    }
}
