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
	인수를 출력으로 사용할 떄는 어색함을 느낀다. 예를 들어
	appendFooter(s) 이 함수는 s를 바닥글로 첨부할까? 아니면 s 에 바닥글을 첨부할까
	public void appendFooter(StringBuffer report) 인수 s가 출력인수가 아니라는 사실은 분명하지만 함수 선언부를 찾아보고 나서야 알았다.

	함수를 선언부를 찾아보는 행위는 코드를 보다 주춤하는 행위와 동급이다.
	-> 인지적으로 거슬린다는 뜻이므로 피해야 한다.

	일반적으로 출력인수는 피해야 한다. 함수에서 뭔가의 상태를 변경해야 한다면 함수가 속하는 객체의 상태를 변경하는 방법을 택한다.

3.8 명령과 조회를 분리하라.  
	함수는 뭔가를 수행하거나, 뭔가에 답하거나 둘중하나만 해야 한다. 둘다 하면 안된다.
	둘다하면 혼란을 초래한다.

	public boolean set(String attribute, String value);
	이 함수는 attribute 속성을 찾아 값을 value 로 설정한후 성공하면 true, 실패하면false 를 반환한다.

	이런 괴상한 코드가 나온다.
	if(set("username", "unclebob")) ...

	독자 입장에서는 코드를 읽을때 username 을 unclebob 으로 설정하는 함수인가 아니면 확인하는 코드인가 애매하다.

	set 이라는 단어가 동사인지, 형용사인지 분간하기 어려운 탓이다.
	함수는 구현한 개발자는 'set' 을 동사로 의도했지만
	
	if 문에 넣고 보면 형용사 처럼 느껴진다. 그래서 "if 문은 "username" 속성이 unclebob 으로 설정되어 있따면.." 로 읽힌다.


	if (attributeExist("username)")) {
		setAttibute("username", "unclebob");
		...
	}

	혼란을 애초에 뿌리뽑는 방법이다.

3.9 오류 코드보다 예외를 사용하라.  
	명령 함수에서 오류 코드를 반환하는 방식은 명령/조회 분리 규칙을 미묘하게 위반한다.

try / catch 블록 뽑아내기  
	try / catch 은 원래가 추하다. 그러므로 블록을 별도 함수로 뽑아내는 편이 낫다.
```java
    public class userValidator {
        public void delete(Page page) {
            try{
                deletePageAndAllReferences(page);
            }catch (Exception e) {
                logError(e);
            }
        }
        
        private void deletePageAndAllReferences(Page page) throws Exception {
            deletePage(page);
            registry.deleteReference(page.name);
            configKeys.deleteKey(page.name.makeKey());
        }
        
        private void logError(Exception e) {
            logger.log(e.getMessage());
        }
        
    }
```
	위에서 delete 함수는 오류만 처리한다. 그래서 코드를 이해하기 쉽다.

오류처리도 한가지 작업이다.  
	오류가 처리도 '한가지' 작업에 속한다. 그러므로 오류를 처리하는 함수는 오류만 처리해야 마땅하다.

3.11 구조적 프로그래밍

3.12 함수를 어떻게 짜죠?  
	소프트웨어를 짜는 행위는 여느 글짓기와 비슷하다. 
	논문이나 기사를 쓸때 먼저 생각을 기록한 후 읽기 좋게 다듬는다. 초안은 대게 서투르고 어수선하므로 원하는 대로 읽힐떄 까지 말을 다듬고 문장을 고치고 문단을 정리한다.
	필자가 함수를 짤때에도 마찬가지다. 처음에는 길고 복잡하다, 들여쓰게 단계도 많고, 중복된 루프도 많다.
	인수 목록도 아주 길다. 이름은 즉흥적이고 코드는 중복된다. 하지만 필자는 서투른 코드를 빠짐없이 테스트하는 단위 테스트 케이스도 만든다.

	그런 다음 필자는 코드를 다듬고, 함수를 만들고, 이름을 바꾸고 중복을 제거한다. 메소드를 줄이고 순서를 바꾼다.
	때로는 전체 클래스를 쪼개기도 한다. 이와중에도 코드는 항상 단위테스트를 통과한다.

3.13 결론  
	프로그래밍의 기술은 언제나 언어의 설계의 기술이다. 예전에도 그랬고 지금도 마찬가지다.
	대가 프로그래머는 시스템을 (구현할) 프로그램이 아니라 (풀어갈) 이야기로 여긴다.
	프로그래밍 언어라는 수단을 사용해 좀더 풍부하고 좀더 표현력이 강한 언어를 만들어 이야기를 풀어간다.

	여기서 설명한 규칙을 따른다면 길이가 짧고, 이름이 좋고, 쳬계가 잡힌 함수가 나오리라. 하지만 진짜 목표는 시스템이라는 이야기를 풀어가는데 있다는 사실을 명심하기를 바란다.
	여러분이 작성하는 함수가 분명하고 정확한 언어에 깔끔하게 맞아야 이야기를 풀어가기 쉬워진다는 사실을 기억하기를 바란다. 



