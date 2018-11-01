import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PalindromeTest {

    @Test
    public void should_return_true_if_palindrome(){
        Palindrome palindrome = new Palindrome();
        assertThat(palindrome.isPalindrome("abba")).isEqualTo(true);
    }

    @Test
    public void should_count_3_longest_palindromes(){
        Palindrome palindrome = new Palindrome();
        assertThat(palindrome.countNLongestPalindromes("sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop", 3)).isEqualTo(3);
    }

    @Test
    public void should_print_3_longest_palindromes() {
        Palindrome palindrome = new Palindrome();
        String input = "sqrrqabccbatudefggfedvwhijkllkjihxymnnmzpop";
        System.out.println(palindrome.printNLongestPalindromes(input,3));
    }

}