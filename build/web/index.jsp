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
                    <input type="hidden" name="accion" value="generar" />
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textAlternativas">Cantidad de Alternativas</label>  
                        <div class="col-md-4">
                            <input id="textAlternativas" name="textAlternativas" placeholder="" class="form-control input-md" value="2" min="2" required="" type="number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textSituaciones">Cantidad de Situaciones</label>  
                        <div class="col-md-4">
                            <input id="textSituaciones" name="textSituaciones" placeholder="" class="form-control input-md" value="2" min="2" required="" type="number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textPrecioVenta">Precio de Venta</label>  
                        <div class="col-md-4">
                            <input id="textPrecioVenta" name="textPrecioVenta" placeholder="" class="form-control input-md" value="0" step="0.01" required="" type="number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textPenalizacion">Penalizacion de Stock</label>  
                        <div class="col-md-4">
                            <input id="textPenalizacion" name="textPenalizacion" placeholder="" class="form-control input-md" value="0" step="0.01" required="" type="number">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textAlfa">Alfa (α)</label>  
                        <div class="col-md-4">
                            <input id="textAlfa" name="textAlfa" placeholder="" class="form-control input-md" required="" value="0" min="0" max="1" step="0.1" type="number">
                            <span class="help-block">Criterio para calcular Hurwicz</span>  
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="buttonGenerar"></label>
                        <div class="col-md-4">
                            <button id="buttonGenerar" type="submit" name="buttonGenerar" class="btn btn-primary">Continuar</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>
