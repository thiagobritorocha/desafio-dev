package com.upload.file.api.adapter.outbound.repository.transaction;

import java.util.HashMap;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transactions_type")
public class TransactionTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private String nature;

    @NotNull
    @Column(nullable = false)
    private String signal;

    public static HashMap<String, TransactionTypeEntity> allTypes() {
        HashMap<String, TransactionTypeEntity> types = new HashMap<>();

        types.put("1", new TransactionTypeEntity(1L, "Débito", "Entrada", "+"));
        types.put("2", new TransactionTypeEntity(2L, "Boleto", "Saída", "-"));
        types.put("3", new TransactionTypeEntity(3L, "Financiamento", "Saída", "-"));
        types.put("4", new TransactionTypeEntity(4L, "Crédito", "Entrada", "+"));
        types.put("5", new TransactionTypeEntity(5L, "Recebimento Empréstimo", "Entrada", "+"));
        types.put("6", new TransactionTypeEntity(6L, "Vendas", "Entrada", "+"));
        types.put("7", new TransactionTypeEntity(7L, "Recebimento TED", "Entrada", "+"));
        types.put("8", new TransactionTypeEntity(8L, "Recebimento DOC", "Entrada", "+"));
        types.put("9", new TransactionTypeEntity(9L, "Aluguel", "Saída", "-"));

        return types;
    }

    public static TransactionTypeEntity getTransactionType(String key) {
        return allTypes().get(key);
    }
}
