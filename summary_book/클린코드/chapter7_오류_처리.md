# 7장 오류 처리
___

### 7-0 들어가기
- 오류 처리는 프로그램에 반드시 필요한 요소이다
- 하지만 오류를 처리하다보면 코드가 지저분해진다
- 그렇기 때문에 오류 처리를 코어 로직과 분리하는 것이 클린 코드라고 할 수 있다
- 그럼 깨끗하고 튼튼한 코드는 어떻게 짜야하는가?

___

### 7-1 오류 코드보다 예외를 사용하라
##### 오류 코드를 반환하는 코드
```java
 public class DeviceController {
        public void sendShotDown () {
        DeviceHandle handle = getHandle(DEV1);
        //디바이스 상태를 점검한다.
        if (handle != DeviceHandle.INVALID) {
            // 레코드 필드에 디바이스 상태를 저장한다
            retrieveDeviceRecord(handle);
            // 디바이스가 일시정지 상태가 아니라면 종료한다.
            if (recode.getStatus() != DEVICE_SUSPENDED) {
                pauseDevice(handle);
                cleanDeviceWorkQueue(handle);
                closeDevice(handle);
            } else {
                log.error("Device suspended. unable to shut down")
            }
        } else {
            log.error("Invalid handle ");
        }
    }
 }
```
##### 예외를 반환하는 코드
```java
public class DeviceController {

       public void sendShutDown() {
           try {
               tryToShotDown();
           } catch (DeviceShoutDownError e){
               log.error(e);
           }

       }

       private void tryToShotDown() {
           DeviceHandle handle = getHandle(DEV1);
           DeviceRecord record = retrieveDeviceRecord(handle);

           pauseDevice(handle);
           clearDeviceWorkQueue(handle);
           closeDevice(handle);
       }
       private DeviceHandle getHandle(DeviceID id){
           ...
           throw new DeviceShoutDownError("Invalid hanlde error");
       }
   }

```
- 비지니스 로직과 오류 처리 로직을 분리하여 코드가 깔끔해졌다.

___

### 7-2 Try-Catch-Finally 문부터 작성하라
- 예외에서 프로그램 안에다 범위를 정의한다는 사실은 매우 흥미롭다.
- try 블록은 트랜지션과 비슷하다 (ACID?)
- 예외가 발생할 코드를 짤 떄는 try-catch-finally 문으로 시작하는 편이 낫다.
- try 블록에서 무슨 일이 생기든 호출자가 기대하는 상태를 정의하기 쉬워진다

##### try-catch로 일관성 있는 에러 처리하기 (feat. TDD)
```java
 @Test(expected = StorageException.class)
    public void retrieveSectionShouldThrowOnInvalidFileName(){
        sectionStore.retrieveSection("invalid - file")
    }

    public List<RecordedGrip> retrieveSection(String sectionName){
        //실제로 구현할 떄까지 비어 있는 더미를 반환한다
        return new Lists.newArrayList();
    }
```
- 에러를 반환하는 테스트를 만들고 예외를 던지지 않게 하여 테스트를 실패하게 한다

```java
    public List<RecordedGrip> retrieveSection(String sectionName){
        try {
            FileInputStream fileInputStream = new FileInputStream(sectionName);
        }catch (Exception e) {
            throw new StorageException("retrieve error," e);
        }
        return Lists.newArrayList();
    }
```
- 코드에서 예외를 던지므로 테스트가 성공한다. 이후에 리팩토링을 한다

``` java
    public List<RecordedGrip> retrieveSection(String sectionName){
        try {
            FileInputStream fileInputStream = new FileInputStream(sectionName);
            ...
            fileInputStream.close(); //IOException?
        }catch (FileNotFoundException e) {
            throw new StorageException("retrieve error," e);
        }
        return Lists.newArrayList();
    }
```
- 이렇게 try-catch문을 범위를 지정하고 안에 로직을 구현하면 트랜잭션 본질을 유지하기 쉬워진다

___

### 7-3 미확인 예외를 사용하라

- 확인된 예외가 처음 나왔을떈 멋진 아이디어라고 생각했다. (예외를 지원하지 않았던 것을 생각하면 신세계)
- 허나 확인된 오류는 그에 사응하는 비용이 있어, 충분한 이익을 제공하는지 따져봐야 한다.
- 왜냐하면 확인된 오류는 OCP를 위반하기 때문이다

