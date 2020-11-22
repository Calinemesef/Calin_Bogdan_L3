package lab3;

import lab3.View.Console;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Start point");
        Console c = new Console();
        c.display();
    }
}
