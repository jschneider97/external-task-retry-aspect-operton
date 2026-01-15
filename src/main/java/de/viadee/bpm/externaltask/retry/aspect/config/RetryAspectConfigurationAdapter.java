package de.viadee.bpm.externaltask.retry.aspect.config;

import de.viadee.bpm.camunda.externaltask.retry.aspect.config.CamundaExternalTaskRetryAspectProperties;
import de.viadee.bpm.operaton.externaltask.retry.aspect.config.config.OperatonExternalTaskRetryAspectProperties;
import io.micrometer.common.util.StringUtils;

import java.util.Optional;

public class RetryAspectConfigurationAdapter extends RetryAspectConfiguration {

    private static final String defaultBehavior = "R3/PT5M";
    private static final String identifier = "RETRY_CONFIG";

    private final CamundaExternalTaskRetryAspectProperties camundaProperties;
    private final OperatonExternalTaskRetryAspectProperties operatonExternalTaskRetryAspectProperties;

    public RetryAspectConfigurationAdapter(CamundaExternalTaskRetryAspectProperties camundaProperties, OperatonExternalTaskRetryAspectProperties operatonExternalTaskRetryAspectProperties) {
        this.camundaProperties = camundaProperties;
        this.operatonExternalTaskRetryAspectProperties = operatonExternalTaskRetryAspectProperties;
    }

    @Override
    public String getDefaultBehavior() {
        return Optional.ofNullable(camundaProperties)
                .map(RetryAspectConfiguration::getDefaultBehavior)
                .filter(StringUtils::isNotBlank).or(() -> Optional.ofNullable(operatonExternalTaskRetryAspectProperties).map(RetryAspectConfiguration::getDefaultBehavior))
                .filter(StringUtils::isNotBlank)
                .orElse(defaultBehavior);
    }

    @Override
    public String getIdentifier() {
        return Optional.ofNullable(camundaProperties)
                .map(RetryAspectConfiguration::getDefaultBehavior)
                .filter(StringUtils::isNotBlank).or(() -> Optional.ofNullable(operatonExternalTaskRetryAspectProperties).map(RetryAspectConfiguration::getIdentifier))
                .filter(StringUtils::isNotBlank)
                .orElse(identifier);
    }

}
