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
        List<Mirror> mirrors = IntStream.range(0, number.length() / 2).boxed()
                .map(index -> buildMirror(number, remainingChanges, index))
                .collect(Collectors.toList());

        return remainingChanges.get() < 0
                ? Optional.empty()
                : buildHighestPalindrome(number, mirrors, remainingChanges);
    }

    private static Mirror buildMirror(String number, AtomicInteger remainingChanges, int index) {
        int leftImage = Character.getNumericValue(number.charAt(index));
        int rightImage = Character.getNumericValue(number.charAt(number.length() - (index + 1)));

        int mirrorCost = leftImage == rightImage ? 0 : 1;
        remainingChanges.getAndUpdate(value -> value - mirrorCost);

        int uniqueDigitsWith9 = Stream.of(leftImage, rightImage, 9).collect(Collectors.toSet()).size();
        int costToBe9 = Math.min(uniqueDigitsWith9 - ((uniqueDigitsWith9 + mirrorCost) % 2), 2) - mirrorCost;

        return new Mirror(Math.max(leftImage, rightImage), costToBe9);
    }

    private static Optional<String> buildHighestPalindrome(String number, List<Mirror> mirrors, AtomicInteger remainingChanges) {
        String leftSide = mirrors.stream()
                .map(mirror -> remainingChanges.get() - mirror.costToBe9 < 0
                        ? String.valueOf(mirror.image)
                        : updateChangesAndGet9(remainingChanges, mirror.costToBe9))
                .collect(Collectors.joining(""));

        return Optional.of(buildPalindrome(number, leftSide, remainingChanges.get()));
    }

    private static String updateChangesAndGet9(AtomicInteger remainingChanges, int costToBe9) {
        remainingChanges.getAndUpdate(value -> value - costToBe9);
        return "9";
    }

    private static String buildPalindrome(String number, String leftSide, int remainingChanges) {
        String middle = number.length() % 2 == 0 ? "" : (remainingChanges > 0 ? "9" : Character.toString(number.charAt(number.length() / 2)));
        return String.join("", leftSide, middle, new StringBuilder(leftSide).reverse().toString());
    }

    private static class Mirror {
        final int image;
        final int costToBe9;

        public Mirror(int image, int costToBe9) {
            this.image = image;
            this.costToBe9 = costToBe9;
        }
    }
}
