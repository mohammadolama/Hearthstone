package Server.Controller.Response;

import Server.Model.DeckModel;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("deckmodel")
public class DeckModelResponse implements Response {

    private DeckModel deckModel;

    public DeckModelResponse() {
    }

    public DeckModelResponse(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    public DeckModel getDeckModel() {
        return deckModel;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
    }
}
