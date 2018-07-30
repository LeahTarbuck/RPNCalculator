public class RPNMain {

//    TODO: Consider the following additions to the code:
//    logger
//    spring
//    strategy pattern

    public static void main(String[] args) {
        System.out.println("Starting Reverse Polish Notation Calculator");
        Calculator rpnCalculator = new RPNCalculator();
        rpnCalculator.calculate();
        System.out.println("Calculation complete, view computedResult.txt for results");
    }
}