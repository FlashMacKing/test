package com.akampany.api.Image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "images")
@NoArgsConstructor
public class Image {

    @Id
    
    private Long id;

    private String imageName;
    @Lob
    @Column(name = "image_data", columnDefinition = "MEDIUMBLOB")
    private byte[] imageData;
    private String description;
    

   

}