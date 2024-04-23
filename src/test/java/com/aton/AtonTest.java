package com.aton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtonTest {

    @Test
    public void testThatObjectCanBeAddedAndFound(){
        PersonService personService = new PersonService();
        PersonDto personDto = TestDataUtil.createA();
        personService.addValue(personDto);
        PersonDto personDto1 = personService.getObjectByAnyField(personDto.getAccount());
        assertEquals(personDto1, personDto);
    }

    @Test
    public void testThatObjectCanBeRemoved(){
        PersonService personService = new PersonService();
        PersonDto personDto = TestDataUtil.createA();
        personService.addValue(personDto);
        personService.removeValue(personDto);

        PersonDto obj = personService.getObjectByAnyField(personDto.getAccount());
        assertEquals(obj, null);
    }

    @Test
    public void testThatObjectCanBeUpdated(){

    }

}
