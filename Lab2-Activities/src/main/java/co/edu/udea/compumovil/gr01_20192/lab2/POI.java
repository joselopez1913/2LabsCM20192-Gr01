package co.edu.udea.compumovil.gr01_20192.lab2;

public class POI {
    private int id;
    private String name;
    private String desc;
    private String point;
    private byte[] image;

    public POI(String name, String desc, String point, byte[] image, int id) {
        this.name = name;
        this.desc = desc;
        this.point = point;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
