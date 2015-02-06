package org.zella.chat.messages.login;

import java.io.Serializable;

/**
 * Created by dru on 06.02.2015.
 */
public class LoginRequestMessage implements Serializable {
    private String name;
    private String room;

    public LoginRequestMessage(String name, String room) {
        this.name = name;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "name='" + name + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
