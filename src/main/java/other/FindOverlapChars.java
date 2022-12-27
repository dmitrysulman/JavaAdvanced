package other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class FindOverlapChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of strings: ");
        int n = Integer.parseInt(scanner.nextLine());
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.printf("Enter string #%d: ", i);
            strings.add(scanner.nextLine());
        }
        Set<Character> overlap = findOverlap(strings);
        System.out.print("Answer: ");
        overlap.forEach(System.out::print);
    }

    private static Set<Character> findOverlap(List<? extends String> strings) {
        Set<Character> result = getSetOfCharsFromString(strings.get(0));
        for (int i = 1; i < strings.size(); i++) {
            result.retainAll(getSetOfCharsFromString(strings.get(i)));
        }
        return result;
    }

    private static Set<Character> getSetOfCharsFromString(String string) {
        return string.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toSet());
    }
}
