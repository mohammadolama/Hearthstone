package Server.Model.Cards;

import Server.Controller.Actions.CardVisitors.Visitor;
import Server.Controller.Manager.Managers;
import Server.Model.Enums.Rarity;
import Server.Model.Enums.Type;
import Server.Model.Heros.Hero;
import Server.Model.Interface.Character;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
public class Gearblade extends Weapon {

    public Gearblade() {
        setName("gearblade");
        setManaCost(2);
        setDamage(2);
        setDurability(3);
        setHealthRestore(0);
        setPrice(20);
        setType(Type.Weapon);
        setHeroClass("Neutral");
        setRarity(Rarity.Common);
        setDescription(null);
        setContiniousAction(false);
        setAttackRestore(0);
        setAttributes(new ArrayList<>());
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        visitor.visitGearblade(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy, managers);
    }
}
