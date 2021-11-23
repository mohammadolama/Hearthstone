package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.CanDoActionResponse;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("candoaction")
public class CanDoActionRequest implements Request {

    private int index;

    public CanDoActionRequest() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        boolean flag = clientHandler.getGameManager().canDoAction(index, clientHandler);
        try {
            outputStream.println(objectMapper.writeValueAsString(new CanDoActionResponse(flag)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
