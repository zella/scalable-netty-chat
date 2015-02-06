package org.zella.chat.messages.login;



import java.io.Serializable;
import java.util.List;

/**
 * Created by dru on 04.02.2015.
 */
public class LoginAcceptedMessage  implements  ILoginResponseMessage {
    private List<String> usersInRoom;
    private String room;

    public LoginAcceptedMessage(List<String> usersInRoom, String room) {
        this.usersInRoom = usersInRoom;
        this.room = room;
    }

    public List<String> getUsersInRoom() {
        return usersInRoom;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "LoginAcceptedMessage{" +
                "usersInRoom=" + usersInRoom +
                ", room='" + room + '\'' +
                '}';
    }
}
