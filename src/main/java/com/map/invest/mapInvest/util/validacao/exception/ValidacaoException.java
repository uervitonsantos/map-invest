package com.map.invest.mapInvest.util.validacao.exception;

import com.map.invest.mapInvest.util.validacao.Codigo;

import java.util.Map;

public class ValidacaoException extends NegocioException {

    private Codigo codigo;
    private Object[] params;
    private MensagemMapInvest mensagemMapInvest;
    private Map<String, String> parametros;

    public ValidacaoException(Codigo codigo) {
        this.codigo = codigo;
    }

    public ValidacaoException(Codigo codigo, Throwable th) {
        super(th);
        this.codigo = codigo;
    }

    public ValidacaoException(Codigo codigo, Throwable th, Object... params) {
        this(codigo, th);
        this.params = params;
    }

    public ValidacaoException(Codigo codigo, Object... params) {
        this(codigo);
        this.params = params;
    }

    public ValidacaoException(MensagemMapInvest mensagemMapInvest) {
        this.mensagemMapInvest = mensagemMapInvest;
    }

    public ValidacaoException(MensagemMapInvest mensagemMapInvest, Object... params) {
        this.mensagemMapInvest = mensagemMapInvest;
        this.params = params;
    }

    public ValidacaoException(MensagemMapInvest mensagemMapInvest, Map<String, String> parametros) {
        this.mensagemMapInvest = mensagemMapInvest;
        this.parametros = parametros;
    }

    public Codigo getCodigo() {
        return codigo;
    }

    public Object[] getParams() {
        return params;
    }

    public boolean hasParams() {
        return params != null && params.length > 0;
    }

    public MensagemMapInvest getMensagemMapInvest() {
        return mensagemMapInvest;
    }

    public void setMensagemMapInvest(MensagemMapInvest mensagemMapInvest) {
        this.mensagemMapInvest = mensagemMapInvest;
    }

    public Map<String, String> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, String> parametros) {
        this.parametros = parametros;
    }

}
