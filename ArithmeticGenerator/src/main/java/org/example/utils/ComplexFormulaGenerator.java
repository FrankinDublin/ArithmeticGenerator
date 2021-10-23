package org.example.utils;

import java.util.HashSet;
import java.util.Set;
import org.example.domain.*;
import static org.example.utils.Preparement.*;
import static org.example.utils.simpleFormulaGenerator.*;


public class ComplexFormulaGenerator {
    //储存表达式类型，判断是否重复
    private static Set<Set> expressionComparator = new HashSet();
    public static int index = 1;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i=1;i<=10000;i++){
            AnswerV2 generated = complicatedGenerate();
            while(generated==null) generated=complicatedGenerate();
            /*System.out.println("第"+i+"次表达式："+generated.getExpression());
            System.out.println("答案："+generated.getResult());
            System.out.println("-------------------");*/
            FileGenerator.writeExpression(i+"、"+generated.getExpression()+" =\n");
            FileGenerator.writeAnswer(i+"、"+generated.getResult()+"\n");
        }
        long end = System.currentTimeMillis();
        System.out.println("生成完毕，耗时："+(end-start)+"毫秒");
    }
    
    public static AnswerV2 complicatedGenerate(){
        GenerateHelper g = new GenerateHelper();
        char mark = markSet[g.getMarkType()];
        //生成第一个数，随机决定生成数字或简单表达式
        if(g.getFirstType() == 0){
            String firstNum = fractionGenerator();
            g.expression.append(firstNum);
            g.setFirstRes(firstNum);
        }
        else{
            AnswerV2 result = GenerateV2();
            while (result==null){
                result = GenerateV2();
            }
            //获取符号进行加括号的判断
            g.setFirstMark(result.getMark());
            g.setSecMark(mark);
            g.setFirstRes(result.getResult());
            //加括号的情况：前一表达式为加减而后一表达式为乘除
            if((g.getFirstMark() == '+' || g.getFirstMark() == '-') && (g.getSecMark() == 'x' || g.getSecMark() == '÷'))
                g.expression.append('(').append(result.getExpression()).append(')');
            else g.expression.append(result.getExpression());
        }
        g.expression.append(" "+mark+" ");

        if(g.getFirstMark()!='0') g.setFirstMark(mark);
        else g.setSecMark(mark);
        //生成第二个数
        if(g.getSecType() == 0){
            String secNum = fractionGenerator();
            g.expression.append(secNum);
            g.setSecRes(secNum);
        }
        else{
            AnswerV2 result = GenerateV2();
            while (result==null){
                result = GenerateV2();
            }
            if(g.getSecMark()=='0') g.setSecMark(result.getMark());
            else g.setThrMark(result.getMark());
            g.setSecRes(result.getResult());
            //加括号的情况：前一表达式为加减而后一表达式为乘除
            //或前除后乘
            if(((g.getThrMark() == '+' || g.getThrMark() == '-') && (g.getSecMark() == 'x' || g.getSecMark() == '÷'))||
                    (g.getThrMark()=='x'&&g.getSecMark()=='÷')
            )
                g.expression.append('(').append(result.getExpression()).append(')');
            else g.expression.append(result.getExpression());
        }

        /*总体答案是负数或除数为0则舍弃*/
        if(mark=='-' && !fractionComparator(g.getFirstRes(),g.getSecRes())) return null;
        else if(mark=='÷' && g.getSecRes().equals("0")) return null;

        //对单双运算符的运算式子进行重复审计
        if(g.getFirstType()+g.getSecType()<2){
            Set<String> newSet = new HashSet();
            newSet.add(g.getFirstRes());
            newSet.add(g.getSecRes());
            newSet.add(mark+"");
            if(expressionComparator.contains(newSet)) return null;
            else expressionComparator.add(newSet);
        }

        g.setFinalRes(fractionCalculator(g.getFirstRes(),g.getSecRes(),g.getMarkType()));
        return new AnswerV2(g.expression.toString(),g.getFinalRes(),'0');
    }
}
