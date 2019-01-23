Chpater 3. 함수

프로그래밍 초창기에는 시스템을 루틴과 하위 루틴으로 나눴다.
어떤 프로그램이든 가장 기본적인 단위가 함수다. 

[목록3-2]
```java
    public class Refactoring {
    public static String renderPageWithSetupAndTeardowns(PageData pageData, boolean isSuite) throws Exception {
        		boolean isTestPage = pageData.hasAttribute("Test");
        		if (isTestPage) {
        			WikiPage testPage = pageData.getWikiPage();
                    StringBuffer newPageContent = new StringBuffer();
        			includeSetupPages(testPage, newPageContent, isSuite);
        			newPageContent.append(pageData.getContent());
        			includeTeardownPages(testPage, newPageContent, isSuite);
        			pageData.setcontent(newPageContent.toString());
        		}
        		return pageData.getHtml();
        	}
    }
```

3.1 작게 만들어라
	함수를 만드는 첫번째 규칙은 **'작게'!** 다 두 번째 규칙은 **더 작게** 이다.  
	함수는 짧을수록 좋다.  
    사실상 [목록3-2] 도 줄이면 더 좋다.
    
[목록3-3]
```java
    public class Refactoring {
    public static String renderPageWithSetupAndTeardowns(PageData pageData, boolean isSuite) throws Exception {
        		if (isTestPage(pageData))
        		    includeSetupAndTeardownPages(pageData, isSuite);
        		return pageData.getHtml();
        	}
    }
```

블록과 들여쓰기
	다시 말해 if / else / whil 등은 블록은 **한줄** 이어야 한다는 의미다.
	대게 거기서 함수를 호출한다. 그러면 바깥 함수가 작아질 뿐 아니라, 블록 안에서 호출하는 함수 이름을 적절히 짓는다면, 코드를 이해하기도 쉬워진다.
	그러므로 함수에서 들여쓰기 수준은 1단이나, 2단을 넘어서면 안된다.


3.2 한가지만 해라
	[목록 3-1] 은 여러가지를 처리한다.
	버퍼를 생성하고, 페이지를 가져오고....
	반면 [목록 3-3] 은 한가지만 처리한다. 설정 페이지와 해제 페이지를 테스트 페이지에 넣는다.
	지난 30년동안 여러가지 다양한 표현으로 프로그래머에게 주어진 충고다.


	함수는 한가지를 해야한다. 그 한가지를 잘해야 한다. 그 한가미나 해야 한다.

	하지만 [목록3-3] 은 한가지만 한다고 할 수 있을까?
	3가지를 한다고 주장할 수도 있다.
		1. 페이지가 테스트 페이지인지 판단한다.
		2. 그렇다면 설정페이지와 해제 페이지를 넣는다.
		3. 페이지를 HTML 로 렌더링 한다.

TO 문단으로 기술할 수 있다.
	TO RenderPageWithSetupAndTeardowns, 페이지가 테스트 페이지인지 확인한 후 테스트 페이지라면 설정페이지와 해제페이지를 넣는다.
	테스트 페이지든 아니든 페이지를 HTML 로 렌더링 한다.

	함수가 지정된 함수 이름아래에서 추상화 수준이 아닌 하나인 단계만 수행한다면 그 함수는 한가지 작업만 한다.
	또한가지 방법으로는 [목록 3-3]을 더이상 의미를 유지하며 줄이기가 불가능하다.


3.3 함수당 추상화 수준은 하나로
	한 함수 내에서 추상화 수준을 섞으면 코드를 읽는 사람이 헷갈린다. 
	특정 표현이 근본개념인지, 아니면 세부 사항인지 구분하기 어려운 탓이다.  
	문제는 근본 개념과, 세부사항이 뒤섞기 시작하면 꺠어진 창문처럼 마구 꺠지게 될것이다.

위에서 아래로 코드 일기: 내려가기 규칙

3.4 Switch문
	switch 문은 작게 만들기 어렵다. case 분기가 단 2개인 switch 문도 필자의 취향에는 너무 길며, 단일블록이나 함수를 선호한다.
	또한 한가지 작업만 하는 switch 문도 만들기 어렵다.



3.5 서술적인 이름을 사용하라.  
	함수에서 이상적인 인수 개수는 0개(무항)이다.
	다음은 1개.. 4개 이상은 특별한 이유가 필요하다. 특별한 이유가 있어도 사용하면 안된다.

	인사가 많아지면 테스트 관점에서도 어렵다.

많이 쓰는 단항 형식
	함수에 인수 1개를 넘기는 이유로 가장 흔한 경우는 두가지다.
	하나는 인수에 질문을 던지는 경우다.

	boolean fileExist("MyFile") 이 좋은예다.
	다른 하나는 인수로 뭔가를 변환해 결과를 반환하는 경우다.
	String 형의 파일이름을 InputStream 으로 변환한다.

	이 2가지 경우면 당연하게 받아들인다. 

	아주 유용한 단항함수 형식이 이벤트다. 
	이벤트 함수는 입력인수만 있다면 출력인수는 없다. 프로그램 함수는 함수 호출을 이벤트로 해석해 입력 인수로 시스템 상태를 바꾼다.

	passwordAttemptFiledNtimes(int attempts)가 좋은 예다. 그러므로 이름과 문맥을 주의해서 선택한다.

	void includeSetupPageInto(StringBuffer pageText)는 피한다.

	StringBuffertransform(StringBuffer in) 이 void transform(StirngBuffer out)보다 낫다.
	적어도 변환형태를 따라야할 필요가 있다. 
	==> 즉, void 로 입력함수로 변환을 하지 말자.

