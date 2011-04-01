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

package org.eclipse.sapphire.tests.modeling.xml.binding.t0001;

import org.eclipse.sapphire.modeling.ElementProperty;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelElementHandle;
import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.annotations.GenerateImpl;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.annotations.XmlElementBinding;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@GenerateImpl

public interface ITestXmlBinding0001ModelElement extends IModelElement
{
    ModelElementType TYPE = new ModelElementType( ITestXmlBinding0001ModelElement.class );
    
    // *** TestProperty ***

    @Type( base = ITestXmlBinding0001ModelElement.class )
    @XmlElementBinding( mappings = @XmlElementBinding.Mapping( element = "", type = ITestXmlBinding0001ModelElement.class ) )
    
    ElementProperty PROP_TEST_PROPERTY = new ElementProperty( TYPE, "TestProperty" );
    
    ModelElementHandle<ITestXmlBinding0001ModelElement> getTestProperty();
    
}
