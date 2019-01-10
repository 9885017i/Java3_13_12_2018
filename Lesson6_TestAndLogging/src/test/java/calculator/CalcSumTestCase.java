package calculator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalcSumTestCase {

    private ICalcucator calc;

    @Before
    public void prepare() {
        this.calc = new Calculator();
    }

    @After
    public void tearDown() {
        this.calc = null;
    }


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0, 0, 0},
                {2, 2, 4},
                {3, 9, 12},
                {5, -8, -3},
                {8, 92, 100},
                {13, 0, 12},
        });
    }


    private int a;
    private int b;

    private int res;

    public CalcSumTestCase(int a, int b, int res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Test
    public void sum() {
        Assert.assertEquals(String.format("(a=%d, b=%d)", a, b), res, calc.add(a, b));
    }
}
