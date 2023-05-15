
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import backend.Backend;
import frontend.Frontend;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Backend back = new Backend();
        Frontend front = new Frontend();
        back.init();
        front.init();
    }
}
