import java.util.Date;

public class User {

    private int id;
    private int user_id;
    private String phone;
    private int is_verified;
    private Date created_at;
    private Date updated_at;

//    @Override
//    public String toString() {
//        return getClass().getSimpleName() + "{id: " + id
//                + ", user_id: " + user_id
//                + ", phone: " + phone
//                + ", is_verified: " + is_verified
//                + ", created_at: " + created_at
//                + ", updated_at: " + updated_at
//        + "}";
//    }


    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: " + id
                + ", user_id: " + user_id
                + ", phone: " + phone
                + ", is_verified: " + is_verified
                + ", created_at: " + created_at
                + ", updated_at: " + updated_at
                + "}";
    }

    public User(){

    }

    public User(int user_id, String phone, int is_verified, Date created_at, Date updated_at) {
        this.user_id = user_id;
        this.phone = phone;
        this.is_verified = is_verified;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public User(int id, int user_id, String phone, int is_verified, Date created_at, Date updated_at) {
        this.id = id;
        this.user_id = user_id;
        this.phone = phone;
        this.is_verified = is_verified;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIs_verified() {
        return is_verified;
    }

    public void setIs_verified(int is_verified) {
        this.is_verified = is_verified;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
