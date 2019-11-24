package ru.unn.agile.QuadraticEquasion.Model;

import org.junit.Test;
import ru.unn.agile.ComplexNumber.model.ComplexNumber;
import ru.unn.agile.QuadraticEquasion.model.QuadraticEquasion;

import static org.junit.Assert.*;

public class QuadraticEquasionTests {

    private final double delta = 0.01;

    @Test
    public void canCreateWithPositiveIndexes() {
        QuadraticEquasion qe = new QuadraticEquasion(1, 2, 3);
        assertNotNull(qe);
    }

    @Test
    public void canCreateWithNegativeIndexes() {
        QuadraticEquasion qe = new QuadraticEquasion(-1, -2, -3);
        assertNotNull(qe);
    }

    @Test
    public void canCreateWithZeroIndexes() {
        QuadraticEquasion qe = new QuadraticEquasion(1, 0, 0);
        assertNotNull(qe);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateWithAllZeroIndexes() {
        QuadraticEquasion qe = new QuadraticEquasion(0, 0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void canNotCreateWithAAndBZeroIndexes() {
        QuadraticEquasion qe = new QuadraticEquasion(0, 0, 10);
    }

    @Test public void canSolveSimpleEquasion() {
        QuadraticEquasion qe = new QuadraticEquasion(2, 2, -4);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(1, 0);
        solution[1] = new ComplexNumber(-2, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
        assertEquals(solution[1], actual[1]);
    }

    @Test public void canSolveEquasionWithZeroACoeff() {
        QuadraticEquasion qe = new QuadraticEquasion(0, 3, -9);
        ComplexNumber[] solution = new ComplexNumber[1];
        solution[0] = new ComplexNumber(3, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
    }

    @Test public void solutionWithZeroACoeffGivesOneX() {
        QuadraticEquasion qe = new QuadraticEquasion(0, 4, -16);
        ComplexNumber[] solution = new ComplexNumber[1];
        solution[0] = new ComplexNumber(4, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(1, solution.length);
    }

    @Test public void canSolveEquasionWithZeroBCoeff() {
        QuadraticEquasion qe = new QuadraticEquasion(1, 0, -49);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(7, 0);
        solution[1] = new ComplexNumber(-7, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
        assertEquals(solution[1], actual[1]);
    }

    @Test public void canSolveEquasionWithZeroCCoeff() {
        QuadraticEquasion qe = new QuadraticEquasion(4, 12, 0);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(0, 0);
        solution[1] = new ComplexNumber(-3, 0);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0], actual[0]);
        assertEquals(solution[1], actual[1]);
    }

    @Test public void canSolveEquasionWithComplexSolution() {
        QuadraticEquasion qe = new QuadraticEquasion(1, 1, 4);
        ComplexNumber[] solution = new ComplexNumber[2];
        solution[0] = new ComplexNumber(-0.5, 1.93);
        solution[1] = new ComplexNumber(-0.5, -1.93);

        ComplexNumber[] actual = qe.solve();

        assertEquals(solution[0].getReal(), actual[0].getReal(), delta);
        assertEquals(solution[0].getImaginary(), actual[0].getImaginary(), delta);
        assertEquals(solution[1].getReal(), actual[1].getReal(), delta);
        assertEquals(solution[1].getImaginary(), actual[1].getImaginary(), delta);
    }

    @Test public void canConvertToString() {
        QuadraticEquasion qe = new QuadraticEquasion(1, 1, 4);
        assertEquals("(x^2) + x + 4.00", qe.toString());
    }

    @Test
    public void canGiveHashCode() {
        QuadraticEquasion qe = new QuadraticEquasion(1, 1, 1);
        assertTrue((qe.hashCode() >= 0));
    }

    @Test
    public void notEqualEquasionAreNotEqual() {
        QuadraticEquasion qe1 = new QuadraticEquasion(1, 1, 1);
        QuadraticEquasion qe2 = new QuadraticEquasion(2, 2, 2);
        assertNotEquals(qe1, qe2);
    }

    @Test
    public void equalEquasionAreEqual() {
        QuadraticEquasion qe1 = new QuadraticEquasion(1, 1, 1);
        QuadraticEquasion qe2 = new QuadraticEquasion(1, 1, 1);
        assertEquals(qe1, qe2);
    }

    @Test
    public void equasionsWithDifferentACoeffsNotEqual() {
        QuadraticEquasion qe1 = new QuadraticEquasion(2, 1, 1);
        QuadraticEquasion qe2 = new QuadraticEquasion(1, 1, 1);
        assertNotEquals(qe1, qe2);
    }

    @Test
    public void equasionsWithDifferentBCoeffsNotEqual() {
        QuadraticEquasion qe1 = new QuadraticEquasion(1, 2, 1);
        QuadraticEquasion qe2 = new QuadraticEquasion(1, 1, 1);
        assertNotEquals(qe1, qe2);
    }

    @Test
    public void equasionsWithDifferentCCoeffsNotEqual() {
        QuadraticEquasion qe1 = new QuadraticEquasion(1, 1, 2);
        QuadraticEquasion qe2 = new QuadraticEquasion(1, 1, 1);
        assertNotEquals(qe1, qe2);
    }
}
