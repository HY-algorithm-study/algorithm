# Chapter 10. 클래스
클린한 클래스를 만들자

## 클래스 체계
- 클래스를 정의하는 자바 컨벤션에 따르면, 클래스 내부에서는 아래 순서대로 변수와 메서드를 선언한다.
    - `public static` 상수
    - `private static` 변수
    - `private` 인스턴스 변수
    - `public` 인스턴스 변수 (public 인스턴스 변수를 사용하는 경우는 거의 없음)
    - `public method`
    - `private method` : 자신을 호출하는 public method 직후에 넣는다.
- 위의 순서를 보면 추상화 단계가 순차적으로 내려가는 것을 알 수 있다.
- 프로그램 코드는 신문 기사처럼 읽힌다.(읽혀야 한다?)

### Java Code Convention
1. Oracle Java Code Convention : https://www.oracle.com/technetwork/java/codeconvtoc-136057.html
2. Google Java Style Guide : https://google.github.io/styleguide/javaguide.html

> ... What is important is that each class uses some logical order, which its maintainer could explain if asked. For example, new methods are not just habitually added to the end of the class, as that would yield "chronological by date added" ordering, which is not a logical ordering. ...

### 캡슐화
- 객체지향 설계 원리 중 하나 (추상화, 캡슐화, 일반화(상속), 다형성)
- 관련있는 변수와 함수를 클래스로 묶고 외부에는 정보를 은닉
- `결합도(Coupling)`는 낮추고 `응집도(Cohesion)`를 높이는 방법
- 종종 변수나 함수를 `protected`로 선언해서 테스트 코드에서 접근을 허용하기도 함
- 이렇게 하면 외부 코드와의 결합도 밑 의존도가 높아지기 때문에 나중에 코드를 수정하기 더 어려워질 수 있다. 최대한 비공개 상태를 유지하도록 하고, 캡슐화를 풀어주는 결정은 언제나 최후의 수단이다. (별도의 클래스로 분리하는 것도 좋은 선택이 될 수 있음.)

## 클래스는 작아야 한다!
- 클래스는 `작게` 만드는 것이 기본!
- 클래스가 맡은 `책임`을 센다

크기도 크고 책임도 많은 SuperDashboard 클래스
```java
public class SuperDashboard extends JFrame implements MetaDataUser {
    public String getCustomizerLanguagePath()
    public void setSystemConfigPath(String systemConfigPath) 
    public String getSystemConfigDocument()
    public void setSystemConfigDocument(String systemConfigDocument) 
    public boolean getGuruState()
    public boolean getNoviceState()
    public boolean getOpenSourceState()
    public void showObject(MetaObject object) 
    public void showProgress(String s)
    public boolean isMetadataDirty()
    public void setIsMetadataDirty(boolean isMetadataDirty)
    public Component getLastFocusedComponent()
    public void setLastFocused(Component lastFocused)
    public void setMouseSelectState(boolean isMouseSelected) 
    public boolean isMouseSelected()
    public LanguageManager getLanguageManager()
    // ...
    // 생략
}
```

메서드 개수를 줄인 SuperDashboard 클래스

```java
public class SuperDashboard extends JFrame implements MetaDataUser {
    public Component getLastFocusedComponent()
    public void setLastFocused(Component lastFocused)
    public int getMajorVersionNumber()
    public int getMinorVersionNumber()
    public int getBuildNumber() 
}
```
메서드 수가 작음에도 불구하고 여전히 `책임`이 너무 많다.
- 클래스 이름은 해당 클래스의 `책임`을 나타내야 한다. 
- 클래스 이름이 모호하다면 클래스의 책임이 너무 많다는 뜻이다. (Manager, Processor, Super 처럼 모호한 단어)
- 클래스에 대한 설명은 if, and, or, but을 사용하지 않고 25단어 이내로 설명이 가능해야 한다.

