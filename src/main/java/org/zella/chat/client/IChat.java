package org.zella.chat.client;


import org.zella.chat.messages.chat.IChatMessage;

import org.zella.chat.messages.presence.IPresenceMessage;

import java.io.Serializable;

/**
 * Created by dru on 06.02.2015.
 */
public interface IChat {


    public boolean isConnected();

    public void connect(String host, int port, String room, String name);//TODO seems bad

    public void sendMessage(Serializable message);

    public void disconnect();


    public static interface IChatMessageCallback {
        // public void onConnect(ILoginResponseMessage message);

        public void onChatMessage(IChatMessage message);

        public void onPresenceMessage(IPresenceMessage message);

        //  public void onError(Throwable cause);
    }


}
