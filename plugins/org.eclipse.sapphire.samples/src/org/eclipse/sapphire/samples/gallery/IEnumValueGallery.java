/******************************************************************************
 * Copyright (c) 2010 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.samples.gallery;

import org.eclipse.sapphire.modeling.ModelElementType;
import org.eclipse.sapphire.modeling.Value;
import org.eclipse.sapphire.modeling.ValueProperty;
import org.eclipse.sapphire.modeling.annotations.DefaultValue;
import org.eclipse.sapphire.modeling.annotations.Label;
import org.eclipse.sapphire.modeling.annotations.Type;
import org.eclipse.sapphire.modeling.xml.IModelElementForXml;
import org.eclipse.sapphire.modeling.xml.annotations.GenerateXmlBinding;
import org.eclipse.sapphire.modeling.xml.annotations.XmlBinding;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

@GenerateXmlBinding( elementPath = "enum" )

public interface IEnumValueGallery

    extends IModelElementForXml

{
    ModelElementType TYPE = new ModelElementType( IEnumValueGallery.class );

    // *** Simple ***

    @Type( base = ThreeChoiceAnswer.class )
    @Label( standard = "simple" )
    @XmlBinding( path = "simple" )

    ValueProperty PROP_SIMPLE = new ValueProperty( TYPE, "Simple" );

    Value<ThreeChoiceAnswer> getSimple();
    void setSimple( String val );
    void setSimple( ThreeChoiceAnswer val );
    
    // *** CustomSerializedWithDefault ***
    
    @Type( base = ThreeChoiceAnswerCustomized.class )
    @Label( standard = "custom serialized with default" )
    @XmlBinding( path = "custom-serialized-with-default" )
    @DefaultValue( "maybe" )

    ValueProperty PROP_CUSTOM_SERIALIZED_WITH_DEFAULT = new ValueProperty( TYPE, "CustomSerializedWithDefault" );

    Value<ThreeChoiceAnswerCustomized> getCustomSerializedWithDefault();
    void setCustomSerializedWithDefault( String val );
    void setCustomSerializedWithDefault( ThreeChoiceAnswerCustomized val );
    
}