**Order 프로젝트의 OrderVendorItemStockManager**
```java
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@NotThreadSafe
@ToString
@SuppressWarnings("checkstyle:methodcount")
public class OrderVendorItemStockManager {
	@Autowired
	private NpsOrderCheckService npsOrderCheckService;
	@Autowired
	private VendorItemFinder vendorItemFinder;
	@Autowired
	private InventoryAdapterWrapper inventoryAdapterWrapper;
	@Autowired
	private VendorItemStockAdjuster vendorItemStockAdjuster;
	@Autowired
	private OrderWmsFulfillmentInventoryAdapter orderWmsFulfillmentInventoryAdapter;
	@Autowired
	private StockRestoreReservationService stockRestoreReservationService;
	@Autowired
	private OrderItemPurchasableCheckService orderItemPurchasableCheckService;
	@Autowired
	private OrderBenefitApiFreebieCancelAdapter orderBenefitApiFreebieCancelAdapter;
	@Autowired
	private OrderPromotionAdapter orderPromotionAdapter;
	@Autowired
	private OrderTicketAdapterService orderTicketAdapterService;
	private Long orderId;
	private String post;
	private List<OrderItem> orderItems;
	private Map<Long, VendorItem> vendorItemByVendorItemId;
	private boolean isNewPromotionOrder;
	private LocalDateTime paidAt;
	private List<StockRestoreReservation> stockRestoreReservations;	
	private OrderVendorItemStockVo preDecreasedStockVo;
	private OrderVendorItemStockVo postDecreaseStockVo;
	private Map<Long, RealStock> realStockByVendorItemId;
    private Set<Long> cancelExpectedFreebieVendorItemIds;
	private List<OrderItem> stockAdjustNeedCoupangWarehouseItems;
	private List<OrderItem> stockAdjustNeedThirdPartyItems;
	private List<OrderItem> stockAdjustNeedTicketReservationItems;
	private TicketReservationResultDto ticketReservationStockAdjustResult;
	private Map<Boolean, List<OrderItem>> coupangWarehouseStockAdjustResult;
	private Map<Boolean, List<OrderItem>> thirdPartyStockAdjustResult;
	private boolean isTicketReservationItemDecreaseStockRequested = false;
	private boolean isCoupangWarehouseItemDecreaseStockRequested = false;
	private boolean isThirdPartyItemDecreaseStockRequested = false;
	private boolean isPurchasableCheckRequired = false;

	public OrderVendorItemStockManager init(Long orderId, String post, List<OrderItem> orderItems, LocalDateTime paidAt)
	public OrderVendorItemStockManager cancelStockRestoreReservations()
	public OrderVendorItemStockManager partitioningOrderItemsAndCheckPurchasability()
	private void partitioningByPreDecreased()
	private void partitioningByFreebie()
	private void partitioningByFreebie(OrderVendorItemStockVo orderVendorItemStockVo)
	private void findVendorItems() 
	private void findRealStocks() 
	private void partitioningPreDecreasedChargedItemsByPurchasable() 
	private void partitioningPostDecreaseChargedItemsByPurchasable()
	private void findCancelExpectedFreebieVendorItemIdsBeforeStockAdjust() 
	private FreebieCanceledOrder predictFreebieCancel(List<OrderItem> chargedPurchasableItems)
	private void partitioningPreDecreasedFreebieItemsByPurchasable()
	private void partitioningPostDecreaseFreebieItemsByPurchasable() 
	public OrderVendorItemStockManager increaseSoldCountAndDecreaseStock() 
	private void fetchDecreaseStockTargetItems()
	private void increaseSoldCountAndDecreaseStockForCoupangWarehouseItems()
	private void increaseSoldCountAndDecreaseStockForThirdPartyItems()
	private void increaseSoldCountAndDecreaseStockForTicketReservationItems()
	private void findCancelExpectedFreebieVendorItemIdsAfterStockAdjust()
	private Set<Long> findCancelExpectedFreebieVendorItemIdsAfterStockAdjust(List<OrderItem> purchasableItems)
	private void confirmPreDecreasedItem()
	private void confirmPostDecreasedItem()
	public OrderVendorItemStockManager rollbackStockRestoreReservationForRefundableItems()
	public List<OrderItem> getPurchasableOrderItems()
	public BeforePurchaseOrderCancelCondition createCancelConditionIfNeeded()
	private List<OrderItem> getRefundableOrderItems()
	public void recoverStock()
	private void recoverCoupangWarehouseStock()
	private void recoverThirdPartyStock()
	private void recoverTicketReservationStock()
}
```

### 단일 책임 원칙(Single Response Principle, SRP)
- Robert C. Martin이 소개한 객체지향 설계 원칙 5개 중 하나
- 클래스나 모듈을 **변경해야 하는 이유는 단 하나**뿐이어야 한다는 원칙. 
- SRP는 `책임` 이라는 개념으로 클래스의 적절한 크기를 제시한다.

