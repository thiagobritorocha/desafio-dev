package com.upload.file.api.domain.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    private String date;
    private Double value;
    private String cpf;
    private String cardNumber;
    private String hour;
    private String onwnerStore;
    private String nameStore;
    private String typeTransaction;
    private Double valueTypeTransaction;
    private String operation;
}
