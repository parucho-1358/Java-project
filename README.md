# ☕ Java 텍스트 기반 RPG 미니 프로젝트

**간단한 턴제 전투와 아이템 수집, 객체 지향 원칙(SRP)을 적용한 텍스트 기반의 RPG 미니 프로젝트입니다.**

---

## 🖥️ 실제 실행 화면

**(실제 게임 플레이 GIF 또는 스크린샷을 여기에 삽입하세요)**

```
```

---

## 🎮 주요 기능

* **턴제 전투 시스템:** 플레이어와 몬스터가 턴을 주고받으며 전투합니다.
* **다양한 몬스터:** Slime, Goblin, Boss 등 다양한 몬스터가 확률적으로 스폰됩니다.
* **아이템 및 인벤토리:** 전투 승리 시 아이템을 획득하며, Map을 이용해 효율적으로 인벤토리를 관리합니다.
* **포션 사용:** 전투 중 포션을 사용하여 체력을 회복할 수 있습니다.
* **예외 처리:** 사용자 정의 예외(InvalidInputException)를 통해 잘못된 입력을 안정적으로 처리합니다.

---

## 🏛️ 프로젝트 설계 및 핵심 개념

### 클래스 다이어그램 (UML)

**(최종적으로 완성한 UML 클래스 다이어그램 이미지를 여기에 삽입하세요)**

```
```

**초기 List 구조에서 Inventory 클래스를 분리하고, 예외 처리를 위한 InvalidInputException을 추가하여 단일 책임 원칙(SRP)을 준수하는 구조로 리팩토링했습니다.**

### 상속과 인터페이스를 사용한 이유 (OOP)

**이 프로젝트는 객체 지향의 핵심인 상속과 인터페이스를 적극적으로 활용했습니다.**

#### Interface (Character.java):

* **이유:** Player와 Monster가 공통적으로 가져야 할 '행동(Behavior)'을 규격화하기 위해 사용했습니다.
* **적용:** attack(), isDodged() 등 필수 기능의 구현을 강제하여 설계의 일관성을 유지했습니다.

#### Inheritance (extends Entity):

* **이유:** 공통된 '속성(Attribute)'과 '상태(State)'를 물려주기 위해 사용했습니다.
* **적용:** Player와 Monster는 모두 생명체(Entity)이므로, hp, name, attackPower 등의 필드를 코드 중복 없이 재사용하고 takeDamage()와 같은 공통 로Ghd을 공유합니다.

#### Abstract Class (Entity, Monster):

* **이유:** 공통 로직은 제공하되, 세부 구현은 자식 클래스에게 맡기기 위해 사용했습니다.
* **적용:** Monster 클래스를 추상 클래스로 만들어, Slime, Goblin 등이 이를 구체화하도록 하여 확장성을 높였습니다.

### 예외 처리 분리 (SOLID - SRP)

**"Exception 클래스는 따로 분리하여 재사용할 수 있는 구조로 만들 것"이라는 요구사항에 따라, 단일 책임 원칙(SRP)을 적용하여 예외 처리 로직을 분리했습니다.**

* **As-Is (변경 전):** InputHandler.java가 입력 로직과 try-catch를 이용한 예외 처리(메시지 출력, 재입력)를 모두 담당했습니다.
* **To-Be (최종 코드):**

  * **InputHandler:** 유효하지 않은 입력이 들어오면, 직접 처리하지 않고 `throw new InvalidInputException(...)`을 통해 예외를 던지는 책임만 집니다.
  * **Game / BattleManager:** InputHandler를 호출하는 상위 클래스에서 try-catch로 예외를 받아 실제 예외를 처리(메시지 출력, 루프 재시작)하는 책임을 집니다.
  * **InvalidInputException.java:** 재사용 가능한 사용자 정의 예외 클래스를 생성하여 코드를 분리했습니다.

### 컬렉션 프레임워크 (Map)

**왜 List가 아닌 Map을 사용했는가?**

* 초기 설계에서는 List을 사용했으나, 아이템 개수를 관리하고 검색하는 데 비효율적이었습니다.
* **Map<String, Integer> (HashMap)**을 사용하여, 아이템 이름(Key)을 통해 그 개수(Value)를 O(1)의 속도로 즉시 찾고 수정할 수 있도록 개선했습니다.
* 이 로직은 Inventory 클래스로 분리되어 관리됩니다.

---

## ⚙️ 겪었던 오류 및 해결 과정

### Player 클래스의 비대화 (SRP 위반)

* **오류:** 초기에는 Player 클래스가 캐릭터 로직과 인벤토리(Map) 관리 로직을 모두 가지고 있어 코드가 복잡했습니다.
* **해결:** Inventory 클래스를 새로 만들어 Map과 아이템 관련 메서드를 모두 위임했습니다. Player는 `inventory.useItem()`처럼 호출만 하도록 변경하여 단일 책임 원칙(SRP)을 준수했습니다.

### 인벤토리 아이템 개수 감소 버그

* **오류:** 포션 사용 시 `player.heal()`은 동작했지만 아이템 개수가 줄지 않았습니다.
* **해결:** `Inventory.java`의 `useItem()` 메서드에서 `items.get(itemName) - 1`로 계산만 하고, `items.put()`으로 맵에 다시 저장하지 않은 논리적 오류를 발견하여 수정했습니다.

### Git & SourceTree 푸시(Push) 오류

* **오류:** SourceTree를 이용해 GitHub에 푸시할 때 non-fast-forward 오류, 유효한 저장소가 아님 오류, 빈 폴더만 업로드되는 등 수많은 Git 연결 오류가 발생했습니다.
* **해결:**

  * 로컬의 꼬인 Git 정보(숨겨진 .git 폴더)를 완전히 삭제했습니다.
  * SourceTree에서 Java-project 최상위 폴더를 기준으로 로컬 저장소를 재생성했습니다.
  * 커밋 시 `bin/`, `.settings/` 등 불필요한 파일(컴파일 결과물)을 제외하고, `src/`, `.classpath`, `.project` 파일만 선택적으로 Stage에 올려 커밋했습니다.
  * `git push --set-upstream origin main` 명령어를 터미널에서 실행하여 로컬 브랜치와 원격 브랜치를 강제로 연결한 후 푸시에 성공했습니다.

---

## 🚀 실행 방법

1. 이 저장소를 **Clone** 받습니다.
2. **Eclipse IDE**에서 `File > Import > General > Existing Projects into Workspace`를 선택합니다.
3. **Select root directory**에서 Clone 받은 `Java-project` 폴더를 선택하고 **Finish**를 누릅니다.
4. `src/project/Game.java` 파일을 찾아 **Run As > Java Application**으로 실행합니다.
