package io.bareun.sample.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class TransactionAspect {

    private final PlatformTransactionManager transactionManager;

    @Around("@within(org.springframework.stereotype.Service)")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            log.info("Transaction [{}] committed", joinPoint.getSignature().getName());
            return result;
        } catch (Throwable ex) {
            transactionManager.rollback(status);
            log.error("Transaction [{}] rolled back", joinPoint.getSignature().getName(), ex);
            throw ex;
        }
    }
}
