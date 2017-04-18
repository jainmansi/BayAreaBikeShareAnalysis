/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package countingwithcounters;

import com.sun.javafx.image.impl.IntArgb;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author mansijain
 */
public class CountingMapper extends Mapper<Object, Text, Text, IntWritable> {

    private static String STATION = "station";

    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        String separatedInputs[] = value.toString().split(",");
        IntWritable one = new IntWritable(1);
        String station = separatedInputs[3];
        station = trim(station);
        context.write(new Text(station), one);
        Counter counter = context.getCounter(STATION, station);
        counter.increment(1);

    }

    private String trim(String token) {
        if (token.endsWith(".") || token.endsWith(",")) {
            token = token.substring(0, token.length() - 2);
        }
        if (token.endsWith(")")) {
            token = token.substring(0, token.length() - 2);
        }
        if (token.startsWith("(")) {
            token = token.substring(1);
        }
        return token;
    }
}
