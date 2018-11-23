package lifestyle.com.lifestyle.model;

public class OwnMeal {

    private String name;
    private String photo;

    public OwnMeal(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }

    public OwnMeal() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
