package org.zella.chat.messages.login;


/**
 * Created by dru on 04.02.2015.
 */
public class LoginDeniedMessage implements ILoginResponseMessage {

    private String cause;

    public LoginDeniedMessage(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public String toString() {
        return "LoginDeniedMessage{" +
                "cause='" + cause + '\'' +
                '}';
    }
}
