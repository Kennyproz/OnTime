package Classes;

/**
 * Created by Ken on 9-6-2017.
 */
public class Message {
    private int id;
    private String title;
    private String message;
    private String location;
    private int senderId;
    private int recieverId;

    public Message(String title, String message) {
        this.title = title;
        this.message = message;
    }
    public Message(String title, String message, String location) {
        this.title = title;
        this.message = message;
        this.location = location;
    }

    public Message(int id, String title, String message, String location, int senderId, int recieverId) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.location = location;
        this.senderId = senderId;
        this.recieverId = recieverId;
    }
}
