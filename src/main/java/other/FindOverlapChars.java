package other;

import java.util.Set;
import java.util.stream.Collectors;

public class FindOverlapChars {
    public static void main(String[] args) {
        Set<Character> overlap = findOverlap("abcd", "daqwe", "podla");
        overlap.forEach(System.out::println);
    }

    private static Set<Character> findOverlap(String s1, String s2, String s3) {
        Set<Integer> set1 = s1.chars()
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> set2 = s2.chars()
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> set3 = s3.chars()
                .boxed()
                .collect(Collectors.toSet());
        set1.retainAll(set2);
        set1.retainAll(set3);
        return set1.stream().map(e -> (char) e.intValue()).collect(Collectors.toSet());
    }
}
