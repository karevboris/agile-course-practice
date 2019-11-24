package ru.unn.agile.QuadraticEquasion.Model;

import org.junit.Test;
import ru.unn.agile.QuadraticEquasion.model.Formatter;
import ru.unn.agile.QuadraticEquasion.model.QuadraticEquasion;

import static org.junit.Assert.assertEquals;

public class FormatterTests {

    @Test
    public void canFormatCoeff() {
        double x = 123456.123;
        StringBuilder s = new StringBuilder();

        String result = Formatter.formatCoeff(x, s).toString();

        assertEquals("123456.12", result);
    }

    @Test
    public void canFormatNegativeCoeff() {
        double x = -9876.543;
        StringBuilder s = new StringBuilder();

        String result = Formatter.formatCoeff(x, s).toString();

        assertEquals(" - 9876.54", result);
    }

    @Test
    public void canFormatZeroCoeff() {
        StringBuilder s = new StringBuilder();

        String result = Formatter.formatCoeff(0, s).toString();

        assertEquals("", result);
    }

    @Test
    public void canConvertToString() {
        QuadraticEquasion qe = new QuadraticEquasion(2.0, 2.0, 3.0);
        assertEquals("2.00(x^2) + 2.00x + 3.00", qe.toString());
    }

    @Test
    public void canConvertFloatingCoeffsToString() {
        QuadraticEquasion qe = new QuadraticEquasion(1.11, 2.22, 3.33);
       assertEquals("1.11(x^2) + 2.22x + 3.33", qe.toString());
    }

    @Test
    public void canConvertNegativeCoeffsToString() {
        QuadraticEquasion qe = new QuadraticEquasion(-1.0, -2.0, -3.0);
        assertEquals(" - (x^2) - 2.00x - 3.00", qe.toString());
    }

    @Test
    public void canConvertCoeffsEqualsToOneToString() {
        QuadraticEquasion qe = new QuadraticEquasion(1.0, -1.0, 1.0);
        assertEquals("(x^2) - x + 1.00", qe.toString());
    }

    @Test
    public void canConvertWithZeroACoeffToString() {
        QuadraticEquasion qe = new QuadraticEquasion(0.0, -1.0, 1.0);
        assertEquals(" - x + 1.00", qe.toString());
    }

    @Test
    public void canConvertWithZeroBCoeffToString() {
        QuadraticEquasion qe = new QuadraticEquasion(1.0, 0.0, 1.0);
        assertEquals("(x^2) + 1.00", qe.toString());
    }

    @Test
    public void canConvertWithZeroCCoeffToString() {
        QuadraticEquasion qe = new QuadraticEquasion(1.0, -1.0, 0.0);
        assertEquals("(x^2) - x", qe.toString());
    }

    @Test
    public void canConvertWithTwoZeroCoeffsToString() {
        QuadraticEquasion qe = new QuadraticEquasion(0.0, 1.0, 0.0);
        assertEquals("x", qe.toString());
    }
}
