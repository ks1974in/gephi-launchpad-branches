/*
Copyright 2008-2010 Gephi
Authors : Eduardo Ramos <eduramiba@gmail.com>
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.datalaboratory.impl.manipulators.generalactions;

import javax.swing.Icon;
import org.gephi.datalaboratory.api.GraphElementsController;
import org.gephi.datalaboratory.impl.manipulators.generalactions.ui.ClearEdgesUI;
import org.gephi.datalaboratory.spi.ManipulatorUI;
import org.gephi.datalaboratory.spi.generalactions.GeneralActionsManipulator;
import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.MixedGraph;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 * GeneralActionsManipulator that clears directed and/or undirected edges of the graph.
 * @author Eduardo Ramos <eduramiba@gmail.com>
 */
@ServiceProvider(service = GeneralActionsManipulator.class)
public class ClearEdges implements GeneralActionsManipulator {
    private boolean deleteDirected=true,deleteUndirected=true;//TODO: Maybe keep these values across calls.

    public void execute() {
        GraphElementsController gec=Lookup.getDefault().lookup(GraphElementsController.class);
        MixedGraph graph=Lookup.getDefault().lookup(GraphController.class).getModel().getMixedGraph();
        if(deleteDirected){
            gec.deleteEdges(graph.getDirectedEdges().toArray());
        }
        if(deleteUndirected){
            gec.deleteEdges(graph.getUndirectedEdges().toArray());
        }
    }

    public String getName() {
        return NbBundle.getMessage(ClearEdges.class, "ClearEdges.name");
    }

    public String getDescription() {
        return "";
    }

    public boolean canExecute() {
        return Lookup.getDefault().lookup(GraphElementsController.class).getEdgesCount()>0;
    }

    public ManipulatorUI getUI() {
        return new ClearEdgesUI();
    }

    public int getType() {
        return 0;
    }

    public int getPosition() {
        return 300;
    }

    public Icon getIcon() {
        return ImageUtilities.loadImageIcon("org/gephi/datalaboratory/impl/manipulators/resources/eraser--minus.png", true);
    }

    public boolean isDeleteDirected() {
        return deleteDirected;
    }

    public void setDeleteDirected(boolean deleteDirected) {
        this.deleteDirected = deleteDirected;
    }

    public boolean isDeleteUndirected() {
        return deleteUndirected;
    }

    public void setDeleteUndirected(boolean deleteUndirected) {
        this.deleteUndirected = deleteUndirected;
    }
}
