package Client.Controller;

import Client.Model.*;
import Client.Model.Enums.Carts;
import Client.Model.Enums.Heroes;

import java.util.ArrayList;
import java.util.HashMap;

public class Responses {

    private static final Responses responses = new Responses();
    private CardModelView cardModelView;
    private DeckModel deckModel, selectedDeck;
    private ArrayList<CardModelView> modelviewList = new ArrayList<>(), purchasedCards = new ArrayList<>(),
            notPurchasedCards = new ArrayList<>(), collectionModels = new ArrayList<>();
    private ArrayList<ActionModel> friendlyModel = new ArrayList<>();
    private ArrayList<ActionModel> enemyModel = new ArrayList<>();
    private long price, wallet;
    private String className, heroName;
    private ArrayList<InfoPassive> passiveList = new ArrayList<>();
    private HashMap<String, DeckModel> decks = new HashMap<>();
    private ArrayList<Heroes> heroesList = new ArrayList<>();
    private ArrayList<Carts> collectionList = new ArrayList<>();
    private ArrayList<String> bestDecks = new ArrayList<>();
    private ArrayList<Integer> targets = new ArrayList<>();
    private boolean canBePlayed, heroCanAttack, canDoAction;
    private int heroPowerCanBePlayed;
    private PlayerModel player;
    public GameState board;

    public static Responses getInstance() {
        return responses;
    }

    public HashMap<String, DeckModel> getDecks() {
        return decks;
    }

    public void setDecks(HashMap<String, DeckModel> decks) {
        this.decks = decks;
    }

    public ArrayList<InfoPassive> getPassiveList() {
        return passiveList;
    }

    public void setPassiveList(ArrayList<InfoPassive> passiveList) {
        this.passiveList = passiveList;
    }

    public DeckModel getSelectedDeck() {
        return selectedDeck;
    }

    public void setSelectedDeck(DeckModel selectedDeck) {
        this.selectedDeck = selectedDeck;
    }

    public DeckModel getDeckModel() {
        return deckModel;
    }

    public ArrayList<Carts> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(ArrayList<Carts> collectionList) {
        this.collectionList = collectionList;
    }

    public void setDeckModel(DeckModel deckModel) {
        this.deckModel = deckModel;
    }

    public CardModelView getCardModelView() {
        return cardModelView;
    }

    public ArrayList<CardModelView> getPurchasedCards() {
        return purchasedCards;
    }

    public void setPurchasedCards(ArrayList<CardModelView> purchasedCards) {
        this.purchasedCards = purchasedCards;
    }

    public ArrayList<CardModelView> getNotPurchasedCards() {
        return notPurchasedCards;
    }

    public void setNotPurchasedCards(ArrayList<CardModelView> notPurchasedCards) {
        this.notPurchasedCards = notPurchasedCards;
    }

    public void setCardModelView(CardModelView cardModelView) {
        this.cardModelView = cardModelView;
    }

    public ArrayList<CardModelView> getModelviewList() {
        return modelviewList;
    }

    public void setModelviewList(ArrayList<CardModelView> modelviewList) {
        this.modelviewList = modelviewList;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getWallet() {
        return wallet;
    }

    public void setWallet(long wallet) {
        this.wallet = wallet;
    }

    public ArrayList<Heroes> getHeroesList() {
        return heroesList;
    }

    public ArrayList<CardModelView> getCollectionModels() {
        return collectionModels;
    }

    public void setCollectionModels(ArrayList<CardModelView> collectionModels) {
        this.collectionModels = collectionModels;
    }

    public boolean isCanBePlayed() {
        return canBePlayed;
    }

    public void setCanBePlayed(boolean canBePlayed) {
        this.canBePlayed = canBePlayed;
    }

    public int getHeroPowerCanBePlayed() {
        return heroPowerCanBePlayed;
    }

    public void setHeroPowerCanBePlayed(int heroPowerCanBePlayed) {
        this.heroPowerCanBePlayed = heroPowerCanBePlayed;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public boolean isHeroCanAttack() {
        return heroCanAttack;
    }

    public void setHeroCanAttack(boolean heroCanAttack) {
        this.heroCanAttack = heroCanAttack;
    }

    public boolean isCanDoAction() {
        return canDoAction;
    }

    public void setCanDoAction(boolean canDoAction) {
        this.canDoAction = canDoAction;
    }

    public void setHeroesList(ArrayList<Heroes> heroesList) {
        this.heroesList = heroesList;
    }

    public ArrayList<String> getBestDecks() {
        return bestDecks;
    }

    public void setBestDecks(ArrayList<String> bestDecks) {
        this.bestDecks = bestDecks;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public ArrayList<Integer> getTargets() {
        return targets;
    }

    public void setTargets(ArrayList<Integer> targets) {
        this.targets = targets;
    }

    public ArrayList<ActionModel> getFriendlyModel() {
        return friendlyModel;
    }

    public void setFriendlyModel(ArrayList<ActionModel> friendlyModel) {
        this.friendlyModel = friendlyModel;
    }

    public ArrayList<ActionModel> getEnemyModel() {
        return enemyModel;
    }

    public void setEnemyModel(ArrayList<ActionModel> enemyModel) {
        this.enemyModel = enemyModel;
    }
}
