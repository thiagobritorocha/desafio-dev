package com.upload.file.api.adapter.outbound.repository.transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="pg-uuid")
    private UUID id;

    @ManyToOne private TransactionTypeEntity type;
    private LocalDate date;
    @Column private Double value;
    @Column private String cpf;
    @Column private String cardNumber;
    @Column private LocalTime hour;
    @Column private String storeOwner;
    @Column private String storeName;
}
