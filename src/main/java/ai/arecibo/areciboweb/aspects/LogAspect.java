package ai.arecibo.areciboweb.aspects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    Logger logger = LogManager.getLogger(LogAspect.class);

    @Before(value = "execution(* ai.arecibo.areciboweb.dao.DatabaseControllerImplement.*(..))")
    public void daoLogBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("Call " + methodName + " in DataBaseController with argument " + Arrays.toString(args));
    }

    @AfterThrowing(value = "execution(* ai.arecibo.areciboweb.dao.DatabaseControllerImplement.*(..))", throwing = "e")
    public void daoLogException(JoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.log(Level.ERROR, "Call " + methodName + " with argument " + Arrays.toString(args) + " throw exception ", e);
    }
}
