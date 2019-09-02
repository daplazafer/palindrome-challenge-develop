package com.ing.mwchapter.services;

import java.util.Optional;

public interface IPalindromeService {

  /**
   * @param number String representation of the number that we want to obtain the highestValuePalindrome
   * @param maxAllowedChanges Max number of change in the number characters
   * @return Optional with: the highestValuePalindrome or empty if is not possible to calculate
   */
  Optional<String> highestValuePalindrome(String number, int maxAllowedChanges);

}
