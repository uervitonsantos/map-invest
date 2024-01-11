package com.map.invest.mapInvest.canonico;

public class ArquivoCanonico {
    public static enum TipoArquivoEnum {
        PDF("pdf"), TXT("txt"), XLSX("xlsx"), XLS("xls");

        private final String extensao;

        public String getExtensao() {
            return extensao;
        }

        private TipoArquivoEnum(String extensao) {
            this.extensao = extensao;
        }
    }

    private static final String NOME_ARQUIVO_COMPLETO = "%s.%s";

    private TipoArquivoEnum tipoArquivo;
    private byte[] arquivo;
    private String nomeArquivo;

    public ArquivoCanonico(TipoArquivoEnum tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public String getExtensao() {
        return tipoArquivo.getExtensao();
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getNomeArquivoCompleto() {
        return String.format(NOME_ARQUIVO_COMPLETO, nomeArquivo, getExtensao());
    }
}
