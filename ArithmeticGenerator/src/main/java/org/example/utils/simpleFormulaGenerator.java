package org.example.utils;

import java.util.Random;
import org.example.domain.*;
import static org.example.utils.Preparement.*;

public class simpleFormulaGenerator {
    //简单表达式生成器，生成运算符只有一个的表达式
    public static AnswerV2 GenerateV2(){
        Random r = new Random();
        StringBuilder expression = new StringBuilder();
        int markType = r.nextInt(4);
        String numOne =fractionGenerator();
        String numTwo =fractionGenerator();
        //排除相减小于0的表达式和除数为0的表达式
        if(markType==1 && !fractionComparator(numOne,numTwo)) return null;
        else if(markType==3 && numTwo.equals("0")) return null;

        else{
            String result = fractionCalculator(numOne,numTwo,markType);
            expression.append(numOne+" "+markSet[markType]+" "+numTwo);
            //返回表达式，计算答案以及计算符
            return new AnswerV2(expression.toString(),result,markSet[markType]);
        }
    }
}
