/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function ()
{

    $("#enviar").on("click", enviar);
    function enviar()
    {
        var texto = editAreaLoader.getValue("textarea_1");
        $.post("Controladora", {
            operacion: "analizar",
            texto: texto
        }, function (data) {
            var resultado = data;
            alert(resultado);
        }).fail(function ()
        {
            alert("Error en la operacion");
        });
    }
    
    $("#generar").on("click", generar);
    function generar()
    {
        $.post("Controladora", {
            operacion: "generar",
        }, function (data) {
            var resultado = data;
            alert(resultado);
        }).fail(function ()
        {
            alert("Error en la operacion");
        });
    }

    $("#breakpoint").on("click", breakpoint);
    function breakpoint()
    {
//        editAreaLoader.insertTags("textarea_1","X","X");
        var campo = document.getElementById("selection_field");
        campo.style.backgroundColor = "red";
    }
    

});
