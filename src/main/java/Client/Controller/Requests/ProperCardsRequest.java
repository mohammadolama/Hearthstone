package Client.Controller.Requests;

import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("propercard")
public class ProperCardsRequest implements Request {
    private int i;


    public ProperCardsRequest(int i) {
        this.i = i;
    }

    public ProperCardsRequest() {
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
            synchronized (object) {
                object.wait();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}