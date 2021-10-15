package com.yektaanil.linklendin.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author : Yekta Anil AKSOY
 * @since : 15.10.2021
 **/
@MappedSuperclass
@ToString
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Column(name = "Status", length = 1, nullable = false, columnDefinition = "integer default 1")
    private Integer status;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @Column(name = "InsertDateTime", nullable = false)
    private LocalDateTime createDate;

    @PrePersist
    public void prePersist() {
        this.createDate = LocalDateTime.now();
        this.status = 1;
    }
}
