package com.univo.backend_app.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AIRepository {

    private final JdbcTemplate jdbcTemplate;

    public AIRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> getLowStockProducts() {

        String sql = """
                SELECT
                    p.nombre,
                    c.nombre AS categoria,
                    i.unidades_disponibles,
                    i.nivel_reorden
                FROM inventario i
                JOIN productos p
                    ON p.id = i.producto_id
                JOIN categorias c
                    ON c.id = p.categoria_id
                WHERE i.unidades_disponibles <= i.nivel_reorden
                ORDER BY i.unidades_disponibles;
                """;

        return jdbcTemplate.queryForList(sql);
    }
    public Map<String, Object> getBusinessSummary() {

        String sql = """
            SELECT

            (SELECT COUNT(*) 
             FROM productos) AS total_productos,

            (SELECT COUNT(*) 
             FROM clientes) AS total_clientes,

            (SELECT COUNT(*) 
             FROM ordenes) AS total_ordenes,

            (SELECT COALESCE(SUM(total),0)
             FROM ordenes
             WHERE estado = 'COMPLETADA') AS ventas_totales,

            (SELECT COUNT(*)
             FROM inventario
             WHERE unidades_disponibles <= nivel_reorden) AS productos_bajo_stock,

            (SELECT COUNT(*)
             FROM devoluciones) AS total_devoluciones,

            (SELECT COUNT(*)
             FROM pagos
             WHERE estado_pago = 'PENDIENTE') AS cantidad_pagos_pendientes,
            
            (SELECT COALESCE(SUM(monto),0)
             FROM pagos
             WHERE estado_pago = 'PENDIENTE') AS monto_pagos_pendientes

            """;


        return jdbcTemplate.queryForMap(sql);
    }
    public List<Map<String, Object>> getTopProducts() {

        String sql = """
            SELECT
                p.nombre,
                c.nombre AS categoria,
                SUM(d.cantidad) AS unidades_vendidas,
                SUM(d.cantidad * d.precio_unitario) AS ingresos_generados

            FROM detalle_orden d

            JOIN productos p
                ON p.id = d.producto_id

            JOIN categorias c
                ON c.id = p.categoria_id

            JOIN ordenes o
                ON o.id = d.orden_id

            WHERE o.estado = 'COMPLETADA'

            GROUP BY
                p.nombre,
                c.nombre

            ORDER BY unidades_vendidas DESC

            LIMIT 10;
            """;


        return jdbcTemplate.queryForList(sql);

    }
    public List<Map<String, Object>> getTopClients() {

        String sql = """
            SELECT
                c.nombre_completo,
                c.email,
                c.ciudad,
                COUNT(o.id) AS total_ordenes,
                SUM(o.total) AS total_gastado

            FROM clientes c

            JOIN ordenes o
                ON o.cliente_id = c.id

            WHERE o.estado = 'COMPLETADA'

            GROUP BY
                c.id,
                c.nombre_completo,
                c.email,
                c.ciudad

            ORDER BY total_gastado DESC

            LIMIT 10;
            """;


        return jdbcTemplate.queryForList(sql);

    }
    public List<Map<String, Object>> getSalesByCategory() {

        String sql = """
            SELECT
                c.nombre AS categoria,
                SUM(d.cantidad) AS unidades_vendidas,
                SUM(d.cantidad * d.precio_unitario) AS ingresos_generados

            FROM detalle_orden d

            JOIN productos p
                ON p.id = d.producto_id

            JOIN categorias c
                ON c.id = p.categoria_id

            JOIN ordenes o
                ON o.id = d.orden_id

            WHERE o.estado = 'COMPLETADA'

            GROUP BY c.nombre

            ORDER BY ingresos_generados DESC;
            """;


        return jdbcTemplate.queryForList(sql);

    }
    public List<Map<String, Object>> getPaymentMethods() {

        String sql = """
            SELECT
                metodo_pago,
                COUNT(*) AS cantidad_pagos,
                SUM(monto) AS monto_total

            FROM pagos

            WHERE estado_pago = 'PAGADO'

            GROUP BY metodo_pago

            ORDER BY monto_total DESC;
            """;


        return jdbcTemplate.queryForList(sql);

    }
    public List<Map<String, Object>> getPendingPayments() {

        String sql = """
            SELECT
                metodo_pago,
                COUNT(*) AS cantidad_pagos_pendientes,
                SUM(monto) AS monto_pendiente

            FROM pagos

            WHERE estado_pago = 'PENDIENTE'

            GROUP BY metodo_pago

            ORDER BY monto_pendiente DESC;
            """;


        return jdbcTemplate.queryForList(sql);

    }
    public List<Map<String, Object>> getReturns() {

        String sql = """
            SELECT
                estado,
                COUNT(*) AS cantidad_devoluciones,
                SUM(cantidad_devuelta) AS productos_devueltos,
                SUM(monto_reembolsado) AS monto_reembolsado_total

            FROM devoluciones

            GROUP BY estado

            ORDER BY cantidad_devoluciones DESC;
            """;


        return jdbcTemplate.queryForList(sql);

    }
    public List<Map<String, Object>> getSalesToday() {

        String sql = """
            SELECT
                COUNT(*) AS ventas_realizadas,
                COALESCE(SUM(total),0) AS ingresos_totales
            FROM ordenes
            WHERE DATE(fecha_orden) = '2026-07-02'
            AND estado = 'COMPLETADA';
            """;


        return jdbcTemplate.queryForList(sql);

    }
}