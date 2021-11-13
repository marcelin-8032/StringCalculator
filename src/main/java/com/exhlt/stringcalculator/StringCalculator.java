package com.exhlt.stringcalculator;

import com.exhlt.stringcalculator.exception.StringCalculatorException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculator {

    private static final String DEFAULT_DELIMETER = "[,\n]";

    private static final Pattern CUSTOM_DELIMETER = Pattern.compile("//(.*)\n(.*)");

    public int add(String numbers) throws StringCalculatorException {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (!numbers.trim().isEmpty()) {
            if (numbers.startsWith("//")) {
                List<Integer> list = splitDifferentDelimiters(numbers);
                checkNegativeNumber(list);
                return sum(list);
            } else {
                String[] array = numbers.split(DEFAULT_DELIMETER);
                List<Integer> list = convertNumberArrayToList(array);
                checkNegativeNumber(list);
                return sum(list);
            }
        }
        return -1;
    }

    private List<Integer> convertNumberArrayToList(String[] array) {
        return Stream.of(array).map(Integer::parseInt).collect(Collectors.toList());
    }

    private int sum(List<Integer> list) {
        return list.stream().filter(i -> i <= 1000).reduce(0, (x, y) -> x + y);
    }

    private void checkNegativeNumber(List<Integer> list) throws StringCalculatorException {
        if (list.stream().anyMatch(i -> i < 0)) {
            throw new StringCalculatorException("Negatives: " + list.stream().filter(j -> j < 0).map(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    private List<Integer> splitDifferentDelimiters(String numbers) {
        Matcher matcher = CUSTOM_DELIMETER.matcher(numbers);
        if (matcher.find()) {
            String customD = matcher.group(1);
            String restOfNumb = matcher.group(2);
            String[] strings = restOfNumb.split(Pattern.quote(customD));
            return convertNumberArrayToList(strings);
        } else {
            throw new StringIndexOutOfBoundsException("Unsupported format" + numbers);
        }
    }

}

