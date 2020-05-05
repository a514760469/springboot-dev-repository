package com.cplh.gis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

import java.io.IOException;

/**
 * @author zhanglifeng
 * @date 2019/12/2/0002
 */
public class TestAsm {


    @Test
    public void testAnnotation() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("com/cplh/gis/asm/PetStoreService.class");

        ClassReader classReader = new ClassReader(classPathResource.getInputStream());

        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor(Thread.currentThread().getContextClassLoader());

        classReader.accept(visitor, ClassReader.SKIP_DEBUG);

        String annotation = "com.cplh.gis.asm.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));

        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);

        Assert.assertEquals("petStoreService", attributes.get("value"));
    }
}