위의 SuperDashboard 코드에서 버전 정보를 다루는 메서드들을 따로 추출해서 독자적이고 재사용이 쉬운 Version 클래스를 만들 수 있다.

**Version.java**

```java
public class Version {
  public int getMajorVersionNumber()
  public int getMinorVersionNumber()
  public int getBuildNumber()
}
```

`SRP`는 객체지향 설계에서 중요한 개념이지만 개발자들이 가장 무시하는 규칙이기도 하다. 
- 대다수의 개발자는 `깨끗하고 체계적인 소프트웨어` 보다 `돌아가는 소프트웨어` 에 초첨을 맞춤. 
- 일단 돌아가는 소프트웨어를 작성하고 나면 (단일 책임 클래스로 분리하지 않고) 다음 문제로 넘어감.

많은 개발자들이 자잘한 단일 책임 클래스가 많아지면 큰 그림을 이해하기 어려워진다고 걱정하지만, 어느 시스템이든 익힐 내용은 비슷하다. 

> "도구 상자를 어떻게 관리하고 싶은가? 작은 서랍을 많이 두고 기능과 이름이 명확한 컴포넌트를 나눠 넣고 싶은가? 아니면 큰 서랍 몇개를 두고 모두를 던져 넣고 싶은가?"

규모가 어느 정도 큰 시스템은 논리도 많고 복잡하기 때문에 체계적인 정리가 필수이다. 작은 클래스 여럿으로 이뤄진 시스템이 더 바람직하다.

### 응집도 Cohesion
- 클래스 내부에 존재하는 변수와 함수등의 밀접한 정도를 나타낸다. 
- 모든 인스턴스 변수를 멤서드마다 사용하는 클래스는 응집도가 가장 높다고 할 수 있다. 
- 응집도가 높다는 말은 클래스에 속한 메서드와 변수가 서로 의존하며 논리적인 단위로 묶인다는 의미로도 볼 수 있다.

**응집도가 높은 Stack 클래스 코드**
```java
public class Stack {
    private int topOfStack = 0;
    List<Integer> elements = new LinkedList<Integer>();

    public int size() {
        return topOfStack;
    }

    public void push(int element) {
        topOfStack++;
        elements.add(element);
    }

    public int pop() throws PoppedWhenEmpty {
        if (topOfStack == 0)
            throw new PoppedWhenEmpty();
        int element = elements.get(--topOfStack);
        elements.remove(topOfStack);
        return element;
    }
}
```

**응집도를 유지하면 작은 클래스 여럿이 나온다**
- 큰 함수를 작은 함수로 쪼개다보면 클래스는 응집력을 잃게된다, 
- 이렇게 응집력이 낮아진 클래스는 더 작은 클래스 여럿으로 나눌 수 있게 된다. 
- 하나의 큰 클래스가 작은 클래스로 쪼개지면서 프로그램은 점점 더 체계가 잡히고 구조가 투명해진다.

**PrintPrimes.java**
```java
public class PrintPrimes {
    public static void main(String[] args) {
        final int M = 1000;
        final int RR = 50;
        final int CC = 4;
        final int WW = 10;
        final int ORDMAX = 30;
        int P[] = new int[M + 1];
        int PAGENUMBER;
        int PAGEOFFSET;
        int ROWOFFSET;
        int C;
        int J;
        int K;
        boolean JPRIME;
        int ORD;
        int SQUARE;
        int N;
        int MULT[] = new int[ORDMAX + 1];

        J = 1;
        K = 1;
        P[1] = 2;
        ORD = 2;
        SQUARE = 9;

        while (K < M) {
            do {
                J = J + 2;
                if (J == SQUARE) {
                    ORD = ORD + 1;
                    SQUARE = P[ORD] * P[ORD];
                    MULT[ORD - 1] = J;
                }
                N = 2;
                JPRIME = true;
                while (N < ORD && JPRIME) {
                    while (MULT[N] < J)
                        MULT[N] = MULT[N] + P[N] + P[N];
                    if (MULT[N] == J)
                        JPRIME = false;
                    N = N + 1;
                }
            } while (!JPRIME);
            K = K + 1;
            P[K] = J;
        }
        {
            PAGENUMBER = 1;
            PAGEOFFSET = 1;
            while (PAGEOFFSET <= M) {
                System.out.println("The First " + M + " Prime Numbers --- Page " + PAGENUMBER);
                System.out.println("");
                for (ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++) {
                    for (C = 0; C < CC;C++)
                        if (ROWOFFSET + C * RR <= M)
                            System.out.format("%10d", P[ROWOFFSET + C * RR]);
                    System.out.println("");
                }
                System.out.println("\f"); PAGENUMBER = PAGENUMBER + 1; PAGEOFFSET = PAGEOFFSET + RR * CC;
            }
        }
    }
}
```

