package utils.Seleniumutils;

public interface IAlertScout {

    /**
     * Accepts the alert if present.
     */
    void acceptAlert();

    /**
     * Dismisses the alert if present.
     */
    void dismissAlert();

    /**
     * Retrieves alert text if present.
     * @return Alert message or null if no alert.
     */
    String getAlertText();

    /**
     * Sends text to prompt alert if present.
     * @param text Input text for alert.
     */
    void sendKeysToAlert(String text);

    /**
     * Checks if an alert is currently present.
     * @return true if alert exists, false otherwise.
     */
    boolean isAlertPresent();

    /**
     * Waits for an alert for a given timeout duration.
     * @param timeoutInSeconds Timeout in seconds.
     * @return true if alert appears within timeout, false otherwise.
     */
    boolean waitForAlert(int timeoutInSeconds);
}
