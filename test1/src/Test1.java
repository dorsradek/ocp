import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created by rdors on 2016-07-27.
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.test();
    }

    public void test() {
        Predicate<String> p = String::isEmpty;

        Consumer<Boolean> c = System.out::println;
        c.accept(p.test(""));

        Function<String, Integer> f1 = String::length;
        int length = f1.apply("test");
        Consumer<Integer> c2 = System.out::println;
        c2.accept(length);
    }
}