플래그 인수
	플래그 인수는 추하다. 함수로 부울 값을 넘기는 관례는 정말로 끔찍하다. 
	왜냐고? 함수가 한꺼번에 여러가지를 처라한다고 대놓고 공표하는 셈이니깐.

	프래그가 참이면 이걸하고 거짓이면 저걸한다는 소리니까!

이항 함수
	인수가 2개인 함수는 인수가 1개인 함수보다 이해하기 어렵다.
	writeFiled(name) 는 writeFile(outputStream, name) 보다 이해하기가 쉽다.
	둘다 의미가 명백하지만 전자가 더 빨리 이해된다. 후자는 잠시 주춤하지만 첫 인수를 무시해야 한다는 사실을 꺠닫는데 시간이 필요하다.

	그리고 사실은 결국 문제를 일으킨다. 
	어떤 코드든 절대로 무시하면 안되는데, 무시한 코드에 오류가 숨어들기 마련이다.
	물론 이항함수가 적절한 경우도 잇다. Point p = new Point(0,0) 가 좋은 예이다.
	직교 좌표 계점은 일반적으로 인수 2개를 취한다. new Point(0) 라면 오히려 더 놀랍다.

	심지어 아주 당연하게 여겨지는 이항함수 assertEquals(expected, actual) 에도 문제가 있다.
	expected 인수에 actual 값을 집어넣는 실수가 얼마나 많던가...?

	두 인수는 자연적인 순서가 없다. expected 다음에 actual 이 온다는 순서를 인위적으로 기억해야 한다.
삼항 함수
	이항 함수보다 어렵다.
	하지만 assertEquals(1.0, amount, .001)은 그리 음험하지 않은 삼항 함수다.
	여전히 주춤하지만, 그만한 가치가 충분하다. 부동소수점 비교가 상대적이라는 사실은 언제든 주지할 주용한 사항이다.

인수 객체
	인수가 2~3개 필요하다면 일부를 독자적 클래스 변수로 선언할 가능성을 짚어본다.

	Circle makeCircle(double x, double y, double radius);
	Circle makeCircle(Point center, double radius);

	객체를 생성해 인수를 줄이는 방법이 눈속임이라 여겨질지 모르지만, 그렇지 않다. 위 예제에서 x,y 를 묶었듯이
	변수를 묶어서 넘기려면 이름을 붙여야 하므로 결국은 개념을 표현하게 된다.

인수 목록 
	때로는 인수 개수가 가변적인 함수도 필요하다. String.format 메소드가 좋은 예다.


	String.format("%s worked %.2f" hours.", name, hours);

	위 예제처럼 가변인수 전부 동등하게 취급하면 List 형 인수 하나로 취급할 수 있다.

	실제로 String.format 선언부를 살펴보면 이항함수이다.

	public String format(String format, Object... args)

	경우에 따라 삼항 까지는 쓸 수 있지만 그 이상에는 문제가 있다.

동사와 키워드
	
3.7 부수 효과를 일으키지 마라.  
	부수효과는 거짓말이다. 함수에서 한가지를 하겠다고 약속하고서는 남몰래 다른짓도 하니까.
	떄로는 예상치 못하게 클래스 변수를 수정한다. 떄로는 함수로 넘어온 인수나 시스템 전역변수를 수정한다.
	어느쪽이든 교활한 해로운 거짓말이다. 많은 경우 일시적인 결합이나 순서 종속성을 초래한다.

	[목록3-6]
	```java
	    public class userValidator {
	    
	        private Cyptographer cyptographer;
	    
	        public boolean checkPassword(String userName, String password) {   
	            User user = UserGateway.findByName(userName);
	            if (user != User.NULL) {
	                String codedPhrase = user.getPhraseEncodedByPassword();
	                String phrase = cyptographer.decrypt(codedPhrase, password);
	                if ("Valid Password".equals(phrase)) {
	                    Session.initialize();
	                    return true;
	                }
	            }
	            return false;
	        }
	    }
	```


	여기서 함수가 일으키는 부수효과는 Session.initialize(); 호출이다.
	checkPassword 함수는 이름 그대로 암호를 확인한다. 이름만 봐서는 세션을 초기화 한다는 사실이 들어나지 않는다.
	checkPassword 는 세션을 초기화해도 괜찮은 경우에만 호출이 가능하다. 자칙 잘못하면 의도치않게 세션이 날라간다.

	[목록 3-6] 은 checkPasswordAndInitializeSession 이라는 이름이 훨씬 낫다.
	물론! 함수가 '한가지' 만 한다는 규칙을 위반하지만.

출력인수










































































