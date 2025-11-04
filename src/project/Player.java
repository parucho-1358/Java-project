package project;

public class Player extends Entity {

    private static final int PLAYER_DODGE_CHANCE = 20;

    
     // Player는 Inventory 객체에 인벤토리 관리를 위임합니다.

    private Inventory inventory;

    public Player(String name, int maxHp, int attackPower) {
        super(name, maxHp, attackPower, PLAYER_DODGE_CHANCE);
        this.inventory = new Inventory();
    }

    //  전투 후 체력 리셋
    public void resetHp() {
        this.hp = this.maxHp;
    }

    
    // 플레이어의 체력 회복 
     
    public void heal(int healAmount) {
        this.hp += healAmount;
        if (this.hp > this.maxHp) {
            this.hp = this.maxHp;
        }
        System.out.println("🧪 **+" + healAmount + "** HP 회복!");
        System.out.println("    [잔여 HP] " + this.name + "의 현재 HP: " + this.hp);
        System.out.println();
    }


    
    // 플레이어의 인벤토리에 아이템을 1개 추가합니다. (Inventory 객체에 위임)
     
    public void addItem(String itemName) {
        inventory.addItem(itemName);
    }

    
    //  플레이어가 해당 아이템을 1개 이상 가지고 있는지 확인합니다. (Inventory 객체에 위임)
     
    public boolean hasItem(String itemName) {
        return inventory.hasItem(itemName);
    }

    
     
    public void useItem(String itemName) {
        inventory.useItem(itemName);
    }

    
    //  플레이어의 통합 인벤토리를 출력합니다. (Inventory 객체에 위임)
     
    public void showInventory() {
        inventory.showInventory();
    }
}