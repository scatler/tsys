package com.scatler.rrweb.util;

import com.scatler.rrweb.entity.Ticket;

import java.lang.reflect.Field;

public class FieldExtractor {
    public static void main(String[] args) {
        System.out.printf("hello");
        System.out.println(Ticket.class);
        run (Ticket.class);
    }
    public static void run(Class c) {
        for(Field f : c.getDeclaredFields()) {
            if(!f.getType().isPrimitive())
                System.out.println("private" + f.getType().getSimpleName()+ " " + f.getName() + ";");
            else
                run(f.getType());
        }

        for(Field f : c.getDeclaredFields()) {
            if(!f.getType().isPrimitive())
                System.out.println(f.getName());
            else
                run(f.getType());
        }
    }
}
