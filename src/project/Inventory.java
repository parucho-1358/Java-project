package project;

import java.util.HashMap;
import java.util.Map;


//  플레이어의 인벤토리를 관리하는 클래스
//  Map 컬렉션 프레임 워크 사용
public class Inventory {

    /**
     * 아이템의 이름(String)을 Key로, 아이템의 개수(Integer)를 Value로 저장하는 Map.
     */
    private Map<String, Integer> items;

    public Inventory() {
        this.items = new HashMap<>();
    }
    
    
     // 인벤토리에 아이템을 1개 추가합니다.
     
    public void addItem(String itemName) {
        // 기존 개수를 가져와 +1 한 후 저장합니다.
        items.put(itemName, items.getOrDefault(itemName, 0) + 1);
    }

    /**
     * 해당 아이템을 1개 이상 가지고 있는지 확인합니다.
     * @param itemName 확인할 아이템의 이름
     * @return 1개 이상 소지하고 있으면 true, 아니면 false
     */
    public boolean hasItem(String itemName) {
        return items.getOrDefault(itemName, 0) > 0;
    }

    /**
     * 인벤토리에서 아이템을 1개 사용(개수 감소)합니다.
     * @param itemName 사용할 아이템의 이름
     */
    public void useItem(String itemName) {
        // 개수를 1 감소시켜 Map에 다시 저장합니다. (포션을 사용해도 갯수가 줄지 않는 버그 수정)
        items.put(itemName, items.get(itemName) - 1);
    }

  //  인벤토리 보이기.
    public void showInventory() {
        System.out.println("\n--- 인벤토리 ---");

        // 1. 작은 포션 (고정 표시)
        int smallPotionCount = items.getOrDefault("작은 포션", 0);
        System.out.print("1. 작은 포션 (고정)");
        if (smallPotionCount == 0) {
            System.out.println(" - 작은 포션이 없습니다!");
        } else {
            System.out.println(" x" + smallPotionCount);
        }

        // 2. 큰 포션 (고정 표시)
        int largePotionCount = items.getOrDefault("큰 포션", 0);
        System.out.print("2. 큰 포션 (고정)");
        if (largePotionCount == 0) {
            System.out.println(" - 큰 포션이 없습니다!");
        } else {
            System.out.println(" x" + largePotionCount);
        }

        // 3. 기타 전리품 (1개 이상인 경우에만 동적 표시)
        int itemIndex = 3;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String itemName = entry.getKey();
            int itemCount = entry.getValue();

            if (itemCount > 0 && !itemName.equals("작은 포션") && !itemName.equals("큰 포션")) {
                System.out.println(itemIndex + ". " + itemName + " x" + itemCount);
                itemIndex++;
            }
        }
        System.out.println("----------------");
    }
}