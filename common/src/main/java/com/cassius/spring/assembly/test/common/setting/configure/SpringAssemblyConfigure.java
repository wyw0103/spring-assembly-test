package com.cassius.spring.assembly.test.common.setting.configure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SpringAssemblyConfigure
 *
 * @author Cassius Cai
 * @version v 0.1 5/11/15 15:13 Exp $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SpringAssemblyConfigure {

    /**
     * Create spy.
     *
     * @return the boolean
     */
    public boolean createSpy() default false;

    /**
     * Reuse spring context.
     *
     * @return the boolean
     */
    public boolean reuseSpringContext() default false;
}
