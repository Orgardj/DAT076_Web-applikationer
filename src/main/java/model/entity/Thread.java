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
public class Thread implements Serializable {

    @Id
    @GeneratedValue
    private Long tId;

    @NonNull
    private String title;

    @NonNull
    private Long views;

    @NonNull
    private Date creationTimestamp;

    @JoinColumn(name = "cId", nullable = false)
    @NonNull
    @ManyToOne
    private Category category;

    @NonNull
    @OneToMany(mappedBy = "thread")
    private List<Post> posts;
}