package Server.Controller.Requests;


import Server.Controller.MainLogic.Admin;
import Server.Controller.MainLogic.ClientHandler;
import Server.Controller.MainLogic.DeckLogic;
import Server.Controller.Manager.Managers;
import Server.Controller.Response.PurchasedCardsResponse;
import Server.Model.CardModelView;
import Server.Model.Cards.Card;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@JsonTypeName("purchasedcard")
public class PurchasedCardsRequest implements Request {
    ArrayList<CardModelView> purchasedCard;

    public PurchasedCardsRequest() {
    }

    @Override
    public void excute(Scanner inputStream, PrintWriter outputStream, ClientHandler clientHandler, ObjectMapper objectMapper, Managers managers) {
        purchasedCard = new ArrayList<>();
        for (Card card : DeckLogic.purchasedCards(clientHandler.getPlayer())) {
            purchasedCard.add(Admin.getInstance().getPureViewModelOf(card.getName()));
        }
        try {
            outputStream.println(objectMapper.writeValueAsString(new PurchasedCardsResponse(purchasedCard)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
