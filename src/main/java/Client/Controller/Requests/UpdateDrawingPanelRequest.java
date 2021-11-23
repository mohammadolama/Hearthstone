package Client.Controller.Requests;


import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeName("updatedrawingpanel")
public class UpdateDrawingPanelRequest implements Request {
    private String value;
    public UpdateDrawingPanelRequest(String value) {
        this.value = value;
    }

    public UpdateDrawingPanelRequest() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ObjectMapper objectMapper, Object object) {
        try {
            outputStream.println(objectMapper.writeValueAsString(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
