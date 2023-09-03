public interface VideoStoreInterface {
    void printVideosRentedByACustomer(int customerId);
    void printAllRentedVideos();
    void printInStoreVideos();
    void printAllVideos();
    void printAllCustomers();
    boolean deleteCustomer (int customerId);
    boolean deleteVideo(int videoId);
    boolean checkIn(int videoId);
    boolean checkOut(int customerId, int videoId);
    boolean checkIfVideoInStore(int id);
    void addCustomer(String customer);
    void addVideo(String video);
    Video getVideoById(int videoId);
    Customer getCustomerById(int id);
}
