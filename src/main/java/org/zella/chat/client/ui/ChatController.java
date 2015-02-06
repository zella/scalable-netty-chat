package org.zella.chat.client.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import org.zella.chat.client.ChatClientImpl;
import org.zella.chat.client.IChat;
import org.zella.chat.messages.chat.IChatMessage;
import org.zella.chat.messages.chat.PrivateMessage;
import org.zella.chat.messages.chat.RoomMessage;
import org.zella.chat.messages.login.ILoginResponseMessage;
import org.zella.chat.messages.login.LoginAcceptedMessage;
import org.zella.chat.messages.login.LoginDeniedMessage;
import org.zella.chat.messages.presence.IPresenceMessage;
import org.zella.chat.messages.presence.JoinMessage;
import org.zella.chat.messages.presence.LeaveMessage;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;

/**
 * Created by dru on 06.02.2015.
 */
public class ChatController implements Initializable {

    @FXML
    ListView<String> usersListView;
    @FXML
    TextArea messageTextArea;
    @FXML
    TextArea chatTextArea;
    @FXML
    Button sendButton;


    ChatClientImpl client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init gui");
        client = new ChatClientImpl(new IChat.IChatMessageCallback() {
            @Override
            public void onChatMessage(IChatMessage message) {

                if (message instanceof RoomMessage) {
                    messageTextArea.appendText(((RoomMessage) message).getFrom() + ": " + ((RoomMessage) message).getContent());
                } else if (message instanceof PrivateMessage) {
                    messageTextArea.appendText(((PrivateMessage) message).getFrom() + ": " + ((PrivateMessage) message).getContent());
                }

            }

            @Override
            public void onPresenceMessage(IPresenceMessage message) {
                if (message instanceof JoinMessage){
                    usersListView.getItems().add(((JoinMessage) message).getName());
                } else if (message instanceof LeaveMessage){
                    usersListView.getItems().add(((LeaveMessage) message).getName());
                }

            }
        });
        //TODO or simple callback
        client.connectAndLogin("127.0.0.1", 6666, "петушки", "Вася2").whenComplete((response, throwable) -> {
            if (response instanceof LoginAcceptedMessage) {
                System.out.println(response.toString());
                usersListView.getItems().clear();
                usersListView.getItems().addAll(((LoginAcceptedMessage) response).getUsersInRoom());


            } else if (response instanceof LoginDeniedMessage) {
                System.out.println(response.toString());
            }
        });
    }




}

