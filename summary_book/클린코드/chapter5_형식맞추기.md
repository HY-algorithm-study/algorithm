## Capter5 형식 맞추기

프로그래머라면 형식을 깔끔하게 맞춰서 코드를 짜야 한다.
코드 형식을 맞추기 위한 간단한 규칙을 정하고, 그 규칙을 착실히 따라야 한다.

### 5.1 형식을 맞추는 목적.  
   코드형식 즉 컨벤션은 의사소통의 일환이다. 오랜시간 지나 코드의 흔적을 더이상 
   찾아보기 어려울 정도로 코드가 바뀌어도 맨 처음에 잡아 놓은 구현 스타일과 
   가독성 수준은 유지보수 용이성과 확장성에 계속해서 영향을 미친다.
   
### 5.2 적절한 행 길이를 유지하라.
   일반적으로 큰 파일보다, 작은 파일이 이해하기가 쉽다.
   
#### 신문 기사처럼 작성하라.  
   신문은 다양한 기사로 이뤄진다. 대다수 기사가 아주 짧고 어떤 기사는 조금 길다.
   소스파일도 신문 기사와 비슷하게 작성한다. 이름은 간단하면서도 설명적으로 짓는다.
   이름만 보고도 올바른 모듈을 살펴보고 있는지 아닌지를 판단할 정도로 신경 써서 짓는다.  
   
   소스파일 첫 부분은 고차원 개념과 알고리즘을 설명한다. 아래로 내려갈수록 의도를 세세하게 묘사한다.
   마지막에는 가장 저차원 함수와 세부내역이 나온다.
   
   신문이 무작위로 뒤섞인 긴 기사 하나만 있다면 아무도 읽지 않을리라.
   
#### 개념은 빈 행으로 분리하라.
   생각 사이는 빈 행을 넣어서 분리해야 마땅하다. 
   예를 들어 [목록 5-1] 을 살펴보자 너무도 간단한 규칙이지만 코드의 세로 레이아웃에 영향을 미친다.
   
[목록 5-1]
```java
    package fitness.wikitext.widgets;

    import java.util.regex.*;
    
    public class BoldWidget extends ParentWidget {
        public static final String REGEXP = "'''.+?'''";
        private  static final Pattern pattern = Pattern.compile("'''(.+?)'''", Pattern.MULTILINE + Pattern.DOTALL);
        
        public BoldWidget(ParentWidget parent, String text) throws Exception {
            super(parent);
            Matcher match = parent.matcher(text);
            match.find();
            addChildWidgets(match.group(1));
        }
            
        public String render() throws Exception {
            StringBuffer html = new StringBuffer("<br>");
            html.append(childHtml()).append("</br>");
            return html.toString();
        }
    }
```
[목록 5-2]
```java
    package fitness.wikitext.widgets;

    import java.util.regex.*;
    
    public class BoldWidget extends ParentWidget {
        public static final String REGEXP = "'''.+?'''";
        private  static final Pattern pattern = Pattern.compile("'''(.+?)'''", Pattern.MULTILINE + Pattern.DOTALL);
        public BoldWidget(ParentWidget parent, String text) throws Exception {
            super(parent);
            Matcher match = parent.matcher(text);
            match.find();
            addChildWidgets(match.group(1));
            }
        public String render() throws Exception {
            StringBuffer html = new StringBuffer("<br>");
            html.append(childHtml()).append("</br>");
            return html.toString();
        }
    }
```

[5-1] 에 비해 [5-2] 는 암호처럼 보인다.

#### 세로 밀집도
   세로 여백이 개념을 분리한다면 세로 밀집도는 연광성을 의미한다.   
   즉, 서로 밀집한 코드행은 세로로 가까이 놓여야 한다는 뜻이다.
   
#### 수직 거리
   함수 연관 관계와 동작 방식을 이해하려고 이 함수에서 저 함수로 오가며 소스 파일을 위아래로 뒤지는 등 뺑뺑이를 돌았으나, 
   결국은 미로 같은 코드 때문에 혼란만 가중된 경험이 있는가?
   
   시스템이 무엇을 하는지 이해하고 싶은데, 이조각 저조각이 어디에 있는지 찾고 기억하느라 시간과 노력을 소모한다.  
   서로 밀접한 개념은 세로로 가까이 두어야 한다. 물론 두 개념이 서로 다른 파일에 속한다면 규칙이 통하지 않는다.
   하지만 타당한 근거가 없다면 서로 밀접한 개념은 한 파일속에 해야 마땅하다.  
   
   이게 바로 protected 변수를 피해야 하는 이유중 하나다. 
   
#### 변수 선언
   변수는 사용하는 위치에 최대한 가까이 선언한다.
   
#### 인스턴스 변수
   반면에 인스턴스 변수는 클래스 처음에 선언한다. 변수 간에 세로로 거리를 두지 않는다.
   잘 설계한 클래스는 많은 (혹은 대다수) 클래스 메소드가 인스턴스 변수를 사용하기 때문이다.
   
   인스턴스 변수를 선언하는 위치는 아직도 논쟁이 분분하다.
   일반적으로 C++ 에서는 모든 인스턴스 변수를 클래스 마지막에 선언한다는 소위 가위 규칙을 적용한다.
   하지만 자바에서는 보통 클래스 맨 처음에 인스턴스를 선언한다.
   
   하지만 어느쪽이든 잘 알려진 위치에 변수를 모은다는 사실이 중요하다.
   
#### 종속함수  










































