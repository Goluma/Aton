package com.aton;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Service {

    private final ConcurrentHashMap<Long, Set<TestDto>> mapForLongKey;

    private final ConcurrentHashMap<String, Set<TestDto>> mapForStringKey;

    private final ConcurrentHashMap<Double, Set<TestDto>> mapForDoubleKey;

    public Service(){
        mapForDoubleKey = new ConcurrentHashMap<>();
        mapForStringKey = new ConcurrentHashMap<>();
        mapForLongKey = new ConcurrentHashMap<>();
    }

    public int addValue(TestDto testDto){
        if (testDto == null) {
            throw new RuntimeException("Нельзя передавать нулевые объекты");
        } else if (!mapForLongKey.containsKey(testDto.getAccount())){
            mapForStringKey.put(testDto.getName(), new HashSet<>());
            mapForLongKey.put(testDto.getAccount(), new HashSet<>());
            mapForDoubleKey.put(testDto.getValue(), new HashSet<>());
        }

        mapForStringKey.get(testDto.getName()).add(testDto);
        mapForDoubleKey.get(testDto.getValue()).add(testDto);
        mapForLongKey.get(testDto.getAccount()).add(testDto);

        return mapForLongKey.get(testDto.getAccount()).size();
    }

    public void removeValue(TestDto testDto){
        if (testDto == null) {
            throw new RuntimeException("Нельзя передавать нулевые объекты");
        } else if (!mapForLongKey.containsKey(testDto.getAccount())){
            throw new RuntimeException("Такого объекта нет в имеющихся");
        }
        mapForLongKey.get(testDto.getAccount()).remove(testDto);
        mapForDoubleKey.get(testDto.getValue()).remove(testDto);
        mapForStringKey.get(testDto.getName()).remove(testDto);
    }

    public <T> TestDto getObjectByAnyField(T field){
        if (field == null){
            throw new RuntimeException("Поле не должно быть пустым");
        }
        Set<TestDto> setOfObjects = null;

        Class classOfField = field.getClass();

        if (classOfField == String.class){
            setOfObjects = mapForStringKey.getOrDefault(field, null);
        } else if (classOfField == Long.class){
            setOfObjects = mapForLongKey.getOrDefault(field, null);
        } else if (classOfField == Double.class){
            setOfObjects = mapForDoubleKey.getOrDefault(field, null);
        } else {
            throw new RuntimeException("Передано поле, не соответствует ни одному из полей объекта записи");
        }
        if (setOfObjects == null || setOfObjects.size() == 0){
            throw new RuntimeException("Элемент не найден");
        }
        return setOfObjects.iterator().next();
    }

    public void update(TestDto newTestDto, TestDto oldTestDto){
        if (newTestDto == null || oldTestDto == null){
            throw new RuntimeException("Нельзя передавать нулевые объекты");
        } else if (!mapForLongKey.containsKey(oldTestDto.getAccount())){
            throw new RuntimeException("Объект, который вы хотите изменить, не существует");
        } else if (newTestDto.equals(oldTestDto)) {
            return;
        }

        putObjectIntoHashMap(newTestDto, oldTestDto, mapForLongKey, newTestDto.getAccount(), oldTestDto.getAccount());
        putObjectIntoHashMap(newTestDto, oldTestDto, mapForDoubleKey, newTestDto.getValue(), oldTestDto.getValue());
        putObjectIntoHashMap(newTestDto, oldTestDto, mapForStringKey, newTestDto.getName(), oldTestDto.getName());
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
