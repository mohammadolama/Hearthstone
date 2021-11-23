package Server.Controller.Response;

import Server.Model.CardModelView;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("modelview")
public class ModelViewResponse implements Response {
    private CardModelView view;

    public ModelViewResponse(CardModelView view) {
        this.view = view;
    }

    public ModelViewResponse() {
    }

    public CardModelView getView() {
        return view;
    }

    public void setView(CardModelView view) {
        this.view = view;
    }

}
