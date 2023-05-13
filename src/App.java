import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import backend.Backend;

public class App {
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        Backend.init();
    }
}
