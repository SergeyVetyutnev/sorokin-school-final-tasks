package dev.sergeyvet.collections.module.task;

import dev.sergeyvet.collections.module.task.exceptions.ContactAlreadyExistException;
import dev.sergeyvet.collections.module.task.exceptions.ContactNotFoundException;
import dev.sergeyvet.collections.module.task.exceptions.InvalidContactDataException;

import java.util.*;

public class ContactBook {
    private final List<Contact> contactsInOrder;
    private final Set<Contact> uniqueContacts;
    private final Map<String, List<Contact>> contactsByGroup;

    public ContactBook (){
        this.contactsInOrder = new ArrayList<>();
        this.uniqueContacts = new HashSet<>();
        this.contactsByGroup = new HashMap<>();
    }



    public void addContact(String name, String phone, String email, String group){
        if (!isValidName(name)){
            throw new InvalidContactDataException("неправильное имя: " + name);
        }
        if (!isValidPhone(phone)){
            throw new InvalidContactDataException("неправильный телефон(нужен +): " + phone);
        }
        if (!isValidEmail(email)){
            throw new InvalidContactDataException("неправильная почта(нужна @): " + email);
        }
        if (!isValidGroup(group)){
            throw new InvalidContactDataException("неправильная группа: " + group);
        }
        Contact contact = new Contact(name, phone, email, group);


        if (!uniqueContacts.add(contact)){
            throw new ContactAlreadyExistException("такой контакт уже есть");
        }

        contactsInOrder.add(contact);

        contactsByGroup.computeIfAbsent(group, k -> new ArrayList<>()).add(contact);
    }

    public void deleteContact (String phone){
        Contact contactToRemove = null;
        for (Contact contact : contactsInOrder){
            if (Objects.equals(contact.getPhone(), phone)){
                contactToRemove = contact;
                System.out.println("удален контакт с номером " + phone);
                break;
            }
        }
        if (contactToRemove == null){
            throw new ContactNotFoundException("Контакт с телефоном " + phone + " не найден");
        }

        contactsInOrder.remove(contactToRemove);
        uniqueContacts.remove(contactToRemove);

        String group = contactToRemove.getGroup();
        List<Contact> groupContacts = contactsByGroup.get(group);

        if (groupContacts != null){
            groupContacts.remove(contactToRemove);

            if (groupContacts.isEmpty()){
                contactsByGroup.remove(group);
            }
        }

    }

    public void printContacts(){
        Iterator<Contact> iterator = contactsInOrder.iterator();
        System.out.println("контакты:");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public void printContactsByGroup(String group){
        List<Contact> groupContacts = contactsByGroup.get(group);
        if (groupContacts == null || groupContacts.isEmpty()){
            System.out.println("в группе '" + group + "' нет контактов");
            return;
        }

        System.out.println("контакты в группе " + group);
        Iterator<Contact> iterator = groupContacts.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    public Contact findByPhone(String phone){
        for (Contact contact : contactsInOrder){
            if (contact.getPhone().equals(phone)){
                return contact;
            }
        }
        throw new ContactNotFoundException("Контакт с телефоном " + phone + " не найден");
    }

    public Contact findByEmail(String email){
        for (Contact contact : contactsInOrder){
            if (contact.getEmail().equals(email)){
                return contact;
            }
        }
        throw new ContactNotFoundException("Контакт с почтой " + email + " не найден");
    }

    public List<Contact> findByName(String name){
        List<Contact> contactsList = new ArrayList<>();

        for (Contact contact : contactsInOrder){
            if (contact.getName().equals(name)){
                contactsList.add(contact);
            }
        }

        if (!contactsList.isEmpty()){
            return contactsList;
        }else {
            throw new ContactNotFoundException("Контакт с именем " + name + " не найден");
        }
    }

    private boolean isValidName(String name){
        return  name != null && !name.isEmpty();
    }

    private boolean isValidPhone(String phone){
        return phone != null && phone.contains("+");
    }

    private boolean isValidEmail(String email){
        return email != null && email.contains("@");
    }

    private boolean isValidGroup(String group){
        return group != null && !group.isEmpty();
    }

}
