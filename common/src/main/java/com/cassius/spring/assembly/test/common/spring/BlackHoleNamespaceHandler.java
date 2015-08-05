package com.cassius.spring.assembly.test.common.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * BlackHoleNamespaceHandler
 *
 * @author Cassius Cai
 * @version v 0.1 8/6/15 02:29 Exp $
 */
public class BlackHoleNamespaceHandler implements NamespaceHandler {

    @Override
    public void init() {
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return null;
    }

    @Override
    public BeanDefinitionHolder decorate(Node node, BeanDefinitionHolder beanDefinitionHolder,
                                         ParserContext parserContext) {
        return null;
    }
}
