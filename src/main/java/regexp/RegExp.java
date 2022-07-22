package regexp;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {
    public static void main(String[] args) {
        String a = "QQWWWWWWEEEEqwe-+test123+BaQs3swWdasdqwdfdefe1qd";
        System.out.println(a.matches("Q{2}W{2,}E{2,4}\\w{3}-?.a?test\\d+[qwe+][a-zA-Z31]+(as|qd)V*"));

        String b = "hello";
        System.out.println(b.matches("[^abc]*"));

        System.out.println(Arrays.toString("asd123sad33s1ad".split("\\d+")));

        String q = "asd123sad33s1ad";
        System.out.println(q.replaceAll("\\d", "!").replaceAll("\\w+", "\\$"));
        System.out.println(q.replaceFirst("\\d", "!").replaceFirst("\\w+", "\\$"));

        String text = "jefhjwehf asdhjashd a134s2dasd@asdasd.com asdwewef sd 123 das2das asdasdsad@111.ru 222@qqq.com";
        Pattern email = Pattern.compile("([\\w\\d]+@[\\w\\d]+)\\.(?<last>com|ru)");
        Matcher matcher = email.matcher(text);

        while (matcher.find()) {
            System.out.print(matcher.group(1) + " ");
            System.out.println(matcher.group("last"));
        }
    }
}
