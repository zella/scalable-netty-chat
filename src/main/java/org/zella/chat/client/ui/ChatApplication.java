package org.zella.chat.client.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dru on 06.02.2015.
 */
public class ChatApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //TODO simplification
        FXMLLoader loader = new FXMLLoader();
        InputStream is = ChatApplication.class.getResourceAsStream("ChatView.fxml");
        Parent root = null;
        try {
            try {
                root = loader.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        primaryStage.setScene(new Scene(root, 400, 250));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        //TODO dispose chat
        super.stop();
        System.exit(0);
    }
}
