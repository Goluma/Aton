package com.aton;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static TestDto createA(){
        return TestDto.builder()
                .setAccount(1L)
                .setName("Maria")
                .setValue(130.5)
                .build();
    }

    public static TestDto createB(){
        return TestDto.builder()
                .setAccount(2L)
                .setName("Evgeny")
                .setValue(120.5)
                .build();
    }

    public static TestDto createC(){
        return TestDto.builder()
                .setAccount(3L)
                .setName("Vladimir")
                .setValue(140.5)
                .build();
    }

    public static TestDto createD(){
        return TestDto.builder()
                .setAccount(4L)
                .setName("Oleg")
                .setValue(150.5)
                .build();
    }

    public static TestDto createE(){
        return TestDto.builder()
                .setAccount(5L)
                .setName("Anna")
                .setValue(160.5)
                .build();
    }

    public static TestDto createF(){
        return TestDto.builder()
                .setAccount(2L)
                .setName("Dmitriy")
                .setValue(170.5)
                .build();
    }

    public static TestDto createG(){
        return TestDto.builder()
                .setAccount(7L)
                .setName("Dmitriy")
                .setValue(190.5)
                .build();
    }

    public static TestDto createH(){
        return TestDto.builder()
                .setAccount(8L)
                .setName("Igor")
                .setValue(170.5)
                .build();
    }
}
