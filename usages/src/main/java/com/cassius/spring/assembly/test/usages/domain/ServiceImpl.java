package com.cassius.spring.assembly.test.usages.domain;

/**
 * ServiceImpl
 *
 * @author Cassius Cai
 * @version v 0.1 8/5/15 23:49 Exp $
 */
public class ServiceImpl implements Service {
    @Override
    public String serve() {
        return "serving";
    }
}
