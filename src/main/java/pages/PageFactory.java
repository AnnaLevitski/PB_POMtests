package pages;


public class PageFactory {

    public static Page createPage(String page){
        if (page.equalsIgnoreCase("contacts")) {
            return new ContactsPage();
        } else if (page.equalsIgnoreCase("login")) {
            return new LoginPage();
        }
        return null;
    }
}
