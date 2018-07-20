package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {

    @Test
    public void rubleToEuro() {
        Converter canv = new Converter();
            int result = canv.rubleToEuro(140);
            assertThat(result, is(2));
        }

    @Test
    public void rubleToDollar() {
        Converter canv = new Converter();
        int result = canv.rubleToDollar(120);
        assertThat(result, is(2));
    }

    @Test
    public void euroToRuble() {
        Converter canv = new Converter();
        int result = canv.euroToRuble(2);
        assertThat(result, is(140));
    }

    @Test
    public void euroToRuble1() {
        Converter canv = new Converter();
        int result = canv.dollarToRuble(2);
        assertThat(result, is(120));
    }
}