import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by rdors on 2016-07-27.
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        test1.test();
        test1.test2();
        test1.test3();
        test1.test4();
        test1.test5();
        test1.test6();
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

    public void test2() {
        Stream<String> s1 = Stream.of("ABC", "qwe");
        String word = s1.reduce(", ", String::concat);
        System.out.println(word);

        Stream<Integer> s2 = Stream.of(1, 2, 3);
        Integer sum = s2.reduce(2, Math::multiplyExact);
        System.out.println(sum);

        Stream<String> s3 = Stream.of("1", "2", "3");
        List<String> sum2 = s3.collect(Collectors.toList());
        System.out.println(sum2);

        Stream<String> stream = Stream.of("w", "o", "l", "f");
        TreeSet<String> set = stream.collect(TreeSet::new, TreeSet::add,
                TreeSet::addAll);
        System.out.println(set); // [f, l, o, w]
    }

    public void test3() {
        List<String> list = Arrays.asList("Toby", "Anna", "Leroy", "Alex");
        List<String> filtered = new ArrayList<>();
        for (String name : list) {
            if (name.length() == 4) filtered.add(name);
        }
        Collections.sort(filtered);
        Iterator<String> iter = filtered.iterator();

        if (iter.hasNext()) System.out.println(iter.next());
        if (iter.hasNext()) System.out.println(iter.next());

        list.stream()
                .filter(i -> i.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);
    }

    public void test4() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        System.out.println(stream.reduce(0, (s, n) -> s + n));

        stream = Stream.of(1, 2, 3);
        stream.forEach(System.out::println);
        stream = Stream.of(1, 2, 3);
        int sum = stream.mapToInt(x -> x).sum();
        System.out.println(sum);
        stream = Stream.of(1, 2, 3);
        OptionalDouble average = stream.mapToInt(x -> x).average();
        average.ifPresent(System.out::println);
    }

    public void test5() {
        DoubleStream random = DoubleStream.generate(Math::random);
        DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
        random.limit(3).forEach(System.out::println);
        System.out.println();
        fractions.limit(3).forEach(System.out::println);
    }

    public void test6() {
        Runnable run = () ->  System.out.println("Run");
        Runnable run2 = () -> {
            System.out.println("Run");
        };
    }
}
