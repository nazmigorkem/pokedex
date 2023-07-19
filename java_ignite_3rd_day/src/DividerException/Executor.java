package DividerException;

public class Executor {
    public static void exec() throws MyUncheckedException {
        try {
            Divider.divide();
        } catch (ArithmeticException arithmeticException) {
//            throw new MyCheckedException("Checked exception");
            throw  new MyUncheckedException("Unchecked exception");
        }
    }
}
