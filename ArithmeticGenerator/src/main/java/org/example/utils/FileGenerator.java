package org.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileGenerator {
    public static File expression = new File("D:\\expression.txt");
    public static File answer = new File("D:\\answer.txt");

    public static void writeExpression(String src){
        OutputStream os;
        if(!expression.exists()) {
            try {
                expression.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            os = new FileOutputStream(expression,true);
            byte[] b = src.getBytes();
            os.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAnswer(String src){
        OutputStream os;
        if(!answer.exists()) {
            try {
                answer.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            os = new FileOutputStream(answer,true);
            byte[] b = src.getBytes();
            os.write(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
