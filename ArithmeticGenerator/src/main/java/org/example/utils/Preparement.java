package org.example.utils;

import java.util.Random;

/**
 *
 * @author Crystry
 * @date 2021/10/23 10:16
 */
public class Preparement {
    public static final char[] markSet = {'+', '-', 'x', '÷'};
    public static int numCapacity = 10;

    /**
     * 真分数计算
     *
     * @param firstNum 第一个运算数
     * @param secNum   第二个运算数
     * @param markType 运算符类型
     * @return 真分数样式的结果
     */
    public static String fractionCalculator(String firstNum, String secNum, int markType) {
        String s = "";
        String[] fir = fractionToArray(firstNum);
        String[] sec = fractionToArray(secNum);

        switch (markSet[markType]) {
            case '+':
                s = fractionAddition(fir, sec);
                break;
            case '-':
                s = fractionSubtraction(fir, sec);
                break;
            case 'x':
                s = fractionMultiplication(fir, sec);
                break;
            case '÷':
                s = fractionDivision(fir, sec);
                break;

        }
        return s;
    }

    /**
     * 将分数转为分数形式
     *
     * @param firstNum 需转分数的整数
     * @return 返回分数形式
     */
    public static String[] fractionToArray(String firstNum) {
        if (firstNum.contains("'")) {
            firstNum = change(firstNum);
        } else if (!firstNum.contains("/")) {
            firstNum = firstNum + "/" + "1";
            //System.out.println(firstNum);
        }
        return firstNum.split("/");
    }


    /**
     * 将带分数转为假分数
     *
     * @param num 检查分数
     * @return 带分数形式
     */
    public static String change(String num) {
        String[] first = num.split("'");
        String[] second = first[1].split("/");
        int molecule = (Integer.parseInt(first[0]) * Integer.parseInt(second[1])) + Integer.parseInt(second[0]);
        return molecule + "/" + second[1];
    }

    /**
     * 加法
     *
     * @param fir 第一个数
     * @param sec 第二个数
     * @return 返回相加后的结果
     */
    public static String fractionAddition(String[] fir, String[] sec) {
        int[] generalPoints = GeneralPoints(fir, sec);
        //分子进行加法
        int molecule = generalPoints[0] + generalPoints[1];
        return fractionForm(molecule, generalPoints[2]);
    }

    /**
     * 减法
     *
     * @param fir 第一位数
     * @param sec 第二位数
     * @return 返回相减后 的结果
     */
    public static String fractionSubtraction(String[] fir, String[] sec) {
        int[] generalPoints = GeneralPoints(fir, sec);
        if (generalPoints[0] > generalPoints[1]) {
            int molecule = generalPoints[0] - generalPoints[1];
            return fractionForm(molecule, generalPoints[2]);
        } else {
            return null;
        }
    }

    /**
     * 找到分母的最小公倍数,并进行通分
     *
     * @param fir 第一位数
     * @param sec 第二位数
     * @return 返回通分后数字数组
     */
    public static int[] GeneralPoints(String[] fir, String[] sec) {
        int firstMol = Integer.parseInt(fir[0]);
        int firstDen = Integer.parseInt(fir[1]);
        int secondMol = Integer.parseInt(sec[0]);
        int secondDen = Integer.parseInt(sec[1]);

        //找到两分母的最小公倍数
        int max = Math.max(firstDen, secondDen);
        int denominator = firstDen * secondDen;
        for (int i = max; i <= denominator; i++) {
            if (i % secondDen == 0 && i % firstDen == 0) {
                denominator = i;     //既然是最小的，第一次出现的数值就是最小公倍数了。
                break;
            }
        }
        firstMol = firstMol * (denominator / firstDen);
        secondMol = secondMol * (denominator / secondDen);
        return new int[]{firstMol, secondMol, denominator};
    }

    /**
     * 乘法
     *
     * @param fir 第一位数
     * @param sec 第二位数
     * @return 返回相乘后的结果
     */
    public static String fractionMultiplication(String[] fir, String[] sec) {
        int molecule = Integer.parseInt(fir[0]) * Integer.parseInt(sec[0]);
        int denominator = Integer.parseInt(fir[1]) * Integer.parseInt(sec[1]);
        if (molecule==0){
            return "0";
        }else {
            return fractionForm(molecule, denominator);
        }
    }

    /**
     * 除法
     *
     * @param fir 第一位数
     * @param sec 第二位数
     * @return 返回除后的结果
     */
    public static String fractionDivision(String[] fir, String[] sec) {
        if (sec[0].equals("0")){
            return null;
        }
        String temp = sec[1];
        sec[1] = sec[0];
        sec[0] = temp;
        return fractionMultiplication(fir, sec);
    }

    /**
     * 分数形式
     *
     * @param molecule    分子
     * @param denominator 分母
     * @return 返回分数形式
     */
    public static String fractionForm(int molecule, int denominator) {
        String num;
        // 判断分子与分母大小关系
        if (molecule < denominator) {
            num = fractionReduction(molecule, denominator);
        } else if (molecule % denominator!=0){
            num = molecule / denominator + "'" + fractionReduction(molecule % denominator, denominator);
        }else {
            num = String.valueOf(molecule / denominator);
        }
        return num;
    }

    /**
     * 运算数生成器
     *
     * @return 返回真分数形式
     */
    public static String fractionGenerator() {
        String num;
        Random random = new Random();
        int molecule = random.nextInt(numCapacity);
        int denominator = random.nextInt(numCapacity);

        if (denominator == 0 ) {
            return fractionGenerator();
        }
        else if (molecule < denominator) {
            num = fractionReduction(molecule, denominator);
        } else if (molecule % denominator != 0){
            num = molecule / denominator + "'" + fractionReduction(molecule % denominator, denominator);
        } else {
            num= String.valueOf(random.nextInt(numCapacity));
        }
        return num;
    }

    /**
     * 分数是否可以约分，若可以，返回约分后形式，不可以则
     *
     * @param molecule    分子
     * @param denominator 分母
     * @return 返回约分后数值
     */
    public static String fractionReduction(int molecule, int denominator) {
        if (molecule==0||denominator==0){
            return "0";
        }
        int num1 = denominator, num2 = molecule;
        if (num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        int remainder = num1 % num2;
        //利用辗转相除法得出最大公约数
        while (remainder != 0) {
            num1 = num2;
            num2 = remainder;
            remainder = num1 % num2;
        }
        molecule = molecule / num2;
        denominator = denominator / num2;
        return molecule + "/" + denominator;
    }


    /**
     * @param firstNum 第一个运算数
     * @param secNum   第二个运算数
     * @return 如果第一个运算数大于第二个 则返回 true
     */
    public static boolean fractionComparator(String firstNum, String secNum) {
        String[] fir = fractionToArray(firstNum);
        String[] sec = fractionToArray(secNum);
        int[] generalPoints = GeneralPoints(fir, sec);
        return generalPoints[0] > generalPoints[1];
    }
}
