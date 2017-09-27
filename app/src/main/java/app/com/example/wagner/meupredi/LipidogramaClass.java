package app.com.example.wagner.meupredi;

/**
 * Created by LeandroDias1 on 27/09/2017.
 */

public class LipidogramaClass {
    int idPacienteLipidograma;
    int HDL;
    int LDL;
    int ColesterolTotal;
    int Triglicerides;

    public int getIdPacienteLipidograma() {
        return idPacienteLipidograma;
    }

    public void setIdPacienteLipidograma(int idPacienteLipidograma) {
        this.idPacienteLipidograma = idPacienteLipidograma;
    }

    public int getHDL() {
        return HDL;
    }

    public void setHDL(int HDL) {
        this.HDL = HDL;
    }

    public int getLDL() {
        return LDL;
    }

    public void setLDL(int LDL) {
        this.LDL = LDL;
    }

    public int getColesterolTotal() {
        return ColesterolTotal;
    }

    public void setColesterolTotal(int colesterolTotal) {
        ColesterolTotal = colesterolTotal;
    }

    public int getTriglicerides() {
        return Triglicerides;
    }

    public void setTriglicerides(int triglicerides) {
        Triglicerides = triglicerides;
    }
}
