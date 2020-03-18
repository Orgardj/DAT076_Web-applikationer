package model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Post implements Serializable {

    @Id
    @GeneratedValue
    private Long pId;

    @NonNull
    @Lob
    private String text;

    @NonNull
    private Date creationTimestamp;

    @JoinColumn(name = "userName", nullable = false)
    @NonNull
    @ManyToOne
    private Account user;

    @JoinColumn(name = "tId", nullable = false)
    @NonNull
    @ManyToOne
    private Thread thread;
    
    @NonNull
    private String editTimestamp;
}
