package Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SetService {

    public static Set<Integer> getNumSet(Integer st, Integer fin) {
        return IntStream.rangeClosed(st, fin)
                .boxed()
                .collect(Collectors.toSet());
    }
}
