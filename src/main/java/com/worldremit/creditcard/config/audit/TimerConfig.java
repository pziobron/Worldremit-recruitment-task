package com.worldremit.creditcard.config.audit;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimerConfig {

    /**
     * Bean required to use the @Timed annotation on methods.
     * @param registry Metric registry.
     * @return Timed aspect for use in measuring method call times via the Timed annotation.
     */
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }

}