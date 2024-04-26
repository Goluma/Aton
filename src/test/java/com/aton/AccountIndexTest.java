package com.aton;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountIndexTest {

    private AccountIndex accountIndex = new AccountIndex();

    private AccountDto accountDto;


    private AccountDto createAccount(Long account, String name, Double value){
        return AccountDto.builder()
                .setAccount(account)
                .setName(name)
                .setValue(value)
                .build();
    }
    @BeforeEach
    public void createAccountDto(){
        accountDto = createAccount(1L, "Evgeny", 130.5);
        accountIndex.add(accountDto);
    }

    @Test
    public void testThatAccountCanBeAddedAndFoundByAccount(){
        AccountDto accountDto1 = accountIndex.getByAccount(accountDto.getAccount()).iterator().next();

        assertEquals(accountDto1, accountDto);
    }

    @Test
    public void testThatAccountCanBeFoundByName(){
        AccountDto accountDto1 = accountIndex.getByName(accountDto.getName()).iterator().next();

        assertEquals(accountDto1, accountDto);
    }

    @Test
    public void testThatAccountCanBeFoundByValue(){
        AccountDto accountDto1 = accountIndex.getByValue(accountDto.getValue()).iterator().next();

        assertEquals(accountDto1, accountDto);
    }

    @Test
    public void testThatAccountCanBeRemoved(){
        accountIndex.remove(accountDto);

        Set<AccountDto> setOfAccounts = accountIndex.getByAccount(accountDto.getAccount());
        assertEquals(setOfAccounts, null);
    }

    @Test
    public void testThatAccountCanBeUpdated(){

        AccountDto newAccountDto = createAccount(1L, "Igor", 140.1);
        accountIndex.update(newAccountDto, accountDto);

        AccountDto accountDto = accountIndex.getByAccount(1L).iterator().next();

        assertEquals(accountDto, newAccountDto);
    }

}