```java
    public void retrieveSectionShouldThrowOnInvalidFileName() throws IOException {
        sectionStore.retrieveSection("invalid - file")
    }


    public List<RecordedGrip> retrieveSection(String sectionName) throws IOException{
        try {
            FileInputStream fileInputStream = new FileInputStream(sectionName);
            ...
            fileInputStream.close();
        }catch (FileNotFoundException e) {
            throw new StorageException("retrieve error," e);
        }
        return Lists.newArrayList();
    }
```
- 최하위 함수에서 던지는 에러를 최상위 함수에서 알아야 하므로 캡슐화가 깨진다
- 확인된 예외도 분명 유용하지만 (중요한 라이브러리나 코어 로직 작성), 일반적인 어플리케이션은 의존성이라는 비용이 이익보다 크다.

___

### 7-4 예외에 의미를 제공해라.
- 예외를 던질 떄는 전후 상황을 충분히 덧붙인다.
- 자바는 예외에 호출 스택을 제공하지만, 가끔은 그걸로 에러를 파악하는데 어려움이 있을 수 있다
- 로깅 기능을 사용한다면 catch 블록에서 충분한 정보와 함께 오류를 기록한다
- (의미있게 남긴 로깅 하나가, 디버깅 한시간 줄여준다.)

```
http-bio-10001-exec-55] c.c.m.i.api.v3.ControllerBase.printError - error has occurred.
- null
```
___
### 7-5 호출자를 고려해 예외 클래스를 정의하라
- 오류를 분류하는 방법은 수없이 많다
- 오류가 발생한 위치, 오류의 유형 등등
- 하지만 프로그래머에게 가장 중요한 관심사는 오류를 잡아내는 방법이 되어야한다.

##### 형편없는 오류 분류
```java
 ACMEPort port = new ACMEPort(12);

    try {
        port.open();
    } catch (DeviceResponseException e) {
        reportPortError(e);
        log.error("device response exception", e);
    } catch (ATM1212UnlockedException e) {
        reportPortError(e);
        log.error("unlock exception", e);
    } catch (GMXError e){
        reportPortError(e);
        log.error("GMX exception", e);
    } finally {
        ...
    }
```
- 중복이 심하지만 그리 놀랍지 않다.
- 대다수 상황에서 오류를 처리하는 방식은 (오류를 일으킨 원인과 무관하게) 비교적 일정하다
- 1) 오류를 기록하고, 2) 프로그램이 계속 수행해도 되는지 확인한다.
- 위의 경우 예외 유형과 무관하게 에러 대응 방식이 거의 동일하다.
- 이럴땐 wrapper 클래스를 사용하면 깔끔해진다.

##### 감싸기 클래스를 사용한 오류 분류
```java
    public class LocalPort {
        private ACMEPort innerPort;

        public LocalPort(int portNumber){
            innerPort = new ACMEPort(portNumber);
        }

        public void open() {
            try {
              innerPort.open()
            }catch (DeviceResponseException | ATM1212UnlockedException | GMXError e) {
               throw new PortDeviceFailure(e);
            }
        }
    }

    ...
    public process() {
         LocalPort localPort = new LocalPort(12);
         localPort.open();
    }
```
- 이런식으로 외부 API를 사용할떄 감싸기 기법이 최선이다.
- 외부 라이브러리와 내부 프로그램 사이의 의존성을 줄일 수 있다.
- 다른 라이브러리로 교체해도 Wrapper 클래스만 변경해주면 된다.
- 마지막 장점은 특정 업체가 API를 설계한 방식에 발목 잡히지 않는다.
- (Port and adapter pattern)

___

### 7-6 정상 흐름을 정의하라
- 앞 절에서의 지침을 따라다보면 클린 코드가 되지만 그러다보면 오류 감지가 프로그램 언저리로 밀려난다
- 외부 API를 감싸 독자적인 예외를 던지고 코드 위에 처리기를 정의해 중단된 계산을 처리한다
- 하지만 때로는 중단이 적합하지 않을 때도 있다.

