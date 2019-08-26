# 9장 단위 테스트

## 9-1 서론
- 이 챕터는 `TDD`에 관련된 것이다.
- 이전에는 돌아간다는 사실만 확인하는 드라이버를 `일회성 코드`로 작성하여 수동으로 실행하는 방식
- 지금은 다음과 작업들을 진행함으로써 `제대로 된 테스트 케이스`를 작성


## 9-2 TDD의 법칙 세 가지
- TDD란 실제 코드를 짜기 전에 단위테스트부터 짜도록 요구하는 것
- 다음과 같은 법칙을 따르면 개발과 테스트를 30초 주기로 묶어 반복할 수 있다.
    1. 실패하는 단위 테스트를 작성할 때까지 실제 코드를 작성하지 않는다.
    2. 컴파일은 실패하지 않으면서 실행이 실패하는 정도로만 단위테스트를 작성한다.
    3. 현재 실패하는 테스트를 통과할 정도로만 실제 코드를 작성한다.
- 이를 통한 장단점
    - 장점 : 이를 통해 실제 코드를 전부 테스트하는 코드를 작성할 수 있다.
    - 단점 : 관리하기 힘들만큼 테스트 코드가 방대해진다.

### 관련도서 : 테스트 주도 개발
- 테스트 작성 규칙
    1. 어떤 코드건 작성하기 전에 실패하는 자동화된 테스트를 작성하라 : 법칙 세 가지에 해당
    2. 중복을 제거하라 : 깨끗한 테스트 코드 유지하기
- 예제를 통해 여러 주기를 진행하면서 깨끗한 테스트 코드를 작성하는 방법을 보여준다.


## 9-3 깨끗한 테스트 코드를 작성해야 하는 이유
- 실제 코드가 진화하면 테스트 코드도 변해야 한다.
    1. 테스트 코드가 더러우면 유지 보수하기 어려워지고 개발자에게는 부담(특히 비용 증가가)이 된다.
    2. 이는 테스트 코드를 관리하지 않도록 만든다.
    3. 그리고 실제 코드가 제대로 작동하는지 확인할 길이 없어 변경을 주저하게 만든다.
    4. 결국 위험성 때문에 코드를 정리하지 않게 되고 코드가 망가지기 시작한다.
- 깨끗한 테스트 코드는 실제 코드에 유연성, 유지보수성, 재사용성을 제공한다.
    - 테스트 케이스가 있으면 변경이 두렵지 않게 되어 변경이 쉬워지게 된다.
    - 테스트 케이스가 없다면 모든 변경이 잠재적인 버그가 되므로 코드 구조를 개선하고 변경하는 능력이 떨어진다.


## 9-4 깨끗한 테스트 코드

### 가독성이 가장 중요
- 가독성이 좋은 코드란 명료성, 단순성, 풍부한 표현력을 가진 코드
- 진짜 필요한 자료 유형과 함수만 사용함으로써 코드를 읽는 사람으로 하여금 코드가 수행하는 기능을 재빨리 이해할 수 있도록 한다.

```java
public void testGetpageHieratchyAsXml() throws Exception {
    // pagePath는 crawler가 사용하는 객체이므로 테스트 의도를 흐린다(테스트 이름과 연관성이 낮다)
    crawler.addPage(root, PathParser.parse("PageOne"));
    crawler.addPage(root, PathParser.parse("PageOne.childOne"));
    crawler.addPage(root, PathParser.parse("PageTwo"));

    // 여기도 마찬가지
    request.setResource("root");
    request.addInput("type", "pages");
    Responder responder = new SerializedPageResponder();
    SimpleResponse response =
        (SimpleResponse) responder.makeResponse(
            new FitNesseConext(root), request);
    String xml = response.getContent();

    assertEquals("text/xml", response.getContentType());
    assertSubString("<name>PageOne</name>", xml);
    assertSubString("<name>PageTwo</name>", xml);
    assertSubString("<name>PageChildOne</name>", xml);
}
```

```java
// BUILD-OPERATE-CHECK 패턴 사용
public void testGetpageHieratchyAsXml() throws Exception {
    makePages("PageOne", "PageOne,childOne", "PageTwo")

    submitRequest("root", "type:pages");

    assertResponseIsXML();
    assertResponseContains(
        "<name>PageOne</name>", "<name>PageTwo</name>", "<name>PageChildOne</name>"
    );
}
```

### BUILD-OPERATE-CHECK 패턴
1. Build : 테스트 자료를 만든다
2. Operate : 테스트 자료를 조작한다
3. Check : 조작한 결과가 올바른지 확인한다.


### 도메인에 특화된 테스트 언어(DSL)
- 시스템 조작 API를 사용하는 대신 API 위에다 함수와 유틸리티를 구현한 후 DSL로서 사용
- 좀 더 간결하고 표현력이 풍부하게 만들어 테스트를 짜고 읽기 쉽도록 함

### 이중 표준
- 실제 코드에 적용하는 표준과 다르다는 것을 의미
- 단순하고, 간결하고, 표현력이 풍부해야 하는 것은 실제 코드와 같지만, 실제 코드만큼 효율적일 필요는 없다.
- CPU나 메모리와 같은 자원이 제한될 가능성이 낮은 테스트 환경이기 때문

```java
hw.setTmpe(WAY_TOO_COLD);
controller.tic();
assertTrue(hw.heaterState());
assertTrue(hw.blowerState());
assertFalse(hw.coolerState());
assertFalse(hw.hiTempAlarm());
assertTrue(hw.loTempAlarm());
```

