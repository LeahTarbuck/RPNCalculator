public class RPNMain {

    public static void main(String[] args) {
        String newLine = System.getProperty("line.separator");
        System.out.println(newLine + "********************************************" + newLine
                + "Starting Reverse Polish Notation Calculator" + newLine +
                "********************************************");
        Calculator rpnCalculator = new RPNCalculator();
        rpnCalculator.calculate();
        System.out.println(newLine + "********************************************" + newLine
                + "Calculation complete, view computedResult.txt" + " for results" + newLine
                + "********************************************");
    }
}