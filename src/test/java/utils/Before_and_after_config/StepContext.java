package utils.Before_and_after_config;

public class StepContext {

    // ThreadLocal ensures safe usage in parallel execution
    private static final ThreadLocal<String> currentStep = new ThreadLocal<>();

    // Set the current step
    public static void set(String step) {
        currentStep.set(step);
    }

    // Get the current step
    public static String get() {
        return currentStep.get();
    }

    // Clear the current step (optional)
    public static void clear() {
        currentStep.remove();
    }
}