```java
wayTooCold();
assert("HBchL", hw.getState());
```

- 동일 표준
    - 이해하기 힘든 이름을 제거(tic -> wayTooCold)
- 이중 표준
    - 의미를 안다면 대소문자 비교를 통해 읽기 쉽게 한다
    - StringBuffer 대신 operator를 사용함으로써 읽기 쉬워진다

## 9-5 테스트 당 assert 하나
- 일반적으로 assert가 하나인 테스트가 이해하기 쉽고 빠르다

```java
public void testGetpageHierarchyAsXml() throws Exception {
    givenPages("PageOne", "PageOne.ChildOne", "PageTwo");

    whenRequestIsIssued("root", "type:pages");

    thenResponseShouldBeXML();
}

public void testGetpageHierarchyHasRightTags() throws Exception {
    givenPages("PageOne", "PageOne.ChildOne", "PageTwo");

    whenRequestIsIssued("root", "type:pages");

    thenResponseShouldContain(
        "<name>PageOne</name>", "<name>PageTwo</name>", "<name>PageChildOne</name>"
    );
}
```
- "출력이 XML이다”와 “특정 문자열을 포함한다”를 쪼갰다.
- Given-when-then이라는 관례 사용
- 읽기가 쉬워졌지만 중복되는 코드가 생겼다.
- Template method 패턴을 사용하여 중복을 제거한다
    - Given-when을 부모 클래스에 두고 then을 자식 클래스에 둔다
    - 아니면 별도로 분리하여 @Before 부분에 Given-when을 넣고 @Test 부분에 then을 넣는다.
    - 이런 것들을 고려하다 보면 결국은 원래의 코드가 나쁘지 않다고 생각된다.
- assert를 하나로 하는 것이 쉽지 않다면 assert의 갯수를 최소화하여 테스트를 작성하자

### Templat Method
- 알고리즘의 구조 자체는 그대로 놔둔 채 알고리즘 각 단계 처리를 서브 클래스에서 재정의할 수 있게 만든다.
- AbstractClass(TemplateMethod(), PrimitiveOperation1(), PrimitiveOperation2())
    - 알고리즘 처리 단계 내의 기본 연산 정의
    - 알고리즘의 뼈대를 정의하는 템플릿 메서드를 구현
    - 템플릿 메서드에서는 AbstractClass에 정의된 메서드, 다른 객체 연산, 기본연산을 호출
- ConcreteClass(PrimitiveOperation1(), PrimitiveOperation2())
    - 서브 클래스마다 달라진 알고리즘 처리 단계를 수행하기 위한 기본 연산을 구현
- ConcreteClass는 AbstractClass를 통하여 알고리즘의 변하지 않는 처리 단계를 구현


### 테스트 당 개념 하나
- "테스트 당 assert를 하나"보다는 "테스트 당 개념 하나”가 더 나은 경우가 많다.
- 결국 가장 좋은 규칙은
    - 개념 당 assert문 수를 최소로 줄여라
    - 테스트 함수 하나는 개념 하나만 테스트하라

```
- 5월 31일이 날짜로 주어진 경우
    - 1달을 더하면 6월 30일이다
    - 2달을 더하면 7월 31일이다
    - 1달을 더하고 또 1달을 더하면 7월 30일이다.
```
```
- (5월처럼) 31일로 끝나는 달의 마지막 날짜가 주어지는 경우
    - (6월처럼) 30일로 끝나는 한 달을 더하면 날짜는 30일이 되어야지 31일이 되어서는 안 된다.
    - 두 달을 더하면 그리고 두 번째 달이 31일로 끝나면 날짜는 31일이 되어야 한다.
- (6월처럼)30일로 끝나는 달의 마지막 날짜가 주어지는 경우
    - 31일로 끝나는 한 달을 더하면 날짜는 30일이 되어야지 31일이 되면 안 된다.
```


## 9-6 F.I.R.S.T
- Fast(빠르게) : 테스트는 빨리 돌아야 한다.
    - 느리면 자주 돌리지 못해 초반에 문제를 찾아내지 못한다.
- Independent(독립적으로) : 각 테스트는 서로 의존하면 안 된다
    - 원인을 진단하기 어려워지고 후반 테스트가 제대로 역할을 하지 못한다.
- Repeatable(반복가능하게) : 어떤 환경에서도 반복 가능해야 한다.
    - 테스트를 수행하지 못하는 상황에 대한 변명이 생긴다
- Self-Validating(자가검증하는) : 테스트는 부울 값으로 결과를 내야한다.
    - 통과 여부는 성공 또는 실패로만 알 수 있게 해야한다.
    - 수작업을 하도록 만들면 된다.
- Timely(적시에) : 단위 테스트는 테스트를 하려는 실제 코드를 구현하기 직전에 구현한다.
    - 코드 구현 후에 테스트를 작성하면 테스트하기 어려울 수 있다.

## 9-7 결론
- 테스트 코드는 실제 코드의 유연성, 유지보수성, 재사용성을 보존하고 강화하기 때문에 실제 코드보다 더 중요할 수도 있다.
- 표현력을 높이고 간결하게 만들어서 지속적으로 깨끗하게 관리하자
- 테스트 API를 구현해 DSL를 만들면 테스트를 짜기가 쉬워진다.

## 번외 - 모바일에서는
- UI와 로직을 분리하기 위해서 MVVM/MVP 패턴 등을 사용
- ViewModel/Presenter을 주로 테스트 대상에 포함
- View는 UITesting과 ScreenShot 테스트를 주로 이용
