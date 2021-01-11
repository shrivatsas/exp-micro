package org.microexp.configuration;

import com.google.common.net.HostAndPort;
import com.orbitz.consul.Consul;
import com.orbitz.consul.HealthClient;
import com.orbitz.consul.model.agent.ImmutableRegistration;
import com.orbitz.consul.model.health.ServiceHealth;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class AppConfiguration {

    public static final Logger LOG = Logger.getLogger(AppConfiguration.class);
    private String instanceId;

    @Produces
    Consul consulClient = Consul.builder()
            .withMultipleHostAndPort(Set.of(
                    HostAndPort.fromString("172.22.0.21:8500"),
                    HostAndPort.fromString("172.22.0.22:8500"),
                    HostAndPort.fromString("172.22.0.23:8500")), 5000)
            .build();

    @ConfigProperty(name = "quarkus.application.name")
    String appName;

    @ConfigProperty(name = "quarkus.application.version")
    String appVersion;

    void onStart(@Observes StartupEvent ev) {
        ScheduledExecutorService executorService = Executors
                .newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            HealthClient healthClient = consulClient.healthClient();
            List<ServiceHealth> instances = healthClient
                    .getHealthyServiceInstances(appName).getResponse();
            instanceId = appName + "-" + instances.size();
            ImmutableRegistration registration = ImmutableRegistration.builder()
                    .id(instanceId)
                    .name(appName)
                    .port(Integer.parseInt(System.getProperty("quarkus.http.port")))
                    .putMeta("version", appVersion)
                    .build();
            consulClient.agentClient().register(registration);
            LOG.info(String.format("Instance registered: id= %s", registration.getId()));
        }, 5000, TimeUnit.MILLISECONDS);
    }

    void onStop(@Observes ShutdownEvent ev) {
        consulClient.agentClient().deregister(instanceId);
        LOG.info(String.format("Instance de-registered: id= %s", instanceId));
    }
}
