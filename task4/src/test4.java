import java.util.Arrays;
import java.util.List;

public class test4 {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Не передано входящих аргументов");
        }
        List<Integer> numbers = Arrays.stream(args).map(Integer::parseInt).sorted().toList();;
        int middleIndex = numbers.size() / 2;
        int result = numbers.stream().reduce(0, (a, b) -> a + Math.abs(b - numbers.get(middleIndex)));
        System.out.println(result);
    }
}