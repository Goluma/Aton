package com.aton;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static PersonDto createA(){
        return PersonDto.builder()
                .setAccount(1L)
                .setName("Maria")
                .setValue(130.5)
                .build();
    }

    public static PersonDto createB(){
        return PersonDto.builder()
                .setAccount(2L)
                .setName("Evgeny")
                .setValue(120.5)
                .build();
    }

    public static PersonDto createC(){
        return PersonDto.builder()
                .setAccount(3L)
                .setName("Vladimir")
                .setValue(140.5)
                .build();
    }

    public static PersonDto createD(){
        return PersonDto.builder()
                .setAccount(4L)
                .setName("Oleg")
                .setValue(150.5)
                .build();
    }

    public static PersonDto createE(){
        return PersonDto.builder()
                .setAccount(5L)
                .setName("Anna")
                .setValue(160.5)
                .build();
    }

    public static PersonDto createF(){
        return PersonDto.builder()
                .setAccount(2L)
                .setName("Dmitriy")
                .setValue(170.5)
                .build();
    }

    public static PersonDto createG(){
        return PersonDto.builder()
                .setAccount(7L)
                .setName("Dmitriy")
                .setValue(190.5)
                .build();
    }

    public static PersonDto createH(){
        return PersonDto.builder()
                .setAccount(8L)
                .setName("Igor")
                .setValue(170.5)
                .build();
    }
}
