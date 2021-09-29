package stream;

import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author 18190
 * @Date: 2021/8/4  11:19
 * @VERSION 1.0
 */
public class TestStream {

    @Test
    public void test1(){
//        extracted1();

//        extracted();

//        extracted2();
//        extracted4();
//        extracted5();

        //。它提供一个起始值（种子）然后依照运算规则（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。
        // 从这个意义上说，字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。
        /**
         * 第一个参数（空白字符）即为起始值，第二个参数（String::concat）为 BinaryOperator。
         * 这类有起始值的 reduce() 都返回具体的对象。而对于第四个示例没有起始值的 reduce()，
         * 缺少第一个参数，       返回的是 Optional，请留意这个区别。
         */
        System.out.println(Stream.of("A","B","C","F").reduce("",String::concat));
        System.out.println(Stream.of("A","B","C","F").reduce(String::concat).get());
        System.out.println(Stream.of(1,3,4,3).reduce(Integer.MAX_VALUE,Integer::min));
        System.out.println(Stream.of(1,3,4,3).reduce(0,Integer::sum));
        System.out.println(Stream.of(1,3,4,3).reduce(Integer::sum).get());
        System.out.println(Stream.of(1,3,4,3).reduce(Integer::sum));
        System.out.println(Stream.of(1,3,4,3).reduce(Integer::sum).getClass().getName());

        System.out.println(Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat));

        int[] ints = new int[]{9,78,6,4,34,3,4,32,3,5};
        int[] ints1 = Arrays.stream(ints).sorted().limit(5).toArray();
        System.out.println(Arrays.toString(ints1));
        int asInt = Stream.of("I", "love", "you", "too", "Is", "toward", "Love", "As", "apple").mapToInt(String::length).max().getAsInt();
        OptionalInt max = Stream.of("I", "love", "you", "too", "Is", "toward", "Love", "As", "apple").mapToInt(String::length).max();
        System.out.println(asInt + " " + max.getAsInt());
        List<String> collect = Stream.of("I", "love", "you", "too", "Is", "toward", "Love", "As", "apple").filter(t -> t.length() > 0).map(String::toLowerCase).distinct().sorted().collect(Collectors.toList());
        System.out.println(collect);

        Random seed = new Random();
        Supplier<Integer> random = seed::nextInt;
        Stream.generate(random).limit(10).forEach(System.out::println);

