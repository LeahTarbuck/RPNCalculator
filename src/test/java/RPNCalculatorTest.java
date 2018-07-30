import mockit.Mock;
import mockit.MockUp;
import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.FileUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class RPNCalculatorTest {

//    @Tested
//    private RPNCalculator rpnCalculator;

    @Test
    public void computeLine_withAddExpression_producesAdditionResult() {
        String result = RPNCalculator.computeLine("1.0 3.0 +");
        assertThat(result).isEqualTo("4.0");
    }

    @Test
    public void computeLine_withSubtractionExpression_producesSubtractionResult() {
        String result = RPNCalculator.computeLine("3.0 1.0 -");
        assertThat(result).isEqualTo("2.0");
    }

    @Test
    public void computeLine_withSubtractionExpression_producesNegativeSubtractionResult() {
        String result = RPNCalculator.computeLine("1.0 3.0 -");
        assertThat(result).isEqualTo("-2.0");
    }

    @Test
    public void computeLine_withMultiplicationExpression_producesMultiplicationResult() {
        String result = RPNCalculator.computeLine("2.0 3.0 *");
        assertThat(result).isEqualTo("6.0");
    }

    @Test
    public void computeLine_withDivisionExpression_producesDivisionResult() {
        String result = RPNCalculator.computeLine("12.0 3.0 /");
        assertThat(result).isEqualTo("4.0");
    }

    @Test
    public void computeLine_withDivisionAndMultiplicationExpression_producesCorrectResult() {
        String result = RPNCalculator.computeLine("4 4 * 2 /");
        assertThat(result).isEqualTo("8.0");
    }

    @Test
    public void computeLine_withAdditionAndSubtractionExpression_producesCorrectResult() {
        String result = RPNCalculator.computeLine("20 20 + 35 -");
        assertThat(result).isEqualTo("5.0");
    }

    @Test
    public void computeLine_withSqrtExpression_producesSqrtResult() {
        String result = RPNCalculator.computeLine("5 6 * 5 - sqrt");
        assertThat(result).isEqualTo("5.0");
    }

    @Test
    public void computeLine_withNonPRNAdditionExpression_producesErrorResult() {
        String result = RPNCalculator.computeLine("1 + 1");
        assertThat(result).isEqualTo("Not Reverse Polish Notation try backwards");
    }

    @Test
    public void computeLine_withNonPRNSubtractionExpression_producesErrorResult() {
        String result = RPNCalculator.computeLine("2 - 1");
        assertThat(result).isEqualTo("Not Reverse Polish Notation try backwards");
    }

    @Test
    public void computeLine_withNonPRNMultiplicationExpression_producesErrorResult() {
        String result = RPNCalculator.computeLine("1 * 1");
        assertThat(result).isEqualTo("Not Reverse Polish Notation try backwards");
    }

    @Test
    public void computeLine_withNonPRNDivisionExpression_producesErrorResult() {
        String result = RPNCalculator.computeLine("10 / 5");
        assertThat(result).isEqualTo("Not Reverse Polish Notation try backwards");
    }

    @Test
    public void computeLine_withNonPRNSqrtExpression_producesErrorResult() {
        String result = RPNCalculator.computeLine("sqrt 16");
        assertThat(result).isEqualTo("Not Reverse Polish Notation try backwards");
    }

    @Test
    public void performCalculation_withFile_writesCalculationsToFileSuccessfully() throws IOException {
        createRPNCalculationFile("inputTestFile.txt","16 2 / 8 + 2 *", "1 1 + 14 + sqrt");
        createRPNCalculationFile("expectedTestFileResults.txt","16 2 / 8 + 2 * = 32.0", "1 1 + 14 + sqrt = 4.0");

        new MockUp<Paths>() {
            @Mock
            Path get(URI uri) {
                return Paths.get("inputTestFile.txt");
            }
        };

        RPNCalculator rpnCalculator = new RPNCalculator();
        rpnCalculator.calculate();

        assertThat(FileUtils.contentEquals(new File("computedResult.txt"),
                new File("expectedTestFileResults.txt"))).isTrue();

        Files.delete(Paths.get("inputTestFile.txt"));
        Files.delete(Paths.get("expectedTestFileResults.txt"));
        Files.delete(Paths.get("computedResult.txt"));
    }

//    @Test
//    public void performCalculation_withIOExceptionOnWritingOutputFile_failsWithError(){
//        RPNCalculator rpnCalculator = new RPNCalculator();
//
//        new Expectations(){{
//            rpnCalculator.writeResultToFile((BufferedWriter) any, anyString, anyString);
//            result = new IOException("Cannot write to file ");
//        }};
//
//        assertThatThrownBy(() -> {
//            rpnCalculator.calculate();
//        }).isExactlyInstanceOf(IOException.class)
//                .hasMessage("Cannot write to file ");
//    }


    private void createRPNCalculationFile(String fileName, String line1, String line2) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName));
        bufferedWriter.write(line1);
        bufferedWriter.newLine();
        bufferedWriter.write(line2);
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}