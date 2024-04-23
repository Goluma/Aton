package com.aton;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AtonTest {

    AccountIndex accountIndex = new AccountIndex();

    @Test
    public void testThatAccountCanBeAddedAndFoundByAccount(){
        AccountDto accountDto = TestDataUtil.createA();
        accountIndex.add(accountDto);
        AccountDto accountDto1 = accountIndex.getByAccount(accountDto.getAccount());

        assertEquals(accountDto1, accountDto);
    }

    @Test
    public void testThatAccountCanBeFoundByName(){
        AccountDto accountDto = TestDataUtil.createA();
        accountIndex.add(accountDto);
        AccountDto accountDto1 = accountIndex.getByName(accountDto.getName());

        assertEquals(accountDto1, accountDto);
    }

    @Test
    public void testThatAccountCanBeFoundByValue(){
        AccountDto accountDto = TestDataUtil.createA();
        accountIndex.add(accountDto);
        AccountDto accountDto1 = accountIndex.getByValue(accountDto.getValue());

        assertEquals(accountDto1, accountDto);
    }

    @Test
    public void testThatAccountCanBeRemoved(){
        AccountDto accountDto = TestDataUtil.createA();
        accountIndex.add(accountDto);
        accountIndex.remove(accountDto);

        AccountDto obj = accountIndex.getByAccount(accountDto.getAccount());
        assertEquals(obj, null);
    }

    @Test
    public void testThatAccountCanBeUpdated(){
        AccountDto oldAccountDto = TestDataUtil.createB();
        accountIndex.add(oldAccountDto);

        AccountDto newAccountDto = TestDataUtil.createF();
        accountIndex.update(newAccountDto, oldAccountDto);

        AccountDto accountDto = accountIndex.getByAccount(2L);

        assertEquals(accountDto, newAccountDto);
    }

}
