package br.com.acbueno.lins.service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValveControlService {

  public void shutDownValve() {
    log.warn("🚨 VÁLVULA DESLIGADA! (Simulação de segurança)");
  }

}
