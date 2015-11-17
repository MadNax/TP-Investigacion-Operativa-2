package Modelo;

public class Alternativa {

    private int _suministro;
    private float _costo;

    public Alternativa() {
        _suministro = 0;
        _costo = 0;
    }

    public int getSuministro() {
        return _suministro;
    }

    public void setSuministro(int _suministro) {
        this._suministro = _suministro;
    }

    public float getCosto() {
        return _costo;
    }

    public void setCosto(float _costo) {
        this._costo = _costo;
    }
}
