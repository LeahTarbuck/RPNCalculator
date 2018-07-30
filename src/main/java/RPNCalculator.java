import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.stream.Stream;

public class RPNCalculator implements Calculator{

    public void RPNCalculator(){};

    @Override
    public void calculate() {
        try {
            Path inputFilePath = Paths.get(ClassLoader.getSystemResource("inputData.txt").toURI());
            try (Stream<String> lines = Files.lines(inputFilePath);
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("computedResult.txt"))) {

                lines.forEach(line -> {
                    String computedResult = RPNCalculator.computeLine(line);
                    writeResultToFile(bufferedWriter, line, computedResult);
                });
            }
        } catch (URISyntaxException | IOException e) {
            System.out.println("Cannot read file " + e.getMessage());
        }
    }

    void writeResultToFile(BufferedWriter bufferedWriter, String line, String computedResult) {
        try {
            bufferedWriter.write(line + " = " + computedResult);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.out.println("Cannot write to file " + e.getMessage());
        }
    }


    public static String computeLine(String line) {
        Stack<Double> stack = new Stack<Double>();
        for (String token : line.trim().split("[ \t]+")) {
            if (token.equals("+")) {
                try {
                    stack.push(stack.pop() + stack.pop());
                } catch (EmptyStackException e) {
                    return "Not Reverse Polish Notation try backwards";
                }

            } else if (token.equals("-")) {
                try {
                    stack.push(-stack.pop() + stack.pop());
                } catch (EmptyStackException e) {
                    return "Not Reverse Polish Notation try backwards";
                }

            } else if (token.equals("*")) {
                try {
                    stack.push(stack.pop() * stack.pop());
                } catch (EmptyStackException e) {
                    return "Not Reverse Polish Notation try backwards";
                }

            } else if (token.equals("/")) {
                try {
                    double divisor = stack.pop();
                    stack.push(stack.pop() / divisor);
                } catch (EmptyStackException e) {
                    return "Not Reverse Polish Notation try backwards";
                }

            } else if (token.equals("sqrt")) {
                try {
                    stack.push(Math.sqrt(stack.pop()));
                } catch (EmptyStackException e) {
                    return "Not Reverse Polish Notation try backwards";
                }
            } else {
                stack.push(Double.parseDouble(token));

            }
        }
        return (stack.pop()).toString();
    }

}