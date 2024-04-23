package com.aton;

import java.util.Objects;

public class PersonDto {

    private Long account;

    private String name;

    private Double value;

    private PersonDto(Long account, String name, Double value){
        this.account = account;
        this.name = name;
        this.value = value;
    }

    public static DtoBuilder builder(){
        return new DtoBuilder();
    }

    public static final class DtoBuilder{

        private Long account;

        private String name;

        private Double value;

        public DtoBuilder setAccount(Long account){
            this.account = account;
            return this;
        }

        public DtoBuilder setName(String name){
            this.name = name;
            return this;
        }

        public DtoBuilder setValue(Double value){
            this.value = value;
            return this;
        }

        public PersonDto build(){
            if (name == null || value == null || account == null){
                throw new IllegalArgumentException("Fields of object can't be null");
            }
            return new PersonDto(account, name, value);
        }
    }

    public Long getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "account=" + account +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(account, personDto.account) && Objects.equals(name, personDto.name) && Objects.equals(value, personDto.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, name, value);
    }
}
