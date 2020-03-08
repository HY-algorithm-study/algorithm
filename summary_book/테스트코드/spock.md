#Spock Study

##Why spock?
- spock 을 이용하여 테스트 코드를 작성하는 것은 다른 표준 프레임워크 보다 시간이 덜듬.
- groovy 를 사용하여 클로저와 맵을 직접 사용할 수 있어서 테스트의 명확성을 더 높일 수 있다.

###Test structure

####given section
**given**  
여기서는 테스트 하고 싶은 기능을 컨텍스트에 지정합니다.
여기서는 테스트 중인 기능에 영향을 미치는 시스템/컴포넌트의 매개 변수를 지정.
spock 초보자들은 이 섹션을 매우 크게 작성하는 경향이 있지만, 궁극적으로는 간결하고 설명되기 쉽도록 만드는 방법을 배워야함.

####when section
**when**  
하고싶은것을 작성하는 곳. 즉 테스트 된 객체 / 컴포넌트와의 상호작용하는것을 확인하는 곳.

####then section
**then**  
when 에서 나온 결과를 확인하는 곳.
메소드 결과 or Mocks 와 spies 를 이용하여 내부에서 테스트된 기능과 상호작용한게 무엇인지 검증.

```groovy
def "test"() {
    given:
          // context
          pageRequiresRole Role.ADMIN
          userHasRole Role.USER
       when:
          // some action is performed
          boolean authorized = authorizationService.isUserAuthorizedForPage(user, page)
       then:
          // expect specific result
          authorized == false
}
```

##Stubbing method calls
spock 에서 다른 클래스나 인터페이스의 동작을 재정의 할 수있게 3종류의 클래스를 제공. 
stub, mock, spy

####Create stub
Stub 을 생성하기 위해서는 Spock 테스트 코드 내에서 Stub() 메서드를 호출. 

####Specific value return
**>>** 연산자를 이요하면 됨.  
example)
```groovy
def "test"() {
	given:
	List list = Stub()
	list.size() >> 3
	expect:
	list.size() == 3
}
```

####Specific side efficts
특정 사이드 이펙트를 원할 경우 클로저 ({}) 를 추가해줍니다.  
```groovy
def "test"() {
		given:
		List list = Stub()
		list.size() >> { println "Size method has been invoked" }
}
```

####Throw an exception 
특정 에러를 던질때도 클로저를 이용합니다. ({})
```groovy
def "test"() {
		given:
		List list = Stub()
		list.size() >> { throw new IllegalStateException() }
}
```
####Sepcifying different behavior baed on invocation order.   
(호출 순서에 따른 특정 행동)


def "test"() {
	given:
	List list = Stub()
	list.size() >>> [1,2,3] 
}

위의 예제는 list size 가 첫번쨰 호출에는 1, 다음에는 2, 3번째 호출에서는 3을 반환합니다.

####Conditional behavior of stubbed method


















