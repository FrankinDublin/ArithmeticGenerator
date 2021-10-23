package org.example.domain;

import java.util.Random;

//初始化生成表达式需要的参数
public class GenerateHelper {
    public StringBuilder expression;
    //生成子表达式类型，0表示数字，1表示表达式
    private int firstType;
    private int secType;
    //运算符类型
    private int markType;
    //子表达式答案
    private String firstRes;
    private String secRes;
    private String finalRes;
    //运算符
    private char firstMark;
    private char secMark;
    private char thrMark;

    public GenerateHelper() {
        expression = new StringBuilder();
        Random r = new Random();
        firstType = r.nextInt(2);
        secType = r.nextInt(2);
        markType = r.nextInt(4);
        firstMark='0';
        secMark='0';
        thrMark='0';
    }

    public char getFirstMark() {
        return firstMark;
    }

    public void setFirstMark(char firstMark) {
        this.firstMark = firstMark;
    }

    public char getSecMark() {
        return secMark;
    }

    public void setSecMark(char secMark) {
        this.secMark = secMark;
    }

    public char getThrMark() {
        return thrMark;
    }

    public void setThrMark(char thrMark) {
        this.thrMark = thrMark;
    }

    public String getFirstRes() {
        return firstRes;
    }

    public void setFirstRes(String firstRes) {
        this.firstRes = firstRes;
    }

    public String getSecRes() {
        return secRes;
    }

    public void setSecRes(String secRes) {
        this.secRes = secRes;
    }

    public String getFinalRes() {
        return finalRes;
    }

    public void setFinalRes(String finalRes) {
        this.finalRes = finalRes;
    }

    public int getFirstType() {
        return firstType;
    }

    public void setFirstType(int firstType) {
        this.firstType = firstType;
    }

    public int getSecType() {
        return secType;
    }

    public void setSecType(int secType) {
        this.secType = secType;
    }

    public int getMarkType() {
        return markType;
    }

    public void setMarkType(int markType) {
        this.markType = markType;
    }
}
