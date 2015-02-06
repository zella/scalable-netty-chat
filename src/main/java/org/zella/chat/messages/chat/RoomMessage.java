package org.zella.chat.messages.chat;

import java.io.Serializable;

/**
 * Created by dru on 03.02.2015.
 */
public class RoomMessage implements IChatMessage {

    private String from;
    private String toRoom;
    private String content;

    public RoomMessage(String from, String to, String content) {
        this.from = from;
        this.toRoom = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public String getToRoom() {
        return toRoom;
    }

    public String getContent() {
        return content;
    }


    @Override
    public String toString() {
        return "RoomMessage{" +
                "from='" + from + '\'' +
                ", toRoom='" + toRoom + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
