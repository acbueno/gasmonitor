package br.com.acbueno.lins.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import br.com.acbueno.lins.model.GasSensor;
import br.com.acbueno.lins.repository.GasMonitorRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GasMonitorService {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @Autowired
  private ValveControlService valveControlService;

  @Autowired
  private GasMonitorRepository repository;

  private static final double PRESSURE_LIMIT = 8.5;

  private boolean valveOpen = true;


  @Scheduled(fixedRate = 10000)
  public void monitorGasSensors() {
    double pressure = readPressure();
    log.info("📡 Pressão Atual: {} bar", pressure);
    messagingTemplate.convertAndSend("/topic/pressure", pressure);
    GasSensor gasSensor = new GasSensor();
    gasSensor.setLocation("Tubo 1");
    gasSensor.setPressure(pressure);
    gasSensor.setLeakDetect(leakDetect(pressure));
    gasSensor.setTimestamp(System.currentTimeMillis());
    repository.save(gasSensor);

    List<GasSensor> last5Readings = repository.findLast5Readings();
    messagingTemplate.convertAndSend("/topic/last5", last5Readings);

    if (leakDetect(pressure)) {
      log.warn("⚠️ ALTA PRESSÃO DETECTADA! DESLIGANDO VÁLVULA...");
      valveControlService.shutDownValve();
      valveOpen = false;
      messagingTemplate.convertAndSend("/topic/valve", "Fechada");
      messagingTemplate.convertAndSend("/topic/alert",
          "🚨 ALERTA: Pressão alta! Válvula desligada.");
    } else if (pressure <= PRESSURE_LIMIT && !valveOpen) {
      valveOpen = true;
      messagingTemplate.convertAndSend("/topic/valve", "Aberta");
      log.info("✅ Pressão normalizada. Válvula pode ser reativada.");
      messagingTemplate.convertAndSend("/topic/alert", "✅ Pressão normalizada. Válvula ativada.");
    }
  }

  private double readPressure() {
    return 5 + Math.random() * 5;
  }

  private boolean leakDetect(double pressure) {
    if (pressure > PRESSURE_LIMIT && valveOpen) {
      return true;
    }
    return false;
  }

  public void resetValve() {
    log.info("🔄 RESETANDO SISTEMA...");
    valveOpen = true;
    messagingTemplate.convertAndSend("/topic/valve", "Aberta");
  }

}
