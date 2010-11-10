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

package org.eclipse.sapphire.ui;

import static org.eclipse.sapphire.ui.util.SwtUtil.gd;
import static org.eclipse.sapphire.ui.util.SwtUtil.gdfill;
import static org.eclipse.sapphire.ui.util.SwtUtil.gdhfill;
import static org.eclipse.sapphire.ui.util.SwtUtil.gdhhint;
import static org.eclipse.sapphire.ui.util.SwtUtil.gdwhint;
import static org.eclipse.sapphire.ui.util.SwtUtil.glayout;
import static org.eclipse.sapphire.ui.util.SwtUtil.hspan;

import org.eclipse.sapphire.ui.def.ISapphireCompositeDef;
import org.eclipse.sapphire.ui.def.ISapphirePartDef;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author <a href="mailto:konstantin.komissarchik@oracle.com">Konstantin Komissarchik</a>
 */

public class SapphireComposite

    extends SapphirePartContainer
    
{
    @Override
    public final void render( final SapphireRenderingContext context )
    {
        final SapphireRenderingContext ctxt;
        Composite parent = context.getComposite();
        
        if( getPreferFormStyle() == true )
        {
            final FormToolkit toolkit = new FormToolkit( context.getDisplay() );
            
            ctxt = new SapphireRenderingContext( this, context, parent )
            {
                public void adapt( final Control control )
                {
                    super.adapt( control );
                    
                    if( control instanceof Composite )
                    {
                        toolkit.adapt( (Composite) control );
                    }
                    else if( control instanceof Label )
                    {
                        toolkit.adapt( control, false, false );
                    }
                    else
                    {
                        toolkit.adapt( control, true, true );
                    }
                }
            };
        }
        else
        {
            ctxt = context;
        }
        
        parent = createOuterComposite( ctxt );

        final ISapphireCompositeDef def = (ISapphireCompositeDef) this.definition;
        final boolean indent = def.getIndent().getContent();
        final boolean scrollVertically = def.getScrollVertically().getContent();
        final boolean scrollHorizontally = def.getScrollHorizontally().getContent();
        
        if( indent )
        {
            final Label label = new Label( parent, SWT.NONE );
            label.setLayoutData( gd() );
            context.adapt( label );
        }

        final ScrolledComposite scrolledComposite;

        if( scrollVertically || scrollHorizontally )
        {
            final int style
                = ( scrollVertically ? SWT.V_SCROLL : SWT.NONE ) | 
                  ( scrollHorizontally ? SWT.H_SCROLL : SWT.NONE );
            
            scrolledComposite = new ScrolledComposite( parent, style );
            scrolledComposite.setExpandHorizontal( true );
            scrolledComposite.setExpandVertical( true );
            
            parent = scrolledComposite;
        }
        else
        {
            scrolledComposite = null;
        }
        
        final boolean expandVertically 
            = this.definition.getHint( ISapphirePartDef.HINT_EXPAND_VERTICALLY, false );
        
        final int widthHint = this.definition.getHint( ISapphirePartDef.HINT_WIDTH, -1 );
        final int heightHint = this.definition.getHint( ISapphirePartDef.HINT_HEIGHT, -1 );
        
        final GridData gd = gdwhint( gdhhint( hspan( ( expandVertically ? gdfill() : gdhfill() ), ( indent ? 1 : 2 ) ), heightHint ), widthHint );
        
        final int marginLeft = def.getMarginLeft().getContent();
        final int marginRight = def.getMarginRight().getContent();
        final int marginTop = def.getMarginTop().getContent();
        final int marginBottom = def.getMarginBottom().getContent();
        
        final Composite composite = new Composite( parent, SWT.NONE );
        composite.setLayout( glayout( 2, marginLeft, marginRight, marginTop, marginBottom ) );
        ctxt.adapt( composite );
        
        if( scrolledComposite != null )
        {
            scrolledComposite.setContent( composite );
            scrolledComposite.setLayoutData( gd );
        }
        else
        {
            composite.setLayoutData( gd );
        }
        
        final String helpContextId = this.definition.getHelpContextId().getText();
        
        if( helpContextId != null )
        {
            PlatformUI.getWorkbench().getHelpSystem().setHelp( composite, helpContextId );
        }
        
        final SapphireRenderingContext innerContext = new SapphireRenderingContext( this, ctxt, composite );
        
        super.render( innerContext );
        
        if( scrolledComposite != null )
        {
            scrolledComposite.setMinSize( composite.computeSize( SWT.DEFAULT, SWT.DEFAULT ) );
        }
        
        final SapphirePartListener partListener = new SapphirePartListener()
        {
            @Override
            public void handleStructureChangedEvent( final SapphirePartEvent event )
            {
                // Something changed in the tree of parts arranged beneath this composite part. If this is
                // the composite closest to the affected part, it will need to re-render.
                
                ISapphirePart part = event.getContext().getPart();
                Boolean needToReRender = null;
                
                while( part != null && needToReRender == null )
                {
                    part = part.getParentPart();
                    
                    if( part instanceof SapphireComposite )
                    {
                        if( part == SapphireComposite.this )
                        {
                            needToReRender = Boolean.TRUE;
                        }
                        else
                        {
                            needToReRender = Boolean.FALSE;
                        }
                    }
                }
                
                if( needToReRender == Boolean.TRUE )
                {
                    for( Control control : composite.getChildren() )
                    {
                        control.dispose();
                    }
                    
                    SapphireComposite.super.render( innerContext );
                    
                    if( scrolledComposite != null )
                    {
                        scrolledComposite.setMinSize( composite.computeSize( SWT.DEFAULT, SWT.DEFAULT ) );
                    }
                    
                    context.layout();
                }
            }
        };
        
        addListener( partListener );
        
        composite.addDisposeListener
        (
            new DisposeListener()
            {
                public void widgetDisposed( final DisposeEvent event )
                {
                    removeListener( partListener );
                }
            }
        );
    }

    protected Composite createOuterComposite( final SapphireRenderingContext context )
    {
        return context.getComposite();
    }
    
    public boolean getPreferFormStyle()
    {
        return this.definition.getHint( ISapphirePartDef.HINT_PREFER_FORM_STYLE, false );
    }
    
}
