# Highest palindrome problem

Palindromes are strings that read the same from the left or right, for example madam or 0110.

You will be given a string representation of a number and a maximum number of changes you can make. Alter the string, one digit at a time, to create the string representation of the largest number possible given the limit to the number of changes. The length of the string may not be altered, so you must consider 0's left of all higher digits in your tests. For example **0110** is valid, **0011** is not.

Given a string representing the starting number and a maximum number of changes allowed, create the largest palindromic string of digits possible or return empty if it's impossible to create a palindrome under the constraints.

**Note:** Treat the integers as numeric strings. Leading zeros are permitted and can't be ignored so 0011 is
not a palindrome, but 0110 is. A digit _can_ be modified more than once.

#### Input Constraints

0 < number.length() <= 10<sup>5</sup>

0 <= maxAllowedChanges <= 10<sup>5</sup>

Each character in the number is an integer where 0 <= d <= 9.

**Note:** Assume these constraints mandatory for any function call, thus removing the need for checking     

#### Function Description

Complete the _public Optional\<String> highestValuePalindrome(String number, int maxAllowedChanges)_ method in the PalindromeServiceImpl class. It should return a string representing the largest number that is a palindrome, or an empty optional if is not possible.
