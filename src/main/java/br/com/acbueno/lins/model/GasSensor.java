package br.com.acbueno.lins.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "gas_monitor")
public class GasSensor {

  @Id
  private String id;

  private String location;

  private double pressure;

  private boolean leakDetect;

  private long timestamp;

}
