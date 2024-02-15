package pages;

public interface Page {
    boolean isLink(String page);
    void assertLink();
    void assertLink(String page);
    String getLink();
}
