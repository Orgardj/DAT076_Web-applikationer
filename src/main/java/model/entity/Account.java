package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Account implements Serializable {

    @Id
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String role;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private Date registerDate;
    
    @NonNull
    private int profilePicture;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Post> posts;

    @OneToMany(mappedBy = "account", orphanRemoval = true, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<AccountAuth> accountAuths;
}
