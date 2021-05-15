import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    List<String> result = getHashtags(Arrays.asList(
        "test #dog #mikl #cat blabla #dog #cat #dog",
        " #cat #cat"));
    System.out.println(result);
  }

  private static List<String> getHashtags(List<String> twits) {
    return twits.stream()
        .flatMap(twit -> Arrays.stream(twit.split(" "))
            .filter(str -> str.startsWith("#"))
        )
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.summingInt(str -> 1)))
        .entrySet().stream()
        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toList());


  }

}
