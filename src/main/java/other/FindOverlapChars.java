package other;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class FindOverlapChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of strings: ");
        int n = Integer.parseInt(scanner.nextLine());
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter string #%d: ", i);
            strings[i] = scanner.nextLine();
        }
        Set<Character> overlap = findOverlap(strings);
        overlap.forEach(System.out::print);
    }

    private static Set<Character> findOverlap(String... strings) {
        Set<Character> result = getSetOfCharsFromString(strings[0]);
        for (int i = 1; i < strings.length; i++) {
            result.retainAll(getSetOfCharsFromString(strings[i]));
        }
        return result;
    }

    private static Set<Character> getSetOfCharsFromString(String string) {
        return string.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toSet());
    }
}
