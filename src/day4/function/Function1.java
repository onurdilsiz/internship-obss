package day4.function;

import javax.print.DocFlavor;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface NumberProcessor{
    double process(double a, double b);
}
public class Function1 {


    static class Calculator {
        public Calculator(Map<String, NumberProcessor> operations) {
            this.operations = operations;
        }

        private final Map<String , NumberProcessor> operations;
        private static final Pattern OP_PATTERN = Pattern.compile("(\\d+)([\\+\\-\\*])(\\d+)");



        public double calculate(String operation){
            Matcher matcher = OP_PATTERN.matcher(operation);
            return 0;
        }

    }

    public static void main(String[] args) {
        NumberProcessor adder = (a,b) -> a+b ;
        NumberProcessor substractor = (a,b) -> a-b ;
        NumberProcessor multiplier = (a,b) -> a*b ;

        Map<String , NumberProcessor> operations = Map.of("+",adder, "-",substractor,"*", multiplier);


    }
}
