package org.zella.chat.messages.chat;

import java.io.Serializable;

/**
 * Created by dru on 03.02.2015.
 */
public class PrivateMessage implements IChatMessage {

    private String from;
    private String to;
    private String content;

    public PrivateMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "PrivateMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
