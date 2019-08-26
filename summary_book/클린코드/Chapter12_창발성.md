# Chapter 12. 창발성 (創發性 / emergent properties)
창발(創發)또는 떠오름 현상은 하위 계층(구성 요소)에는 없는 특성이나 행동이 상위 계층(전체 구조)에서 자발적으로 돌연히 출현하는 현상이다.<br> 
또한 불시에 솟아나는 특성을 창발성(영어: emergent property) 또는 이머전스(영어: emergence)라고도 부른다.
- 예를 들어 암모니아 냄새는 수소나 질소에서는 존재하지 않고, 화학의 법칙으로서는 예견할 수 없다. 
- "개개의 구성원이 가지고 있지 않으므로 그것들이 상호 작용했을 때에 나타날 것으로 결코 예상하지도 못한 동작이 그야말로 창조적으로 발현되는 것"(John. L. Casti, 1997)
- Related Link : [Wikipedia - 창발](https://ko.wikipedia.org/wiki/%EC%B0%BD%EB%B0%9C)

## 단순한 설계 규칙 - by Kent Beck
우리들 대다수는 켄트 벡이 제시한 단순한 설계 규칙 네 가지가 소프트웨어 설계 품질을 크게 높여준다고 믿는다.
1. 모든 테스트를 실행한다.
2. 중복을 없앤다.
3. 프로그래머 의도를 표현한다.
4. 클래스와 메서드 수를 최소로 줄인다.


### 모든 테스트를 실행하라.
- 테스트를 철저히 거쳐 모든 테스트 케이스를 항상 통과하는 시스템 -- `테스트 가능한 시스템`
- 테스트 가능한 시스템을 만들고자 하면 테스트 하기 쉽도록 코드를 작성하게 되면서 자동으로 
    - SRP (Single Responsibility Principle) 를 준수하게 된다.
    - 결합도를 낮추기 위해 DIP (Dependency Inversion Principle) 와 같은 원칙을 적용하게 되고 설계 품질은 높아진다.


### [리팩터링]
- 테스트 케이스 작성 이후 코드를 점진적으로 리팩터링 해나간다.
- 코드 몇 줄을 추가할 때마다 잠시 멈추고 설계를 조감한다.
- 응집도를 높인다.
- 결합도를 낮춘다.
- 관심사를 분리한다.
- 시스템 관심사를 모듈로 나눈다.
- 함수와 클래스 크기를 줄인다.
- 더 나은 이름을 선택한다.
- ...


### 중복을 없애라
- 중복은 추가 작업, 추가 위험, 불필요한 복잡도를 뜻한다.
- 구현 중복도 중복의 한 형태다.
    ```java
    class SomeList {
        List<String> list1;
        List<String> list2;

        int size() {
            return list1.size() + list2.size();
        }

        boolean isEmpty() {
            return (list1.size() + list2.size()) == 0;
        }
    }
    ```
    위와 같이 각 메서드를 따로 구현하는 방법도 있지만, 아래처럼 isEmpty 메서드에서 size 메서드를 이용하면 코드를 중복해 구현할 필요가 없어진다.
    ```java
    class SomeList {
        List<String> list1;
        List<String> list2;

        int size() {
            return list1.size() + list2.size();
        }

        boolean isEmpty() {
            return size() == 0;
        }
    }
    ```
- 깔끔한 시스템을 만드려면 단 몇 줄이라도 중복을 제거하겠다는 의지가 필요하다.
   ```java
   public void scaleToOneDimension(float desiredDimension, float imageDimension) {
        if (Math.abs(desiredDimension - imageDimension) < errorThreshold) {
            return;
        }
        float scalingFactor = desiredDimension / imageDimension;
        scalingFactor = (float)(Math.floor(scalingFactor * 100) * 0.01f);

        RenderedOp newImage = ImageUtilities.getScaledImage(image, scalingFactor, scalingFactor);
        image.dispose();
        System.gc();
        image = newImage;
    }

    public synchronized void rotate(int degrees) {
        RenderedOp newImage = ImageUtilities.getRotatedImage(image, degrees);
        image.dispose();
        System.gc();
        image = newImage;
    }
    ```
    ==>
    ```java
    public void scaleToOneDimension(float desiredDimension, float imageDimension) {
        if (Math.abs(desiredDimension - imageDimension) < errorThreshold) {
            return;
        }
        float scalingFactor = desiredDimension / imageDimension;
        scalingFactor = (float)(Math.floor(scalingFactor * 100) * 0.01f);

        RenderedOp newImage = ImageUtilities.getScaledImage(image, scalingFactor, scalingFactor);
        replaceImage(newImage);
    }

    public synchronized void rotate(int degrees) {
        RenderedOp newImage = ImageUtilities.getRotatedImage(image, degrees);
        replaceImage(newImage);
    }

    private void replaceImage(RenderedOp newImage) {
        image.dispose();
        System.gc();
        image = newImage;
    }
    ```
    공통적인 코드를 새 메서드로 뽑고 보니 클래스가 SRP 를 위반한다.<br>
    새 메서드 replaceImage 를 다른 클래스로 옮긴다면 해당 메서드의 가시성이 높아지고, 누군가는 이를 좀 더 추상화해 다른 맥락에서 재사용할 기회를 포착할 수도 있다.<br>
    이런 '소규모 재사용' 은 시스템 복잡도를 극적으로 줄여준다.<br>
    소규모 재사용을 제대로 익혀야 대규모 재사용이 가능하다.
- TEMPLATE METHOD 패턴은 고차원 중복을 제거할 목적으로 자주 사용하는 기법이다.
    ```java
    public class VacationPolicy {
        public void accureUSDivitionVacation() {
            // 지금까지 근무한 시간을 바탕으로 휴가 일수를 계산하는 코드
            // ...
            // 휴가 일수가 미국 최소 법정 일수를 만족하는지 확인하는 코드
            // ...
            // 휴가 일수를 급여 대장에 적용하는 코드
            // ...
        }

        public void accureEUDivisionVacation() {
            // 지금까지 근무한 시간을 바탕으로 휴가 일수를 계산하는 코드
            // ...
            // 휴가 일수가 유럽연합 최소 법정 일수를 만족하는지 확인하는 코드
            // ...
            // 휴가 일수를 급여 대장에 적용하는 코드
            // ...
        }
    }
    ```
    중복되는 부분은 상위 클래스로, 나머지 부분은 하위 클래스에서 구현한다.
    ```java
    public abstract class VacationPolicy {
        public void accureVacation() {
            calculateBaseVacationHours();
            alterForLegalMinimums();
            applyToPayroll();
        }
    
        private void calculateBaseVacationHours() {
            // ...
        }

        protected abstract void alterForLegalMinimums();
    
        private void applyToPayroll() {
            // ...
        }
    }

    public class USVacationPolicy extends VacationPolicy {
        @Override
        protected void alterForLegalMinimums() {
            // 미국 최소 법정 일수를 사용한다.
        }
    }

    public class EUVacationPolicy extends VacationPolicy {
        @Override
        protected void alterForLegalMinimums() {
            // 유럽연합 최소 법정 일수를 사용한다
        }
    }
    ```


### 표현하라
코드는 개발자의 의도를 분명히 표현해야 한다.
개발자가 코드를 명백하게 짤 수록 다른 사람이 코드를 이해하기 쉬워진다.
그래야 결함이 줄어# Chapter 13 동시성
          
          ### overview
          - 동시성을 동시에 돌리는 어려움
          - 어려움에 대처하고 깨끗한 코드를 작성하는 방법
          - 동시성을 테스트 하는 방법과 문제점
          
          ## 동시성이 필요한 이유?
          - 동시성은 결합을 없애는 전략
              - 결합을 없애으로써 구조적 개선을 꾀할 수 있음
          - 응답 시간과 작업 처리량을 개선
              - 웹 정보 수집기, 사용자를 처리하는 시스템, 정보 대량 분석 시스템
          
          ### 미신과 오해
          - 동시성은 항상 때로 성능을 높여준다 ?
          - 동시성을 구현해도 설계는 변하지 않는다 ?
          - 웹 또는 EJB 컨테이너를 사용하면 동시성을 이해할 필요없다 ?
          
          - 타당한 생각
              - 동시성은 다소 부하를 유발한다. 
              - 동시성은 복잡하다.
              - 일반적으로 동시성 버그는 재현하기 어렵다.
              - 동시성을 구현하려면 흔히 근본적인 설계 전략을 재고해야 한다.
          
          ## 난관
          ```java
          public class X {
            private int lastIdUsed;
            
            public int getNextId() {
              return ++lastIdUsed;
            }
          }
          ```
          간단한 클래스임에도 다중 스레드가 실행하는 경로는 많고 대다수는 올바른 결과를 내지만 일부 경로는 잘못된 경로를 내놓을 수 있다!
          
          ## 동시성 방어 원칙
          - 단일 책임 원칙 :
              - 동시성은 복잡성 하나만으로도 따로 분리할 이유가 충분하다 
              - 동시성 코드는 다른 코드와 분리하라
          - 자료 범위를 제한하라 : 
              - 공유 객체를 사용하는 코드 내 임계영역을 synchronized 키워드로 보호하라
              - 자료를 캡슐화하라
              - 공유 자료를 최대한 줄여라
          - 자료 사본을 사용하라
          - 스레드는 가능한 독립적으로 구현하라
              - 다른 스레드와 자료 공유를 줄여라
              - 독립적인 스레드로 가능하면 다른 프로세서에서, 돌려도 괜찮도록 자료를 독립적인 단위로 분할하라
          
          ## 라이브러리를 이해하라
          
          ### 스레드 환경에 안전한 컬렉션
          - java.util.concurrent.ConcurrentHashMap
          
          ```java
          /**
           * A {@link java.util.Map} providing thread safety and atomicity
           * guarantees.
           *
           * <p>Memory consistency effects: As with other concurrent
           * collections, actions in a thread prior to placing an object into a
           * {@code ConcurrentMap} as a key or value
           * <a href="package-summary.html#MemoryVisibility"><i>happen-before</i></a>
           * actions subsequent to the access or removal of that object from
           * the {@code ConcurrentMap} in another thread.
           *
           * <p>This interface is a member of the
           * <a href="{@docRoot}/../technotes/guides/collections/index.html">
           * Java Collections Framework</a>.
           *
           * @since 1.5
           * @author Doug Lea
           * @param <K> the type of keys maintained by this map
           * @param <V> the type of mapped values
           */
          public interface ConcurrentMap<K, V> extends Map<K, V> {
              ...
          }
          ```
          
          - java.util.Collections.synchronizedSet/SynchronizedMap
          ```java
          private static class SynchronizedMap<K,V>
                  implements Map<K,V>, Serializable {
              ...
              public int size() {
                          synchronized (mutex) {return m.size();}
                      }
                      public boolean isEmpty() {
                          synchronized (mutex) {return m.isEmpty();}
                      }
                      public boolean containsKey(Object key) {
                          synchronized (mutex) {return m.containsKey(key);}
                      }
                      public boolean containsValue(Object value) {
                          synchronized (mutex) {return m.containsValue(value);}
                      }
                      public V get(Object key) {
                          synchronized (mutex) {return m.get(key);}
                      }
              
                      public V put(K key, V value) {
                          synchronized (mutex) {return m.put(key, value);}
                      }
                      ...
          }
          ```
          - java.util.concurrent.locks.ReentrantLock
              - 한 메서드에서 잠그고 다른 메서드에서 푸는 락이다. 
          - java.util.concurrent.Semaphore 
              - 개수가 있는 락 
          - java.util.concurrent.CountDownLatch
              - 지정한 수만큼 이벤트 발생 후 대기 중인 스레드를 모두 해제하는 락
          - java.util.concurrent, java.util.concurrent.atomic, java.util.concurrent.locks
          
          ## 실행 모델을 이해하라
          - 다중 스레드에서 사용하는 실행 모델들을 이해하고 각 해법을 이해하는 것이 좋다.
          
          ### 생산자-소비자
          생산자 스레드와 소비자 스레드가 한정된 자원으 사용
          서로 시그널을 보냄으로써 동작
          동시에 서로에게서 시그널을 기다릴 가능성이 존재 
          
          ### 읽기-쓰기
          읽기 스레드와 쓰기 스레드가 공유 자원을 사용하되 쓰기 스레드에서 갱신
          읽기 스레드와 쓰기 스레드간 균형 및 동시 갱신 문제를 피하는 해법이 필요
          
          ### 식사하는 철학자들
          ![The-Dining-Philosophers](http://adit.io/imgs/dining_philosophers/at_the_table.png)
          여러 프로세스가 자원을 얻기위해 경쟁
          데드락, 라이브락, 처리율 저하, 효율성 저하등을 겪음
          
          > 기본 용어 
          * 한정된 자원 : 다중 스레드 환경에서 사용하는 자원으로, 크기나 숫자가 제한적, ex) 데이터베이스 연결
          * 상호 배제 : 한번에 한 스레드만 공유 자료나 공유 자원을 사용할 수 있는 경우 
          * 기아 : 스레드가 굉장히 오랫동안 혹은 영원히 자원을 기다리는 현상
          * 데드락 : 여러 스레드가 서로 끝나기를 기다리는 현상
          * 라이브락 : 락을 거는 단계에서 스레드가 서로를 방해
          
          ## 동기화하는 메서드 사이에 존재하는 의존성을 이해하라
          - 공유 객체 하나에는 메서드 하나만 사용하라
          ```java
          public class IntegerIterator implements Iterator<Integer> {
              public Integer nextValue = 0;
              
              public synchronized boolean hasNext() {
                  return nextValue < 100000;
              }
              
              public synchronized Integer next() {
                  if (nextValue == 100000)
                      throw new IteratorPasEndException();
                  return nextValue++;
              }
              
              public synchronized Integer getNextValue() {
                  return nextValue;
              }
          }
          ```
          
          ```java
          public class Test {
              IntegerIterator iterator = new IntegerIterator();
              while(iterator.hasNext) { 
                  int nextValue = iterator.next();
                  ...
              }   
          }
          ```
          hasNext() -> next() 순서로 수행한다고 했을 때 
          개별 메서드는 동기화 되었음에도 버그가 발생할 수 있다.
          
          - 클라이언트/서버에서 잠금하도록 구현
          - 잠금을 구현하는 연결 서버를 구현
          
          ## 동기화하는 부분을 작게 만들어라
          - 락을 사용하여 임계영역을 보호하되 최대한 범위를 줄이고 수를 줄여야 한다.
          
          ## 올바른 종료 코드는 구현하기 어렵다
          - 종료 코드를 짜야한다면 시간을 투자해 `데드락`과 같은 문제를 사전에 방지할 수 있도록 구현해야 한다.
          
          ## 스레드 코드 테스트하기
          스레드 코드는 복잡하기에 고려해야할 사항이 아주 많다 
          충분한 테스트는 위험을 낮추 수 있다.
          
          - 말이 안되는 실패는 잠정적인 스레드 문제로 취급하라
              - 재현이 어렵거나 때때로 말이 안되는 오류를 단순한 `일회성` 문제로 치부하여 잘못된 코드가 쌓이지 않도록 한다. 
          
          - 다중 스레드를 고려하지 않은 순차 코드부터 제대로 돌게 만들자 
              - 스레드 환경 밖에서 생기는 버그와 스레드 환경에서 생기는 버그를 동시에 디버깅하지 않기 위해서 
           우선 스레드 환경 밖에서 코드를 올바르게 돌려야 한다.
          
          - 다중 스레드 코드가 다양한 상황이나 다양한 설정에서 사용 가능하도록 구현한다.
              - 다중 스레드를 쓰는 코드 부분을 다양한 환경에 쉽게 끼워 넣을 수 있게 스레드 코드를 구현하라
              - 다중 스레드를 쓰는 코드 부분을 상황에 맞게 조율할 수 있게 작성하라
              - 프로세서 수보다 많은 스레드를 돌려보라
              - 다른 플랫폼에서 돌려보라
          
          - 코드에 보조 코드를 넣어 돌려라 강제로 실패를 일으키게 해보라 
              - 실패하는 경로가 실행될 확률은 극도로 낮기때문에 일부러 코드를 다양한 순서로 실행하도록 한다.
                  - 직접 구현하기 
                  - 자동화
          
          ### 직접 구현하기 
           - 코드에 직접 wait(), sleep(), yield(), priority() 함수를 추가하여 실패할 가능성을 열어준다.
           - 적당한 삽입 및 호출 위치, 무작위적인 문제 존재 -> 보조 도구 사용
          
          ### 자동화
           - ThreadJigglePoint.jiggle() 과 같이 무작위로 스레드 메소드를 호출
           - 흔들기 기법을 사용해 오류를 찾아내라
          
          ## 결론
          >다중 스레드 코드는 올바로 구현하기 어렵기 때문에 각별히 주의하여 깨끗하게 코드를 짜야한다. 
          동시성 오류가 발생할 수 있는 잠정적인 원인을 철저히 이해하고 그것들을 해결하기 위한 라이브러리와 기본 알고리즘을 이해한다. 
          혹시라도 발생하는 문제를 일회성으로 넘기지 않고 다양한 방법으로 계속 테스트함으로써 코드가 올바르게 돌아가도록 노력해야 한다!들고 유지보수 비용이 적게 든다.
1. 좋은 이름을 선택한다.
2. 함수와 클래스 크기를 가능한 줄인다.
3. 표준 명칭을 사용한다.
    - ex) COMMAND 나 VISITOR 와 같은 표준 디자인 패턴을 사용해 구현된다면 클래스 이름에 패턴 이름을 넣어 다른 개발자가 클래스 설계 의도를 이해하게 쉽게 한다.
4. 단위 테스트 케이스를 꼼꼼히 작성한다.
    - 테스트 케이스는 소위 '예제로 보여주는 문서'다.
5. 가장 중요한 방법은 노력이다.


### 클래스와 메서드 수를 최소로 줄여라
- 클래스와 메서드 크기를 줄이기 위해 조그만 클래스와 메서드를 수없이 많이 만드는건 오히려 좋지 않다.
- 클래스마다 무조건 인터페이스를 생성한다던가, 자료 클래스와 동작 클래스는 무조건 분리해야 한다는 등의 독단적인 견해에 의해 무의미한 클래스와 메서드가 늘어나기도 한다.
- 목표는 함수와 클래스 크기를 작게 유지하면서 동시에 시스템 크기도 작게 유지하는 것이다.
- 하지만 4개 규칙 중 우선순위가 가장 낮다.


## 결론
단순한 설계 규칙을 따른다면 오랜 경험 후에야 익힐 수 있는 우수한 기법과 원칙을 단번에 활용할 수 있다.
