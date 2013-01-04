/******************************************************************************
 * Copyright (c) 2013 Oracle
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Shenxue Zhou - initial implementation and ongoing maintenance
 ******************************************************************************/

package org.eclipse.sapphire.ui.diagram.actions;

import java.util.Collections;
import java.util.List;

import org.eclipse.sapphire.FilteredListener;
import org.eclipse.sapphire.modeling.IModelElement;
import org.eclipse.sapphire.modeling.ModelElementList;
import org.eclipse.sapphire.ui.ISapphirePart;
import org.eclipse.sapphire.ui.SapphireAction;
import org.eclipse.sapphire.ui.SapphireActionHandler;
import org.eclipse.sapphire.ui.SapphireRenderingContext;
import org.eclipse.sapphire.ui.SapphireEditorPagePart.SelectionChangedEvent;
import org.eclipse.sapphire.ui.def.ActionHandlerDef;
import org.eclipse.sapphire.ui.diagram.editor.SapphireDiagramEditorPagePart;
import org.eclipse.sapphire.ui.diagram.editor.ShapeFactoryPart;
import org.eclipse.sapphire.ui.diagram.editor.ShapePart;

/**
 * @author <a href="mailto:shenxue.zhou@oracle.com">Shenxue Zhou</a>
 */

public class MoveShapeUpActionHandler extends SapphireActionHandler 
{

    @Override
    public void init( final SapphireAction action,
                      final ActionHandlerDef def )
    {
        super.init( action, def );
        
        final ISapphirePart part = getPart();
        SapphireDiagramEditorPagePart editorPart = part.nearest(SapphireDiagramEditorPagePart.class);
        editorPart.attach
        (
            new FilteredListener<SelectionChangedEvent>()
            {
                @Override
                protected void handleTypedEvent( final SelectionChangedEvent event )
                {
                    refreshEnablement();
                }
            }
        );        		
        refreshEnablement();
    }
    
    private void refreshEnablement()
    {
        final ISapphirePart part = getPart();
    	boolean enabled = false;
    	
    	if (part instanceof ShapePart && part.getParentPart() instanceof ShapeFactoryPart)
    	{
    		ShapeFactoryPart shapeFactory = (ShapeFactoryPart)part.getParentPart();
    		List<ShapePart> children = shapeFactory.getChildren();
    		enabled = children.indexOf(part) > 0;
    	}
    	setEnabled( enabled );
    }

	@Override
	protected Object run(SapphireRenderingContext context) 
	{
        final ISapphirePart part = getPart();
    	ShapeFactoryPart shapeFactory = (ShapeFactoryPart)part.getParentPart();
    	IModelElement element = part.getLocalModelElement();
    	ModelElementList<IModelElement> list = shapeFactory.getModelElementList();
		list.moveUp(element);
		SapphireDiagramEditorPagePart editorPart = part.nearest(SapphireDiagramEditorPagePart.class);
		editorPart.setSelections(Collections.singletonList(part));
		return null;
	}
}
