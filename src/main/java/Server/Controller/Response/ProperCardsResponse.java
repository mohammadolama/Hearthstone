package Server.Controller.Response;


import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

import java.util.ArrayList;

@JsonTypeName("propercards")
public class ProperCardsResponse implements Response {
    private ArrayList<CardModelView> list;

    public ProperCardsResponse() {
    }

    public ProperCardsResponse(ArrayList<CardModelView> list) {
        this.list = list;
    }

    public ArrayList<CardModelView> getList() {
        return list;
    }

    public void setList(ArrayList<CardModelView> list) {
        this.list = list;
    }

}
