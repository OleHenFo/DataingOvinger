import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;

public class Solar extends GLCanvas implements GLEventListener{
	private GLU glu;
	private GLUT glut = new GLUT();
	private Random rand = new Random();
	private static float spin = 0f;
	private static float pitch = 0f;
	private static float yaw = 0f;
	private static float[] col = {0f,0f,0f};
	private static float[][] field = new float[500][3];

	public void generateAsteroids(){
		for (int i = 0; i<500;i++){
			field[i][0] = (float) (Math.cos(2 * Math.PI * i / 500 )+(-1+rand.nextFloat()*2)/20)*7;
			field[i][1] = (float) (Math.sin(2 * Math.PI * i / 500 )+(-1+rand.nextFloat()*2)/20)*7;
			field[i][2] = (-1+(rand.nextFloat()*2))/5;
		}
	}

	public Solar() {
		this.addGLEventListener(this);
		this.addKeyListener(new Keys());
	}
	public void init(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		generateAsteroids();
	}

	public void reshape(GLAutoDrawable drawable,int x,int y,int w,int h){
		GL2 gl = drawable.getGL().getGL2();

		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();
		glu.gluPerspective(45.0, (float)w/h, 0.1, 100.0);

		gl.glMatrixMode(GL2.GL_MODELVIEW);
	}

	public void newPlanet(GLAutoDrawable drawable, float size, float[] color, float dfs, float tas,float rotate){
		GL2 gl = drawable.getGL().getGL2();

		gl.glPushMatrix();
		gl.glColor3fv(color,0);
		gl.glRotatef(-rotate,0,1,0);
		gl.glRotatef(spin/tas,0,0,1);
		gl.glTranslatef(1.8f+dfs,0f,0f);
		glut.glutSolidSphere(size,50,50);
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glRotatef(-rotate,0,1,0);
		gl.glBegin(GL2.GL_LINE_LOOP);
			for(int i =0; i <= 300; i++){
				double angle = 2 * Math.PI * i / 300;
				double x = Math.cos(angle)*(dfs+1.8);
				double y = Math.sin(angle)*(dfs+1.8);
				gl.glVertex2d(x,y);
			}
		gl.glEnd();
		gl.glPopMatrix();
	}

	public void display(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		gl.glTranslatef(0f,0f,-35f);

		gl.glRotatef(pitch,1,0,0);
		gl.glRotatef(yaw,0,1,0);

		// Pluto
		col[0] = 0.3f;
		col[1] = 0.3f;
		col[2] = 0.3f;
		newPlanet(drawable,0.1f,col,14f,200f,17f);
		// Neptune
		col[0] = 0f;
		col[1] = 0f;
		col[2] = 0.7f;
		newPlanet(drawable,0.4f,col,13f,163.7f,1f);
		// Uranus
		col[0] = 0.2f;
		col[1] = 1f;
		col[2] = 1f;
		newPlanet(drawable,0.42f,col,11f,83.7f,0f);
		// Saturn
		col[0] = 0.6f;
		col[1] = 0.4f;
		col[2] = 0.2f;
		newPlanet(drawable,0.6f,col,9f,29.4f,2f);
		// Jupiter
		col[0] = 1f;
		col[1] = 0.6f;
		col[2] = 0.3f;
		newPlanet(drawable,0.7f,col,7f,11.9f,1f);
		// Mars
		col[0] = 1f;
		col[1] = 0f;
		col[2] = 0f;
		newPlanet(drawable,0.18f,col,4f,1.88f,1f);
		// Earth
		col[0] = 0f;
		col[1] = 0f;
		col[2] = 1f;
		newPlanet(drawable,0.25f,col,3f,1f,0f);
		// Venus
		col[0] = 1f;
		col[1] = 0.5f;
		col[2] = 0f;
		newPlanet(drawable,0.2f,col,2f,0.615f,3f);
		// Mercury
		col[0] = 0.4f;
		col[1] = 0.4f;
		col[2] = 0.4f;
		newPlanet(drawable,0.1f,col,1f,0.241f,7f);
		// sun
		gl.glPushMatrix();
		gl.glColor3f(1f,1f,0f);
		glut.glutSolidSphere(1.8,50,50);
		gl.glPopMatrix();
		// Asteroid field
		gl.glPushMatrix();
		gl.glBegin(GL2.GL_POINTS);
			for (int i = 0; i < 500;i++){
				gl.glVertex3fv(field[i],0);
			}
		gl.glEnd();
		gl.glPopMatrix();

		spin+=10;
	}

	public class Keys extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			int id = e.getKeyCode();
			if (id == 38){
				pitch--;
			} else if (id==40){
				pitch++;
			} else if (id == 37){
				yaw--;
			} else if (id==39){
				yaw++;
			}
			Solar.this.repaint();
		}
	}

	public void dispose(GLAutoDrawable drawable){
	}

	public static void main(String[] args){
		GLCanvas canvas = new Solar();
		canvas.setPreferredSize(new Dimension(800, 600));

		JFrame frame = new JFrame();
		FPSAnimator anim = new FPSAnimator(canvas,30,true);
		frame.getContentPane().add(canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Solarsystem");
		frame.pack();
		frame.setVisible(true);
		anim.start();
	}
}