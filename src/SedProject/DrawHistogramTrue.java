package SedProject;
import processing.core.PApplet;

public class DrawHistogramTrue extends PApplet {
	 private int width=350;
	 private int height=300;
	 private int leftmargin=20;
	 private int bottommargin=20;
	 private int rightmargin=10;
	 private int topmargin=50;
	 private int padding=5;
	 private int ticks;
	 private int w;
	 private int maxnumber;
	 private int h;
	 private int choose=-1;
	  
	 private int[] numbers1 = {511,434,364,325,372,297,227,120,155,147};
	 private int[] numbers2 = {220,333,434,222,135,59,71};
	  
	 private String[] numberItems1 = {"0-83","83-166","166-249","249-332","332-415","415-498","498-581","581-664","664-747","747-830"};
	 private String[] numberItems2 = {"0-0.10","0.10-0.15","0.15-0.20","0.20-0.25","0.25-0.30","0.30-0.40","0.40-1.0"};

	 private int falseconst = 55;
	 private int trueconst = 70;
	 private int c1 = color(227,193,111);
	 private int c2 = color(250,255,47);
	 private int mid;
	 private int selectColor = color(255,165,0);
	 private int[] selectmid = new int[10];
	 public int nodesFlag =  -1;
	 
	 public static boolean judgeDraw = true;
	 
	 /**
		 * @return the width
		 */
	 //·â×°width±äÁ¿
		public int getWidth() {
			return width;
		}

		/**
		 * @param width the width to set
		 */
		public void setWidth(int width) {
			this.width = width;
		}

		/**
		 * @return the rightmargin
		 */
		public int getRightmargin() {
			return rightmargin;
		}

		/**
		 * @param rightmargin the rightmargin to set
		 */
		public void setRightmargin(int rightmargin) {
			this.rightmargin = rightmargin;
		}

		/**
		 * @return the topmargin
		 */
		public int getTopmargin() {
			return topmargin;
		}

		/**
		 * @param topmargin the topmargin to set
		 */
		public void setTopmargin(int topmargin) {
			this.topmargin = topmargin;
		}

		/**
		 * @return the padding
		 */
		public int getPadding() {
			return padding;
		}

		/**
		 * @param padding the padding to set
		 */
		public void setPadding(int padding) {
			this.padding = padding;
		}

		/**
		 * @return the ticks
		 */
		public int getTicks() {
			return ticks;
		}

		/**
		 * @param ticks the ticks to set
		 */
		public void setTicks(int ticks) {
			this.ticks = ticks;
		}

		/**
		 * @return the w
		 */
		public int getW() {
			return w;
		}

		/**
		 * @param w the w to set
		 */
		public void setW(int w) {
			this.w = w;
		}

		/**
		 * @return the maxnumber
		 */
		public int getMaxnumber() {
			return maxnumber;
		}

		/**
		 * @param maxnumber the maxnumber to set
		 */
		public void setMaxnumber(int maxnumber) {
			this.maxnumber = maxnumber;
		}

		/**
		 * @return the numbers1
		 */
		public int[] getNumbers1() {
			return numbers1;
		}

		/**
		 * @param numbers1 the numbers1 to set
		 */
		public void setNumbers1(int[] numbers1) {
			this.numbers1 = numbers1;
		}

		/**
		 * @return the numbers2
		 */
		public int[] getNumbers2() {
			return numbers2;
		}

		/**
		 * @param numbers2 the numbers2 to set
		 */
		public void setNumbers2(int[] numbers2) {
			this.numbers2 = numbers2;
		}

		/**
		 * @return the numberItems1
		 */
		public String[] getNumberItems1() {
			return numberItems1;
		}

		/**
		 * @param numberItems1 the numberItems1 to set
		 */
		public void setNumberItems1(String[] numberItems1) {
			this.numberItems1 = numberItems1;
		}

		/**
		 * @return the numberItems2
		 */
		public String[] getNumberItems2() {
			return numberItems2;
		}

		/**
		 * @param numberItems2 the numberItems2 to set
		 */
		public void setNumberItems2(String[] numberItems2) {
			this.numberItems2 = numberItems2;
		}

		/**
		 * @return the trueconst
		 */
		public int getTrueconst() {
			return trueconst;
		}

		/**
		 * @param trueconst the trueconst to set
		 */
		public void setTrueconst(int trueconst) {
			this.trueconst = trueconst;
		}

