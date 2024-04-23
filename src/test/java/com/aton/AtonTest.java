package com.aton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtonTest {

    PersonService personService = new PersonService();

    @Test
    public void testThatObjectCanBeAddedAndFound(){
        PersonDto personDto = TestDataUtil.createA();
        personService.addValue(personDto);
        PersonDto personDto1 = personService.getObjectByAnyField(personDto.getAccount());

        assertEquals(personDto1, personDto);
    }

    @Test
    public void testThatObjectCanBeRemoved(){
        PersonDto personDto = TestDataUtil.createA();
        personService.addValue(personDto);
        personService.removeValue(personDto);

        PersonDto obj = personService.getObjectByAnyField(personDto.getAccount());
        assertEquals(obj, null);
    }

    @Test
    public void testThatObjectCanBeUpdated(){
        PersonDto oldPersonDto = TestDataUtil.createB();
        personService.addValue(oldPersonDto);

        PersonDto newPersonDto = TestDataUtil.createF();
        personService.update(newPersonDto, oldPersonDto);

        PersonDto personDto = personService.getObjectByAnyField(2L);

        assertEquals(personDto, newPersonDto);
    }

}
