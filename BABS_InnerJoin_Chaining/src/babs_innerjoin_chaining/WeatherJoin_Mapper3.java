/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_innerjoin_chaining;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 *
 * @author mansijain
 */
public class WeatherJoin_Mapper3 extends Mapper<Object, Text, ZipCodeDate, Text> {

    ZipCodeDate compositeKey = new ZipCodeDate();
    Text outValue = new Text();

    @Override
    protected void map(Object key, Text value, Context context) {
        String separatedInput[] = value.toString().split(",");
        if (!separatedInput[0].equalsIgnoreCase("PDT")) {
            try {
                String date = separatedInput[0];
                String meanTemp = separatedInput[2];
                String zipCode = separatedInput[23];

                compositeKey.setZipcode(zipCode);
                compositeKey.setDate(date);
                outValue.set("B" + meanTemp);

                context.write(compositeKey, outValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
