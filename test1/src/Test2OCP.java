import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by rdors on 2016-07-27.
 */
public class Test2OCP {

    public static void main(String[] args) {
        Test2OCP test1 = new Test2OCP();
        //test1.test1();
        //test1.test2();
        //test1.test3();
        test1.test4();
    }

    public void test1() {
        List<Integer> ls = Arrays.asList(3, 4, 6, 9, 2, 5, 7);
        System.out.println(ls.stream().reduce(Integer.MIN_VALUE, (a, b) -> a > b ? a : b)); //1
        System.out.println(ls.stream().max(Integer::max).get()); //2
        System.out.println(ls.stream().max(Integer::compare).get()); //3
        System.out.println(ls.stream().max((a, b) -> a > b ? a : b)); //4
        System.out.println(ls.stream().max((a, b) -> a - b).get()); //4
    }

    class Person {
        private String name;
        private Optional<A> a;

        public Person() {
        }


        public Optional<A> getA() {
            return a;
        }

        public void setA(Optional<A> a) {
            this.a = a;
        }

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    class A {
        private String name;

        public A() {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class Helper {
        public void helpPeople(Queue people, Queue helped) {
            do {
                Person p = (Person) people.poll();
                System.out.println("Helped : " + p + " ");
                helped.offer(p.getName());
            } while (!people.isEmpty());
        }
    }

    public void test2() {
        Queue<Person> q = new LinkedList<Person>();
        q.offer(new Person("Pope"));
        q.offer(new Person("John"));
        Queue<Person> helpedQ = new LinkedList<Person>();
        Helper h = new Helper();
        h.helpPeople(q, helpedQ);
    }

    public void test3() {
        //Supplier<Person> sP = Person::new;
        Supplier<Person> sP = () -> {
            Person p = new Person();
            A a = new A();
            a.setName("ASD");
            Optional<A> aO = Optional.of(a);
            p.setA(aO);
            return p;
        };
        Optional<Person> oH = Optional.of(sP.get());
        String name = oH.flatMap(Person::getA).map(A::getName).orElse("NIC");
        Consumer<String> c = System.out::println;
        c.accept(name);
    }

    public void test4() {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("Thread");
            }
        };
        t.start();

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        };
        new Thread(r).start();
    }

    class AA {
        void a() {

        }
    }

    class BB extends AA {
        @Override
        void a() throws RuntimeException {
            super.a();
        }
    }

    public void test5() {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Callable";
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> f = executorService.submit(c);

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        };
        new Thread(r).start();
    }


}

class TopLevelClass {

    class Inner1 {

        final static String test1 = "3";
        //static String test2 = "3";

//        static void test3() {
//
//        }

    }

    static class Inner2 {

        final static String test1 = "3";
        static String test2 = "3";

        static void test3() {

        }

    }

}
