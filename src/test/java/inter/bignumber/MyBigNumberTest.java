package inter.bignumber;

import org.junit.*;

import static org.junit.Assert.*;

public class MyBigNumberTest {
    private BigNumber n1;
    private BigNumber n2;

    @Test
    public void add() {
        assertEquals("20504962648058829508635007156124476621", n1.add(n2).toString());
    }

    @Ignore
    @Test
    public void sub() {
    }

    @Test
    public void compareTo() {
    }

    @Before
    public void setUp() throws Exception {
        n1 = new MyBigNumber("20504962648058829508634537240139148734");
        n2 = new MyBigNumber("469915985327887");
    }

    @After
    public void tearDown() throws Exception {
    }
}