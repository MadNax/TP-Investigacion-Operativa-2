package Controladora;

import Modelo.Alternativa;
import Modelo.Decision;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControlDecision", urlPatterns = {"/ControlDecision"})
public class ControlDecision extends HttpServlet {

    private Decision _decision = new Decision();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");  
        HttpSession session = request.getSession(true);
        session.invalidate();
        session = request.getSession(true);
        String accion = request.getParameter("accion");

        switch (accion) {
            case "generar":
                this.generar(request, response, session);
                break;
            case "calcular":
                this.calcular(request, response, session);
                break;
        }
    }

    private void generar(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        Decision nuevaDecision = new Decision();       
        nuevaDecision.setCantidadAlternativas(Integer.parseInt(request.getParameter("textAlternativas")));
        nuevaDecision.setCantidadSituaciones(Integer.parseInt(request.getParameter("textSituaciones")));
        nuevaDecision.setPrecioVenta(Float.parseFloat(request.getParameter("textPrecioVenta")));
        nuevaDecision.setPenalizacion(Float.parseFloat(request.getParameter("textPenalizacion")));
        nuevaDecision.setAlfa(Float.parseFloat(request.getParameter("textAlfa")));
        this.setDecision(nuevaDecision);
        session.setAttribute("decision", this);
        response.sendRedirect("ingresarDatosTabla.jsp");
    }

    private void calcular(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {

        List<Integer> listaDemandas = new ArrayList<>();
        List<Integer> listaSuministros = new ArrayList<>();
        List<Float> listaCostos = new ArrayList<>();
        
        String[] demandas = request.getParameterValues("textDemanda");
        for (String demanda : demandas) {
            listaDemandas.add(Integer.valueOf(demanda.trim()));
        }      
        
        String[] suministros = request.getParameterValues("textSuministro");
        for (String suministro : suministros) {
            listaSuministros.add(Integer.valueOf(suministro.trim()));
        }
        
        String[] costos = request.getParameterValues("textCosto");
        for (String costo : costos) {
            listaCostos.add(Float.valueOf(costo.trim()));
        }

        this.getDecision().setListaSituaciones(listaDemandas);
        
        ArrayList<Alternativa> listaAlternativas = new ArrayList<>();
        for (int i = 0; i < this.getDecision().getCantidadAlternativas(); i++) {
            Alternativa alternativa = new Alternativa();
            alternativa.setCosto(listaCostos.get(i));
            alternativa.setSuministro(listaSuministros.get(i));
            listaAlternativas.add(alternativa);
        }
        this.getDecision().setListaAlternativas(listaAlternativas);
        
        int filas = this.getDecision().getListaAlternativas().size();
        int columnas = this.getDecision().getListaSituaciones().size();
        float[][] matrizCeldas = new float[columnas][filas];
        float valorCelda;

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                valorCelda = this.getDecision().calcularValorCelda(this.getDecision().getListaAlternativas().get(f), this.getDecision().getListaSituaciones().get(c));
                matrizCeldas[c][f] = valorCelda;
            }
        }

        this.getDecision().setMatrizCeldas(matrizCeldas);
        this.getDecision().setListaOptimista(this.getDecision().calcularListaOptimista(filas, columnas, matrizCeldas));
        this.getDecision().setListaPesimista(this.getDecision().calcularListaPesimista(filas, columnas, matrizCeldas));
        this.getDecision().setListaLaplace(this.getDecision().calcularListaLaplace(filas, columnas, matrizCeldas));
        this.getDecision().setListaHurwicz(this.getDecision().calcularListaHurwicz(this.getDecision().getAlfa(), filas, columnas, matrizCeldas));
        this.getDecision().setListaSavage(this.getDecision().calcularListaSavage(filas, columnas, matrizCeldas));
        
        session.setAttribute("decision", this);
        response.sendRedirect("resultados.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public Decision getDecision() {
        return _decision;
    }

    public void setDecision(Decision _decision) {
        this._decision = _decision;
    }

}
