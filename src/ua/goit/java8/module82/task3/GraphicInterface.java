package ua.goit.java8.module82.task3;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import ua.goit.java8.module82.utils.Images;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Taras on 16.09.2017.
 */

public class GraphicInterface {
    private Stage primaryStage;
    private static Pane root = new Pane();
    public static final int WIDTH = 900;
    public static final int HEIGHT = 825;

    public static void draw(Stage primaryStage) {
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
        primaryStage.setTitle("Images");

        // BufferedImage img = ImageIO.read(url);

        Button refresh = new Button();
        refresh.setTranslateX(10);
        refresh.setTranslateY(10);
        refresh.setText("Refresh");
        refresh.setOnMouseClicked(event -> {
            try {
                drawImages(getURLs());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(refresh);

        Rectangle rectangle = new Rectangle(100,100,500,500);
        rectangle.setFill(Color.TRANSPARENT);
        root.getChildren().add(rectangle);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private static void drawImage(URL url, double x, double y) throws IOException {
        Image image = new Image(url.openStream());
        ImageView imageView = new ImageView(image);
        Platform.runLater(() -> {
            imageView.setTranslateX(x);
            imageView.setTranslateY(y);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            root.getChildren().add(imageView);
        });

    }

    // завантажуєм кожну картинку окремим потоком
    private static void drawImages(ArrayList<URL> urls) throws IOException {
        int n = 0;
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                final int nn = n;
                final int ii = i;
                final int jj = j;
                new Thread(()->{
                    try {
                        drawImage(urls.get(nn),100*(jj+1),100*(ii+1));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                if (n < urls.size()-1) n++;
            }
        }
    }

    private static ArrayList<URL> getURLs() throws MalformedURLException {
        ArrayList<String> imageLinks = Images.getRandomImages();

        ArrayList<URL> imageUrls = new ArrayList<>(imageLinks.size());
        for (int i = 0; i < imageLinks.size(); i++){
            imageUrls.add(new URL(imageLinks.get(i)));
            //System.out.println(i + " " + imageUrls.get(i));
        }
        return imageUrls;
    }

}
