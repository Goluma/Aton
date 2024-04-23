package com.aton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtonTest {

    @Test
    public void testThatObjectCanBeAddedToHashMapAndFound(){
        Service service = new Service();
        TestDto testDto = TestDataUtil.createA();
        service.addValue(testDto);
        TestDto testDto1 = service.getObjectByAnyField(1L);
        assertEquals(testDto1, testDto);
    }

}
