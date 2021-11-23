package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("finishgame")
public class FinishGameRequest implements Request {

    public FinishGameRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        clientHandler.getGameManager().finishGame(clientHandler);
    }
}
