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
        scene.setFill(Color.rgb(0, 128, 255));

        Rectangle rectangle = new Rectangle(130,50,230,240);
        rectangle.setFill(Color.WHITE);

        Polygon triangle = new Polygon(
                300.0, 115.0,
                280.0, 330.0,
                550.0, 315.0
        );
        triangle.setFill(Color.rgb(192, 192, 192));

        Polygon polygon = new Polygon(
                50.0, 150.0,
                265.0, 160.0,
                150.0, 350.0,
                25.0, 300.0
        );
        polygon.setFill(Color.rgb(4, 255, 128));

        Polyline polyline = new Polyline(
                105.0, 70.0,
                105.0, 20.0,
                380.0, 20.0,
                380.0, 70.0,
                370.0, 70.0,
                370.0, 30.0,
                115.0, 30.0,
                115.0, 70.0
        );
        polyline.setFill(Color.rgb(254, 255, 0));
        polyline.setStroke(Color.rgb(254, 255, 0));

        root.getChildren().addAll(
                rectangle,
                triangle,
                polygon,
                polyline
        );
        primaryStage.setTitle("Lab1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}