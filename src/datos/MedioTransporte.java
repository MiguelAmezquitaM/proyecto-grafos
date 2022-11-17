package datos;

public class MedioTransporte {
    private String tipo;

    public MedioTransporte(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "MedioTransporte [tipo=" + tipo + "]";
    }
}