        IntStream.generate(() -> (int) (System.nanoTime() % 73)).
                limit(10).forEach(System.out::println);


        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));
        System.out.println();
        Stream.iterate(BigInteger.ZERO,n->n.add(BigInteger.valueOf(3L))).limit(10).forEach(x-> System.out.print(x+" "));

        System.out.println("".isEmpty());

        Random random1 = new Random();
        random1.ints().limit(10).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);



        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();
        System.out.println(stats);

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

        System.out.println(numbers.stream().map(i->i*i).sorted((x,y)->y-x).limit(3).collect(Collectors.toList()));
        System.out.println(numbers.stream().sorted((x,y)->y-x).collect(Collectors.toList()));

        Stream.generate(Math::random).map(x->(int)(x*100)).limit(31).forEach(x-> System.out.print(x+" "));
        System.out.println();

        Stream.iterate(1,x->x+2).limit(10).forEach(x-> System.out.print(x+" "));
        System.out.println();

        System.out.println(Arrays.stream(new Integer[]{1,3,4,5,6},0,3).collect(Collectors.toList()));
        String[] strings = new String[]{"hello","world","zzl"};
        List<List<String>> collect1 = Arrays.stream(strings).map(w -> letters(w).collect(Collectors.toList())).collect(Collectors.toList());
        System.out.println(collect1);

        List<String> collect2 = Arrays.stream(strings).flatMap(w -> letters(w)).collect(Collectors.toList());
        System.out.println(collect2);
    }

    public static Stream<String> letters(java.lang.String s){
        List<String> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            result.add(s.substring(i,i+1));
        }
        return result.stream();
    }

    private void extracted5() {
        Stream<String>  stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        // 筛选长度大于等于4  根据首字母逆序排序, 获得长度
        // t -> -t.charAt(0) 加个-就是逆序
        List<Integer> collect = stream.filter(t -> t.length() >= 4).sorted(Comparator.comparingInt(t -> -t.charAt(0))).map(String::length).collect(Collectors.toList());
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        // 对于Map sort没用
        Map<String, Integer> collect1 = stream.filter(t -> t.length() >= 4).sorted(Comparator.comparingInt(t -> -t.charAt(0))).collect(Collectors.toMap(t -> t, String::length));
        System.out.println(collect);
        System.out.println(collect1);

        IntStream.of(1,2,3,4).forEach(System.out::println);
        IntStream.range(1,7).forEach(System.out::println);
        IntStream.rangeClosed(1,7).forEach(System.out::println);
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        String[] objects = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(objects));

        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        List<String> collect2 = stream.collect(Collectors.toList());
        System.out.println(collect2);
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        ArrayList<String> collect3 = stream.collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect3);
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        LinkedList<String> collect4 = stream.collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collect4);
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        Stack<String> collect5 = stream.collect(Collectors.toCollection(Stack::new));
        System.out.println(collect5);

        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        String collect6 = stream.collect(Collectors.joining(",", "{ ", " }"));
        System.out.println(collect6);


        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        List<String> collect7 = stream.map(t -> t.substring(0, 1).toUpperCase() + t.substring(1)).collect(Collectors.toList());
        System.out.println(collect7);

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(9);
        arrayList.add(10);
        Stream<List<Integer>> listStream = Stream.of(
                Arrays.asList(0,1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6),
                arrayList
        );
        System.out.println(listStream.collect(Collectors.toList()));


        listStream = Stream.of(
                Arrays.asList(0,1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6),
                arrayList
        );
        Stream<Integer> integerStream = listStream.flatMap(t -> t.stream());
        System.out.println(Arrays.toString(integerStream.toArray()));

        String a = "sss",b = null;
        Optional.ofNullable(a).ifPresent(System.out::println);
        System.out.println(Optional.ofNullable(b).map(String::toString).orElse("null"));

        System.out.println( Optional.ofNullable(b).map(String::length).orElse(-1));



    }

    private void extracted4() {
        Stream<String> stream = Stream.of("I", "love", "you");
        String collect = stream.collect(Collectors.joining());
        System.out.println(collect);
        stream = Stream.of("I", "love", "you");
        collect = stream.collect(Collectors.joining(","));
        System.out.println(collect);
        stream = Stream.of("I", "love", "you");
        collect = stream.collect(Collectors.joining(",","{","}"));
        System.out.println(collect);
    }

    private void extracted2() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        List<String> list1 = stream.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        stream = Stream.of("I", "love", "you", "too");
        Map<String,Integer> map = stream.collect(Collectors.toMap(t->t, String::length));
        System.out.println(map);
        stream = Stream.of("I", "love", "you", "too");
        // 分成两部分
        Map<Boolean, List<String>> collect = stream.collect(Collectors.partitioningBy(s -> s.charAt(0) > 96));
        System.out.println(collect);
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love");
        // 分成多个组
        Map<Character, List<String>> collect1 = stream.collect(Collectors.groupingBy(s -> s.charAt(0)));
        System.out.println(collect1);
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        // 分成多个组
        Map<Character, List<String>> collect2 = stream.collect(Collectors.groupingBy(s -> {
            return s.charAt(0)>=96?(char) (s.charAt(0)-32):s.charAt(0);
        }));
        System.out.println(collect2);
        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        Map<Character, Long> collect3 = stream.collect(Collectors.groupingBy(s -> {
            return s.charAt(0) >= 96 ? (char) (s.charAt(0) - 32) : s.charAt(0);
        }, Collectors.counting()));
        System.out.println(collect3);

        stream = Stream.of("I", "love", "you", "too","Is","toward","Love","As","apple");
        Map<Character, Map<String, Integer>> collect4 = stream.collect(Collectors.groupingBy(s -> {
            return s.charAt(0) >= 96 ? (char) (s.charAt(0) - 32) : s.charAt(0);
        }, Collectors.toMap(t->t, String::length)));
        System.out.println(collect4);
    }

    private void extracted1() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            list.add(i);
        }
        System.out.println(list.stream().filter(w->w%2 == 0).count());

        System.out.println(list.stream().allMatch(w->w > 33 && w % 5 == 0));
        System.out.println(list.stream().allMatch(w->w<100));
        System.out.println(list.stream().allMatch(w->w<99));
    }

    private void extracted() {
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        List<String> list1 = stream.collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list1.getClass().getName());
        stream = Stream.of("I", "love", "you", "too");
        Set<String> set = stream.collect(Collectors.toSet());
        System.out.println(set);
        System.out.println(set.getClass().getName());


        // Function.identity  等价于 lambda t->t
        // String::length 替代某些特定形式的 lambda表达式  String::length == t -> t.length()
//        Map<String,Integer> map= stream.collect(Collectors.toMap(Function.identity(),String::length));
        stream = Stream.of("I", "love", "you", "too");
        Map<String,Integer> map= stream.collect(Collectors.toMap(Function.identity(),t->t.length()+1));
        stream = Stream.of("I", "love", "you", "too");
        Map<String,Integer> map1= stream.collect(Collectors.toMap(t->t,String::length));
        System.out.println(map);
        System.out.println(map1);
    }

}
