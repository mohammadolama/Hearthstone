package Client.Model;

public class InfoPassive {

    private String name;
    private String description;

    private InfoPassive(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public InfoPassive() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
