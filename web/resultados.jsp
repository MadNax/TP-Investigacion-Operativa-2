<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <jsp:include page="includeHeader.jsp"/>
    <body>
        <h1 class="text-center"><strong>Toma de Decisiones</strong></h1>
        <div class="container col-lg-offset-2" style="padding-top: 50px">
            <table class="table table-striped text-center">
                <th class="text-center">Alternativas</th>
                    <c:forEach items="${decision.getDecision().getListaSituaciones()}" var="demanda" varStatus="loop">
                    <th class="text-center">D${loop.count}= ${demanda}</th>
                    </c:forEach>
                <th class="text-center"></th>
                <th class="text-center">Optimista</th>
                <th class="text-center">Pesimista</th>
                <th class="text-center">Laplace</th>
                <th class="text-center">Hurwicz</th>
                <th class="text-center">Savage</th>
                <c:forEach items="${decision.getDecision().getListaAlternativas()}" var="alternativa" varStatus="alt">
                    <tr>
                        <td>A${alt.count}= ${alternativa.getSuministro()}</td>
                        <c:forEach begin="0" end="${decision.getDecision().getCantidadSituaciones()}" varStatus="sit">
                            <td>${decision.getDecision().getMatrizCeldas()[sit.index][alt.index]}</td>
                        </c:forEach>
                        <td class="optimista">${decision.getDecision().getListaOptimista().get(alt.index)}</td>
                        <td class="pesimista">${decision.getDecision().getListaPesimista().get(alt.index)}</td>
                        <td class="laplace">${decision.getDecision().getListaLaplace().get(alt.index)}</td>
                        <td class="hurwicz">${decision.getDecision().getListaHurwicz().get(alt.index)}</td>
                        <td class="savage">${decision.getDecision().getListaSavage().get(alt.index)}</td>
                    </tr>
                </c:forEach>        
            </table>
        </div>
        <script src="scripts/jquery-2.1.4.min.js" type="text/javascript"></script>
        <script src="scripts/scriptResultados.js" type="text/javascript"></script>
    </body>
</html>
