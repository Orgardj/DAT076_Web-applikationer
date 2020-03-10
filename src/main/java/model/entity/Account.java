package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class Account implements Serializable {
 
    
    @OneToMany(mappedBy = "user") private List<Post> posts;
    
    @Id
    @NonNull
    private String userName;
    
    @NonNull
    @Column(unique=true)
    private String email;
    
    @NonNull 
    private String role;
    
    @NonNull 
    private String firstName;
    
    @NonNull 
    private String lastName;
    
    @NonNull 
    private String password;
    
    @NonNull 
    private Date registerDate;
}
