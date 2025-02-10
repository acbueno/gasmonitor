package br.com.acbueno.lins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.acbueno.lins.service.GasMonitorService;

@RestController
@RequestMapping("/api")
public class ValveController {

  @Autowired
  private GasMonitorService service;


  @PostMapping("/valve/reset")
  public String resetValve() {
    service.resetValve();

    return "✅ Válvula reativada!";
  }

}
