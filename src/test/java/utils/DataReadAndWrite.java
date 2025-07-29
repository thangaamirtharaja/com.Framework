package utils;

public interface DataReadAndWrite {
    String readvalue(String type, Object... args);

    void writevalue(String type, Object... args);

}
