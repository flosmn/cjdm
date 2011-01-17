/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// $Id: GenericsTest.java 20721 2010-09-27 12:40:10Z hardy.ferentschik $

package org.hibernate.jpamodelgen.test.generatedannotation;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor;
import org.hibernate.jpamodelgen.test.util.CompilationTest;

import static org.hibernate.jpamodelgen.test.util.TestUtil.assertMetamodelClassGeneratedFor;
import static org.hibernate.jpamodelgen.test.util.TestUtil.getMetaModelSourceAsString;
import static org.testng.Assert.assertTrue;

/**
 * @author Hardy Ferentschik
 */
public class GeneratedAnnotationTest2 extends CompilationTest {

	@Test
	public void testGeneratedAnnotationGenerated() {
		assertMetamodelClassGeneratedFor( TestEntity.class );

		// need to check the source because @Generated is not a runtime annotation
		String metaModelSource = getMetaModelSourceAsString( TestEntity.class );
		assertTrue( metaModelSource.contains( "@Generated" ), "@Generated should be added to the metamodel." );
	}

	@Override
	protected Map<String, String> getProcessorOptions() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put(
				JPAMetaModelEntityProcessor.ADD_GENERATED_ANNOTATION,
				"true"
		);
		return properties;
	}

	@Override
	protected String getPackageNameOfCurrentTest() {
		return GeneratedAnnotationTest2.class.getPackage().getName();
	}
}