		/**
		 * @return the mid
		 */
		public int getMid() {
			return mid;
		}

		/**
		 * @param mid the mid to set
		 */
		public void setMid(int mid) {
			this.mid = mid;
		}

		/**
		 * @return the selectColor
		 */
		public int getSelectColor() {
			return selectColor;
		}

		/**
		 * @param selectColor the selectColor to set
		 */
		public void setSelectColor(int selectColor) {
			this.selectColor = selectColor;
		}

		/**
		 * @return the selectmid
		 */
		public int[] getSelectmid() {
			return selectmid;
		}

		/**
		 * @param selectmid the selectmid to set
		 */
		public void setSelectmid(int[] selectmid) {
			this.selectmid = selectmid;
		}
	  
	public void setup(){
		
	  size(625,300);
	  background(246,244,243);
	  stroke(100,100,100);
	  fill(255);
	  
	  if(judgeDraw == false) {
		  w = (width-leftmargin-rightmargin)/numbers1.length;
		  maxnumber = max(numbers1)/falseconst;
		  h = (height-bottommargin-topmargin)/maxnumber;
		  ticks=10;
		  textSize(18);
		  fill(0);
		  text("About the mediation of the nodes",(float) (leftmargin+padding*14.5),topmargin/2);
		  textSize(11);
		  line(leftmargin+padding*5,(height-bottommargin),(float) (width+leftmargin*2.5),(height-bottommargin));
		  line(leftmargin+padding*5-5,(height-bottommargin),leftmargin+padding*5-5,(float) (bottommargin*2.5));//x1,y1,x2,y2
		  int ytickh = (height-bottommargin-topmargin)/(ticks-1);
		  int ytickval = (maxnumber)/(ticks-1);
		  for(int i = 0;i<ticks;i++){
		      fill(0);
		      line(leftmargin + padding * 5,(height-bottommargin) - i * ytickh,width + leftmargin * 4,(height-bottommargin) - i * ytickh);
		      fill(0);
		      text(i * ytickval * falseconst,leftmargin - padding * 2,(height-bottommargin) - i * ytickh);//s,x,y
		  }

		  for(int i=0;i<numbers1.length;i++){
		     fill(0);
		     text(i,leftmargin+i*(w+5)+w/4+padding*5,(float) (height-bottommargin+padding*2.5));
		     text(numbers1[i],(float) (leftmargin+i*(w+5)+w/4+padding*3.5) + 5,(float) (height*0.92-h*numbers1[i]/falseconst));
		  }
		  textSize(13);
		  text("Legend:",width+leftmargin*5,topmargin);
		  for(int i=0;i<numbers1.length;i++){
		      text(i+"-->"+83*i+"-"+(83*(i+1)-1),width+leftmargin*5,topmargin+23*(i+1));
		  }
		  for(int i=0;i<numbers1.length;i++){
			  if(nodesFlag ==  -1) {
				  mid=lerpColor(c1,c2, (float) (i*1.0/numbers1.length));
				  selectmid[i]=mid;
			  }
		     fill(selectmid[i]);
		     strokeWeight(0);
		     rect(leftmargin+i*(w+5)+padding*5,(height-bottommargin),w,-h*numbers1[i]/falseconst);//x,y,width,heigh
		  }
	  }
	  
	  if(judgeDraw == true) {
		  w=(width-leftmargin-rightmargin)/numbers2.length;
		  maxnumber=max(numbers2)/trueconst;
		  h=(height-bottommargin-topmargin)/maxnumber;
		  ticks=7;
		  textSize(18);
		  fill(0);
		  text("About the connectivity of the edges",(float) (leftmargin+padding*14.5),topmargin/2);
		  textSize(11);
		  line(leftmargin+padding*5,(height-bottommargin),(float) (width+leftmargin*2.5),(height-bottommargin));
		  line(leftmargin+padding*5-5,(height-bottommargin),leftmargin+padding*5-5,(float) (bottommargin*2.5));//x1,y1,x2,y2
		  int ytickh=(height-bottommargin-topmargin)/(ticks-1);
		  int ytickval=(maxnumber)/(ticks-1);
		  for(int i=0;i<ticks;i++){
		      fill(0);
		      line(leftmargin+padding*5,(height-bottommargin)-i*ytickh,width+leftmargin*4,(height-bottommargin)-i*ytickh);
		      fill(0);
		      text(i*ytickval*trueconst,leftmargin-padding*2,(height-bottommargin)-i*ytickh);//s,x,y
		  }
		  for(int i=0;i<numbers2.length;i++){
		     fill(0);
		     text(i,leftmargin+i*(w+5)+w/4+padding*5,(float) (height-bottommargin+padding*2.5));
		     text(numbers2[i],(float) (leftmargin+i*(w+5)+w/4+padding*3.5)+5,(float) (height*0.92-h*numbers2[i]/trueconst));
		  }
		  textSize(13);
		  text("Legend:",width+leftmargin*5,topmargin);
		  for(int i=0;i<numbers2.length;i++){
		      text(numberItems2[i],width+leftmargin*5,topmargin+23*(i+1));
		  }
		  for(int i=0;i<numbers2.length;i++){
			  if(nodesFlag ==  -1) {
				  mid=lerpColor(c1,c2, (float) (i*1.0/numbers2.length));
				  selectmid[i]=mid;
			  }
		     fill(selectmid[i]);
		     strokeWeight(0);
		     rect(leftmargin+i*(w+5)+padding*5,(height-bottommargin),w,-h*numbers2[i]/trueconst);//x,y,width,height
		  }
	  }
	}
	DrawForce d = new DrawForce();
	public void draw(){
		setup();
		stroke(0);
		fill(0,0,0);
		if(judgeDraw == false) {
			for(int i = 0 ;i < ticks ; i++) {
				if(mouseX>leftmargin+i*(w+5)+padding*5&&mouseX<leftmargin+i*(w+5)+padding*5+w&&mouseY<height-bottommargin&&mouseY>height-bottommargin-h*numbers1[i]/falseconst){
					fill(selectColor);
				    rect(leftmargin+i*(w+5)+padding*5,(height-bottommargin),w,-h*numbers1[i]/falseconst);
					fill(0);  
				    text(numberItems1[i],mouseX,mouseY);
				  }
			}
		}
		if(judgeDraw == true) {
			for(int i = 0 ; i < ticks ; i++){
				  if(mouseX>leftmargin+i*(w+5)+padding*5&&mouseX<leftmargin+i*(w+5)+padding*5+w&&mouseY<height-bottommargin&&mouseY>height-bottommargin-h*numbers2[i]/trueconst){
					  fill(selectColor);
					  rect(leftmargin+i*(w+5)+padding*5,(height-bottommargin),w,-h*numbers2[i]/trueconst);
					  fill(0);  
					  text(numberItems2[i],mouseX,mouseY);
				  }
				  
			}
		}
	}
	
