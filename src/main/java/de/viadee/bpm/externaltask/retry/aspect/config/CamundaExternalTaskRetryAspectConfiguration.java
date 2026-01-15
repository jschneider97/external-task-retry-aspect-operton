package de.viadee.bpm.externaltask.retry.aspect.config;

import de.viadee.bpm.camunda.externaltask.retry.aspect.CamundaExternalTaskRetryAspect;
import de.viadee.bpm.camunda.externaltask.retry.aspect.config.CamundaExternalTaskRetryAspectProperties;
import de.viadee.bpm.externaltask.retry.aspect.service.BusinessErrorService;
import de.viadee.bpm.externaltask.retry.aspect.service.FailureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(org.camunda.bpm.client.ExternalTaskClient.class)
@EnableConfigurationProperties({CamundaExternalTaskRetryAspectProperties.class})
public class CamundaExternalTaskRetryAspectConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CamundaExternalTaskRetryAspect camundaExternalTaskRetryAspect(@Autowired final FailureService failureService, @Autowired BusinessErrorService businessErrorService) {
        return new CamundaExternalTaskRetryAspect(
                businessErrorService,
                failureService
        );
    }

}
