import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.print("Partitioned: {");
        numbers.stream().collect(Collectors.groupingBy(integer -> integer%2==0)).forEach((aBoolean, integers) -> System.out.print(aBoolean+" "+integers+" "));
        System.out.println("}");
    }
}