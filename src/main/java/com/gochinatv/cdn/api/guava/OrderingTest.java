package com.gochinatv.cdn.api.guava;

import com.google.common.collect.Ordering;
import org.junit.Test;

import javax.annotation.Nullable;

/**
 * Created by jacktomcat on 17/10/15.
 */
public class OrderingTest {


    @Test
    public void orderingTest() {

        Ordering ordering = new Ordering() {
            @Override
            public int compare(@Nullable Object left, @Nullable Object right) {
                return 0;
            }
        };

        Ordering.natural();

    }
}
