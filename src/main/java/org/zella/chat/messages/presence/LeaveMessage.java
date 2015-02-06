package org.zella.chat.messages.presence;

import java.io.Serializable;

/**
 * Created by dru on 03.02.2015.
 */
public class LeaveMessage implements IPresenceMessage {

    private String name;
    private String room;

    public LeaveMessage(String name, String room) {
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
        return "LeaveMessage{" +
                "name='" + name + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
