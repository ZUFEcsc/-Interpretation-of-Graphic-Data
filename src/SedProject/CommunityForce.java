package SedProject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import processing.core.PApplet;
import processing.core.PFont;

public class CommunityForce extends PApplet{
	public String communityfilename = "";
	public static int nodesFlag = -1;
	public static boolean flag = false;
	public boolean flagc1 = false;
	public boolean flagc2 = false;
	public boolean flagc3 = false;
	public boolean flagc4 = false;
	public boolean flagc5 = false;
	public String cfilename1 = "C:\\Users\\³ÂÉ¼\\Desktop\\Big_Homework\\community_1.txt";
	public String cfilename2 = "C:\\Users\\³ÂÉ¼\\Desktop\\Big_Homework\\community_2.txt";
	public String cfilename3 = "C:\\Users\\³ÂÉ¼\\Desktop\\Big_Homework\\community_3.txt";
	public String cfilename4 = "C:\\Users\\³ÂÉ¼\\Desktop\\Big_Homework\\community_4.txt";
	public String cfilename5 = "C:\\Users\\³ÂÉ¼\\Desktop\\Big_Homework\\community_5.txt";
	public int height = 340;
	public int width = 680;
	
	
	int nodeColor = color (240,192,112);
    int selectColor = color(255,48,48);
    int fixedColor =  color(255,128,128);
    int edgeColor = color(0,0,0);
    
//    int drawtime = 5;
    int muti = 3;
    int nodeCount;
    Node[] nodes = new Node[1040];
    HashMap<String, Node> nodeTable = new HashMap<String, Node>();
    
    int edgeCount;
    Edge[] edges = new Edge[10010];
    
	PFont font;
	
    public void setup(){
    	background(246,244,243);
		size(width, height);
        loadData();
        font = createFont("SansSerif",10);
        textFont(font);
        smooth();
    }
    
    void loadData() {
    	String a1 ="1";
    	String a2 ="2";
    	String a3 ="3";
    	String a4 ="4";
    	String a5 ="5";
    	
    	addEdge(a1, a2);
    	addEdge(a1, a3);
    	addEdge(a1, a4);
    	addEdge(a1, a5);
    	addEdge(a2, a3);
    	addEdge(a2, a5);
    	addEdge(a3, a4);
    	addEdge(a3, a5);
    	
//    	addEdge(a4, a5);
    	
//    	addEdge(a2, a4);
    	
    	nodes[0].size = 83;
    	nodes[1].size = 89;
    	nodes[2].size = 138;
    	nodes[3].size = 43;
    	nodes[4].size = 23;
    	
    	nodes[0].color =color(166,38,57);
        nodes[1].color = color (110,235,131);//6EEB83
        nodes[2].color = color (228,255,26);//E4FF1A
        nodes[3].color = color (33,111,244);//210B2C
        nodes[4].color = color (125,122,188);//7D7ABC
        
        edges[0].len = 86 * muti;
        edges[1].len = (float) 110.5 * muti;
        edges[2].len = 63 * muti;
        edges[3].len = 53 * muti;
        edges[4].len = (float) 113.5 * muti;
//        edges[5].len = 66 * muti;
        edges[5].len = 56 * muti;
        edges[6].len = (float) 90.5 * muti;
        edges[7].len = (float) 80.5 * muti;
//        edges[8].len = 33 * muti;
        
    }
    
    
     void addEdge(String fromLabel,String toLabel) {
    	Node from = findNode(fromLabel);
    	Node to = findNode(toLabel);
    	Edge e = new Edge(from,to);
    	if(edgeCount == edges.length) {
    		edges=(Edge[])expand(edges);
    	}
    	edges[edgeCount++] = e;
    }
    
     Node findNode(String label){
    	label = label.toLowerCase();
    	Node n = (Node)nodeTable.get(label);
    	if(n == null) {
    		return addNode(label);
    	}
    	return n;
    }
    
     Node addNode(String label) {
    	Node n= new Node(label);
    	if(nodeCount == nodes.length) {
    		nodes = (Node[])expand(nodes);
    	}
    	nodeTable.put(label,n);
    	nodes[nodeCount++] = n;
    	return n;
    }

