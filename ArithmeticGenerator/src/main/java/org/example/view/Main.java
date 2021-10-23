package org.example.view;


import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Crystry
 * @date 2021/10/23 11:20
 */
public class Main extends Application{
    private final int DEFAULT_HEIGHT = 300;
    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_FONT = 14;
    private final int DEFAULT_HGAP = 2;
    private final int DEFAULT_VGAP = 10;
    private static final double DEFAULT_SECONDS = 0.1;
    private static final double DEFAULT_FROMVALUE = 0;
    private static final double DEFAULT_TOVALUE = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gr = new GridPane();
        gr.setStyle("-fx-background-color: #FFF5EE");

        Scene scene = new Scene(gr);
        primaryStage.setScene(scene);
        //设置窗口标题
        primaryStage.setTitle("四则运算");
        // 设置图标
        primaryStage.getIcons().add(new Image("ico/title.jpg"));
        //设置窗体高度
        primaryStage.setHeight(DEFAULT_HEIGHT);
        //设置窗体宽度
        primaryStage.setWidth(DEFAULT_WIDTH);
        //设置窗体不可改变
        primaryStage.setResizable(false);
        primaryStage.show();



        Label topicsNumber=new Label("题目数量：");
        topicsNumber.setFont(Font.font(DEFAULT_FONT));
        TextField textTopicsNumber =new TextField();
        textTopicsNumber.setPromptText("请输入个数：");


        Label digitalRange = new Label("数字范围：");
        digitalRange.setFont(Font.font(DEFAULT_FONT));
        TextField textDigitalRange = new TextField();
        textDigitalRange.setPromptText("请输入范围");


        Button generate = new Button("生成");
        Button check = new Button("检验答案");

        //设置坐标
        gr.add(topicsNumber,0,0);
        gr.add(textTopicsNumber,1,0);
        gr.add(digitalRange, 0, 1);
        gr.add(textDigitalRange, 1, 1);
        gr.add(check, 0, 4);

        gr.add(generate, 1, 4);

        gr.setAlignment(Pos.CENTER);

        gr.setHgap(DEFAULT_HGAP);
        gr.setVgap(DEFAULT_VGAP);
        GridPane.setMargin(generate, new Insets(0, 0, 0, 150));
        //生成
        generate.setOnAction(
                (final ActionEvent e) -> {
                    String textTopicsNumberText=textTopicsNumber.getText();                   //获取文本上的内容
                    String textDigitalRangeText=textDigitalRange.getText();

                    //闪动的画面
                    FadeTransition fade = new FadeTransition();
                    fade.setDuration(Duration.seconds(DEFAULT_SECONDS));
                    fade.setNode(gr);
                    fade.setFromValue(DEFAULT_FROMVALUE);
                    fade.setToValue(DEFAULT_TOVALUE);
                    textTopicsNumber.setText("");
                    textDigitalRange.setText("");
                    primaryStage.setTitle("题目已生成，请查看生成文件");

                });


        check.setOnAction(actionEvent -> {
            primaryStage.close();
            Compare compare = new Compare();
            compare.start(primaryStage);
        });

    }
}
