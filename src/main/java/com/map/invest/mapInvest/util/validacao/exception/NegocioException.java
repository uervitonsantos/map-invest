package com.map.invest.mapInvest.util.validacao.exception;

public class NegocioException extends MapInvestException {

    protected NegocioException() {
    }

    public NegocioException(Throwable th) {
        super(th);
    }

    public NegocioException(String mensagem, Throwable th) {
        super(mensagem, th);
    }

    public NegocioException(String mensagem) {
        super(mensagem);
    }

}