함수 하나로 이루어진 위의 프로그램은 리팩토링을 통해 역할에 맞는 클래스로 분리할 수 있다.

- **PrimePrinter** : main 함수를 포함하며 실행 환경에 대한 책임을 갖는다.
- **RowColumnPagePrinter** : 숫자 목록을 행과 열에 맞추어 페이지에 출력하는 책임을 갖는다.
- **PrimeGenerator** : 소수 목록을 생성하는 책임을 갖는다.

**PrimePrinter.java**
```java
public class PrimePrinter {
    public static void main(String[] args) {
        final int NUMBER_OF_PRIMES = 1000;
        int[] primes = PrimeGenerator.generate(NUMBER_OF_PRIMES);

        final int ROWS_PER_PAGE = 50; 
        final int COLUMNS_PER_PAGE = 4; 
        
      	RowColumnPagePrinter tablePrinter = 
            new RowColumnPagePrinter(ROWS_PER_PAGE, COLUMNS_PER_PAGE, 
                        "The First " + NUMBER_OF_PRIMES + " Prime Numbers");
        
      	tablePrinter.print(primes); 
    }
}
```

**RowColumnPagePrinter.java**
```java
public class RowColumnPagePrinter { 
    private int rowsPerPage;
    private int columnsPerPage; 
    private int numbersPerPage; 
    private String pageHeader; 
    private PrintStream printStream;

    public RowColumnPagePrinter(int rowsPerPage, int columnsPerPage, String pageHeader) { 
        this.rowsPerPage = rowsPerPage;
        this.columnsPerPage = columnsPerPage; 
        this.pageHeader = pageHeader;
        numbersPerPage = rowsPerPage * columnsPerPage; 
        printStream = System.out;
    }

    public void print(int data[]) { 
        int pageNumber = 1;
        for (int firstIndexOnPage = 0 ; 
            firstIndexOnPage < data.length ; 
            firstIndexOnPage += numbersPerPage) { 
            int lastIndexOnPage =  Math.min(firstIndexOnPage + numbersPerPage - 1, data.length - 1);
            printPageHeader(pageHeader, pageNumber); 
            printPage(firstIndexOnPage, lastIndexOnPage, data); 
            printStream.println("\f");
            pageNumber++;
        } 
    }

    private void printPage(int firstIndexOnPage, int lastIndexOnPage, int[] data) { 
        int firstIndexOfLastRowOnPage =
        firstIndexOnPage + rowsPerPage - 1;
        for (int firstIndexInRow = firstIndexOnPage ; 
            firstIndexInRow <= firstIndexOfLastRowOnPage ;
            firstIndexInRow++) { 
            printRow(firstIndexInRow, lastIndexOnPage, data); 
            printStream.println("");
        } 
    }

    private void printRow(int firstIndexInRow, int lastIndexOnPage, int[] data) {
        for (int column = 0; column < columnsPerPage; column++) {
            int index = firstIndexInRow + column * rowsPerPage; 
            if (index <= lastIndexOnPage)
                printStream.format("%10d", data[index]); 
        }
    }

    private void printPageHeader(String pageHeader, int pageNumber) {
        printStream.println(pageHeader + " --- Page " + pageNumber);
        printStream.println(""); 
    }

    public void setOutput(PrintStream printStream) { 
        this.printStream = printStream;
    } 
}
```

