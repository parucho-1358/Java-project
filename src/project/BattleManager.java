package project;

public class BattleManager {
    private Player player;
    private InputHandler inputHandler;

    public BattleManager(Player player, InputHandler inputHandler) {
        this.player = player;
        this.inputHandler = inputHandler;
    }

    private Entity spawnMonster() {
        double rand = Math.random();
        if (rand < 0.1) {
            return new Boss();
        } else if (rand < 0.5) {
            return new Goblin();
        } else {
            return new Slime();
        }
    }

    public void startBattle() {
        Entity monster = spawnMonster();
        System.out.println("\n*** " + monster.getName() + "이(가) 나타났다! ***");

        boolean playerTurn = true;
        boolean battleOver = false;

        while (player.isAlive() && monster.isAlive() && !battleOver) {

            System.out.println("#########################################");
            System.out.println("  ➡️ **" + player.getName() + "** HP: " + player.getHp() + " | **" + monster.getName() + "** HP: " + monster.getHp());
            System.out.println("#########################################\n");

            if (playerTurn) {
                System.out.println("🌟 [플레이어의 턴]");
                System.out.println("1. 공격하기");
                System.out.println("2. 인벤토리 열기");
                System.out.println("3. 도망가기");

                try { // InvalidInputException 처리를 위한 try 블록 시작
                    int choice = inputHandler.getBattleInput(); 

                    switch (choice) {
                        case 1:
                            player.attack(monster);
                            playerTurn = false;
                            break;
                        case 2:
                            player.showInventory();
                            // 인벤토리 아이템 번호를 받습니다. (잘못된 입력 시 예외 발생)
                            int itemChoice = inputHandler.getInventoryChoiceInput("사용할 아이템 번호를 입력하세요 (0: 취소): ");

                            if (itemChoice == 0) {
                                System.out.println("인벤토리를 닫습니다.");
                                break; // 턴이 넘어가지 않음
                            } else if (itemChoice == 1) {
                                if (player.hasItem("작은 포션")) {
                                    player.useItem("작은 포션");
                                    player.heal(40);
                                    playerTurn = false;
                                } else {
                                    System.out.println("작은 포션이 없습니다! (턴이 소모되지 않습니다.)");
                                }
                            } else if (itemChoice == 2) {
                                if (player.hasItem("큰 포션")) {
                                    player.useItem("큰 포션");
                                    player.heal(70);
                                    playerTurn = false;
                                } else {
                                    System.out.println("큰 포션이 없습니다! (턴이 소모되지 않습니다.)");
                                }
                            } else {
                                System.out.println("전투 중에는 해당 아이템을 사용할 수 없습니다! (턴이 소모되지 않습니다.)");
                            }
                            break;

                        case 3:
                            System.out.println("\n*** 무사히 도망쳤다! ***");
                            battleOver = true;
                            break;
                    } // end switch
                } catch (InvalidInputException e) { // InvalidInputException 처리
                    System.out.println("❌ " + e.getMessage());
                    // playerTurn이 true인 상태로 루프가 다시 시작되어 다시 입력 기회를 줍니다.
                }

            } else {
                // 몬스터 턴
                System.out.println("😈 [몬스터의 턴]");
                System.out.println("몬스터가 어떤 행동을 할지 고민중입니다...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println();
                monster.attack(player);
                playerTurn = true;
            }
        }

        // --- 전투 종료 ---
        System.out.println("\n*** 전투 종료 ***");

        if (battleOver) {
            System.out.println("전투에서 벗어났습니다.");
        } else if (player.isAlive()) {
            System.out.println(monster.getName() + "을(를) 물리쳤습니다! 승리!");
            System.out.println("\n전리품을 확인합니다...");

            if (Math.random() < 0.50) {
                player.addItem("작은 포션");
                System.out.println(">> 작은 포션을 획득했습니다!");
            }

            if (Math.random() < 0.20) {
                player.addItem("큰 포션");
                System.out.println(">> 큰 포션을 획득했습니다!");
            }

            if (Math.random() < 0.30) {
                String lootName = monster.getName() + "의 전리품";
                player.addItem(lootName);
                System.out.println(">> " + lootName + "을(를) 획득했습니다!");
            }

        } else {
            System.out.println("패배했습니다... 게임 오버!");
            System.exit(0);
        }

        player.resetHp();
        System.out.println("체력이 모두 회복되었습니다. (현재 HP: " + player.getHp() + ")");
    }
}