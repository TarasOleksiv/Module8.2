package ua.goit.java8.module82.utils;

import java.io.*;

/**
 * Created by Taras on 17.09.2017.
 */
public class FileUtils {

    public static String readTextFromFile(String filePath, String textCoding){
        StringBuffer buffer = new StringBuffer();
        try (
                FileInputStream myFile = new FileInputStream(filePath);
                InputStreamReader inputStreamReader =
                        new InputStreamReader(myFile, textCoding);
                Reader reader = new BufferedReader(inputStreamReader);){
            int ch; // the code of one character
            while ((ch = reader.read()) > -1) {
                buffer.append((char)ch);
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Unable to read from file " + filePath);
        }

        return buffer.toString();
    }

    public static void saveTextToFile(String stringToWrite, String filePath, String textCoding){
        try (FileOutputStream myFile = new FileOutputStream(filePath);
             Writer out = new BufferedWriter(
                     new OutputStreamWriter(myFile, textCoding));) {
            out.write(stringToWrite);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static String getApplicationPath(){
        String result = "N/A";
        try {
            result = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean fileExists(String filePathString){
        File f = new File(filePathString);
        if(f.exists() && !f.isDirectory()) {
            return true;
        } else {
            return false;
        }
    }

    public static void createFile(String filePathString) throws IOException {
        File f = new File(filePathString);
        f.getParentFile().mkdirs();
        f.createNewFile();
    }

}
