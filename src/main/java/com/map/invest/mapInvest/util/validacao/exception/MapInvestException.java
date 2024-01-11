package com.map.invest.mapInvest.util.validacao.exception;

import jakarta.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class MapInvestException extends RuntimeException {

    public MapInvestException() {
    }

    public MapInvestException(Throwable th) {
        super(th);
    }

    MapInvestException(String mensagem) {
        super(mensagem);
    }

    MapInvestException(String mensagem, Throwable th) {
        super(mensagem, th);
    }
}
