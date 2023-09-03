import Lists.*;

import java.util.NoSuchElementException;

public class Customer {
    private String name;
    private int id;
    private Lists rentedVideos;
    public enum ListType { SLL, DLIST }

    public Customer(String name, int id, ListType type) {
        this.name = name;
        this.id = id;
        if (type == ListType.SLL) {
            rentedVideos = new SLL();
        } else {
            rentedVideos = new DList();
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Lists getRentedVideos() {
        return rentedVideos;
    }

    public void rentVideo(Video videoToRent) {
        rentedVideos.add(videoToRent);
    }

    public Video returnVideo(Video videoToReturn) {
        if (! rentedVideos.contains(videoToReturn)) {
            throw new NoSuchElementException();
        }
        return (Video) rentedVideos.remove(videoToReturn);
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // If they are the exact same memory location
        if (o == null || getClass() != o.getClass()) return false;  // Checks to see if both are Videos
        Customer customer = (Customer) o;  // Casts to Video in order to call methods and reach data fields
        return name.equals(customer.name);  // Return whether Titles are the same
    }
}
