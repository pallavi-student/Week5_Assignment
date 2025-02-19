package com.week5.day1csv;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.IOException;


public class FilterRecordsFromCSV {
    public static void main(String[] args) {
        //file to read the data
        String filePath="src/main/java/com/day1/intermediate/student.csv";

        try(CSVReader reader= new CSVReader(new FileReader(filePath))){
            String[] nextLine;
             while((nextLine=reader.readNext())!=null){
                 String name=nextLine[0];
                 String subject=nextLine[1];
                 int marks= Integer.parseInt(nextLine[2]);
          //check if marks is greater than 80
                 if(marks>80){
                     System.out.println(name+ " scored "+ marks);
                 }

             }

        }catch(IOException e){
            e.printStackTrace();
        } catch (CsvException e) {//handle csv exception
            throw new RuntimeException(e);
        }
    }
}
