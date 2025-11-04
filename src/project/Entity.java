package project;

public abstract class Entity implements Character {
    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attackPower;
    protected int dodgeChance;

    public Entity(String name, int maxHp, int attackPower, int dodgeChance) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackPower = attackPower;
        this.dodgeChance = dodgeChance;
    }

    @Override
    public void attack(Entity target) {
        if (target.isDodged()) {
            System.out.println("⚡️ " + target.getName() + "이(가) 회피했다!!!!!!!!!!!!!!!!! (데미지: 0)\n");
            return;
        }

        int damage = this.attackPower;
        System.out.println("⚔️ " + this.name + "이(가) " + target.getName() + "을(를) 공격! **" + damage + "**의 데미지!");
        target.takeDamage(damage);
    }

    @Override
    public boolean isDodged() {
        double randomValue = Math.random();
        if (randomValue < (this.dodgeChance / 100.0)) {
            return true;
        }
        return false;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
        System.out.println("    [잔여 HP] " + this.name + "의 현재 HP: " + this.hp);
        System.out.println();
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }
}