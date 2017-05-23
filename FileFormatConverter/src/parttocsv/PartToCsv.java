/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parttocsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author mansijain
 */
public class PartToCsv {
    public static void main(String args[]) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader("data//station_hive"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("data//station_hive.csv"));
        
        String line;
        while(( line = br.readLine()) != null){
            String[] values = line.split(",", -1);
            
            for(int i=0; i<3; i++){
                bw.write(values[i]+",");
            }
            bw.write("\n");
               
        }
        
        br.close();
        bw.close();
    }
}
