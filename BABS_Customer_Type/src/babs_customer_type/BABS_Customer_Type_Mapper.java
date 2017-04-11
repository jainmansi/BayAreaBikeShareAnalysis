/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_customer_type;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author mansijain
 */
public class BABS_Customer_Type_Mapper extends Mapper<Object, Text, IntWritable, Text> {

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        try {
            String seperatedInput[] = value.toString().split(",");

            String rider_type = seperatedInput[9];
            String strDate = seperatedInput[2].split(" ")[0].split("/")[2];
            if (!rider_type.contains("ype")) {
                
                IntWritable outYear = new IntWritable(Integer.parseInt(strDate));
                /*SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy h:mm:ss a");
                Date date = sdf.parse(strDate);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                int year = cal.get(Calendar.YEAR);

                IntWritable outYear = new IntWritable(new Integer(year));*/

                context.write(outYear, new Text(rider_type));
            }
        } catch(Exception e){
            
        }

    }
}
