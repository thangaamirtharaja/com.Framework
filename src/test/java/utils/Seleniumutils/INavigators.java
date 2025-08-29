package utils.Seleniumutils;

public interface INavigators {
    public void openUrl(String url);

    public void navigateTo(String url);

    public void goBack();

    public void goForward();

    public void refreshPage();

    public void openUrlInNewTab(String url);

    public void openUrlInNewWindow(String url);
}