**PrimeGenerator.java**
```java
public class PrimeGenerator {
    private static int[] primes;
    private static ArrayList<Integer> multiplesOfPrimeFactors;

    protected static int[] generate(int n) {
        primes = new int[n];
        multiplesOfPrimeFactors = new ArrayList<Integer>(); 
        set2AsFirstPrime(); 
        checkOddNumbersForSubsequentPrimes();
        return primes; 
    }

    private static void set2AsFirstPrime() { 
        primes[0] = 2; 
        multiplesOfPrimeFactors.add(2);
    }

    private static void checkOddNumbersForSubsequentPrimes() { 
        int primeIndex = 1;
        for (int candidate = 3 ; primeIndex < primes.length ; candidate += 2) { 
            if (isPrime(candidate))
                primes[primeIndex++] = candidate; 
        }
    }

    private static boolean isPrime(int candidate) {
        if (isLeastRelevantMultipleOfNextLargerPrimeFactor(candidate)) {
            multiplesOfPrimeFactors.add(candidate);
            return false; 
        }
        return isNotMultipleOfAnyPreviousPrimeFactor(candidate); 
    }

    private static boolean isLeastRelevantMultipleOfNextLargerPrimeFactor(int candidate) {
        int nextLargerPrimeFactor = primes[multiplesOfPrimeFactors.size()];
        int leastRelevantMultiple = nextLargerPrimeFactor * nextLargerPrimeFactor; 
        return candidate == leastRelevantMultiple;
    }

    private static boolean isNotMultipleOfAnyPreviousPrimeFactor(int candidate) {
        for (int n = 1; n < multiplesOfPrimeFactors.size(); n++) {
            if (isMultipleOfNthPrimeFactor(candidate, n)) 
                return false;
        }
        return true; 
    }

    private static boolean isMultipleOfNthPrimeFactor(int candidate, int n) {
        return candidate == smallestOddNthMultipleNotLessThanCandidate(candidate, n);
    }

    private static int smallestOddNthMultipleNotLessThanCandidate(int candidate, int n) {
        int multiple = multiplesOfPrimeFactors.get(n); 
        while (multiple < candidate)
            multiple += 2 * primes[n]; 
        multiplesOfPrimeFactors.set(n, multiple); 
        return multiple;
    } 
}
```

리팩토링 후 코드가 길어진 이유
- 리팩토링 후 더 길고 서술적인 이름을 사용함
- 주석대신 함수 선언과 클래스 선언으로 코드를 설명함
- 가독성을 높히기 위해 공백을 추가하고 형식을 맞춤

## 변경하기 쉬운 클래스
소프트웨어는 변경이 불가피하다. 그리고 변경이 있을 때 마다 의도대로 동작하지 않을 위험이 따른다. 깨끗한 시스템은 클래스를 체계적으로 관리해 변경에 따르는 위험을 최대한 낮춘다.

**Sql.java** 변경이 필요해 손대야 하는 클래스
```java
public class Sql {
    public Sql(String table, Column[] columns)
    public String create()
    public String insert(Object[] fields)
    public String selectAll()
    public String findByKey(String keyColumn, String keyValue)
    public String select(Column column, String pattern)
    public String select(Criteria criteria)
    public String preparedInsert()
    private String columnList(Column[] columns)
    private String valuesList(Object[] fields, final Column[] columns) 
    private String selectWithCriteria(String criteria)
    private String placeholderList(Column[] columns)
}
```

위 클래스는 다음 2가지 이유로 `SRP`를 위반한다.

1. 새로운 SQL문을 지원하려면 반드시 Sql 클래스를 수정해야 한다.
2. 기존의 SQL문 하나를 수정할 때도 Sql 클래스를 수정해야 한다.

`selectWithCriteria()` 메서드의 경우 일부 메서드에서만 사용되는데, 이렇게 일부에서만 사용되는 private method는 코드를 개선할 잠재적 여지가 있음을 시사한다.

Sql 클래스에 있던 public method들을 각각 Sql에서 파생하는 클래스로 쪼개면 아래 코드와 같이 만들 수 있다.

