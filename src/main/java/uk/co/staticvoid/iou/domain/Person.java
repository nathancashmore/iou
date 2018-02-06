package uk.co.staticvoid.iou.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "nickname")
    private String nickname;

    public Person() {
        this.uuid = UUID.randomUUID().toString();
    }

    public Person(String nickname) {
        this();
        this.nickname = nickname;
    }

    public Person(String uuid, String nickname) {
        this.uuid = uuid;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getNickname() {
        return nickname;
    }
}
