package com.univo.backend_app.ai;

import org.springframework.stereotype.Component;

@Component
public class StockClassifier {

    public String clasificarStock(int stock, int reorden) {

        if (stock < (reorden / 2.0)) {
            return "CRÍTICO";
        }

        if (stock <= reorden) {
            return "ATENCIÓN";
        }

        return "NORMAL";
    }
}