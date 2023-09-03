public class Video {
    private final String title;
    private final int id;

    public Video(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;  // If they are the exact same memory location
        if (o == null || getClass() != o.getClass()) return false;  // Checks to see if both are Videos
        Video video = (Video) o;  // Casts to Video in order to call methods and reach data fields
        return title.equals(video.title);  // Return whether Titles are the same
    }
}
