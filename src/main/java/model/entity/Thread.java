package model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NonNull
        @OneToMany(mappedBy = "thread", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Post> posts;
}