	public void mousePressed(){
		if(judgeDraw == false) {
			for(int i = 0 ;i < 10 ; i++) {
				if(mouseX>leftmargin+i*(w+5)+padding*5&&mouseX<leftmargin+i*(w+5)+padding*5+w&&mouseY<height-bottommargin&&mouseY>height-bottommargin-h*numbers1[i]/falseconst){
				     fill(selectColor);
				     for(int j=0;j<numbers1.length;j++){
							  mid=lerpColor(c1,c2, (float) (j*1.0/numbers1.length));
							  selectmid[j]=mid;
					}
				     if(choose != i) {
					     selectmid[i] = selectColor;
					     strokeWeight(0);
					     rect(leftmargin+i*(w+5)+padding*5,(height-bottommargin),w,-h*numbers1[i]/falseconst);
					     nodesFlag = i;
					     d.changeSize(i);
					     choose=i;
				     }
				     else {
				    	 d.changeSize(-1);
				    	 choose=-1;
				     }
				  }
			}
		}
		if(judgeDraw == true) {
			for(int i = 0 ; i < 7 ; i++){
				  if(mouseX>leftmargin+i*(w+5)+padding*5&&mouseX<leftmargin+i*(w+5)+padding*5+w&&mouseY<height-bottommargin&&mouseY>height-bottommargin-h*numbers2[i]/trueconst){
				     fill(selectColor);
				     
				     for(int j=0;j<numbers2.length;j++){
						  mid=lerpColor(c1,c2, (float) (j*1.0/numbers2.length));
						  selectmid[j]=mid;
				     }
				     selectmid[i] = selectColor;
				     strokeWeight(0);
				     rect(leftmargin+i*(w+5)+padding*5,(height-bottommargin),w,-h*numbers2[i]/trueconst);
				     nodesFlag = i;
				  }
			}
		}
	}
}
