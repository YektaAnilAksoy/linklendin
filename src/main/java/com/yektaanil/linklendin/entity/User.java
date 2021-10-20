package com.yektaanil.linklendin.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "user")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_Sequence")
    @SequenceGenerator(name = "User_Sequence", sequenceName = "User_Sequence_Id", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname", nullable = false, length = 30)
    private String surname;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Link> links;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Email(regexp = ".+@.+\\..+")
    @Column(name = "email", nullable = false, unique = true, length = 250)
    private String email;

    public void addLink(Link link) {
        links.add(link);
        link.setUser(this);
    }

    public void removeLink(Link link) {
        links.remove(link);
        link.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User other = (User) o;

        return id != null &&
                id.equals(other.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}