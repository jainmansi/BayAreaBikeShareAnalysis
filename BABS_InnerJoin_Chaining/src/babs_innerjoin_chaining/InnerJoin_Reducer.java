/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package babs_innerjoin_chaining;

import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 *
 * @author mansijain
 */
public class InnerJoin_Reducer extends Reducer<ZipCodeDate, Text, Text, Text> {

    private Text tmp = new Text();

    private ArrayList<Text> listA = new ArrayList<Text>();
    private ArrayList<Text> listB = new ArrayList<Text>();
    private String joinType = null;
    String zipcode;
    String date;

    protected void reduce(ZipCodeDate key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        listA.clear();
        listB.clear();
        zipcode = "";
        date = "";

        while (values.iterator().hasNext()) {
            zipcode = key.getZipcode();
            //date = key.getDate();
            tmp = values.iterator().next();
            if (tmp.charAt(0) == 'A') {
                listA.add(new Text(zipcode + "\t" + tmp.toString().substring(1)));
            } else if (tmp.charAt(0) == 'B') {
                listB.add(new Text(tmp.toString().substring(1)));
            }
        }
        executeJoinLogic(context);
    }

    private void executeJoinLogic(Context context) throws IOException, InterruptedException {

        if (!listA.isEmpty() && !listB.isEmpty()) {
            for (Text A : listA) {
                for (Text B : listB) {
                    context.write(A, B);
                }
            }
        }
    }
}
