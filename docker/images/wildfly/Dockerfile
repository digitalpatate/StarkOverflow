FROM jboss/wildfly:20.0.1.Final


# maintaiener is deprecated
LABEL maintainer="olivier.liechti@heig-vd.ch"

RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

WORKDIR /opt/jboss/wildfly/standalone/deployments
COPY  StarkOverflow.war .                                             