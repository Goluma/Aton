package com.aton;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AccountIndex {

    private final ConcurrentHashMap<Long, Set<AccountDto>> accountMap;

    private final ConcurrentHashMap<String, Set<AccountDto>> nameMap;

    private final ConcurrentHashMap<Double, Set<AccountDto>> valueMap;

    public AccountIndex(){
        valueMap = new ConcurrentHashMap<>();
        nameMap = new ConcurrentHashMap<>();
        accountMap = new ConcurrentHashMap<>();
    }

    public AccountDto add(AccountDto accountDto){
        if (accountDto == null) {
            throw new IllegalArgumentException("Cannot pass null objects");
        } else if (!accountMap.containsKey(accountDto.getAccount())){
            nameMap.put(accountDto.getName(), new HashSet<>());
            accountMap.put(accountDto.getAccount(), new HashSet<>());
            valueMap.put(accountDto.getValue(), new HashSet<>());
        }

        nameMap.get(accountDto.getName()).add(accountDto);
        valueMap.get(accountDto.getValue()).add(accountDto);
        accountMap.get(accountDto.getAccount()).add(accountDto);

        return accountDto;
    }

    public AccountDto remove(AccountDto accountDto){
        if (accountDto == null) {
            throw new IllegalArgumentException("Cannot pass null objects");
        } else if (!accountMap.containsKey(accountDto.getAccount())){
            throw new NoSuchElementException("There is no such object in the existing");
        }

        if (accountMap.get(accountDto.getAccount()).size() == 1){
            accountMap.remove(accountDto.getAccount());
        } else {
            accountMap.get(accountDto.getAccount()).remove(accountDto);
        }

        if (valueMap.get(accountDto.getValue()).size() == 1){
            valueMap.remove(accountDto.getValue());
        } else {
            valueMap.get(accountDto.getValue()).remove(accountDto);
        }

        if (nameMap.get(accountDto.getName()).size() == 1){
            nameMap.remove(accountDto.getName());
        } else {
            nameMap.get(accountDto.getName()).remove(accountDto);
        }
        return accountDto;

    }

    public Set<AccountDto> getByName(String name){
        if (name == null){
            throw new IllegalArgumentException("The field must not be empty");
        }
        Set<AccountDto> setOfObjects =  nameMap.getOrDefault(name, null);
        if (setOfObjects == null){
            return null;
        }
        return setOfObjects;
    }

    public Set<AccountDto> getByAccount(Long account){
        if (account == null){
            throw new IllegalArgumentException("The field must not be empty");
        }
        Set<AccountDto> setOfObjects = accountMap.getOrDefault(account, null);
        if (setOfObjects == null){
            return null;
        }
        return setOfObjects;
    }

    public Set<AccountDto> getByValue(Double value){
        if (value == null){
            throw new IllegalArgumentException("The field must not be empty");
        }
        Set<AccountDto> setOfObjects =  valueMap.getOrDefault(value, null);
        if (setOfObjects == null){
            return null;
        }
        return setOfObjects;
    }

    public AccountDto update(AccountDto newAccountDto, AccountDto oldAccountDto){
        if (newAccountDto == null || oldAccountDto == null){
            throw new IllegalArgumentException("Cannot pass null objects");
        } else if (!accountMap.containsKey(oldAccountDto.getAccount())){
            throw new NoSuchElementException("The object you want to change does not exist");
        } else if (newAccountDto.equals(oldAccountDto)) {
            return newAccountDto;
        }

        updateObjectInIndex(newAccountDto, oldAccountDto, accountMap, newAccountDto.getAccount(), oldAccountDto.getAccount());
        updateObjectInIndex(newAccountDto, oldAccountDto, valueMap, newAccountDto.getValue(), oldAccountDto.getValue());
        updateObjectInIndex(newAccountDto, oldAccountDto, nameMap, newAccountDto.getName(), oldAccountDto.getName());

        return newAccountDto;
    }

    private <T, K, V extends Map<B, Set<T>>, B, C> void updateObjectInIndex(T newTestDto, K oldTestDto, V map,
                                                                  B newKeyField, C oldKeyField){
        if (newKeyField.equals(oldKeyField)){
            map.get(newKeyField).remove(oldTestDto);
            map.get(newKeyField).add(newTestDto);
        } else {
            map.get(oldKeyField).remove(oldTestDto);
            if (map.get(oldKeyField).size() == 0){
                map.remove(oldKeyField);
            }
            map.put(newKeyField, new HashSet<>());
            map.get(newKeyField).add(newTestDto);
        }
    }
}
