# ☕ Java 텍스트 기반 RPG 미니 프로젝트

**간단한 턴제 전투와 아이템 수집, 객체 지향 원칙(SRP)을 적용한 텍스트 기반의 RPG 미니 프로젝트입니다.**


---
## 🎮 주요 기능

* **턴제 전투 시스템:** 플레이어와 몬스터가 턴을 주고받으며 전투합니다.
* **다양한 몬스터:** Slime, Goblin, Boss 등 확률적으로 몬스터 스폰 
* **아이템 및 인벤토리:** 전투 승리 시 아이템을 획득하며, Map을 이용해 효율적으로 인벤토리를 관리합니다.
* **포션 사용:** 전투 중 포션을 사용하여 Player의 체력을 회복할 수 있습니다.
* **예외 처리:** 사용자 정의 예외(InvalidInputException)를 통해 잘못된 입력을 안정적으로 처리합니다.


### 클래스 다이어그램 (UML)

<img width="3948" height="2428" alt="제목 없음 (27)" src="https://github.com/user-attachments/assets/04e3a195-33bc-4a3a-beca-e2297f68c553" />

---

## 🖥️ 실제 실행 화면


1) ## 전투 (Slime , Goblin , Boss -> 랜덤 스폰) Player turn , Monster turn
   attack(공격) , isDodged(회피)
![Playlist](https://github.com/user-attachments/assets/e81f8623-de26-4ea9-a79c-5f3f92cacfeb)
<br/>

2) ## 인벤토리 (포션 , 전리품)
   포션 사용 , 전리품 획득
![인벤토리 , 회복](https://github.com/user-attachments/assets/e6c2f014-2a30-4209-9ef0-2076fb776a2c)
<br/>


3) ## 잘못된 입력을 했을 경우 (Exception)
    메인 메뉴 (잘못된 숫자 입력 )<br/>
    전투 메뉴 (잘못된 숫자 입력) <br/>
    인벤토리 메뉴 (잘못된 숫자 입력) <br/>
   + 숫자가 아닌 다른 타입의 입력을 받았을 경우도 처리 (String ...)
