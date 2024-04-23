package com.aton;

import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class PersonService {

    private final ConcurrentHashMap<Long, Set<PersonDto>> mapForLongKey;

    private final ConcurrentHashMap<String, Set<PersonDto>> mapForStringKey;

    private final ConcurrentHashMap<Double, Set<PersonDto>> mapForDoubleKey;

    public PersonService(){
        mapForDoubleKey = new ConcurrentHashMap<>();
        mapForStringKey = new ConcurrentHashMap<>();
        mapForLongKey = new ConcurrentHashMap<>();
    }

    public PersonDto addValue(PersonDto personDto){
        if (personDto == null) {
            throw new IllegalArgumentException("Cannot pass null objects");
        } else if (!mapForLongKey.containsKey(personDto.getAccount())){
            mapForStringKey.put(personDto.getName(), new HashSet<>());
            mapForLongKey.put(personDto.getAccount(), new HashSet<>());
            mapForDoubleKey.put(personDto.getValue(), new HashSet<>());
        }

        mapForStringKey.get(personDto.getName()).add(personDto);
        mapForDoubleKey.get(personDto.getValue()).add(personDto);
        mapForLongKey.get(personDto.getAccount()).add(personDto);

        return personDto;
    }

    public PersonDto removeValue(PersonDto personDto){
        if (personDto == null) {
            throw new IllegalArgumentException("Cannot pass null objects");
        } else if (!mapForLongKey.containsKey(personDto.getAccount())){
            throw new NoSuchElementException("There is no such object in the existing");
        }

        if (mapForLongKey.get(personDto.getAccount()).size() == 1){
            mapForLongKey.remove(personDto.getAccount());
        } else {
            mapForLongKey.get(personDto.getAccount()).remove(personDto);
        }

        if (mapForDoubleKey.get(personDto.getValue()).size() == 1){
            mapForDoubleKey.remove(personDto.getValue());
        } else {
            mapForDoubleKey.get(personDto.getValue()).remove(personDto);
        }

        if (mapForStringKey.get(personDto.getName()).size() == 1){
            mapForStringKey.remove(personDto.getName());
        } else {
            mapForStringKey.get(personDto.getName()).remove(personDto);
        }
        return personDto;

    }

    public <T> PersonDto getObjectByAnyField(T field){
        if (field == null){
            throw new IllegalArgumentException("The field must not be empty");
        }
        Set<PersonDto> setOfObjects = null;

        Class classOfField = field.getClass();

        if (classOfField == String.class){
            setOfObjects = mapForStringKey.getOrDefault(field, null);
        } else if (classOfField == Long.class){
            setOfObjects = mapForLongKey.getOrDefault(field, null);
        } else if (classOfField == Double.class){
            setOfObjects = mapForDoubleKey.getOrDefault(field, null);
        } else {
            throw new IllegalArgumentException("The field passed does not match any of the fields in the record object");
        }

        if (setOfObjects == null){
            return null;
        }
        return setOfObjects.iterator().next();
    }

    public PersonDto update(PersonDto newPersonDto, PersonDto oldPersonDto){
        if (newPersonDto == null || oldPersonDto == null){
            throw new IllegalArgumentException("Cannot pass null objects");
        } else if (!mapForLongKey.containsKey(oldPersonDto.getAccount())){
            throw new NoSuchElementException("The object you want to change does not exist");
        } else if (newPersonDto.equals(oldPersonDto)) {
            return newPersonDto;
        }

        putObjectIntoHashMap(newPersonDto, oldPersonDto, mapForLongKey, newPersonDto.getAccount(), oldPersonDto.getAccount());
        putObjectIntoHashMap(newPersonDto, oldPersonDto, mapForDoubleKey, newPersonDto.getValue(), oldPersonDto.getValue());
        putObjectIntoHashMap(newPersonDto, oldPersonDto, mapForStringKey, newPersonDto.getName(), oldPersonDto.getName());

        return newPersonDto;
    }

    private <T, K, V extends Map<B, Set<T>>, B, C> void putObjectIntoHashMap(T newTestDto, K oldTestDto, V map,
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
