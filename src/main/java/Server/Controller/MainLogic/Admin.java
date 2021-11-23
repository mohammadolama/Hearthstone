package Server.Controller.MainLogic;

import Server.Controller.Manager.DeckReaderManager;
import Server.Controller.Manager.Multiplayer;
import Server.Model.*;
import Server.Model.Cards.Card;
import Server.Model.Cards.Weapon;
import Server.Model.Enums.Carts;
import Server.Model.Enums.Heroes;
import Server.Model.Heros.Hero;

import java.util.*;

public class Admin {
    private static Admin admin;
    private final LogInSignUp logInSignUp;
    private final ArrayList<Requirements> deckReaderWaitings = new ArrayList<>();
    private final ArrayList<Requirements> multiplayerWaitings = new ArrayList<>();

    private Admin() {
        logInSignUp = new LogInSignUp();
    }

    public static Admin getInstance() {
        if (admin == null) {
            admin = new Admin();
        }
        return admin;
    }

    public void unlockHero(Player player) {
        Hero.HeroAdder(player);
        saveAndUpdate(player);
    }

    public void Log(String log, Player player) {
        LOGGER.playerlog(player, log);
    }

    public String signUp(String username, String password) {
        return logInSignUp.create(username, password);
    }


    public String logIn(String username, String password, ClientHandler clientHandler) {
        return logInSignUp.check(username, password, clientHandler);
    }


    public void deleteAccount(Player player) {
        logInSignUp.DeleteAccount(player);
    }

    public void logOut(Player player) {
        Log("Sign out ", player);
        saveAndUpdate(player);
    }

    public void selectFirstHero(String string, Player player) {
        HeroLogic.updateFirstHero(string, player);
        playerFirstUpdate(string, player);
    }

    private void playerFirstUpdate(String string, Player player) {
        Log(String.format("Select : %s as first selected hero", string.toUpperCase()), player);
        player.setNewToGame(false);
        List<Heroes> ar1 = player.getPlayerHeroes();
        if (player.getPlayerHeroes() == null) ar1 = new ArrayList<>();
        ar1.add(Heroes.valueOf(string));
        player.setPlayerHeroes(ar1);
        DataBaseManagment.savePlayer(player);
    }

    public boolean createNewDeck(Player player) {
        return player.getAllDecks().size() < 12;
    }

    public void saveAndUpdate(Player player) {
        DataBaseManagment.savePlayer(player);
    }


    public String buyCard(String name, Player player) {
        if (Shop.Buy(name.toLowerCase(), player)) {
            Log(String.format("Buy : %s  is added to purchased cards .", name), player);
            saveAndUpdate(player);
            return "ok";
        }
        return "gold";
    }

    public String sellCard(String name, Player player) {
        if (canBeSold(name, player)) {
            Shop.Sell(name.toLowerCase(), player);
            Log(String.format("Sell : %s  is removed from  purchased cards .", name), player);
            saveAndUpdate(player);
            return "ok";
        }
        return "reject";
    }

    public boolean canBeSold(String name, Player player) {
        return Shop.CanBeSold(name.toLowerCase(), player);
    }

    public ArrayList<CardModelView> properCards(int i, Player player) {
        ArrayList<Card> ar;
        if (i == 1) {
            ar = Shop.Buyable(player);
        } else if (i == 2) {
            ar = Shop.Sellable(player);
        } else {
            ar = DeckLogic.allCards();
        }
        return modelList(ar);
    }

    public ArrayList<CardModelView> modelList(ArrayList<Card> list) {
        CardModelViewGetter cd = new CardModelViewGetter();
        ArrayList<CardModelView> list1 = new ArrayList<>();
        for (Card card : list) {
            list1.add(cd.getPureViewModelOf(card.getName(), card));
        }
        return list1;
    }

    public synchronized String createDeck(String name, ArrayList<Carts> selectedCards, String heroName, Player player) {
        DeckLogic d = new DeckLogic();
        return d.createDeck(name, selectedCards, heroName, player);
    }

    public synchronized String changeDeck(DeckModel deck, ArrayList<Carts> selectedCards, String heroName, String previous, String current, Player player) {
        DeckLogic d = new DeckLogic();
        return d.changeDeck(deck, selectedCards, heroName, previous, current, player);
    }

    public void setSelectedDeck(DeckModel deck, Player player) {
        Deck deck1 = player.getAllDecks().get(deck.getName());
        player.setSelectedDeck(deck1);
        saveAndUpdate(player);
    }

    public String removeDeck(DeckModel selectedDeck, Player player) {
        player.getAllDecks().remove(selectedDeck.getName());
        Log(String.format("Delete : deck %s is deleted.", selectedDeck.getName()), player);
        saveAndUpdate(player);
        if (player.getSelectedDeck() != null && selectedDeck.getName().equalsIgnoreCase(player.getSelectedDeck().getName())) {
            player.setSelectedDeck(null);
            saveAndUpdate(player);
            return "ok1";
        }
        return "ok2";
    }

    public synchronized ArrayList<String> bestDecks(Player player) {
        return DeckLogic.bestDeck(player);
    }

    public ArrayList<InfoPassive> generatePassive() {
        return InfoPassive.randomGenerate(InfoPassive.infoPassiveCreator());
    }

