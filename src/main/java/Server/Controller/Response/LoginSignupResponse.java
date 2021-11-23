package Server.Controller.Response;

import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("login")
public class LoginSignupResponse implements Response {

    private int i;
    private String res;

    public LoginSignupResponse(int i, String res) {
        this.i = i;
        this.res = res;
    }

    public LoginSignupResponse() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }
}
