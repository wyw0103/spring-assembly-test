package com.cassius.spring.assembly.test.common.reflect;

import com.cassius.spring.assembly.test.usages.domain.Person;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

/**
 * FieldWriterTest
 *
 * @author Cassius Cai
 * @version v 0.1 5/30/15 16:04 Exp $
 */
public class FieldWriterTest {

    @Test
    public void testWriteField() throws Exception {
        Person person = new Person();
        int age = 20;
        FieldWriter.newInstance(person, "age", age).write();
        Assertions.assertThat(person.getAge()).isEqualTo(age);
    }
}
