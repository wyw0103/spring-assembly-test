package com.cassius.spring.assembly.test.common.reflect;

import com.cassius.spring.assembly.test.usages.domain.Address;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * FieldReaderTest
 *
 * @author Cassius Cai
 * @version v 0.1 5/30/15 15:18 Exp $
 */
public class FieldReaderTest {

    /**
     * Test read field.
     *
     * @throws Exception the exception
     */
    @Test
    public void testReadField() throws Exception {
        Address address = Address.region("CN").province("ZJ").city("HZ").street("WT").build();
        Assertions.assertThat(FieldReader.newInstance(address, "region").read()).isEqualTo("CN");
        Assertions.assertThat(FieldReader.newInstance(address, "province").read()).isEqualTo("ZJ");
        Assertions.assertThat(FieldReader.newInstance(address, "city").read()).isEqualTo("HZ");
        Assertions.assertThat(FieldReader.newInstance(address, "street").read()).isEqualTo("WT");
    }

}
