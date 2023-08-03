package com.upload.file.api.adapter.outbound.repository.transaction;

import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "transactions")
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne private TransactionTypeEntity type;
    private String date;
    @Column private Double value;
    @Column private String cpf;
    @Column private String cardNumber;
    @Column private String hour;
    @Column private String storeOwner;
    @Column private String storeName;
}
