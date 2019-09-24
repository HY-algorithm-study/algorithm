# 8장 경계
___

### 8-0 Overview
- 시스템에 들어가는 모든 소프트웨어를 직접 다 개발하는 경우는 드물다.
- 외부 패키지, 오픈소스 등을 필연적으로 사용하게 된다.
- 따라서 이러한 외부 코드를 우리의 코드에 깔끔하게 통합해야할 필요가 있다.
- 이 장에서는 외부 코드와 내부 코드 사이의 경계, 즉 소프트웨어 경계를 깔끔하게 처리하는 기법에 대해 공부한다. 

___

### 8-1 외부 코드 사용하기
##### 인터페이스 제공자와 사용자 간의 입장 차
- 제공자는 사용성과 범용성을 보다 넓히려고 하고,
- 사용자는 자신이 원하는 인터페이스에 집중하기를 원한다.
- 따라서, 사용자는 제공된 인터페이스를 사용함에 있어 경계 처리에 주의해야 한다.
      
##### 예시:  java.util.Map을 사용한다고 치자.
- clear() void - map
- containsKey(Object key) boolean - Map
- containsValue(Object value) boolean - Map
- entrySet() set - Map
- equals(Object o) boolean - Map
- get(Object key)Object - Map
- getClass() Class<? extends Object> - Object
- hashCode() int - Map
- isEmpty() boolean - Map
- keySet() Set - Map
- notify() void - Object
- 등등..의 수많은 메소드를 제공한다.

##### 하지만 무턱대고 모든 메소드를 import하여 사용하는 것은 위험하다.

``` java
Map sensors = new HashMap();
Sensor s = (Sensor)sensors.get(sensorId);
```

- Map을 만들어 인수나 반환으로 이리 저리 넘길 경우 clear() 메서드 사용으로 의도치않게 지울 수 있다.
- Map에 특정 객체 유형만 정의하기로 하였을 때, 다른 유형의 객체가 추가될 수 있다. (generic 이전의 map은 객체 유형을 제한하지 않았다)

``` java
Map<String, Sensor> sensors = new HashMap<String, Sensor>();
Sensor s = sensors.get(sensorId); 
```

- Map의 interface가 변경될 경우 변경해야할 코드가 너무 많아진다.
	- Map을 사용하는 코드가 여기저기 퍼져있는데, Generic 이전의 Map이 Generic 이후의 Map으로 바뀌었을 때 version up을 하는 상황을 생각해보자.

##### 이러한 문제는 객체를 감싸는 것을 통해 해결 가능하다.

``` java
public class Sensors {
	private Map sensors = new Hashmap();

	public Sensor getById(string id) {
		return (Sensor) sensors.get(id);
	}
}
```

- Sensors 객체를 사용할 때는 객체 내부에서 사용된 외부 라이브러리의 구현에 신경쓰지 않아도 된다.
- 또한, 필요하지 않은 메소드는 제공하지 않을 수도 있다.
##### 결과적으로 Map과 같은 경계에 해당하는 인터페이스를 여기저기서 사용하지 않는 것이 중요하다.

### 8-2 경계 살피고 익히기
##### **학습테스트** (Learning test)
- 외부 코드를 사용하면 적은 시간을 투자해 많은 기능을 제공할 수 있다. 
- 하지만, 외부 코드에 대한 이해가 충분한 상태에서 사용해야 한다.
- 그렇지 않을 경우, 자신의 코드가 문제인지 외부 코드가 문제인지 파악하기 어려워 디버깅의 지옥에 빠질 수 있다.
- *학습 테스트*란, 외부 코드를 사용하는 간단한 테스트 케이스들을 작성 및 구현함으로써 외부 코드를 익히는 방법을 지칭한다.

##### Example - log4j 익히기 
- 로깅을 편리하게 하기 위한 라이브러리가 필요하다
- 구글링하여 log4j라는 패키지를 발견하여 이를 설치한다.
- 문서를 자세히 읽기 전 테스트 케이스를 작성한다.
	- 해당 라이브러리에게 내가 기대하는 기능들.
	
``` java
Logger logger = Logger.getLogger("MyLogger");
logger.info("test success");
```

- 동작하지 않는다. 문서를 더 읽어보고 콘솔에 로그를 써주는 ConsoleAppender가 필요함을 깨닫고, 테스트를 재작성한다.

``` java
Logger logger = Logger.getLogger("MyLogger");
ConsoleAppender appender = new ConsoleAppender();
logger.addAppender(appender);
logger.info("hello");
```

- 여전히 동작하지 않는다. 이번엔 구글링을 해보고 아래와 같이 시도한다.

``` java
Logger  logger = Logger.getLogger("MyLogger");
ConsoleAppender consoleAppender = new ConsoleAppender( new PatternLayout (PatternLayout.TTCC_CONVERSION_PATTERN));
logger.addAppender(consoleAppender);
logger.info("hello");
```

- 잘 된다. 이 과정에서 log4j를 많이 이해하게 되었을 것이다. 여기서 멈추지 않고, unit 테스트를 몇 개 더 작성하는게 좋다.

``` java
public class LogTest {
     private Logger logger;

     @Before
     public void initialize() {
         logger = Logger.getLogger("logger");
         logger.removeAllAppenders();
         Logger.getRootLogger().removeAllAppenders();
     }

     @Test
     public void basicLogger() {
         BasicConfigurator.configure();
         logger.info("basicLogger");
     }

     @Test
     public void addAppenderWithStream() {
         logger.addAppender(new ConsoleAppender(
             new PatternLayout("%p %t %m%n"),
             ConsoleAppender.SYSTEM_OUT));
         logger.info("addAppenderWithStream");
     }

     @Test
     public void addAppenderWithoutStream() {
         logger.addAppender(new ConsoleAppender(
             new PatternLayout("%p %t %m%n")));
         logger.info("addAppenderWithoutStream");
     }
 }
```

##### 학습 테스트는 공짜 이상이다.
- 드는 비용에 비해 얻는 지식이 훨씬 크다.
- 또한, 외부 코드가 예상대로 도는지 검증할 수 있고 외부 코드의 버전이 바뀌어도 주기적으로 검증이 가능하다.
	- 학습 테스트와 함께라면 버전 변경도 두렵지 않다.

### 8-3 결론 - 깨끗한 경계
##### 변경이 이루어질 때 경계를 통제하고 있다면 향후 외부 코드가 변경됨으로 인해 발생하는 비용을 줄일 수 있다.
- 경계에 위치하는 코드는 Map의 경우 처럼 새로운 클래스로 감싸거나, adapter 패턴 등을 적용하여 깔끔하게 분리.
- 외부 코드에 대한 기대값을 정의하는 테스트 케이스 작성.

##### 사실, 통제 불가능한 외부 코드보다는 통제 가능한 우리 코드에 최대한 의존하는 편이 좋다. 
