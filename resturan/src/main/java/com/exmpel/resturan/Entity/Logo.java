package com.exmpel.resturan.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "logoes")
public class Logo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private Byte[] data;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Resturan_id")
    private Resturan resturan;

    public Logo(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = convert(data);
    }

    public Logo() {
    }

    public Resturan getResturan() {
        return resturan;
    }

    public void setResturan(Resturan resturan) {
        this.resturan = resturan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    public Byte[] convert(byte[] data)
    {
        Byte[] byteObjects = new Byte[data.length];

        int i=0;
        for(byte b: data)
            byteObjects[i++] = b;  // Autoboxing.

        return byteObjects;
    }
}
