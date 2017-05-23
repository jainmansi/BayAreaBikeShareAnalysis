/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_innerjoin_chaining;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

/**
 *
 * @author mansijain
 */
public class BABS_InnerJoin_Chaining {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job1 = Job.getInstance(conf, "trip_count");
        job1.setJarByClass(BABS_InnerJoin_Chaining.class);
        job1.setMapperClass(TripCount_Mapper1.class);
        job1.setReducerClass(TripCount_Reducer1.class);

        job1.setMapOutputKeyClass(ZipCodeDate.class);
        job1.setMapOutputValueClass(IntWritable.class);

        job1.setOutputKeyClass(ZipCodeDate.class);
        job1.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job1, new Path(args[0]));
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));
        boolean complete = job1.waitForCompletion(true);

        Configuration conf2 = new Configuration();
        Job job2 = Job.getInstance(conf2, "chaining");
        if (complete) {
            job2.setJarByClass(BABS_InnerJoin_Chaining.class);
            MultipleInputs.addInputPath(job2, new Path(args[1]), TextInputFormat.class, TripJoin_Mapper2.class);
            MultipleInputs.addInputPath(job2, new Path(args[2]), TextInputFormat.class, WeatherJoin_Mapper3.class);
            
            job2.setMapOutputKeyClass(ZipCodeDate.class);
            job2.setMapOutputValueClass(Text.class);
            //job2.setNumReduceTasks(0);
            job2.setReducerClass(InnerJoin_Reducer.class);
            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(Text.class);

            job2.setOutputFormatClass(TextOutputFormat.class);
            TextOutputFormat.setOutputPath(job2, new Path(args[3]));
            
            System.exit(job2.waitForCompletion(true) ? 0 : 1);
        }
    }

}