    public void draw() {
  	  	background(246,244,243);
//    	background(255,255,255,255);
    	if(flag == true) {
    		nodeCount = 0;
    		edgeCount = 0;
    		nodeTable.clear();
//    		readData();
//    		setup();
    		flag = false;
//    		drawtime = 0;
    	}
//    	if(drawtime<80) {
    		  for(int i = 0 ; i < edgeCount ; i++) {
		  	    edges[i].relax( );
		  	  }
		  	  for (int i = 0; i < nodeCount; i++) {
		  	    nodes[i].relax( );
		  	  }
		  	  
//    	}
    	for (int i = 0; i < nodeCount; i++) {
		  	    nodes[i].update( );
		  	  }
		  	  for (int i = 0 ; i < edgeCount ; i++) {
		    	    edges[i].draw( );
		  	  }
		  	  for (int i = 0 ; i < nodeCount ; i++) {
		  	    nodes[i].draw(nodes[i].size,nodes[i].color);
	  	}
//  	  drawtime = drawtime + 1;
    }

    Node selection;
    public void mousePressed() {
    	int choose = -9999;
    	DrawForce df = new DrawForce();
//    	drawtime = 60;
    	float closest = 50;
    	for(int i=0;i<nodeCount;i++) {
    		Node n = nodes[i];
    		closest = n.size/2;
    		float d =dist(mouseX,mouseY,n.x,n.y);
    		if(d < closest) {
    			selection = n;
    			closest = d;
    			choose=i+1;
    		}
    	}
    	if(selection != null) {
    		if(mouseButton == LEFT) {
    			selection.fixed=true;
    			df.changeColor(choose,nodes[choose-1].color);
    		}else if(mouseButton == RIGHT) {
    			selection.fixed = false;
    			df.changeColor(choose,nodeColor);
    		}
    	}
    }
    public void mouseDragged() {
//    	drawtime = 60;
    	if(selection != null) {
    		  selection.x = mouseX;
    		  selection.y = mouseY;
    	}
    }
    public void mouseReleased() {
//    	drawtime = 60;
    	selection=null;
    }
    class Edge{
    	Node from;
    	Node to;
    	float len;
    	
    	 Edge(Node from,Node to){
    		this.from = from;
    		this.to = to;
    		this.len = 300;
    	}
    	  void relax() {
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
    	 void draw() {
    		stroke(edgeColor);
    		strokeWeight((float) 6);
    		line(from.x,from.y,to.x,to.y);
    	}
    }
    
    class Node{
    	float x,y;
    	float dx,dy;
    	boolean fixed;
    	String label;
    	int color;
    	int size;
    	
    	
    	Node(String label){
    		this.label = label;
    		x = random(width);
    		y = random(height);
    	}
    	
    	void relax( ) {
    	    float ddx = 0;
    	    float ddy = 0;
    	    for (int j = 0; j < nodeCount; j++) {
    	      Node n = nodes[j];
    	      if (n != this) {
    	        float vx = x - n.x;
    	        float vy = y - n.y;
    	        float lensq = 2 * (vx * vx + vy * vy);
    	        if (lensq == 0) {
    	          ddx += random(1);
    	          ddy += random(1);
    	        } else if (lensq < 200 * 200) { 
    	          ddx += vx / lensq;
    	          ddy += vy / lensq;
    	        }
    	      }
    	    }
    	    float dlen = mag(ddx, ddy) / 2;
    	    if (dlen > 0) {
    	    dx += ddx / dlen;
    	    dy += ddy / dlen;
    	    }
    	  }
    	
    	void update( ) {
    	    if (!fixed) {
    	      x += constrain(dx, -5, 5);
    	      y += constrain(dy, -5, 5);
    	      x = constrain(x, 0, width);
    	      y = constrain(y, 0, height);
    	    }
    	    dx /= 2;
    	    dy /= 2;
    	  }
    	
    	public void draw(int size,int color) {
    		
    		fill(color);
    		stroke(0);
    		if(fixed) strokeWeight((float) 4);
    		else strokeWeight((float)0.1);
    		
    		
    		ellipse(x,y,size,size);
    		
    		fill(0);
    		textAlign(CENTER,CENTER);
    		textSize(32);
    		text(size,x,y);
    	}
    }
}