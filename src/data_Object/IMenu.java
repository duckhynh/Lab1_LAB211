package data_Object;

public interface IMenu {
    // add a menu item--> add text to menu
    void addItem(String s);
    // get user choice( user input their choice)
    int getChoice();
    // show menu for user choice
    void showMenu();
    // confirm yes/ no (Y/N)
    boolean confirmYesNo(String welcome);
}
