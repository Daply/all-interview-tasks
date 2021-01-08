import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Result {

    public static int segment(int x, List<Integer> space) {

        List<Integer> minimums = new ArrayList<>();
        IntStream.range(0, space.size())
                .forEach(i -> {
                            if (space.size() - i >= x)
                                minimums.add(space.subList(i, i+x)
                                        .stream()
                                        .min(Integer::compareTo).orElse(0));
                        }
                );

        return minimums.stream().max(Integer::compareTo).orElse(0);
    }

}
