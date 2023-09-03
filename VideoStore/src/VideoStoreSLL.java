import Lists.SLL;

import java.util.NoSuchElementException;

public class VideoStoreSLL implements VideoStoreInterface{
    private final SLL videosInStore = new SLL();
    private final SLL customers = new SLL();
    private static int nextId = 1;

    public Customer getCustomerById(int id) {
        boolean match = false;
        int index = 0;
        Customer currCustomer = null;
        while (!match && index < customers.size()) {
            currCustomer = (Customer) customers.get(index++);
            match = currCustomer.getId() == id;
        }
        if (!match) {
            throw new NoSuchElementException();
        }
        return currCustomer;
    }

    public Video getVideoById(int videoId) {
        // Check Store
        if (!(videosInStore.size() == 0)){
            for (int i = 0; i < videosInStore.size(); i++) {
                var currVideo = (Video) videosInStore.get(i);
                if (currVideo.getId() == videoId) {
                    return currVideo;
                }
            }
        }
        // Check Customers
        for (int i = 0; i < customers.size(); i++) {  // Runs through each customer
            Customer currentCustomer = (Customer) customers.get(i);
            SLL rentedVideos = (SLL) currentCustomer.getRentedVideos();
            for (int j = 0; j < rentedVideos.size(); j++) {  // Runs through all rented videos for currCustomer
                Video currVideo = (Video) rentedVideos.get(j);
                if (currVideo.getId() == videoId) {
                    return currVideo;
                }
            }
        }
        return null;
    }

    public void addVideo(String video) {
        videosInStore.add(new Video(video, nextId++));
    }

    public void addCustomer(String customer) {
        customers.add(new Customer(customer, nextId++, Customer.ListType.SLL));
    }

    public boolean checkIfVideoInStore(int id) {
        return videosInStore.contains(getVideoById(id));
    }

    public boolean checkOut(int customerId, int videoId) {
        if (!checkIfVideoInStore(videoId)){
            return false;
        }
        Customer customer = getCustomerById(customerId);
        Video video = getVideoById(videoId);
        customer.rentVideo((Video) videosInStore.remove(video));
        return true;
    }

    public boolean checkIn(int videoId) {
        for (int i = 0; i < customers.size(); i++) {
            Customer currCustomer = (Customer) customers.get(i);
            SLL rentedVideos = (SLL)currCustomer.getRentedVideos();
            for (int j = 0; j < rentedVideos.size(); j++){
                Video video = (Video) rentedVideos.get(j);
                if (videoId == video.getId()) {
                    videosInStore.add(currCustomer.returnVideo(video));  // Remove from customer and add to store
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteVideo(int videoId) {
        for (int x = 0; x < videosInStore.size(); x++) {
            Video currVideo = (Video) videosInStore.get(x);
            if (currVideo.getId() == videoId) {
                videosInStore.remove(currVideo);
                return true;
            }
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer currCustomer = (Customer) customers.get(i);
            SLL rentedVideos = (SLL) currCustomer.getRentedVideos();
            for (int j = 0; j < rentedVideos.size(); j++){
                Video video = (Video) rentedVideos.get(j);
                if (videoId == video.getId()) {
                    currCustomer.returnVideo(video);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteCustomer (int customerId) {
        for (int i = 0; i < customers.size(); i++) {
            Customer currCustomer = (Customer) customers.get(i);
            if (currCustomer.getId() == customerId) {
                customers.remove(currCustomer);
                return true;
            }
        }
        return false;

    }

    public void printAllCustomers() {
        customers.print();
    }

    public void printAllVideos() {
        if (videosInStore.size() == 0){
            printAllRentedVideos();
        }
        else{
            printInStoreVideos();
            printAllRentedVideos();
        }
    }

    public void printInStoreVideos() {
        videosInStore.print();
    }

    public void printAllRentedVideos() {
        for (int i = 0; i < customers.size(); i++) {
            Customer currentCustomer = (Customer) customers.get(i);
            SLL videos = (SLL) currentCustomer.getRentedVideos();
            if (videos.size() > 0) {
                videos.print();
            }
        }
    }

    public void printVideosRentedByACustomer(int customerId) {
        getCustomerById(customerId).getRentedVideos().print();
    }
}
