package ch.heigvd.amt.starkoverflow.domain;

import lombok.Builder;

import java.security.cert.CertPathBuilder;

public interface IEntity {
    <U extends Id> U getId();
}
