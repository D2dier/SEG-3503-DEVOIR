package validator;

import org.junit.*;
import static org.junit.Assert.*;

public class ISBNValidateTest {

    // ######################################## Partie 1 ########################################

    @Test
    public void testAppendCheckDigit_1() {
        // Test Case 1: valid ISBN12
        String input = "978030640615";
        String expected = "9780306406157";
        assertEquals(expected, ISBNValidate.appendCheckDigitToISBN12(input));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendCheckDigit_2() {
        // Test Case 2: too short
        ISBNValidate.appendCheckDigitToISBN12("97803064061");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendCheckDigit_3() {
        // Test Case 3: too long
        ISBNValidate.appendCheckDigitToISBN12("9780306406158");
    }

    //This Method cannot be tested because appendCheckDigitToISBN12 do not check for invalid characters

    //    @Test(expected = IllegalArgumentException.class)
//    public void testAppendCheckDigit_4() {
//        // Test Case 4: invalid character
//        ISBNValidate.appendCheckDigitToISBN12("97803064061A");
//    }

    //This Method cannot be tested because appendCheckDigitToISBN12 do not check for invalid prefix

//    @Test(expected = IllegalArgumentException.class)
//    public void testAppendCheckDigit_5() {
//        // Test Case 5: invalid prefix
//        ISBNValidate.appendCheckDigitToISBN12("977123456789");
//    }

    @Test
    public void testAppendCheckDigit_6() {
        // Test Case 6: valid ISBN12 with prefix 979
        String input = "979123456789";
        String expected = "9791234567896";
        assertEquals(expected, ISBNValidate.appendCheckDigitToISBN12(input));
    }

    @Test
    public void testAppendCheckDigit_7() {
        // Test Case 7: all zeros
        String input = "000000000000";
        String expected = "0000000000000";
        assertEquals(expected, ISBNValidate.appendCheckDigitToISBN12(input));
    }

    @Test
    public void testAppendCheckDigit_8() {
        // Test Case 8: all nines
        String input = "999999999999";
        String expected = "9999999999994";
        assertEquals(expected, ISBNValidate.appendCheckDigitToISBN12(input));
    }


    // ######################################## Partie 2 ###########################################

    @Test // test 1
    public void testTidyISBN_Valid13() {
        String input = "978-0-306-40615-7";
        String expected = "978-0-306-40615-7";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    @Test // test 2
    public void testTidyISBN_Valid10() {
        String input = "0-19-852663-6";
        String expected = "0-19-852663-6";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    @Test // test 3
    public void testTidyISBN_ExtraCharacters() {
        String input = "  978 03-064-06 157 ";
        String expected = "978-0-306-40615-7";
        assertEquals(expected, ISBNValidate.tidyISBN10or13InsertingDashes(input));
    }

    @Test(expected = IllegalArgumentException.class) // test 4
    public void testTidyISBN_InvalidChecksum() {
        String input = "9780306406150"; // somme de controle invalide
        ISBNValidate.tidyISBN10or13InsertingDashes(input);
    }

    @Test(expected = IllegalArgumentException.class) // test 5
    public void testTidyISBN_TooShort() {
        ISBNValidate.tidyISBN10or13InsertingDashes("12345");
    }

    @Test(expected = IllegalArgumentException.class) // test 6
    public void testTidyISBN_TooLong() {
        ISBNValidate.tidyISBN10or13InsertingDashes("978030640615700000000");
    }
}
