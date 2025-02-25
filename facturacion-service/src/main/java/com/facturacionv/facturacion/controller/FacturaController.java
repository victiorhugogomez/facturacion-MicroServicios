package com.facturacionv.facturacion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @GetMapping("/test")
    public String test() {
        return "¡El servicio de facturación está funcionando correctamente!";
    }
}
