package SedProject;
//无社区变色
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PFont;

//继承PApplet类
public class DrawForce extends PApplet{
	
	public static int nodesFlag = -1;
	
	int nodeColor = color (240,192,112);
    int selectColor = color(255,48,48);
    int fixedColor =  color(255,128,128);
    int edgeColor = color(0,0,0);
    int histogramColor = color(0,0,225);

    public int height = 1000;
	public int width = 1100;

	int drawtime = 0;
	
    ArrayList[] S;
    int[] nodesColor = new int[5];

    int nodeCount;
    public static Node[] nodes = new Node[1000];
    HashMap nodeTable = new HashMap();
    
    int[] c1 = {258, 516, 520, 10, 269, 273, 274, 275, 277, 790, 279, 791, 281, 282, 285, 797, 32, 33, 289, 35, 36, 37, 38, 39, 809, 810, 814, 568, 316, 63, 64, 65, 66, 68, 69, 326, 327, 328, 72, 329, 330, 70, 73, 582, 71, 331, 593, 332, 81, 599, 345, 354, 621, 366, 368, 118, 375, 119, 374, 636, 387, 136, 654, 674, 166, 682, 683, 685, 686, 691, 692, 435, 184, 185, 200, 720, 220, 221, 734, 248, 224, 757, 760};
    int[] c2 = {515, 510, 776, 787, 22, 792, 284, 796, 542, 799, 34, 805, 806, 297, 301, 303, 50, 309, 54, 56, 824, 569, 58, 57, 579, 78, 84, 607, 97, 100, 101, 614, 102, 104, 105, 106, 363, 108, 109, 107, 625, 120, 633, 638, 641, 129, 138, 155, 156, 412, 164, 165, 677, 679, 168, 680, 681, 171, 684, 174, 177, 180, 186, 444, 701, 445, 191, 446, 709, 199, 208, 466, 724, 471, 472, 223, 737, 229, 741, 231, 232, 744, 746, 239, 240, 242, 759, 254, 255};
    int[] c3 = {512, 513, 517, 518, 528, 529, 535, 26, 540, 547, 548, 558, 559, 574, 587, 588, 592, 83, 596, 99, 611, 617, 618, 622, 623, 624, 111, 626, 644, 649, 137, 650, 652, 664, 154, 670, 159, 672, 161, 673, 160, 678, 170, 690, 693, 702, 193, 705, 194, 717, 207, 213, 729, 735, 225, 226, 227, 739, 740, 742, 230, 236, 749, 755, 251, 252, 764, 766, 253, 256, 769, 771, 262, 265, 780, 276, 283, 801, 290, 292, 296, 298, 300, 813, 302, 815, 304, 817, 819, 308, 823, 311, 314, 827, 826, 828, 320, 323, 333, 339, 341, 342, 343, 349, 351, 352, 353, 359, 367, 370, 371, 372, 373, 384, 385, 386, 388, 395, 400, 411, 414, 415, 416, 421, 443, 458, 459, 463, 464, 465, 481, 483, 486, 496, 499, 501, 502, 503};
    int[] c4 = {770, 261, 651, 141, 142, 143, 144, 530, 658, 659, 402, 403, 407, 404, 405, 23, 406, 293, 687, 688, 560, 437, 438, 55, 442, 318, 575, 704, 706, 578, 708, 209, 211, 340, 87, 601, 476, 605, 612, 234, 369, 631, 639};
    int[] c5 = {257, 260, 524, 525, 526, 527, 786, 767, 538, 795, 30, 798, 294, 295, 40, 552, 554, 299, 43, 561, 306, 818, 307, 310, 822, 566, 315, 61, 576, 581, 583, 585, 74, 589, 590, 77, 79, 594, 344, 600, 90, 348, 606, 94, 609, 610, 98, 615, 103, 616, 619, 364, 365, 112, 629, 630, 122, 123, 124, 125, 635, 382, 383, 127, 642, 640, 130, 131, 132, 391, 133, 134, 135, 657, 662, 663, 152, 668, 669, 157, 418, 427, 172, 175, 696, 187, 189, 190, 703, 192, 447, 707, 201, 202, 203, 205, 206, 719, 718, 721, 468, 730, 733, 738, 228, 488, 489, 250, 747, 237, 493, 763, 494, 500, 245, 246, 247, 505, 762, 507, 508, 765, 511};

    
    int edgeCount;
    Edge[] edges = new Edge[500];
    
