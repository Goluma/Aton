package com.aton;

public class Aton {

    public static void main(String[] args) {
        Service service = new Service();
        TestDto testDto = TestDto.builder()
                .setAccount(1L)
                .setName("Evgeny")
                .setValue(140.5)
                .build();
        TestDto testDto1 = TestDto.builder()
                .setAccount(2L)
                .setName("Evgeny")
                .setValue(140.5)
                .build();
        TestDto testDto2 = TestDto.builder()
                .setAccount(3L)
                .setName("Evgeny")
                .setValue(140.5)
                .build();

        TestDto testDto3 = TestDto.builder()
                .setAccount(4L)
                .setName("Evgeny")
                .setValue(140.5)
                .build();

        TestDto testDto4 = TestDto.builder()
                .setAccount(4L)
                .setName("Evgeny")
                .setValue(140.5)
                .build();

        service.addValue(testDto);
        service.addValue(testDto1);
        service.addValue(testDto2);
        service.addValue(testDto3);

        service.removeValue(testDto4);

        System.out.println(service.getObjectByAnyField(4L));

    }
}
