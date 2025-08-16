package utils.listeners;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.*;
import utils.Before_and_after_config.StepContext;

public class StepListener implements EventListener {

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, event -> {
            if (event.getTestStep() instanceof PickleStepTestStep) {
                PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
                StepContext.set(step.getStep().getKeyword() + step.getStep().getText());
            }
        });
    }
}
