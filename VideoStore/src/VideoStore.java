import java.util.*;

public class VideoStore {
    private static VideoStoreInterface videoStore;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Sets which list type will be used
        if (Objects.equals(args[0].toUpperCase(), "SLL")){
            videoStore = new VideoStoreSLL();
        } else if (Objects.equals(args[0].toUpperCase(), "DLL")){
            videoStore = new VideoStoreDLL();
        } else {
            throw new IllegalArgumentException("Must use either SLL or DLL");
        }

        // Either runs automated system or user interaction
        if (args.length > 1 && args.length != 4) {
            throw new IllegalArgumentException();
        } else if (args.length == 4) {
            automated(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        } else {
            boolean exit = false;

            while (!exit) {
                printOptions();
                var command = scanner.nextInt();
                switch (command) {
                    case 1 -> addVideo();
                    case 2 -> deleteVideo();
                    case 3 -> addCustomer();
                    case 4 -> deleteCustomer();
                    case 5 -> checkIfVideoInStore();
                    case 6 -> checkOutVideo();
                    case 7 -> checkInVideo();
                    case 8 -> printAllCustomers();
                    case 9 -> printAllVideos();
                    case 10 -> printInStoreVideos();
                    case 11 -> printRentedVideos();
                    case 12 -> printVideosRentedByACustomer();
                    case 13 -> exit = true;
                }
            }
            System.out.println("Have a good day! Goodbye!");
        }
    }

    private static void automated(int numOfVideos, int numOfCustomers, int numOfRequests){
        // Video ids will be 1 (inclusive) to numOfVideos + 2 (exclusive)
        for (int x = 0; x < numOfVideos; x++) {
            videoStore.addVideo(randomString());
        }
        // Customer ids will be from numOfVideos + 2 (inclusive) to numOfVideos + 2 + numOfCustomers (exclusive)
        for (int x = 0; x < numOfCustomers; x++) {
            videoStore.addCustomer(randomString());
        }

        // Generate requests (5, 6, 7) and add to Queue
        int[] queue = new int[numOfRequests];
        Random rand = new Random();
        for (int i = 0; i < queue.length; i++) {
            queue[i] = rand.nextInt(5, 8);
        }
        // Run requests
        long startTime = System.nanoTime();
        for (int i = 0; i < queue.length; i++) {
            if (queue[i] == 5){
                videoStore.checkIfVideoInStore(rand.nextInt(1, numOfVideos + 1));
            } else if (queue[i] == 6) {
                videoStore.checkOut(rand.nextInt(numOfVideos + 1, numOfVideos + 1 + numOfCustomers), rand.nextInt(1, numOfVideos + 1));
            } else if (queue[i] == 7) {
                videoStore.checkIn(rand.nextInt(1, numOfVideos + 1));
            }
        }
        System.out.println("Processing time: " + (System.nanoTime() - startTime) + " nanoseconds");
    }

    private static String randomString() {
        Random rand = new Random();
        StringBuilder str = new StringBuilder();

        str.append( (char) rand.nextInt(65,91) );  // Capital Letter
        for (int i = 0; i < 10 - 1; i++) str.append( (char) rand.nextInt(97, 123) );  // Remaining lowercase letters
        return str.toString();
    }

// 1
    private static void addVideo(){
        System.out.println("Enter video name to add: ");
        scanner.nextLine();
        String videoName = scanner.nextLine();
        videoStore.addVideo(videoName);
        System.out.println("Video added.");
    }
// 2
    private static void deleteVideo() {
        System.out.println("Enter video ID to delete: ");
        int videoID = scanner.nextInt();
        if (videoStore.deleteVideo(videoID)) {
            System.out.println("Video deleted.");
        }
        else {
            System.out.println("Item does not exist.");
        }
    }
// 3
    private static void addCustomer() {
        System.out.println("Enter customer name to add: ");
        scanner.nextLine();
        String customerName = scanner.nextLine();
        videoStore.addCustomer(customerName);
        System.out.println("Customer added.");
    }
// 4
    private static void deleteCustomer() {
        System.out.println("Enter customer ID to delete: ");
        int customerID = scanner.nextInt();
        if (videoStore.deleteCustomer(customerID)) {
            System.out.println("Customer deleted.");
        } else {
            System.out.println("Customer does not exist.");
        }
    }
// 5
    private static void checkIfVideoInStore() {
        System.out.println("Enter ID of video to check: ");
        int videoID = scanner.nextInt();
        if (videoStore.checkIfVideoInStore(videoID))
            System.out.println("It is in the store.");
        else
            System.out.println("It is not in the store.");
    }

// 6
    private static void checkOutVideo() {
        System.out.println("Enter customer ID :");
        int customerID = scanner.nextInt();
        System.out.println("Enter video ID to check out: ");
        int videoID = scanner.nextInt();
        videoStore.checkOut(customerID, videoID);
        System.out.println("Video checked out.");
    }
// 7
    private static void checkInVideo() {
        System.out.println("Enter the video ID to check in: ");
        int videoID = scanner.nextInt();
        videoStore.checkIn(videoID);
        System.out.println("Video checked in.");
    }
// 8
    private static void printAllCustomers() {
        videoStore.printAllCustomers();
    }
// 9
    private static void printAllVideos() {
        videoStore.printAllVideos();
    }
// 10
    private static void printInStoreVideos() {
        videoStore.printInStoreVideos();
    }
// 11
    private static void printRentedVideos() {
        videoStore.printAllRentedVideos();
    }
// 12
    private static void printVideosRentedByACustomer() {
        System.out.println("Please enter customer ID: ");
        int customerID = scanner.nextInt();
        videoStore.printVideosRentedByACustomer(customerID);
    }

    private static void printOptions() {
        System.out.println("""
            ===========================
            Select one of the following:
            1: Add a video
            2: Delete a video
            3: Add a customer
            4: Delete a customer
            5: Check if a video is in the store
            6: Check out a video
            7: Check in a video
            8: Print all customers
            9: Print all videos
            10: Print in store videos
            11: Print rented videos
            12: Print videos rented by customer
            13: Exit
            ===========================
                """);
    }
}