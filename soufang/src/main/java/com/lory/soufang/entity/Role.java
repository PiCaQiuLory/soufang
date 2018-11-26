package com.lory.soufang.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String name;
}