**Sql.java**
```java
abstract public class Sql {
	public Sql(String table, Column[] columns) 
	abstract public String generate()
}

public class CreateSql extends Sql {
	public CreateSql(String table, Column[] columns) 
	@Override
    public String generate()
}

public class SelectSql extends Sql {
	public SelectSql(String table, Column[] columns) 
	@Override 
    public String generate()
}

public class InsertSql extends Sql {
	public InsertSql(String table, Column[] columns, Object[] fields) 
	@Override 
    public String generate()
	private String valuesList(Object[] fields, final Column[] columns)
}

public class SelectWithCriteriaSql extends Sql { 
	public SelectWithCriteriaSql(String table, Column[] columns, Criteria criteria) 
	@Override 
    public String generate()
}

public class SelectWithMatchSql extends Sql { 
	public SelectWithMatchSql(String table, Column[] columns, Column column, String pattern) 
	@Override 
    public String generate()
}

public class FindByKeySql extends Sql {
    public FindByKeySql(String table, Column[] columns, String keyColumn, String keyValue)
	@Override 
    public String generate()
}

public class PreparedInsertSql extends Sql {
	public PreparedInsertSql(String table, Column[] columns) 
	@Override 
    public String generate()
	private String placeholderList(Column[] columns)
}

public class Where {
	public Where(String criteria) public String generate()
	public String generate()
}

public class ColumnList {
	public ColumnList(Column[] columns) public String generate()
	public String generate()
}
```
클래스를 분리한 결과
- 클래스가 서로 분리되어 각각의 클래스가 극도로 단순해짐, 
- 테스트 관점에서 모든 논리를 증명하기도 쉬워짐. 
- 새로운 update문을 추가한다 해도 기존 클래스를 변경할 필요가 없음. 

위 코드는 아래와 같은 장점이 있다.

1. `SRP`를 만족한다.
2. `OCP`를 만족한다. (Open-Closed Principle, 확장에는 열려있고 수정에는 닫혀있어야 한다는 원칙)

### 변경으로부터 격리
- 요구사항은 변하기 마련이므로 그에 따라 코드도 변하기 마련이다. 
- 상세한 구현에 의존하는 클래스는 그 구현이 바뀌면 위험에 빠지고 테스트가 어렵다. 
- 구체적인 구현 코드와의 결합을 낮추기 위해 `인터페이스`와 `추상클래스`를 사용할 수 있다.

Porfolio라는 클래스를 만든다고 가정 해보면,

- 이 클래스는 외부 TokyoStockExchange API를 사용해 값을 계산
- 때문에 테스트 코드는 시세 변화에 영향을 받음
- 5분마다 값이 달라지는 API로 테스트 코드를 짜기는 쉽지 않음

Porfolio 클래스에서 TokyoStockExchange를 직접 호출하지 않고 인터페이스에 의존하도록 한다.

```java
public insterface StockExchange {
	Money currentPrice(String symbol);
}
```

``` java
public Portfolio {
	private StockExchange exchange;
	public Portfolio(StockExchange exchange) {
		this.exchange = exchange;
	}
	// ...
}
```

이제 TokyoStockExchange를 대신할 테스트용 클래스를 만들 수 있다.

```java
public class PortfolioTest {
	private FixedStockExchangeStub exchange;
	private Portfolio portfolio;

	@Before
	protected void setUp() throws Exception {
		exchange = new FixedStockExchangeStub();
		exchange.fix("MSFT", 100);
		portfolio = new Portfolio(exchange);
	}

	@Test
	public void GivenFiveMSFTTotalShouldBe500() throws Exception {
		portfolio.add(5, "MSFT");
		Assert.assertEquals(500, portfolio.value());
	}
}
```

위와 같이 결합도를 최소로 줄이면 아래와 같은 장점이 있다.
- 자연스럽게 또 다른 설계 원칙인 `DIP(Dependency Inversion Principal)`를 따르는 클래스가 나온다. 
- 각 클래스간의 결합도를 낮추면 유연성과 재사용성이 높아진다. 
- 시스템 요소가 격리되어 있으면 각 요소를 이해하기도 쉬워진다.


## 정리

- **캡슐화** : 구체적인 구현 부분은 외부로부터 감추어서 **응집도는 높히고 결합도는 낮추자**.
- **SRP** : 클래스를 변경하는 이유, 즉 **책임은 하나여야 한다**.
- **응집도** : 응집도가 높을 수록 클래스에는 꼭 필요한 구성요소만 모여있기 때문에 코드를 이해하고 유지보수 하는데 도움이 된다.
- **OCP** : 새로운 기능을 추가할 때는 기존 코드를 수정하지 않고도 가능해야 한다.
- **DIP** : 구체적인 것 보다는 추상적인 것에 의존하도록 하여 클래스간 결합도를 낮추자.
