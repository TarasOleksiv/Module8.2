package ua.goit.java8.module82.textRedactor.task1;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ua.goit.java8.module82.utils.FileUtils;

import java.io.IOException;

/**
 * Created by Taras on 17.09.2017.
 */
public class GraphicInterface {

    private static Pane root = new Pane();
    private static final int WIDTH = 900;
    private static final int HEIGHT = 825;
    private static final String TEXT_CODING = "CP1251";
    private static final String FILE_PATH = "files\\text.txt";

    public static void draw(Stage primaryStage) {
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setTitle("Text Redactor");

        TextField filePath = new TextField();
        filePath.setTranslateX(10);
        filePath.setTranslateY(10);
        filePath.setPrefWidth(300);
        filePath.setText(FileUtils.getApplicationPath() + "\\" + FILE_PATH);
        root.getChildren().add(filePath);

        TextArea fileText = new TextArea();
        fileText.setTranslateX(10);
        fileText.setTranslateY(60);
        fileText.setPrefWidth(850);
        fileText.setPrefHeight(700);
        root.getChildren().add(fileText);

        Button load = new Button();
        load.setTranslateX(350);
        load.setTranslateY(10);
        load.setText("Load");
        load.setOnMouseClicked(event -> {
            new Thread(() -> {
                Platform.runLater(() -> {
                    fileText.setText(FileUtils.readTextFromFile(filePath.getText(), TEXT_CODING));
                });
            }).start();
        });
        root.getChildren().add(load);

        Button save = new Button();
        save.setTranslateX(400);
        save.setTranslateY(10);
        save.setText("Save");
        save.setOnMouseClicked(event -> {
            new Thread(() -> {
                Platform.runLater(() -> {
                    final String filePathName = filePath.getText();
                    if (!FileUtils.fileExists(filePathName)) {
                        try {
                            FileUtils.createFile(filePathName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    FileUtils.saveTextToFile(fileText.getText(),filePathName,TEXT_CODING);
                });
            }).start();
        });
        root.getChildren().add(save);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
