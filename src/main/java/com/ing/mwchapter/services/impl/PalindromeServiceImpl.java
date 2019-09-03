package com.ing.mwchapter.services.impl;

import com.ing.mwchapter.services.IPalindromeService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PalindromeServiceImpl implements IPalindromeService {

    @Override
    public Optional<String> highestValuePalindrome(String number, int maxAllowedChanges) {
        AtomicInteger remainingChanges = new AtomicInteger(maxAllowedChanges);
        List<Integer> costsToBe9 = IntStream.range(0, number.length() / 2).boxed()
                .map(index -> getRemainingCostTo9(number, index, remainingChanges))
                .collect(Collectors.toList());

        return remainingChanges.get() < 0
                ? Optional.empty()
                : Optional.of(buildHighestPalindrome(number, costsToBe9, remainingChanges));
    }

    private static int getRemainingCostTo9(String number, int index, AtomicInteger remainingChanges) {
        int mirrorCost = left(number, index) == right(number, index) ? 0 : 1;
        remainingChanges.getAndUpdate(value -> value - mirrorCost);

        int uniqueDigitsWith9 = Stream.of(left(number, index), right(number, index), 9)
                .collect(Collectors.toSet())
                .size();

        return Math.min(uniqueDigitsWith9 - ((uniqueDigitsWith9 + mirrorCost) % 2), 2) - mirrorCost;
    }

    private static String buildHighestPalindrome(String number, List<Integer> costsToBe9, AtomicInteger remainingChanges) {
        String leftSide = IntStream.range(0, number.length() / 2).boxed()
                .map(index -> remainingChanges.get() - costsToBe9.get(index) < 0
                        ? String.valueOf(Math.max(left(number, index), right(number, index)))
                        : updateChangesAndGet9(remainingChanges, costsToBe9.get(index)))
                .collect(Collectors.joining(""));

        return buildPalindrome(number, leftSide, remainingChanges.get());
    }

    private static String updateChangesAndGet9(AtomicInteger remainingChanges, int costToBe9) {
        remainingChanges.getAndUpdate(value -> value - costToBe9);
        return "9";
    }

    private static String buildPalindrome(String number, String leftSide, int remainingChanges) {
        String middle = number.length() % 2 == 0 ? "" : (remainingChanges > 0 ? "9" : Character.toString(number.charAt(number.length() / 2)));
        return String.join("", leftSide, middle, new StringBuilder(leftSide).reverse().toString());
    }

    private static int left(String number, int index) {
        return Character.getNumericValue(number.charAt(index));
    }

    private static int right(String number, int index) {
        return Character.getNumericValue(number.charAt(number.length() - (index + 1)));
    }
}
