package de.viadee.bpm.externaltask.retry.aspect.config;

import de.viadee.bpm.externaltask.retry.aspect.service.BusinessErrorService;
import de.viadee.bpm.externaltask.retry.aspect.service.FailureService;
import de.viadee.bpm.operaton.externaltask.retry.aspect.config.OperatonExternalTaskRetryAspect;
import de.viadee.bpm.operaton.externaltask.retry.aspect.config.config.OperatonExternalTaskRetryAspectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(org.operaton.bpm.client.ExternalTaskClient.class)
@EnableConfigurationProperties(OperatonExternalTaskRetryAspectProperties.class)
public class OperatonExternalTaskRetryAspectConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public OperatonExternalTaskRetryAspect operatonExternalTaskRetryAspect(@Autowired final FailureService failureService, @Autowired BusinessErrorService businessErrorService) {
        return new OperatonExternalTaskRetryAspect(
                businessErrorService,
                failureService
        );
    }

}
