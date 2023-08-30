package org.example;

import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collections;

public class App {
    public static void main( String[] args ) {
        Configuration configuration = new Configuration().
                addAnnotatedClass(Person.class).
                addAnnotatedClass(Item.class);

        SessionFactory factory = configuration.buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            /*Получили товары для человека
            Person person = session.get(Person.class, 3);
            System.out.println(person);

            List<Item> items = person.getItems();
            System.out.println(items);
            */

            /*Получим человека по товарам
            Item item = session.get(Item.class, 5);
            System.out.println(item);

            Person person = item.getOwner();
            System.out.println(person);
             */

            /*Добавим товар для существующего человека
            Person person = session.get(Person.class, 2);
            //связь товара с человеком
            Item newItem = new Item("newItem", person);
            //добавляем товар на стороне человека
            person.getItems().add(newItem);
            session.save(newItem);
            */

            /*Создадим нового человека и создадим новый заказ*/
            Person person = new Person("Kataj", 20);
            Item item = new Item("Mouse", person);

            person.setItems(new ArrayList<>(Collections.singletonList(item)));

            session.save(person);
            session.save(item);


            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }
}
