package wrteam.ecart.shop.model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity
public class Slider implements Serializable {
    @ColumnInfo
    String image;
    @ColumnInfo
    String type;
    @PrimaryKey(autoGenerate = true)
    Integer type_id;
    @ColumnInfo
    String name;

    public Slider(String type, Integer type_id, String name, String image) {
        this.type = type;
        this.type_id = type_id;
        this.name = name;
        this.image = image;
    }

    public Slider() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
