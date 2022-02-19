package mk.ukim.finki.wp.cineverse.model;

import lombok.Data;

@Data
public class Image {

    private Long imageId;
    private String imageUrl;

    public Image(Long imageId, String imageUrl) {
        this.imageId = imageId;
        this.imageUrl = imageUrl;
    }
}
