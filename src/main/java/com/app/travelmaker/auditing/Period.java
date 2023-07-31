package com.app.travelmaker.auditing;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class Period {
    @Column(updatable = false)
    private LocalDateTime registerDate;
    private LocalDateTime updateDate;


    @PrePersist
    public void create(){
        this.registerDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PrePersist
    public void update(){
        this.updateDate = LocalDateTime.now();
    }
}
