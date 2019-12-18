package programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Created by hong2 on 28/11/2019
 * Time : 1:04 AM
 */

public class Unary {
    public static void main(String[] args) {
        Unary unary = new Unary();
        unary.unaryFunction();

    }

    private void unaryFunction() {
        List<UnaryOperator<Long>> unaryOperators = new ArrayList<>();
        UnaryOperator<Long> sum = s -> s + s;
        UnaryOperator<Long> power = s -> s * s;
        unaryOperators.add(sum);
        unaryOperators.add(power);
        final Long[] val = {3L};
        unaryOperators.forEach(e -> {
            val[0] = e.apply(val[0]);
            System.out.println(val[0]);
        });
    }

    private void hello() {
        System.out.println("hello world");
    }
}
