package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Topic implements Serializable {
    
    @JoinColumn(name = "sId", nullable = false)
    @NonNull
    @ManyToOne private Section section;
    
    @Id
    @GeneratedValue
    private Long tId;
    
    @NonNull
    @OneToMany(mappedBy = "topic") private List<Post> posts;
    
    @NonNull
    private String title;
    
    @NonNull
    private String text;
    
    @NonNull
    private Long views;
    
    @NonNull
    private Date creationTimestamp;
}
