package com.ing.mwchapter.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ing.mwchapter.services.impl.PalindromeServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class PalindromeServiceTest {

  private IPalindromeService palindromeService = new PalindromeServiceImpl();

  @Test
  public void oneChangeTest() {
    //Given
    String number = "3943";
    int maxAllowedChanges = 1;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertTrue(highestValuePalindrome.isPresent());
    assertEquals("3993", highestValuePalindrome.get());
  }

  @Test
  public void threeChangesTest() {
    //Given
    String number = "092282";
    int maxAllowedChanges = 3;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertTrue(highestValuePalindrome.isPresent());
    assertEquals("992299", highestValuePalindrome.get());
  }

  @Test
  public void notAllChangesDoneTest() {
    //Given
    String number = "9227";
    int maxAllowedChanges = 2;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertTrue(highestValuePalindrome.isPresent());
    assertEquals("9229", highestValuePalindrome.get());      //Just one change needed
  }

  @Test
  public void notPalindromeAvailableTest() {
    //Given
    String number = "0011";
    int maxAllowedChanges = 1;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertFalse(highestValuePalindrome.isPresent());
  }

  @Test
  public void oddLengthChangesTest() {
    //Given
    String number = "07382";
    int maxAllowedChanges = 2;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertTrue(highestValuePalindrome.isPresent());
    assertEquals("28382", highestValuePalindrome.get());
  }

  @Test
  public void oddLengthAlreadyPalindromeChangesTest() {
    //Given
    String number = "999979999";
    int maxAllowedChanges = 1;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertTrue(highestValuePalindrome.isPresent());
    assertEquals("999999999", highestValuePalindrome.get());
  }

  @Test
  public void alreadyAPalindromeTest() {
    //Given
    String number = "77777";
    int maxAllowedChanges = 3;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertTrue(highestValuePalindrome.isPresent());
    assertEquals("97979", highestValuePalindrome.get());
  }

  @Test
  public void wholeSubstitutionTest() {
    //Given
    String number = "12345";
    int maxAllowedChanges = 5;

    //When
    Optional<String> highestValuePalindrome = palindromeService.highestValuePalindrome(number, maxAllowedChanges);

    //Then
    assertTrue(highestValuePalindrome.isPresent());
    assertEquals("99999", highestValuePalindrome.get());
  }

}
