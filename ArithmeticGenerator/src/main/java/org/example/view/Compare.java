package org.example.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 *
 * @author Crystry
 * @date 2021/10/23 12:10
 */
public class Compare extends Application {
    private final int DEFAULT_HEIGHT = 300;
    private final int DEFAULT_WIDTH = 500;
    private final int DEFAULT_FONT = 14;
    private final int DEFAULT_HGAP = 2;
    private final int DEFAULT_VGAP = 10;


    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage)  {
        GridPane gr = new GridPane();
        gr.setStyle("-fx-background-color: #FFF5EE");

        Scene scene = new Scene(gr);
        primaryStage.setScene(scene);
        //设置窗口标题
        primaryStage.setTitle("四则运算验算");
        // 设置图标
        primaryStage.getIcons().add(new Image("ico/title.jpg"));
        //设置窗体高度
        primaryStage.setHeight(DEFAULT_HEIGHT);
        //设置窗体宽度
        primaryStage.setWidth(DEFAULT_WIDTH);
        //设置窗体不可改变
        primaryStage.setResizable(false);
        primaryStage.show();

        FileChooser fileChooser = new FileChooser();

        Label fromFileLabel=new Label("答案文件：");
        fromFileLabel.setFont(Font.font(DEFAULT_FONT));
        TextField fromFile =new TextField();
        fromFile.setPromptText("请输入答案文件路径：");
        //设置文本框只读
        fromFile.setEditable(false);
        Button calculationChecking = new Button("生成");
        Button filePath=new Button("请选择文件");
        gr.add(fromFileLabel,0,0);
        gr.add(fromFile,1,0);
        gr.add(filePath,2,0);

        gr.add(calculationChecking,1,3);

        gr.setAlignment(Pos.CENTER);

        gr.setHgap(DEFAULT_HGAP);
        gr.setVgap(DEFAULT_VGAP);
        GridPane.setMargin(calculationChecking, new Insets(0, 0, 0, 150));

        filePath.setOnAction(
                (final ActionEvent e) -> {
                    fileChooser.setInitialDirectory(new File("D:\\java\\Learn\\Homework\\Arithmetic"));
                    File file = fileChooser.showOpenDialog(primaryStage);
                    if (file!=null)
                        fromFile.setText(file.getAbsolutePath());
                });

        calculationChecking.setOnAction(
                (final ActionEvent e) -> {
                    String text = fromFile.getText();


                });


    }
}
