package org.zella.chat.hazelcast;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by dru on 02.02.2015.
 */
public class HazelcastMessage implements Serializable {

    private String text;
    private UUID channelUUID;

    public HazelcastMessage(String text, UUID channelUUID) {
        this.text = text;
        this.channelUUID = channelUUID;
    }

    public String getText() {
        return text;
    }

    public UUID getChannelUUID() {
        return channelUUID;
    }
}
