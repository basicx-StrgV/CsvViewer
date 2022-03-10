import java.io.PrintStream;

public class Console {
    private static PrintStream consoleStream = System.out;

    private Console(){}

    public static void write(String value){
        consoleStream.print(value);
    }

    public static void writeLine(String value){
        consoleStream.println(value);
    }

    public class Colors {
        public static final String RESET = "\033[0m";

        public static final String RED = "\033[0;31m";
        public static final String GREEN = "\033[0;32m";

        private Colors(){}
    }
}