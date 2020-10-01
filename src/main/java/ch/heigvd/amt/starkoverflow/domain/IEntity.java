package ch.heigvd.amt.starkoverflow.domain;

import lombok.Builder;

import java.security.cert.CertPathBuilder;

public interface IEntity <ENTITY extends IEntity, U extends Id>{
     U getId();
     ENTITY deepClone();
}
