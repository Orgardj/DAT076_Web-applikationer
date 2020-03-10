package model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name = "userName", nullable = false)
    @NonNull
    @ManyToOne private Account user;
    
    @JoinColumn(name = "tId", nullable = false)
    @NonNull
    @ManyToOne private Topic topic;

    @Id
    @GeneratedValue
    private Long pid;
    
    @NonNull
    private String title;
    
    @NonNull
    private String text;
    
    @NonNull
    private Date timestamp;
}
