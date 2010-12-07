package org.gephi.layout.plugin.dualcirclelayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Comparator;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.Node;
import org.gephi.layout.plugin.AbstractLayout;
import org.gephi.layout.spi.Layout;
import org.gephi.layout.spi.LayoutBuilder;
import org.gephi.layout.spi.LayoutProperty;
import org.openide.util.NbBundle;

/**
 *
 * @author Matt Groeninger
 */
public class DualCircleLayout extends AbstractLayout implements Layout {

    private Graph graph;
    private boolean converged;
    private boolean highdegreeoutside;
    private int secondarynodecount;
    static double TWO_PI = (2*Math.PI);
    private String stringNodePlacementDirection = "CCW";


    public DualCircleLayout(LayoutBuilder layoutBuilder, int secondarynodecount) {
        super(layoutBuilder);
        this.secondarynodecount = secondarynodecount;
    }

    @Override
    public void initAlgo() {
        converged = false;
        graph = graphModel.getGraphVisible();
    }

    @Override
    public void goAlgo() {
        graph = graphModel.getGraphVisible();
        float[] nodeCoords = new float[2];
        double tmpsecondarycirc = 0;
        double tmpprimarycirc = 0;
        int index = 0;
        double twopi = TWO_PI;
        double lasttheta = 0;
        double primary_theta = 0;
        double secondary_theta = 0;
        double primary_scale = 1;
        double secondary_scale = 1;
        double correct_theta = 0;
        if (this.stringNodePlacementDirection == null ? "CW" == null : this.stringNodePlacementDirection.equals("CW")) {
            twopi = -twopi;
        }

        Node[] nodes = graph.getNodes().toArray();
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                int f1 = (int) graph.getDegree(o1);
                int f2 = (int) graph.getDegree(o2);
              return f2-f1;
            }
        });


        for (Node n : nodes) {
            if (index < this.secondarynodecount) {
                tmpsecondarycirc += (n.getNodeData().getRadius()*2);
            } else {
                tmpprimarycirc += (n.getNodeData().getRadius()*2);
            }
            index++;
        }
        index = 0;//reset index

        double circum_ratio = tmpprimarycirc/tmpsecondarycirc;

        if (circum_ratio < 2) {
            primary_scale = (2/circum_ratio);
            tmpprimarycirc = 2*tmpsecondarycirc;
        }

        if (this.isHighDegreeOutside()) {
            secondary_scale = ((2*tmpprimarycirc)/tmpsecondarycirc); //Need to know how much the circumference has changed from the original
            tmpsecondarycirc = tmpprimarycirc*2; //Scale to a better relationship
        } else {
            secondary_scale = (tmpprimarycirc/(2*tmpsecondarycirc)); //Need to know how much the circumference has changed from the original
            tmpsecondarycirc = tmpprimarycirc/2; //Scale to a better relationship
        }

        tmpprimarycirc = tmpprimarycirc*1.2;
        primary_theta = (twopi/tmpprimarycirc);
        double primaryradius = (tmpprimarycirc/Math.PI)/2;


        tmpsecondarycirc = tmpsecondarycirc*1.2;
        secondary_theta = (twopi/tmpsecondarycirc);
        double secondaryradius = (tmpsecondarycirc/Math.PI)/2;

        for (Node n : nodes) {
            if (index < this.secondarynodecount ) {
                //Draw secondary circle
                double noderadius = (n.getNodeData().getRadius());
                System.out.println(secondary_scale);
                //This step is hackish... but it makes small numbers of nodes symetrical on both the secondary circles.
                if (secondary_scale > 2) {
                    noderadius = (tmpsecondarycirc/(2*this.secondarynodecount*secondary_scale*1.2));
                }
                double noderadian = (secondary_theta*noderadius*1.2*secondary_scale);
                if (index==0) {
                    correct_theta=noderadian; //correct for cosmetics... overlap prevention offsets the first node by it's radius which looks weird.
                }
                nodeCoords = this.cartCoors(secondaryradius, 1,lasttheta+noderadian-correct_theta);
                lasttheta += (noderadius*2*secondary_theta*1.2*secondary_scale);
            } else {
                double noderadius = (n.getNodeData().getRadius());
                double noderadian = (primary_theta*noderadius*1.2*primary_scale);
                if (index==this.secondarynodecount) {
                    lasttheta=0;
                    correct_theta = noderadian; //correct for cosmetics... overlap prevention offsets the first node by it's radius which looks weird.
                }
                //Draw primary circle
                nodeCoords = this.cartCoors(primaryradius, 1,lasttheta+noderadian-correct_theta);
                lasttheta += (noderadius*2*primary_theta*1.2*primary_scale);
            }
            n.getNodeData().setX(nodeCoords[0]);
            n.getNodeData().setY(nodeCoords[1]);
            index++;

        }
        converged = true;
    }

    @Override
    public boolean canAlgo() {
        return !converged;
    }

    @Override
    public void endAlgo() {
    }

    @Override
    public LayoutProperty[] getProperties() {
        List<LayoutProperty> properties = new ArrayList<LayoutProperty>();
        try {
            properties.add(LayoutProperty.createProperty(
                    this, Boolean.class,
                    NbBundle.getMessage(DualCircleLayout.class, "DualCircleLayout.HighDegreeOutside.name"),
                    null,
                    NbBundle.getMessage(DualCircleLayout.class, "DualCircleLayout.HighDegreeOutside.desc"),
                    "isHighDegreeOutside", "setHighDegreeOutside"));
            properties.add(LayoutProperty.createProperty(
                    this, Integer.class,
                    NbBundle.getMessage(DualCircleLayout.class, "DualCircleLayout.InnerNodeCount.name"),
                    null,
                    NbBundle.getMessage(DualCircleLayout.class, "DualCircleLayout.InnerNodeCount.desc"),
                    "getInnerNodeCount", "setInnerNodeCount"));
            properties.add(LayoutProperty.createProperty(
                    this, String.class,
                    NbBundle.getMessage(DualCircleLayout.class, "DualCircleLayout.NodePlacement.Direction.name"),
                    "Node Placement",
                    NbBundle.getMessage(DualCircleLayout.class, "DualCircleLayout.NodePlacement.Direction.desc"),
                    "getNodePlacementDirection", "setNodePlacementDirection"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties.toArray(new LayoutProperty[0]);
    }

    @Override
    public void resetPropertiesValues() {
        setInnerNodeCount(4);
    }

    public void setInnerNodeCount(Integer secondarynodecount) {
        this.secondarynodecount = secondarynodecount;
    }

    public Integer getInnerNodeCount() {
        return secondarynodecount;
    }


    public Boolean isHighDegreeOutside() {
        return highdegreeoutside;
    }
    
    public void setHighDegreeOutside(Boolean highdegreeoutside) {
        this.highdegreeoutside = highdegreeoutside;
    }
    
    public String getNodePlacementDirection() {
        return this.stringNodePlacementDirection;
    }
    public void setNodePlacementDirection(String stringNodePlacementDirection) {
        if ((stringNodePlacementDirection == null ? "CCW" == null : stringNodePlacementDirection.equals("CCW")) || (stringNodePlacementDirection == null ? "CW" == null : stringNodePlacementDirection.equals("CW"))) {
            this.stringNodePlacementDirection = stringNodePlacementDirection;
        } else {
            this.stringNodePlacementDirection="CCW";
        }
    }

    private float[] cartCoors(double radius, int whichInt,double theta) {
       	float[] coOrds = new float[2];
        coOrds[0] = (float) (radius * (Math.cos((theta * whichInt)+(Math.PI/2))));
        coOrds[1] = (float) (radius * (Math.sin((theta * whichInt)+(Math.PI/2))));
        return coOrds;
    }
}
