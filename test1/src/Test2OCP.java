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
public class Test2OCP {

    public static void main(String[] args) {
        Test2OCP test1 = new Test2OCP();
        test1.test1();
    }

    public void test1() {
        List<Integer> ls = Arrays.asList(3, 4, 6, 9, 2, 5, 7);
        System.out.println(ls.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b)); //1
        System.out.println(ls.stream().max(Integer::max).get()); //2
        System.out.println(ls.stream().max(Integer::compare).get()); //3
        System.out.println(ls.stream().max((a, b) -> a > b ? a : b)); //4
        System.out.println(ls.stream().max((a, b) -> a - b).get()); //4
    }

}
