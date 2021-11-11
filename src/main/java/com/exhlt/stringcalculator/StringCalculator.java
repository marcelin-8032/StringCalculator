package com.exhlt.stringcalculator;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringCalculator {

    private static final String DEFAULT_DELIMETER = "[,\n]";

    private static final Pattern CUSTOM_DELIMETER = Pattern.compile("//(.*)\n(.*)");

    public int add(String numbers) throws Exception {
        if (numbers.isEmpty()) {
            return 0;
        }
        List<Integer> list = null;
        if (!numbers.trim().isEmpty()) {
            if (numbers.startsWith("//")) {
                list = splitDifferentDelimiters(numbers);
                checkNegativeNumber(list);
                return sum(list);
            } else {
                String[] array = numbers.split(DEFAULT_DELIMETER);
                list = convertNumberArrayToList(array);
                checkNegativeNumber(list);
                return sum(list);
            }

        }
        return sum(list);
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

    private List<Integer> convertNumberArrayToList(String[] strings) {
        List<Integer> list = Stream.of(strings).map(Integer::parseInt).collect(Collectors.toList());
        return list;
    }

    private void checkNegativeNumber(List<Integer> numbers) throws Exception {
        if (numbers.stream().anyMatch(i -> i < 0)) {
            throw new StringIndexOutOfBoundsException("Negatives: " + numbers.stream().filter(j -> j < 0).map(String::valueOf).collect(Collectors.joining(",")));
        }
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream().filter(i -> i <= 1000).reduce(0, (x, y) -> x + y);
    }

}

