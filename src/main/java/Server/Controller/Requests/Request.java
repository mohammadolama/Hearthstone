package Server.Controller.Requests;

import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.Manager.Managers;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.PrintWriter;
import java.util.Scanner;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LoginRequest.class, name = "login"),
        @JsonSubTypes.Type(value = SignupRequest.class, name = "signup"),
        @JsonSubTypes.Type(value = LogoutRequest.class, name = "logout"),
        @JsonSubTypes.Type(value = DeleteAccountRequest.class, name = "deleteaccount"),
        @JsonSubTypes.Type(value = ExitRequest.class, name = "exit"),
        @JsonSubTypes.Type(value = UnlockHeroRequest.class, name = "unlockhero"),
        @JsonSubTypes.Type(value = BestDecksRequest.class, name = "bestdecks"),
        @JsonSubTypes.Type(value = LogRequest.class, name = "log"),
        @JsonSubTypes.Type(value = ChangeCardRequest.class, name = "changecard"),
        @JsonSubTypes.Type(value = AylarActionRequest.class, name = "aylaraction"),
        @JsonSubTypes.Type(value = CreateGameRequest.class, name = "creategame"),
        @JsonSubTypes.Type(value = FinishGameRequest.class, name = "finishgame"),
        @JsonSubTypes.Type(value = PlayerHerosRequest.class, name = "playerheros"),
        @JsonSubTypes.Type(value = ValidateDeckNameRequest.class, name = "validdeck"),
        @JsonSubTypes.Type(value = CreateDeckRequest.class, name = "createdeck"),
        @JsonSubTypes.Type(value = ChangeDeckRequest.class, name = "changedeck"),
        @JsonSubTypes.Type(value = SelectedDeckCardsRequest.class, name = "selecteddeckcards"),
        @JsonSubTypes.Type(value = BuyCardRequest.class, name = "buycard"),
        @JsonSubTypes.Type(value = SellCardRequest.class, name = "sellcard"),
        @JsonSubTypes.Type(value = PureModelViewRequest.class, name = "puremodelview"),
        @JsonSubTypes.Type(value = ProperCardsRequest.class, name = "propercard"),
        @JsonSubTypes.Type(value = PurchasedCardsRequest.class, name = "purchasedcard"),
        @JsonSubTypes.Type(value = NotPurchasedCardsRequest.class, name = "notpurchasedcard"),
        @JsonSubTypes.Type(value = PlayerDecksRequest.class, name = "playerdecks"),
        @JsonSubTypes.Type(value = CreateNewDeckRequest.class, name = "createnewdeck"),
        @JsonSubTypes.Type(value = UpdateDrawingPanelRequest.class, name = "updatedrawingpanel"),
        @JsonSubTypes.Type(value = SelectDeckRequest.class, name = "selectdeck"),
        @JsonSubTypes.Type(value = SelectedDeckRequest.class, name = "selecteddeck"),
        @JsonSubTypes.Type(value = FirstHeroRequest.class, name = "firsthero"),
        @JsonSubTypes.Type(value = WantToPlayRequest.class, name = "wanttoplay"),
        @JsonSubTypes.Type(value = PlayerModelRequest.class, name = "playermodel"),
        @JsonSubTypes.Type(value = RemoveDeckRequest.class, name = "removedeck"),
        @JsonSubTypes.Type(value = SaveRequest.class, name = "save"),
        @JsonSubTypes.Type(value = EndTurnRequest.class, name = "endturn"),
        @JsonSubTypes.Type(value = WalletRequest.class, name = "wallet"),
        @JsonSubTypes.Type(value = PassiveRequest.class, name = "passive"),
        @JsonSubTypes.Type(value = CollectionRequest.class, name = "collection"),
        @JsonSubTypes.Type(value = BoardPanelRequest.class, name = "boardpanel"),
        @JsonSubTypes.Type(value = TargetListRequest.class, name = "targetlist"),
        @JsonSubTypes.Type(value = CanBePlayedRequest.class, name = "canbeplayed"),
        @JsonSubTypes.Type(value = DeckModelRequest.class, name = "deckmodel"),
        @JsonSubTypes.Type(value = PriceRequest.class, name = "price"),
        @JsonSubTypes.Type(value = ThreeCardRequest.class, name = "threecard"),
        @JsonSubTypes.Type(value = HeroPowerCanBePlayedRequest.class, name = "heropowercanbeplayed"),
        @JsonSubTypes.Type(value = HeroCanAttackRequest.class, name = "herocanattack"),
        @JsonSubTypes.Type(value = PlayHeroPowerRequest.class, name = "heropower"),
        @JsonSubTypes.Type(value = PlayCardRequest.class, name = "playcard"),
        @JsonSubTypes.Type(value = CanDoActionRequest.class, name = "candoaction"),
        @JsonSubTypes.Type(value = AttackRequest.class, name = "attack"),
        @JsonSubTypes.Type(value = ActionChartRequest.class, name = "actionchart"),
        @JsonSubTypes.Type(value = CancleGameRequest.class, name = "cancle"),
})
public interface Request {
    void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers);
}