    public String checkNecessary(Player player) {
        if (player.getSelectedDeck() == null) {
            return "You must choose a deck, first";
        }
        if (player.getSelectedDeck().getDeck().size() < 15 || player.getSelectedDeck().getDeck().size() > 30) {
            return "notEnoughCard.Please go to Collection.";
        }
        return "ok";
    }

    public CardModelView getWeaponViewModel(Weapon weapon) {
        CardModelViewGetter cd = new CardModelViewGetter();
        return cd.getWeaponViewModel(weapon);
    }

    public CardModelView getPureViewModelOf(String string) {
        CardModelViewGetter cd = new CardModelViewGetter();
        Card cards = getCardOf(string.toLowerCase());
        return cd.getPureViewModelOf(string, cards, null);
    }

    public ArrayList<CardModelView> threeCardChoose(Player player) {
        ArrayList<CardModelView> list = new ArrayList<>();
        ArrayList<Card> list1 = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(list1);

        list.add(getPureViewModelOf(list1.get(0).getName().toLowerCase()));
        list.add(getPureViewModelOf(list1.get(1).getName().toLowerCase()));
        list.add(getPureViewModelOf(list1.get(2).getName().toLowerCase()));
        return list;
    }

    public CardModelView changeCard(String card1, String card2, String card3, Player player) {
        ArrayList<Card> list = DeckLogic.UpdateDeck(player.getSelectedDeck().getDeck());
        Collections.shuffle(list);
        for (Card card : list) {
            if (!card.getName().equalsIgnoreCase(card1) && !card.getName().equalsIgnoreCase(card2) && !card.getName().equalsIgnoreCase(card3)) {
                return (getPureViewModelOf(card.getName().toLowerCase()));
            }
        }
        return null;
    }


    public void updateDeckStates(int i, Player player) {
        player.getAllDecks().get(player.getSelectedDeck().getName()).setTotalPlays(player.getSelectedDeck().getTotalPlays() + 1);
        if (i == 1) {
            player.getAllDecks().get(player.getSelectedDeck().getName()).setTotalWins(player.getSelectedDeck().getTotalWins() + 1);
        }
    }


    public void updateCardUsageTime(String name, Player player) {
        Map<String, Deck> allDecks = player.getAllDecks();
        Deck deck = allDecks.get(player.getSelectedDeck().getName());
        Map<String, Integer> usedTimes1 = deck.getUsedTimes();
        if (usedTimes1.containsKey(name.toLowerCase())) {
            usedTimes1.replace(name, usedTimes1.get(name) + 1);
        }
    }


    public Card getCardOf(String name) {
        return DeckLogic.getCardOf(name.toLowerCase());
    }

    public PlayerModel getPlayerModel(Player player) {
        String st = null;
        if (player.getSelectedDeck() != null) {
            st = player.getSelectedDeck().getName();
        }
        return new PlayerModel(player.getUsername(), player.getExp(), player.getLevel(),
                player.getMoney(), st);
    }

    public DeckModel getDeckModel(Deck deck) {
        Carts mostused = DeckLogic.mostUsedCard(deck);
        double avg = DeckLogic.avarageMana(deck);
        double winrate = DeckLogic.winRate(deck);
        return new DeckModel(deck.getName(), deck.getDeck(), deck.getHero().getName(), mostused,
                deck.getTotalPlays(), deck.getTotalWins(), avg, winrate, deck.getCup());

    }

    public synchronized HashMap<String, DeckModel> playerDecks(Player player) {
        HashMap<String, DeckModel> map = new HashMap<>();
        for (Map.Entry<String, Deck> entry : player.getAllDecks().entrySet()) {
            DeckModel deckModel = getDeckModel(entry.getValue());
            map.put(entry.getKey(), deckModel);
        }
        return map;
    }

    public synchronized void addDeckReader(Requirements requirements) {
        deckReaderWaitings.add(requirements);
        if (deckReaderWaitings.size() >= 2) {
            DeckReaderManager dr = new DeckReaderManager(deckReaderWaitings.get(0), deckReaderWaitings.get(1));
            deckReaderWaitings.get(0).getClientHandler().setGameManager(dr);
            deckReaderWaitings.get(1).getClientHandler().setGameManager(dr);
            deckReaderWaitings.get(0).getClientHandler().notifyStartGame();
            deckReaderWaitings.get(1).getClientHandler().notifyStartGame();
            deckReaderWaitings.remove(0);
            deckReaderWaitings.remove(0);
        }
    }

    public synchronized void addMultiplayer(Requirements requirements) {
        multiplayerWaitings.add(requirements);
        if (multiplayerWaitings.size() >= 2) {
            ArrayList<Card> list1 = DeckLogic.UpdateDeck(multiplayerWaitings.get(0).getClientHandler().getPlayer().getSelectedDeck().getDeck());
            ArrayList<Card> list2 = DeckLogic.UpdateDeck(multiplayerWaitings.get(1).getClientHandler().getPlayer().getSelectedDeck().getDeck());
            Multiplayer om = new Multiplayer(multiplayerWaitings.get(0), multiplayerWaitings.get(1), list1, list2);
            multiplayerWaitings.get(0).getClientHandler().setGameManager(om);
            multiplayerWaitings.get(1).getClientHandler().setGameManager(om);
            multiplayerWaitings.get(0).getClientHandler().notifyStartGame();
            multiplayerWaitings.get(1).getClientHandler().notifyStartGame();
            multiplayerWaitings.remove(0);
            multiplayerWaitings.remove(0);
        }
    }

}


