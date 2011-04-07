/******************************************************************************
 * Copyright (c) 2011 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.tests.java.t0001;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.sapphire.java.JavaType;
import org.eclipse.sapphire.java.JavaTypeKind;
import org.eclipse.sapphire.java.internal.ClassLoaderJavaTypeReferenceService;
import org.eclipse.sapphire.tests.SapphireTestCase;

/**
 * Tests correctness of Java type kind determination of ClassLoaderJavaTypeReferenceService.
 * 
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public final class TestJava0001

    extends SapphireTestCase
    
{
    private static final String PACKAGE_NAME = "org.eclipse.sapphire.tests.java.t0001";
    
    private TestJava0001( final String name )
    {
        super( name );
    }
    
    public static Test suite()
    {
        final TestSuite suite = new TestSuite();
        
        suite.setName( "Java0001" );

        suite.addTest( new TestJava0001( "testIsClass" ) );
        suite.addTest( new TestJava0001( "testIsAbstractClass" ) );
        suite.addTest( new TestJava0001( "testIsInterface" ) );
        suite.addTest( new TestJava0001( "testIsAnnotation" ) );
        suite.addTest( new TestJava0001( "testIsEnum" ) );
        
        return suite;
    }
    
    public void testIsClass()
    {
        final ClassLoaderJavaTypeReferenceService service = new ClassLoaderJavaTypeReferenceService( TestJava0001.class.getClassLoader() );
        final JavaType type = service.resolve( PACKAGE_NAME + ".TestClass" );
        
        assertNotNull( type );
        assertEquals( JavaTypeKind.CLASS, type.getKind() );
    }

    public void testIsAbstractClass()
    {
        final ClassLoaderJavaTypeReferenceService service = new ClassLoaderJavaTypeReferenceService( TestJava0001.class.getClassLoader() );
        final JavaType type = service.resolve( PACKAGE_NAME + ".TestAbstractClass" );
        
        assertNotNull( type );
        assertEquals( JavaTypeKind.ABSTRACT_CLASS, type.getKind() );
    }

    public void testIsInterface()
    {
        final ClassLoaderJavaTypeReferenceService service = new ClassLoaderJavaTypeReferenceService( TestJava0001.class.getClassLoader() );
        final JavaType type = service.resolve( PACKAGE_NAME + ".TestInterface" );
        
        assertNotNull( type );
        assertEquals( JavaTypeKind.INTERFACE, type.getKind() );
    }
    
    public void testIsAnnotation()
    {
        final ClassLoaderJavaTypeReferenceService service = new ClassLoaderJavaTypeReferenceService( TestJava0001.class.getClassLoader() );
        final JavaType type = service.resolve( PACKAGE_NAME + ".TestAnnotation" );
        
        assertNotNull( type );
        assertEquals( JavaTypeKind.ANNOTATION, type.getKind() );
    }
    
    public void testIsEnum()
    {
        final ClassLoaderJavaTypeReferenceService service = new ClassLoaderJavaTypeReferenceService( TestJava0001.class.getClassLoader() );
        final JavaType type = service.resolve( PACKAGE_NAME + ".TestEnum" );
        
        assertNotNull( type );
        assertEquals( JavaTypeKind.ENUM, type.getKind() );
    }

}
