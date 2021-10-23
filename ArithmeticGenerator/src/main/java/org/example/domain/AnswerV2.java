package org.example.domain;

//存储计算结果后必要的信息
public class AnswerV2 {
    private String expression;
    private String result;
    private char mark;

    public AnswerV2(String expression, String result,char mark) {
        this.expression = expression;
        this.result = result;
        this.mark = mark;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }
}
