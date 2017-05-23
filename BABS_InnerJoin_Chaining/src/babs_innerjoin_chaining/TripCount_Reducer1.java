/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_innerjoin_chaining;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author mansijain
 */
public class TripCount_Reducer1 extends Reducer<ZipCodeDate, IntWritable, ZipCodeDate, IntWritable>{
    
    public IntWritable result = new IntWritable();
    protected void reduce(ZipCodeDate compositeKey, Iterable<IntWritable> values, Context context){
        int sum = 0;
        for(IntWritable val : values){
            sum += val.get();
        }
        result.set(sum);
        try {
            context.write(compositeKey, result);
        } catch (IOException ex) {
            Logger.getLogger(TripCount_Reducer1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(TripCount_Reducer1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
