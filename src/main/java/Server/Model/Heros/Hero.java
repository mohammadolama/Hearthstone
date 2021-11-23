package Server.Model.Heros;


import Server.Controller.Actions.SPVisitor.PowerVisitor;
import Server.Controller.Manager.Managers;
import Server.Model.Cards.Card;
import Server.Model.Cards.Weapon;
import Server.Model.Enums.Carts;
import Server.Model.Enums.Heroes;
import Server.Model.HeroPowers.HeroPower;
import Server.Model.Interface.Character;
import Server.Model.Player;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "model")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Hunter.class, name = "hunter"),
        @JsonSubTypes.Type(value = Warlock.class, name = "warlock"),
        @JsonSubTypes.Type(value = Mage.class, name = "mage"),
        @JsonSubTypes.Type(value = Rogue.class, name = "rogue"),
        @JsonSubTypes.Type(value = Priest.class, name = "priest"),
})
@JsonIgnoreProperties({"specialPower", "heroPower", "maxLife", "weapon", "damage"})
@Entity
public abstract class Hero implements Character, Cloneable {
    @Id
    private String name;
    @Column
    private int health;
    @Column
    private int maxHealth;
    @Column
    private boolean canAttack;
    @Column
    private int damage;
    @Transient
    private HeroPower heroPower;
    @Column
    private int heroPowerManaCost;
    @Column
    private boolean powerNeedFriendlyTarget;
    @Column
    private boolean powerNeedEnemyTarget;
    @Column
    private int defence;
    @Transient
    private Weapon weapon;

    public Hero() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void HeroAdder(Player player) {
        List<Heroes> ar = player.getPlayerHeroes();
        if (!ar.contains(Heroes.mage))
            ar.add(Heroes.mage);
        if (!ar.contains(Heroes.warlock))
            ar.add(Heroes.warlock);
        if (!ar.contains(Heroes.rogue))
            ar.add(Heroes.rogue);
        if (!ar.contains(Heroes.priest))
            ar.add(Heroes.priest);
        if (!ar.contains(Heroes.hunter))
            ar.add(Heroes.hunter);
        player.setPlayerHeroes(ar);
        List<Carts> ar2 = player.getPlayerCarts();
        if (!ar2.contains(Carts.benyamin))
            ar2.add(Carts.benyamin);
        if (!ar2.contains(Carts.polymorph))
            ar2.add(Carts.polymorph);
        if (!ar2.contains(Carts.aylar))
            ar2.add(Carts.aylar);
        if (!ar2.contains(Carts.faeze))
            ar2.add(Carts.faeze);
        if (!ar2.contains(Carts.shahryar))
            ar2.add(Carts.shahryar);
        player.setPlayerCarts(ar2);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public HeroPower getHeroPower() {
        return heroPower;
    }

    public void setHeroPower(HeroPower heroPower) {
        this.heroPower = heroPower;
    }

    public int getHeroPowerManaCost() {
        return heroPowerManaCost;
    }

    public void setHeroPowerManaCost(int heroPowerManaCost) {
        this.heroPowerManaCost = heroPowerManaCost;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        if (weapon != null) {
            this.weapon = weapon;
            this.canAttack = true;
            this.damage = weapon.getDamage();
        } else {
            this.weapon = null;
            this.canAttack = false;
            this.damage = 0;
        }
    }

    public boolean isPowerNeedFriendlyTarget() {
        return powerNeedFriendlyTarget;
    }

    public void setPowerNeedFriendlyTarget(boolean powerNeedFriendlyTarget) {
        this.powerNeedFriendlyTarget = powerNeedFriendlyTarget;
    }

    public boolean isPowerNeedEnemyTarget() {
        return powerNeedEnemyTarget;
    }

    public void setPowerNeedEnemyTarget(boolean powerNeedEnemyTarget) {
        this.powerNeedEnemyTarget = powerNeedEnemyTarget;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    @Override
    public int getAttack() {
        return getDamage();
    }

    @Override
    public void setAttack(int i) {
        setDamage(i);
    }

    @Override
    public int getLife() {
        return getHealth();
    }

    @Override
    public void setLife(int i) {
        setHealth(i);
    }

    @Override
    public int getMaxLife() {
        return getMaxHealth();
    }

    public abstract void accept(PowerVisitor visitor, Character target, ArrayList<Card> myDeck,
                                ArrayList<Card> myHand, ArrayList<Card> myPlayed, ArrayList<Card> targetDeck,
                                ArrayList<Card> targetHand, ArrayList<Card> targetPlayed, Managers managers);

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


