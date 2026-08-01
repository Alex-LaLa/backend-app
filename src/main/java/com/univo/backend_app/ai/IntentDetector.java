package com.univo.backend_app.ai;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class IntentDetector {

    private final IntentClassifierAI classifierAI;


    public IntentDetector(IntentClassifierAI classifierAI) {

        this.classifierAI = classifierAI;

    }


    private final Map<Intent, List<String>> keywords = Map.of(

            Intent.LOW_STOCK,
            List.of(
                    "stock",
                    "inventario",
                    "agot",
                    "reabaste",
                    "comprar",
                    "proveedor",
                    "faltan"
            ),

            Intent.TOP_PRODUCTS,
            List.of(
                    "producto más vendido",
                    "productos más vendidos",
                    "vende más",
                    "ventas productos",
                    "productos estrella"
            ),

            Intent.TOP_CLIENTS,
            List.of(
                    "mejor cliente",
                    "mejores clientes",
                    "clientes frecuentes",
                    "clientes que compran más",
                    "cliente más importante"
            ),

            Intent.SALES_BY_CATEGORY,
            List.of(
                    "categoría",
                    "categoria",
                    "ventas por categoría",
                    "ventas por categoria"
            ),

            Intent.PAYMENT_METHODS,
            List.of(
                    "método de pago",
                    "metodo de pago",
                    "formas de pago",
                    "tarjeta",
                    "efectivo",
                    "transferencia"
            ),

            Intent.RETURNS,
            List.of(
                    "devolución",
                    "devolucion",
                    "devoluciones",
                    "productos devueltos"
            ),

            Intent.PENDING_PAYMENTS,
            List.of(
                    "pagos pendientes",
                    "pago pendiente",
                    "pendientes",
                    "debo",
                    "deuda",
                    "adeudo"
            ),

            Intent.SALES_TODAY,
            List.of(
                    "ventas hoy",
                    "ventas de hoy",
                    "vendimos hoy",
                    "venta del día",
                    "ventas del día"
            ),

            Intent.BUSINESS_SUMMARY,
            List.of(
                    "resumen",
                    "dashboard",
                    "estado general",
                    "informe del negocio",
                    "situación del negocio"
            )

    );


    public Intent detect(String question){

        String text = question.toLowerCase();


        // 1. Reglas específicas

        if(text.contains("pagos pendientes")
                || text.contains("pago pendiente")
                || text.contains("debo")
                || text.contains("deuda")
                || text.contains("adeudo")){

            return Intent.PENDING_PAYMENTS;
        }


        if(text.contains("producto más vendido")
                || text.contains("productos más vendidos")
                || text.contains("vende más")){

            return Intent.TOP_PRODUCTS;
        }


        if(text.contains("mejor cliente")
                || text.contains("mejores clientes")
                || text.contains("clientes que compran más")){

            return Intent.TOP_CLIENTS;
        }


        if(text.contains("ventas hoy")
                || text.contains("ventas de hoy")
                || text.contains("vendimos hoy")
                || text.contains("ventas del día")){

            return Intent.SALES_TODAY;
        }



        // 2. Reglas generales

        for(var entry : keywords.entrySet()){

            for(String keyword : entry.getValue()){

                if(text.contains(keyword.toLowerCase())){

                    return entry.getKey();

                }

            }

        }


        // 3. Si no encuentra nada, usa IA

        return classifierAI.classify(question);

    }

}