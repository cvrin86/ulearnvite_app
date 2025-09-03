package com.illan.ulearnvite.model.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude="lesson")
public class VideoFile {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String fileName;

    @NotBlank(message = "L'URL du fichier est obligatoire")
    @Column( nullable = false, length  = 50)
    private String fileUrl;

    @Column(length = 50)
    private String fileSize;

    @Column(length = 50)
    private String duration;

    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;

    @OneToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

}
