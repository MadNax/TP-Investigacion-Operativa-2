package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Decision {

    private ArrayList<Alternativa> _listaAlternativas;
    private List<Integer> _listaSituaciones;
    private List<Float> _listaOptimista;
    private List<Float> _listaPesimista;
    private List<Float> _listaLaplace;
    private List<Float> _listaSavage;
    private List<Float> _listaHurwicz;
    private int _cantidadAlternativas;
    private int _cantidadSituaciones;
    private float _precioVenta;
    private float _penalizacion;
    private float _alfa;
    private float[][] _matrizCeldas;

    public Decision() {
        this._listaAlternativas = new ArrayList<>();
        this._listaSituaciones = new ArrayList<>();
        this._listaOptimista = new ArrayList<>();
        this._listaPesimista = new ArrayList<>();
        this._listaLaplace = new ArrayList<>();
        this._listaSavage = new ArrayList<>();
        this._listaHurwicz = new ArrayList<>();
        this._cantidadAlternativas = 0;
        this._cantidadSituaciones = 0;
        this._precioVenta = 0;
        this._penalizacion = 0;
        this._alfa = 0;
        this._matrizCeldas = new float[0][0];
    }

    // SUMINISTRO > DEMANDA
    // D*(PV-C)-(C-PV/2)*(S-D)
    // SUMINISTRO < DEMANDA
    // S*(PV-C)-M*(D-S)
    public float calcularValorCelda(Alternativa alternativa, int demanda) {
        float valorCelda;
        if (alternativa.getSuministro() >= demanda) {
            valorCelda = demanda * (this.getPrecioVenta() - alternativa.getCosto())
                    - (alternativa.getCosto() - this.getPrecioVenta() / 2) * (alternativa.getSuministro() - demanda);
        } else {
            valorCelda = alternativa.getSuministro() * (this.getPrecioVenta()
                    - alternativa.getCosto()) - this.getPenalizacion() * (demanda - alternativa.getSuministro());
        }
        return valorCelda;
    }
     
    public List<Float> calcularListaOptimista(int filas, int columnas, float[][] matrizCeldas) {
        List<Float> filaOptimista = new ArrayList<>();
        List<Float> columnaOptimista = new ArrayList<>();
        for (int c = 0; c < columnas; c++) {
            for (int f = 0; f < filas; f++) {
                filaOptimista.add(matrizCeldas[f][c]);
            }
            columnaOptimista.add(Collections.max(filaOptimista));
            filaOptimista.clear();
        }
        return columnaOptimista;
    }
    
    public List<Float> calcularListaPesimista(int filas, int columnas, float[][] matrizCeldas) {
        List<Float> filaPesimista = new ArrayList<>();
        List<Float> columnaPesimista = new ArrayList<>();
        for (int c = 0; c < columnas; c++) {
            for (int f = 0; f < filas; f++) {
                filaPesimista.add(matrizCeldas[f][c]);
            }
            columnaPesimista.add(Collections.min(filaPesimista));
            filaPesimista.clear();
        }
        return columnaPesimista;
    }
    
    public List<Float> calcularListaLaplace(int filas, int columnas, float[][] matrizCeldas) {
        List<Float> columnaLaplace = new ArrayList<>();
        float promedio = 0;
        for (int c = 0; c < columnas; c++) {
            for (int f = 0; f < filas; f++) {
                promedio += matrizCeldas[f][c];
            }
            columnaLaplace.add(promedio / columnas);
            promedio = 0;
        }
        return columnaLaplace;
    }
    
    public List<Float> calcularListaHurwicz(float alfa, int filas, int columnas, float[][] matrizCeldas) {
        List<Float> filaHurwicz = new ArrayList<>();
        List<Float> columnaHurwicz = new ArrayList<>();
        float resultado;
        float maximo;
        float minimo;
        
        for (int c = 0; c < columnas; c++) {
            for (int f = 0; f < filas; f++) {
                filaHurwicz.add(matrizCeldas[f][c]);
            }
            minimo = Collections.min(filaHurwicz);
            maximo = Collections.max(filaHurwicz);
            resultado = maximo * alfa + (1 - alfa) * minimo;
            columnaHurwicz.add(resultado);

            filaHurwicz.clear();
        }
        return columnaHurwicz;
    }
    
    public List<Float> calcularListaSavage(int filas, int columnas, float[][] matrizCeldas) {
        List<Float> columnaSavage = new ArrayList<>();
        List<Float> filaLamentos = new ArrayList<>();
        List<Float> columnaLamentos = new ArrayList<>();
        float[][] matrizLamentos = new float[filas][columnas];
        float maximoColumna;
        
        for (int c = 0; c < columnas; c++) {
            for (int f = 0; f < filas; f++) {
                columnaSavage.add(matrizCeldas[c][f]);
            }
            maximoColumna = Collections.max(columnaSavage);
            for(int cs = 0; cs < columnaSavage.size(); cs++){
                matrizLamentos[c][cs] = maximoColumna - columnaSavage.get(cs);
            }
            columnaSavage.clear();
        }
        
        for (int c = 0; c < columnas; c++) {
            for (int f = 0; f < filas; f++) {
                filaLamentos.add(matrizLamentos[f][c]);
            }
            columnaLamentos.add(Collections.max(filaLamentos));
            filaLamentos.clear();
        }
        
        return columnaLamentos;
    }
    
    public ArrayList<Alternativa> getListaAlternativas() {
        return _listaAlternativas;
    }

    public void setListaAlternativas(ArrayList<Alternativa> _listaAlternativas) {
        this._listaAlternativas = _listaAlternativas;
    }

    public List<Integer> getListaSituaciones() {
        return _listaSituaciones;
    }

    public void setListaSituaciones(List<Integer> _listaSituaciones) {
        this._listaSituaciones = _listaSituaciones;
    }

    public float getPrecioVenta() {
        return _precioVenta;
    }

    public void setPrecioVenta(float _precioVenta) {
        this._precioVenta = _precioVenta;
    }

    public float getPenalizacion() {
        return _penalizacion;
    }

    public void setPenalizacion(float _penalizacion) {
        this._penalizacion = _penalizacion;
    }

    public float getAlfa() {
        return _alfa;
    }

    public void setAlfa(float _alfa) {
        this._alfa = _alfa;
    }

    public List<Float> getListaOptimista() {
        return _listaOptimista;
    }

    public void setListaOptimista(List<Float> _listaOptimista) {
        this._listaOptimista = _listaOptimista;
    }

    public List<Float> getListaPesimista() {
        return _listaPesimista;
    }

    public void setListaPesimista(List<Float> _listaPesimista) {
        this._listaPesimista = _listaPesimista;
    }

    public List<Float> getListaLaplace() {
        return _listaLaplace;
    }

    public void setListaLaplace(List<Float> _listaLaplace) {
        this._listaLaplace = _listaLaplace;
    }

    public List<Float> getListaSavage() {
        return _listaSavage;
    }

    public void setListaSavage(List<Float> _listaSavage) {
        this._listaSavage = _listaSavage;
    }

    public List<Float> getListaHurwicz() {
        return _listaHurwicz;
    }

    public void setListaHurwicz(List<Float> _listaHurwicz) {
        this._listaHurwicz = _listaHurwicz;
    }

    public int getCantidadAlternativas() {
        return _cantidadAlternativas;
    }

    public void setCantidadAlternativas(int _cantidadAlternativas) {
        this._cantidadAlternativas = _cantidadAlternativas;
    }

    public int getCantidadSituaciones() {
        return _cantidadSituaciones;
    }

    public void setCantidadSituaciones(int _cantidadSituaciones) {
        this._cantidadSituaciones = _cantidadSituaciones;
    }

    public float[][] getMatrizCeldas() {
        return _matrizCeldas;
    }

    public void setMatrizCeldas(float[][] _matrizCeldas) {
        this._matrizCeldas = _matrizCeldas;
    }

}
