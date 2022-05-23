package com.example.lab1;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Group root = new Group();
        Scene scene = new Scene(root, 600, 400);
        scene.setFill(Color.rgb(0, 0, 0));

        Circle blueCircle = new Circle(300,180,90);
        blueCircle.setFill(Color.BLUE);

        Circle blueInner = new Circle(300,180,75);
        blueInner.setFill(null);
        blueInner.setStrokeWidth(1);
        blueInner.setStrokeType(StrokeType.INSIDE);
        blueInner.setStroke(Color.BLACK);

        Circle redCircle = new Circle(300,180,60);
        redCircle.setFill(Color.RED);

        Circle redInner = new Circle(300,180,45);
        redInner.setFill(null);
        redInner.setStrokeWidth(1);
        redInner.setStrokeType(StrokeType.INSIDE);
        redInner.setStroke(Color.BLACK);

        Circle yellowCircle = new Circle(300,180,30);
        yellowCircle.setFill(Color.YELLOW);

        Circle yellowInner = new Circle(300,180,15);
        yellowInner.setFill(null);
        yellowInner.setStrokeWidth(1);
        yellowInner.setStrokeType(StrokeType.INSIDE);
        yellowInner.setStroke(Color.BLACK);

        Line horizontalXLine = new Line(295, 180, 305, 180);
        Line verticalXLine = new Line(300, 175, 300, 185);

        root.getChildren().addAll(
                blueCircle,
                blueInner,
                redCircle,
                redInner,
                yellowCircle,
                yellowInner,
                horizontalXLine,
                verticalXLine
        );
        primaryStage.setTitle("lab 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}