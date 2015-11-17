<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <jsp:include page="includeHeader.jsp"/>
    <body>  
        <h1 class="text-center"><strong>Toma de Decisiones</strong></h1>
        <div class="container" style="padding-top: 50px">
            <form class="form-horizontal" action="ControlDecision">
                <fieldset>
                    <input type="hidden" name="accion" value="calcular" />
                    <c:forEach begin="1" end="${decision.getDecision().getCantidadSituaciones()}" varStatus="index">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="textDemanda">Demanda ${index.count}</label>  
                            <div class="col-sm-3">
                                <input id="textDemanda" name="textDemanda" placeholder="" class="form-control input-md" required="" step="0.01" type="number">
                            </div>
                        </div>
                    </c:forEach>
                    <c:forEach begin="1" end="${decision.getDecision().getCantidadAlternativas()}" varStatus="index">
                        <div class="form-group">
                            <label class="col-sm-3 control-label" for="textSuministro">Suministro ${index.count}</label>  
                            <div class="col-sm-3">
                                <input id="textSuministro" name="textSuministro" placeholder="" class="form-control input-md" required="" step="0.01" type="number">
                            </div>
                            <label class="col-sm-3 control-label" for="textCosto">Costo ${index.count}</label>  
                            <div class="col-sm-3">
                                <input id="textCosto" name="textCosto" placeholder="" class="form-control input-md" required="" step="0.01" type="number">
                            </div>
                        </div>
                    </c:forEach>
                    <div class="form-group">
                        <label class="col-sm-5 control-label" for="buttonGenerar"></label>
                        <div class="col-sm-5">
                            <button id="buttonGenerar" name="buttonGenerar" class="btn btn-primary">Continuar</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>
