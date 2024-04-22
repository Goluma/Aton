package com.aton;

import java.util.HashSet;
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

    public void addValue(TestDto testDto){
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

        addObjectIntoLongHashMap(newTestDto, oldTestDto);
        addObjectIntoDoubleHashMap(newTestDto, oldTestDto);
        addObjectIntoStringHashMap(newTestDto, oldTestDto);
    }

    private void addObjectIntoLongHashMap(TestDto newTestDto, TestDto oldTestDto){
        if (newTestDto.getAccount().equals(oldTestDto.getAccount())){
            mapForLongKey.get(newTestDto.getAccount()).remove(oldTestDto);
            mapForLongKey.get(newTestDto.getAccount()).add(newTestDto);
        } else {
            mapForLongKey.get(oldTestDto.getAccount()).remove(oldTestDto);
            if (mapForLongKey.get(oldTestDto.getAccount()).size() == 0){
                mapForLongKey.remove(oldTestDto.getAccount());
            }
            mapForLongKey.put(newTestDto.getAccount(), new HashSet<>());
            mapForLongKey.get(newTestDto.getAccount()).add(newTestDto);
        }
    }

    private void addObjectIntoStringHashMap(TestDto newTestDto, TestDto oldTestDto){
        if (newTestDto.getName().equals(oldTestDto.getName())){
            mapForStringKey.get(newTestDto.getName()).remove(oldTestDto);
            mapForStringKey.get(newTestDto.getName()).add(newTestDto);
        } else {
            mapForStringKey.get(oldTestDto.getName()).remove(oldTestDto);
            if (mapForStringKey.get(oldTestDto.getName()).size() == 0){
                mapForStringKey.remove(oldTestDto.getName());
            }
            mapForStringKey.put(newTestDto.getName(), new HashSet<>());
            mapForStringKey.get(newTestDto.getName()).add(newTestDto);
        }
    }

    private void addObjectIntoDoubleHashMap(TestDto newTestDto, TestDto oldTestDto){
        if (newTestDto.getValue().equals(oldTestDto.getValue())){
            mapForDoubleKey.get(newTestDto.getValue()).remove(oldTestDto);
            mapForDoubleKey.get(newTestDto.getValue()).add(newTestDto);
        } else {
            mapForDoubleKey.get(oldTestDto.getValue()).remove(oldTestDto);
            if (mapForDoubleKey.get(oldTestDto.getValue()).size() == 0){
                mapForDoubleKey.remove(oldTestDto.getValue());
            }
            mapForDoubleKey.put(newTestDto.getValue(), new HashSet<>());
            mapForDoubleKey.get(newTestDto.getValue()).add(newTestDto);
        }
    }
}