##### 식비 비용 청구 어플리케이션
```java
    try {
        MealExpenses expenses = expenseReportDao.getMeals(employee.getID());
        total += expenses.getTotal();
    } catch (MealExpensesNotFound e) {
        total += getMealPerDiem();
    }
```
- 예외가 논리를 따라가게 어렵게 한다
- 정말 예외를 통해 상황을 처리해야하는가?

```java
        MealExpenses expenses = expenseReportDao.getMeals(employee.getID());
        total += expenses.getTotal();

    public class PerDiemMealExpenses implements MealExpenses {
        public int getTotal() {
            return dailyPerDiem;
        }
    }
```
- 만약 식비 비용을 청구 하지 않으면, PerDiemMealExpenses 반환하게 한다
- 그러면 로직이 간결하게 보이게 된다.
- 특수 사례 패턴(special case pattern) - fowler

___

### 7-7 null을 반환하지 마라
- null을 반환하는 습관은 오류를 유발하는 행위이다.
- 한번의 null check를 실수하면 NPE가 발생한다.

##### 통상적인 null check를 하는 로직
```java
public void registerItem(Item item) {
	if (item != null) {
		ItemRegistry registry = peristentStore.getItemRegistry();
		if (registry != null) {
			Item existing = registry.getItem(item.getID());
			if (existing.getBillingPeriod().hasRetailOwner()) {
				existing.register(item);
			}
		}
	}
}
```
- 많이 봐왔던 코드이지만 사실 나쁜 코드이다. 이유는 아래와 같다.
- 1) null check를 누락하기 쉽다 - 실제 위의 코드는 누락이 됬다.
- 2) null check가 너무 많다.
- 이럴떈 예외를 던지거나, 특수 사례 객체를 반환하게 한다

##### 직원 payroll 처리 로직
```java
// bad
List<Employee> employees = getEmployees();
if(employees != null) {
	for(Employee e : employees) {
		totalPay += e.getPay();
	}
}

// good
List<Employee> employees = getEmployees();
for(Employee e : employees) {
	totalPay += e.getPay();
}

public List<Employee> getEmployees() {
	if (..직원이 없다면..)
		return Collections.emptyList();
}
```
- 이렇게 하면 코드도 깔끔해지고 NPE도 가능성도 줄어든다.

___

### 7-8 null을 전달하지 마라
- null을 리턴하는 것도 나쁘지만 null을 메서드로 넘기는 것은 더 나쁘다.
- null을 메서드의 파라미터로 넣어야 하는 API를 사용하는 경우가 아니면 null을 메서드로 넘기지 마라.
- 일반적으로 대다수의 프로그래밍 언어들은 파라미터로 들어온 null에 대해 적절한 방법을 제공하지 못한다.
- 가장 이성적인 해법은 null을 파라미터로 받지 못하게 하는 것이다.

##### 두 지점 사이의 거리를 계산하는 로직
```java
public class MetricsCalculator {
  public double xProjection(Point p1, Point p2) {
    return (p2.x – p1.x) * 1.5;
  }
}
```
- null이 들어오면 NPE가 발생한다

```java
public class MetricsCalculator {
  public double xProjection(Point p1, Point p2) {
   if(p1 == null || p2 == null){
        throw InvalidArgumentException("Invalid argument for MetricsCalculator.xProjection");
      }
    return (p2.x – p1.x) * 1.5;
  }
}
```
- null 처리는 하였지만 InvalidArgumentException을 처리해주는 곳이 필요하다.

```java
public class MetricsCalculator {
  public double xProjection(Point p1, Point p2) {
    assert p1 != null : "p1 should not be null";
    assert p2 != null : "p2 should not be null";
    return (p2.x – p1.x) * 1.5;
  }
}
```
- Asset을 이용하는 것도 방법이다.

---

### 7-9 결론
- 깨끗한 코드는 읽기도 좋아야하지만 안정성도 높아야한다.
- 이 두 가지는 상충하는 목표가 아니다.
- 오류 처리와 로직을 분리할 수 있는지 고려하면 튼튼하고 깨끗한 코드를 작성할 수 있다.
- 또한 각각의 로직에만 집중할 수 있기 때문에 유지보수성도 크게 높아진다.
- 그럼 어떻게 해야하는가? 계속 고민하고 또 고민하고 겪어봐야한다.
