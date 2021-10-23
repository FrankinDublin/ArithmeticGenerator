package org.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
/**
 *
 * @author Crystry
 * @date 2021/10/23 12:13
 */
public class FileUtil {
    /**
     * @param filePath 文件路径
     * @return 返回文件内容
     */
    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> arrayList = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("节点基本信息文件未找到");
            System.exit(0);
        }
        try (Scanner s = new Scanner(file)) {
            while (s.hasNext()) {
                String de = s.next();
                String[] string = de.split("、");
                //System.out.println(string[1]);
                arrayList.add(string[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * 比较回答与正确答案是否相同
     *
     * @param answer 答案
     * @param reply  回答
     */
    public static void compare(ArrayList<String> answer, ArrayList<String> reply) {
        ArrayList<String> correctList = new ArrayList<>();
        ArrayList<String> wrongList = new ArrayList<>();
        for (int i = 0; i < answer.size(); i++) {
            if (reply.get(i).equals(answer.get(i))) {
                correctList.add(String.valueOf(i + 1));
            } else {
                wrongList.add(String.valueOf(i + 1));
            }
        }
        String string = "Correct: " + correctList.size() + " " + Arrays.toString(correctList.toArray()).replace("[", "(").replace("]", ")") + "\n";
        string += "Wrong: " + wrongList.size() + " " + Arrays.toString(wrongList.toArray()).replace("[", "(").replace("]", ")");
        fileWriter(string, "D://grade.txt");
    }

    /**
     * 将结果写入文件中
     *
     * @param str      需写入的字符串
     * @param filePath 路径
     */
    public static void fileWriter(String str, String filePath) {
        FileWriter fileWriter = null;
        try {
            File file = new File(filePath);
            fileWriter = new FileWriter(file);
            fileWriter.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

