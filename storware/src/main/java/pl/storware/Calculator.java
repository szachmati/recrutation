package pl.storware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static pl.storware.Operation.*;

public class Calculator implements IOperation {

    private static final String SPACE = " ";
    private final Logger log = Logger.getLogger("calculator");


    public double calculate(String filePath) {
        Map<Operation, Integer> operationNumberMap = groupOperations(readLines(filePath));
        double appliedValue = operationNumberMap.get(APPLY);
        double result = operationNumberMap.get(APPLY);
        String explanation = null;
        List<String> historyList = new LinkedList<>();

        for (Map.Entry<Operation, Integer> entry : operationNumberMap.entrySet()) {
            Integer value = entry.getValue();
            switch (entry.getKey()) {
                case ADD:
                    historyList.add(format(" + %s ", value));
                    result = add(result, value);
                    break;
                case SUBTRACT:
                    historyList.add(format(" - %s ", value));
                    result = subtract(result, value);
                    break;
                case DIVIDE:
                    historyList.add(format(" / %s ", value));
                    result = divide(result, value);
                    break;
                case MULTIPLY:
                    historyList.add(format(" * %s ", value));
                    result = multiply(result, value);
                    break;
                case APPLY:
                    explanation = buildExplanationMsg(appliedValue, historyList, result);
                    break;
                default:
                    break;
            }
        }
        System.out.println(explanation);
        return result;
    }

    private String buildExplanationMsg(double appliedValue, List<String> historyList, double result) {
        StringBuilder sb = new StringBuilder();
        sb.append(appliedValue);
        historyList.forEach(sb::append);
        sb.append(format(" = %s", result));
        return sb.toString();
    }

    private Map<Operation, Integer> groupOperations(List<String> lines) {
        Map<Operation, Integer> operationMap = new LinkedHashMap<>(lines.size());
        lines.forEach(line -> {
            String[] splitted = line.split(SPACE);
            operationMap.put(Operation.valueOf(splitted[0].toUpperCase()), Integer.parseInt(splitted[1]));
        });
        return operationMap;
    }

    private List<String> readLines(String filePath) {
        try {
            Path path = Paths.get(Objects.requireNonNull(
                    getClass().getClassLoader().getResource(filePath)).toURI());
            if (Files.exists(path)) {
                log.log(INFO, "File exists");
                return Files.readAllLines(path);
            }
            throw new FileNotFoundException(filePath + " not found!");
        } catch (URISyntaxException uriEx) {
            log.log(SEVERE, "Cannot read path");
        } catch (IOException ioEx) {
            log.log(SEVERE, "Cannot read file");
        }
        return Collections.emptyList();
    }


}
