package model.entity;

import java.io.Serializable;
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
public class AccountAuth implements Serializable {

    @Id
    @GeneratedValue
    private Long aAId;

    @NonNull
    private String selector;

    @NonNull
    private String validator;

    @JoinColumn(name = "userName", nullable = false)
    @NonNull
    @ManyToOne
    private Account account;
}
