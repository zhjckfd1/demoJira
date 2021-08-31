package com.example.demojira.exceptions;

import javassist.NotFoundException;

public class MyEntityNotFoundException extends NullPointerException {

    public MyEntityNotFoundException() {
        super("Entity is not found");
    }

    //Имя сущности как правильно вводим?
    //    (если просто ввести - много исправлений при переименовании) (в каждом классе найти переменную и переименовать)
    //    Entity.class.getName()  - лишние связи

    //При поиске по id пользователь и так его вводит, поэтому id не вводим
    public MyEntityNotFoundException(Integer id) {
        super("entity is not found, id = " + id);
    }

    //выводим по сообщению (и по id) (отдельно (2 метода))

    public MyEntityNotFoundException(String message) {
        super(message);
    }

}
