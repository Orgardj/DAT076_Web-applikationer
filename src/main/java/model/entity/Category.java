package model.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Category implements Serializable {
    //Section is a reserved SQL keyword

    @Id
    @GeneratedValue
    private Long cId;

    @NonNull
    @Column(unique = true)
    private String name;

    @NonNull
    private String description;

    @NonNull
    @OneToMany(mappedBy = "category")
    private List<Thread> threads;
}
