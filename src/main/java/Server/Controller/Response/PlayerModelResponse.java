package Server.Controller.Response;

import Server.Model.PlayerModel;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("playermodel")
public class PlayerModelResponse implements Response {

    private PlayerModel playerModel;

    public PlayerModelResponse(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public PlayerModelResponse() {
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

}
