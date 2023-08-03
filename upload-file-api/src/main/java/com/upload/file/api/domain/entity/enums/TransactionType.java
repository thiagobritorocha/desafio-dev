package com.upload.file.api.domain.entity.enums;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum TransactionType {
    DEBITO(1, "Débito", "Entrada") {
        @Override
        public Double calcValue(Double value) {
            return value * 1;
        }
    },
    BOLETO(2, "Boleto", "Saída") {
        @Override
        public Double calcValue(Double value) {
            return value * -1;
        }
    },
    FINANCIAMENTO(3, "Financiamento", "Saída") {
        @Override
        public Double calcValue(Double value) {
            return value * -1;
        }
    },
    CREDITO(4, "Crédito", "Entrada") {
        @Override
        public Double calcValue(Double value) {
            return value * 1;
        }
    },
    RECEBIMENTO_EMPRESTIMO(5, "Recebimento de empréstimo", "Entrada") {
        @Override
        public Double calcValue(Double value) {
            return value * 1;
        }
    },
    VENDAS(6, "Vendas", "Entrada") {
        @Override
        public Double calcValue(Double value) {
            return value * 1;
        }
    },
    RECEBIMENTO_TED(7, "Recebimento de Ted", "Entrada") {
        @Override
        public Double calcValue(Double value) {
            return value * 1;
        }
    },
    RECEBIMENTO_DOC(8, "Recebimento de doc", "Entrada") {
        @Override
        public Double calcValue(Double value) {
            return value * 1;
        }
    },
    ALUGUEL(9, "Aluguel", "Saída") {
        @Override
        public Double calcValue(Double value) {
            return value * -1;
        }
    };

    private int transactionType;
    private String transactionTypeDescription;
    private String operation;

    public static TransactionType getType(final int type) {
        return Arrays.asList(TransactionType.values())
                .stream()
                .filter(transactionType -> transactionType.transactionType == type)
                .findFirst()
                .orElse(null);
    }

    public abstract Double calcValue(Double value);
}
