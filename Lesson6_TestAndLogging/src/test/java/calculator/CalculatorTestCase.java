package calculator;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTestCase {


    private ICalcucator calc;

    @Before
    public void prepare() {
        this.calc = new Calculator();
    }

    @After
    public void tearDown() {
        this.calc = null;
    }

    @Test
    public void test_sum1() {
        Assert.assertEquals(4, calc.add(2, 2));
    }

    @Test
    public void test_sum2() {
        Assert.assertEquals(16, calc.add(7, 9));
    }

    @Test
    public void test_mul() {
        Assert.assertThat(calc.mul(-5, 10), Is.is(-50));
    }


    @Test
    public void test_div() {
        Assert.assertThat(calc.div(15, 3), Is.is(5));
    }

    @Test
    public void test_pow() {
        Calculator calculator = new Calculator();
        Assert.assertThat(calculator.power(2, 3), Is.is(8));
    }


    @Test(expected = ArithmeticException.class)
    public void test_div_by_zero() {
        Assert.assertThat(calc.div(15, 0), Is.is(0));
    }
}
