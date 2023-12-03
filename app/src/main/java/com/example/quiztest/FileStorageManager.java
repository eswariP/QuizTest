package com.example.quiztest;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileStorageManager {
    String fileName = "QuizScore.txt";

    void writeResult(Context context, int userScore) {
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_APPEND);
            fos.write((userScore + "$").getBytes());
           fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    void deleteAllToDos(Context context){
        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            int userScore;
            fos.write(("").getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
           }



  /*  public ArrayList<String> readAllResults(Context context){
        ArrayList<String> allResultsFromTheFile = new ArrayList<>(0);
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            int read = 0;
            StringBuffer stringBuffer = new StringBuffer();

            while (( read = inputStreamReader.read() ) != -1){
                stringBuffer.append((char)read);
            }
            allResultsFromTheFile = fromStringToResults(stringBuffer.toString());
            //Close the FileInputStream after reading all the results
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allResultsFromTheFile;

    }
    private ArrayList<String> fromStringToResults(String fileContent){
        int firstIndex = 0;
        ArrayList<String> list = new ArrayList<>(0);
        char[] charArray = fileContent.toCharArray();
        for (int i = 0 ;i< charArray.length;i++){
            if (charArray[i] == '$'){
                String result = fileContent.substring(firstIndex,i);
                list.add(result);
                firstIndex = i + 1;
            }
        }
        return list;
    }
    */




    //Read file

    public List<String> readAllResults(Context context){

        List<String> allResultsFromTheFile = new ArrayList<>();

        try {

            FileInputStream fis = context.openFileInput(fileName);

            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);

            int read = 0;

            StringBuffer stringBuffer = new StringBuffer();

            while (( read = inputStreamReader.read() ) != -1){

                stringBuffer.append((char)read);

            }

            allResultsFromTheFile.addAll(convertStringToResults(stringBuffer.toString()));

            //Close the FileInputStream after reading all the results

            fis.close();

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        return allResultsFromTheFile;

    }

    private   List<String> convertStringToResults(String fileContent){

        List<String> resultList;

        if(fileContent!=null && !fileContent.isEmpty()){

            // String[] resultArray=fileContent.split("#");

            String[] resultArray=fileContent.split("\\$");

            resultList= Arrays.asList(resultArray);

        }else {

            //file content is empty or null

            resultList=new ArrayList<String>();

        }

        return resultList;

    }



}






