package com.zimblesystems;

import com.zimblesystems.model.TestObject;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import org.junit.jupiter.api.Test;

public class RandomTest {


    @Test
    void testUni() {

        Uni<String> item1Uni = Uni.createFrom().item("123");

        Uni<TestObject> item2Uni = item1Uni
                .onItem().transform(s -> "Number is " + s)
                .onItem().invoke(s -> Log.info("######Item is " + s))
                .onItem().transform(s -> new TestObject(s));

        Log.info(" Uni has not been executed ");

        item2Uni.subscribe().with(
                item -> Log.info("######Item is " + item),
                failure -> Log.error("Failed with " + failure)
        )
        ;


    }

}
