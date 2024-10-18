import java.util.*;
import java.util.regex.*;

public class PasswordGame {

    private static final List<String> MONTHS = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    );

    private static final List<String> VALID_COMPANIES = Arrays.asList("pepsi", "starbucks", "shell");

    private static final Map<Character, Integer> ROMAN_NUMERALS = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000
    );

    public static void main(String[] args) {
        String captchaSolution = "12345";  // Replace with actual solution
        String wordleAnswer = "crane";      // Replace with today's Wordle answer
        String passwordInput = "A1b!DecemberXVPepsi1234";  // Example password input

        if (isPasswordValid(passwordInput, captchaSolution, wordleAnswer)) {
            System.out.println("Password is valid!");
        } else {
          
        }
    }

    private static boolean isPasswordValid(String password, String captchaSolution, String wordleAnswer) {
        // Check length
        if (password.length() < 5) return false;

        // Check for at least one digit
        if (!Pattern.compile("[0-9]").matcher(password).find()) return false;

        // Check for at least one uppercase letter
        if (!Pattern.compile("[A-Z]").matcher(password).find()) return false;

        // Check for at least one special character
        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) return false;

        // Check digits summing to 25
        int digitSum = password.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .sum();
        if (digitSum != 25) return false;

        // Check for a valid month
        if (MONTHS.stream().noneMatch(month -> password.toLowerCase().contains(month.toLowerCase()))) return false;

        // Check for valid Roman numeral that multiplies to 35
        String romanNumeral = password.replaceAll("[^IVXLCDM]", "");
        if (romanToInt(romanNumeral) != 35) return false;

        // Check for valid company name
        if (VALID_COMPANIES.stream().noneMatch(company -> password.toLowerCase().contains(company))) return false;
        

        // Check for two-letter periodic table symbol
        Set<String> periodicSymbols = Set.of("He", "Li", "Na", "K", "Be", "Mg", "Ca", "Sc", "Ti", "V", "Cr", "Mn",
                "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr",
                "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe", "Cs",
                "Ba", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb",
                "Lu", "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At",
                "Rn", "Fr", "Ra", "Ac", "Th", "Pa", "U", "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm",
                "Md", "No", "Lr", "Rf", "Db", "Sg", "Bh", "Hf", "Ds", "Rg", "Cn", "Nh", "Fl", "Mc", "Lv",
                "Ts", "Og");
        if (periodicSymbols.stream().noneMatch(symbol -> password.contains(symbol))) return false;

        return true;
    }

    private static int romanToInt(String roman) {
        int total = 0;
        int prevValue = 0;

        for (char c : roman.toCharArray()) {
            int value = ROMAN_NUMERALS.getOrDefault(c, 0);
            if (value > prevValue) {
                total += value - 2 * prevValue; // Adjust for the previous value
            } else {
                total += value;
            }
            prevValue = value;
        }
        return total;
    }
}
