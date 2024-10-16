package day3.labHandlingExceptions;

public class Executor {
    public static void exec() throws MyCheckedException {
        try {
            Divider.divide();
        }catch (Exception e){
                throw new MyCheckedException("Checked Exception: Division by zero.");

//            throw new MyUncheckedException("Unchecked Exception: Division by zero.");


        }
    }
}
