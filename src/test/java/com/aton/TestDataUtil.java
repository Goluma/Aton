package com.aton;

public final class TestDataUtil {
    private TestDataUtil(){}

    public static AccountDto createA(){
        return AccountDto.builder()
                .setAccount(1L)
                .setName("Maria")
                .setValue(130.5)
                .build();
    }

    public static AccountDto createB(){
        return AccountDto.builder()
                .setAccount(2L)
                .setName("Evgeny")
                .setValue(120.5)
                .build();
    }

    public static AccountDto createC(){
        return AccountDto.builder()
                .setAccount(3L)
                .setName("Vladimir")
                .setValue(140.5)
                .build();
    }

    public static AccountDto createD(){
        return AccountDto.builder()
                .setAccount(4L)
                .setName("Oleg")
                .setValue(150.5)
                .build();
    }

    public static AccountDto createE(){
        return AccountDto.builder()
                .setAccount(5L)
                .setName("Anna")
                .setValue(160.5)
                .build();
    }

    public static AccountDto createF(){
        return AccountDto.builder()
                .setAccount(2L)
                .setName("Dmitriy")
                .setValue(170.5)
                .build();
    }

    public static AccountDto createG(){
        return AccountDto.builder()
                .setAccount(7L)
                .setName("Dmitriy")
                .setValue(190.5)
                .build();
    }

    public static AccountDto createH(){
        return AccountDto.builder()
                .setAccount(8L)
                .setName("Igor")
                .setValue(170.5)
                .build();
    }
}
