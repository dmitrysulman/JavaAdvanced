package regexp;

public class RegExp {
    public static void main(String[] args) {
        String a = "QQWWWWWWEEEEqwe-+test123+BaQs3swWdasdqwdfdefe1qd";
        System.out.println(a.matches("Q{2}W{2,}E{2,4}\\w{3}-?.a?test\\d+[qwe+][a-zA-Z31]+(as|qd)V*"));

        String b = "hello";
        System.out.println(b.matches("[^abc]*"));
    }
}
