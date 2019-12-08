package ru.unn.agile.Queue.viewmodel;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ViewModelTests {
    private ViewModel viewModel;
    @Before
    public void creatingViewModel() {
        viewModel = new ViewModel();
    }

    @After
    public void deletingViewModel() {
        viewModel = null;
    }

    @Test
    public void canInitDefaultQueueInputValue() {
        assertEquals("", viewModel.getQueueElement());
    }

    @Test
    public void canInitDefaultResult() {
        assertEquals("", viewModel.getQueueResult());
    }

    @Test
    public void canInitDefaultStatus() {
        assertEquals("", viewModel.getQueueStatus());
    }


    @Test
    public void canSetElementInputValue() {
        String expectedValue = "2";

        viewModel.setQueueInputElement("2");
        var actualValue = viewModel.getQueueElement();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void canGetElementInputProperty() {
        viewModel.setQueueInputElement("1");

        assertEquals(viewModel.getQueueElement(), viewModel.queueElementProperty().get());
    }

    @Test
    public void canGetResultProperty() {
        viewModel.setQueueResult("1,2,3");

        assertEquals(viewModel.getQueueResult(), viewModel.queueResultProperty().get());
    }

    @Test
    public void canPushElementToQueue() {
        String expectedStatusValue = "Push element: 3.0";

        viewModel.setQueueInputElement("3");
        viewModel.pushProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canPushMoreThenOneElementToQueue() {
        String expectedStatusValue = "Push element: 5.0";

        viewModel.setQueueInputElement("3");
        viewModel.pushProcess();
        viewModel.setQueueInputElement("5");
        viewModel.pushProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canPopElementFromQueue() {
        String expectedStatusValue = "Pop element: 3.0";

        viewModel.setQueueInputElement("3");
        viewModel.pushProcess();
        viewModel.popProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canNotPopElementFromQueue() {
        String expectedStatusValue = "Queue is empty.";

        viewModel.popProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canClearQueueWithElements() {
        String expectedStatusValue = "Queue cleared";

        viewModel.setQueueInputElement("3");
        viewModel.pushProcess();
        viewModel.clearProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canClearEmptyQueue() {
        String expectedStatusValue = "Queue cleared";

        viewModel.clearProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canGetHeadFromQueue() {
        String expectedStatusValue = "Head is: 3.0";

        viewModel.setQueueInputElement("3");
        viewModel.pushProcess();
        viewModel.getHeadProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canNotGetHeadFromQueue() {
        String expectedStatusValue = "Queue is empty.";

        viewModel.getHeadProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canGetTailFromQueue() {
        String expectedStatusValue = "Tail is: 3.0";

        viewModel.setQueueInputElement("3");
        viewModel.pushProcess();
        viewModel.getTailProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }

    @Test
    public void canNotGetTailFromEmptyQueue() {
        String expectedStatusValue = "Queue is empty.";

        viewModel.getTailProcess();
        var actualStatusValue = viewModel.getQueueResult();

        assertEquals(expectedStatusValue, actualStatusValue);
    }
}
