package project;

public abstract class Monster extends Entity {

    public Monster(String name, int maxHp, int attackPower, int dodgeChance) {
        super(name, maxHp, attackPower, dodgeChance);
    }
}