![예외처리](https://github.com/user-attachments/assets/32baf655-ee3f-47e2-b134-d73048f7c465)
<br/>

---

## 🎮 주요 기능

* **턴제 전투 시스템:** 플레이어와 몬스터가 턴을 주고받으며 전투합니다.
* **다양한 몬스터:** Slime, Goblin, Boss 등 확률적으로 몬스터 스폰 
* **아이템 및 인벤토리:** 전투 승리 시 아이템을 획득하며, Map을 이용해 효율적으로 인벤토리를 관리합니다.
* **포션 사용:** 전투 중 포션을 사용하여 Player의 체력을 회복할 수 있습니다.
* **예외 처리:** 사용자 정의 예외(InvalidInputException)를 통해 잘못된 입력을 안정적으로 처리합니다.

---

## 🏛️ 프로젝트 설계 및 핵심 개념

### 클래스 다이어그램 (UML)

```
implements
- Entity implements Character

extends
- Player extends Entity
- Monster (abstract) extends Entity
- Slime  extends Monster
- Goblin extends Monster
- Boss   extends Monster

has (composition)
- Player has Inventory
```

**초기 List 구조에서 Inventory 클래스를 분리하고, 예외 처리를 위한 <br/>
InvalidInputException을 추가하여 단일 책임 원칙(SRP)을 준수하는 구조로 리팩토링했습니다.**

### 상속과 인터페이스를 사용한 이유 (OOP)

**이 프로젝트는 객체 지향의 핵심인 상속과 인터페이스를 적극적으로 활용했습니다.**

#### Interface (Character.java):

* **이유:** Player와 Monster가 공통적으로 가져야 할 '행동(Behavior)'을 규격화하기 위해 사용했습니다.
* **적용:** attack(), isDodged() 등 필수 기능의 구현을 강제하여 설계의 일관성을 유지했습니다.

#### Inheritance (extends Entity):

* **이유:** 공통된 '속성'과 '상태'를 물려주기 위해 사용했습니다.
* **적용:** Player와 Monster는 모두 생명체(Entity)이므로, <br/> hp, name, attackPower 등의 필드를 코드 중복 없이 재사용하고 takeDamage()와 같은 공통 로직을 공유합니다.

#### Abstract Class (Entity, Monster):

* **이유:** 공통 로직은 제공하되, 세부 구현은 자식 클래스에게 맡기기 위해 사용했습니다.
* **적용:** Monster 클래스를 추상 클래스로 만들어, Slime, Goblin 등이 이를 구체화하도록 하여 확장성을 높였습니다. <br/>

``` 
한눈에 보기!
1. 인터페이스 (Character.java):

            목적: attack(), isDodged()라는 행동의 규격을 정의하여,
                  모든 전투 참여 객체(Entity를 상속받는 모든 것)가 이 행동을 구현하도록 강제합니다.

2. 추상 클래스 (Entity.java):

            목적: name, hp, attackPower 같은 공통 속성과 takeDamage()처럼 모든 캐릭터가
                 공유하는 기본 로직을 제공합니다. (단, Entity 그 자체는 객체로 만들 수 없습니다.)

3. 상속 (Player.java, Monster.java):

            목적: Player와 Monster는 Entity를 상속받아 기본 능력치와 공통 로직을 물려받아 사용하고,
                  각자의 고유한 기능(예: Player의 heal(), Inventory 사용)을 추가하여 확장합니다.
```


### 예외 처리 분리 (SOLID - SRP)

**"Exception 클래스는 따로 분리하여 재사용할 수 있는 구조. 단일 책임 원칙(SRP)을 적용하여 예외 처리 로직을 분리했습니다.**

* **As-Is (변경 전):** InputHandler.java가 입력 로직과 try-catch를 이용한 예외 처리(메시지 출력, 재입력)를 모두 담당했습니다.
* **To-Be (최종 코드):**

  * **InputHandler:** 유효하지 않은 입력 -> 직접 처리하지 않고 `throw new InvalidInputException(...)`을 통해 예외를 던짐
  * **Game / BattleManager:** InputHandler를 호출하는 상위 클래스에서 try-catch로 예외를 받아 -> 실제 예외를 처리(메시지 출력, 루프 재시작)
  * **InvalidInputException.java:** 재사용 가능한 사용자 정의 예외 클래스를 생성하여 코드를 분리했습니다.

### 컬렉션 프레임워크 (Map)

**왜 List가 아닌 Map을 사용했는가?**

* 초기 설계에서는 List을 사용했으나, 아이템 개수를 관리하고 검색하는 데 비효율적이었습니다.
* **Map<String, Integer> (HashMap)**을 사용하여, 아이템 이름(Key)을 통해 그 개수(Value)를 O(1)의 속도로 즉시 찾고 수정할 수 있도록 개선했습니다.
* 이 로직은 Inventory 클래스로 분리되어 관리됩니다.

---

## ⚙️ 겪었던 오류 및 해결 과정

### 1)인벤토리 아이템 개수 감소 버그 (Map 데이터 갱신 누락)
```
java public void useItem(String itemName) { int newCount = items.get(itemName) - 1; // !! ⚠️ newCount를 Map에 다시 저장하는 코드 누락 !! }
```
수정 ↓↓↓↓↓↓↓

```
java public void useItem(String itemName) { // 개수를 계산하고... // items.put()을 통해 Map의 Value를 최종 갱신합니다. items.put(itemName, items.get(itemName) - 1); }
```
items.get()을 통해 값을 가져와 연산한 후에는 반드시 **items.put(Key, NewValue)**를 호출하여 <br/>Map 내의 데이터를 최종적으로 갱신해주는 코드를 추가하여 해결했습니다.

### 2) 최대 체력 초과 회복 버그 (Boundary Check 누락)
```
java public void heal(int healAmount) { this.hp += healAmount; // Max HP 초과 }
```
수정 ↓↓↓↓↓↓↓
```
java public void heal(int healAmount) { this.hp += healAmount; if (this.hp > this.maxHp) { // 경계 조건 체크 this.hp = this.maxHp; // 최대치로 제한 } }
```
HP를 증가시킨 후 if (this.hp > this.maxHp) 조건문을 추가하여, 현재 HP가 최대 HP를 초과할 경우 HP를 maxHp로 강제로 설정

---
## 🚀 실행 방법

1. 이 저장소를 **Clone** 받습니다.
2. **Eclipse IDE**에서 `File > Import > General > Existing Projects into Workspace`를 선택합니다.
3. **Select root directory**에서 Clone 받은 `Java-project` 폴더를 선택하고 **Finish**를 누릅니다.
4. `src/project/Game.java` 파일을 찾아 **Run As > Java Application**으로 실행합니다.


--- 

## 추후 추가기능 예상

 1) **방어 로직** - 지금은 공격(attack)과 인벤토리 (inventory) 만 존재하지만 방어(defense)를 추가 
 2) **경험치 추가** - Player가 Mosnter를 잡았을경우 경험치 상승 (Level up!)
 3) **gold 시스템 추가** - Player가 전투에서 승리했을 경우 gold 획득
 4) **상점 시스템 추가**- 메인 메뉴에 1. 전투개시 + 2. 인벤토리 + new (3. 상점 ) + 4. 종료<br/>
                     + 판매 시스템 도입 - Monster 전리품 을 판매할 수 있음
 5) **몬스터 추가** - 현재는 3개의 Monster가 있지만 추가로 약한 몬스터~강한 몬스터 = 다양한 몬스터 추가 예정
 6) **몬스터 도감** - Player가 만난 Monster 들을 도감에 저장 (정보 저장 - hp , attackPower ,  isDodged ...)

## 마치며
   
이번 미니 프로젝트를 진행하며 단순한 텍스트 RPG 게임을 완성하는 것을 넘어, <br/> 
견고한 객체 지향 프로그래밍(OOP) 설계의 중요성을 깊이 있게 고민하고 적용해볼 수 있었습니다. <br/>

인터페이스와 추상클래스 그리고 상속등 복잡한 관계들을 다시한번 적립하고 공부할수 있는 시간이였고 <br/>
예외처리 try-catch 문을 사용해 예외가 발생할수 있는 부분을 미리 생각함과 동시에 <br/>
효율적인 처리를 위해 많은 고민을 했습니다 <br/>
또한, InvalidInputException을 통해 예외 처리 로직을 비즈니스 로직과 분리(SRP)하는 과정을 통해,<br/>
단순히 오류를 잡는 것을 넘어 책임을 명확히 분리하여 견고하고 유지보수하기 좋은 코드를 작성하는 예외 처리의 핵심 개념을 배울 수 있었습니다. <br/>

실제로 적용하고 부딪혔던 문제들을 해결해 나간 과정은 매우 의미 있고 소중한 학습 경험이었습니다.

