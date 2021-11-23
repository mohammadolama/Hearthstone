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
public class LightforgedBlessing extends Spell {

    public LightforgedBlessing() {
        setName("lightforgedblessing");
        setDescription("Restore 2Hp to friendly Minions.");
        setManaCost(2);
        setType(Type.Spell);
        setHeroClass("Neutral");
        setRarity(Rarity.Rare);
        setPrice(20);
        setAttributes(new ArrayList<>());
        setContiniousAction(false);
        setHealthRestore(2);
        setAttackRestore(0);
        setUsageTimes(1);
        setManaSpendOnSth(0);
    }

    @Override
    public void accept(Visitor visitor, Character target, ArrayList<Card> myDeck, ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck, ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Hero friendly, Hero enemy, Managers managers) {
        visitor.visitLightforgedBlessing(this, target, myDeck, myHand, myPlayed, targetDeck, targetHand, targetPlayed, friendly, enemy, managers);
    }
}