	PFont font;

	//方法覆盖
    public void setup(){
  	  	background(246,244,243);
    	size(width, height);
        readData();
        font = createFont("SansSerif",10);
        textFont(font);
        smooth();
    }
    
    public void loadData(String a, String b) {
    	addEdge(a, b);
    }
    
    
    
    //======================readData()=======================
    public void readData() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\陈杉\\Desktop\\Big_Homework\\new_data.txt")),"UTF-8"));
    		String lineTxt = null;
    		while((lineTxt = br.readLine()) != null) {
    			String[] names = lineTxt.split(" ");
    			loadData(names[0],names[1]);
    		}
    		br.close();
    	}catch(Exception e) {
    		System.err.println("read errors :" + e);
    	}
    }
    
    //=====================changeColor()+changeSize()=========================
    public void changeColor(int x,int color) {
    	if(x>0) {
	    	for(int i=0;i<nodes.length;i++) {
	    		if(nodes[i]==null)continue;
	    		if(x==1) {
	    			for(int j=0;j<c1.length;j++) 
	    				if(c1[j] == nodes[i].nodenum) 
	    					nodes[i].color = color;
	    		}
	    		else if(x==2) {
	    			for(int j=0;j<c2.length;j++) 
	    				if(c2[j] == nodes[i].nodenum) 
	    					nodes[i].color = color;
	    		}
	     		else if(x==3) {
	    			for(int j=0;j<c3.length;j++) 
	    				if(c3[j] == nodes[i].nodenum) 
	    					nodes[i].color = color;
	    		}
	     		else if(x==4) {
	    			for(int j=0;j<c4.length;j++) 
	    				if(c4[j] == nodes[i].nodenum) 
	    					nodes[i].color = color;
	    		}
	     		else if(x==5) {
	    			for(int j=0;j<c5.length;j++) 
	    				if(c5[j] == nodes[i].nodenum) 
	    					nodes[i].color = color;
	     		}
	    	}
    	}
    }
    public void changeSize(int x) {
    	for(int i=0;i<nodes.length;i++) {
    		if(nodes[i] == null)continue;
     		nodes[i].show = false;
    	}
    	if(x!=-1) {
	     	for(int i=0;i<nodes.length;i++) {
	    		if(nodes[i] == null)continue;
	    		int a = nodes[i].nodenum;
	    		if(a>=x*82 && a<(x+1)*82) {
	    			nodes[i].show = true;
	    		}
	    	}   		
    	}

    }
    
    
    //===================================================
    public void addEdge(String fromLabel,String toLabel) {
    	Node from = findNode(fromLabel);
    	Node to = findNode(toLabel);
    	Edge e = new Edge(from,to);
    	if(edgeCount== edges.length) {
    		edges=(Edge[])expand(edges);
    	}
    	edges[edgeCount++] = e;
    }
    
    public Node findNode(String label){
    	label = label.toLowerCase();
    	Node n = (Node)nodeTable.get(label);
    	if(n==null) {
    		return addNode(label);
    	}
    	return n;
    }
    
    public Node addNode(String label) {
    	Node n= new Node(label);
    	if(nodeCount == nodes.length) {
    		nodes = (Node[])expand(nodes);
    	}
    	nodeTable.put(label,n);
    	int x = nodeCount++;
    	nodes[x] = n;
    	nodes[x].color = nodeColor;
    	nodes[x].show = false;
    	nodes[x].nodenum = Integer.valueOf(label);
    	return n;
    }

    public void draw() {
//    	background(255);
  	  	background(246,244,243);
//    	if(nodesFlag != -1) {
			for(int i  = (nodesFlag - 1) * 0 ; i <= (nodesFlag - 1) * 403;i++) {
				Node n = new Node(str(i));
				for(int j = 0 ; j < nodes.length ; j++) {
					if(n == nodes[j]) nodes[j].color = histogramColor;
				}
			}
//    	}
    	if(drawtime < 280) {
	    	for(int i=0;i<edgeCount;i++) {
	    		edges[i].relax();
	    	}
	    	for(int i =0;i<nodeCount;i++) {
	    		nodes[i].relax();
	    	}
    	}
    	drawtime = drawtime + 1;
    	for(int i=0;i<nodeCount;i++) {
    		nodes[i].update();
    	}
    	for(int i=0;i<edgeCount;i++) {
    		edges[i].draw();
    	}
    	for(int i=0;i<nodeCount;i++) {
    		nodes[i].draw(nodes[i].color, nodes[i].show);
    	}
    }

    Node selection;
    
    //=====================MOUSE事件===================
    public void mousePressed() {
    	drawtime = 160;
    	float closest = 100;
    	for(int i=0;i<nodeCount;i++) {
    		Node n = nodes[i];
    		float d =dist(mouseX,mouseY,n.x,n.y);
    		if(d<closest) {
    			selection = n;
    			closest = d;
    		}
    	}
    	if(selection != null) {
    		if(mouseButton == LEFT) {
    			selection.fixed=true;
    		}else if(mouseButton == RIGHT) {
    			selection.fixed = false;
    		}
    	}
    }
    public void mouseDragged() {
    	drawtime = 160;
    	if(selection != null) {
    		selection.x= mouseX;
    		selection.y=mouseY;
    	}
    }
    public void mouseReleased() {
    	drawtime = 160;
    	selection=null;
    }
    
    //=====================Edge类========================
    class Edge{
    	Node from;
    	Node to;
    	float len;
    	
    	public Edge(Node from,Node to){
    		this.from = from;
    		this.to = to;
    		this.len = 100;
    	}
    	 public void relax() {
    		    float vx = to.x - from.x;
    		    float vy = to.y - from.y;
    		    float d = mag(vx, vy);
    		    if (d > 0) {
    		      float f = (len - d) / (d * 3);
    		      float dx = f * vx;
    		      float dy = f * vy;
    		      to.dx += dx;
    		      to.dy += dy;
    		      from.dx -= dx;
    		      from.dy -= dy;
    		    }
    	}
    	public void draw() {
    		stroke(edgeColor);
    		strokeWeight((float) 0.1);
    		line(from.x,from.y,to.x,to.y);
    	}
    }
    
    //=====================Node类=========================
    class Node{
    	float x,y;
    	float dx,dy;
    	boolean fixed;
    	String label;
    	int color;
    	boolean show = false;
    	int nodenum;
    	
    	Node(String label){
    		this.label = label;
    		x = random(width);
    		y = random(height);
    	}
    	public void relax() {
    		float ddx = 0;
    		float ddy = 0;
    		
    		for(int j=0;j<nodeCount;j++) {
    			Node n = nodes[j];
    			if(n !=this) {
    				float vx = x -n.x;
    				float vy =y - n.y;
    				float lensq = vx*vx+vy*vy;
    				if(lensq == 0) {
    					ddx +=random(1);
    					ddx +=random(1);
    				}else if(lensq<1000*1000) {
    					ddx +=vx/lensq;
    					ddy +=vy/lensq;
    				}
    			}
    		}
    		float dlen = mag(ddx,ddy)/2;
    		if(dlen>0) {
    			dx += ddx/dlen;
    			dy += ddy/dlen;
    		}
    	}
    	
    	public void update() {
    		if(!fixed) {
    			x += constrain(dx,-5,5);
    			y += constrain(dy,-5,5);
    			
    			x = constrain(x,0,width);
    			y = constrain(y,0,height);
    		}
    		dx/=2;
    		dy/=2;
    	}
    	public void draw(int colornode,boolean show) {
    		if(selection == this) {
    			fill(selectColor);
    		}else if(fixed) {
    			fill(fixedColor);
    		}else {
    			fill(colornode);
    		}
    		if(show)ellipse(x,y,50,50);
    		else ellipse(x,y,10,10);
    		
    	}
    }
	
	
}