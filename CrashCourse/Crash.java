import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.io.File;
import java.io.IOException;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.Random;

public class Crash extends GLCanvas implements GLEventListener {

	private GLU glu;
	private GLUT glut = new GLUT();
	private static float spin = 0;
	private static final float[][] corners = {{-1f,1f,1f},{1f,1f,1f},{1f,-1f,1f},{-1f,-1f,1f},{-1f,1f,-1f},{1f,1f,-1f},{1f,-1f,-1f},{-1f,-1f,-1f}};
	private static final float[][] colors = {{1f,0.1f,0.1f},{0.1f,1f,0.1f},{0.1f,0.1f,1f},{1f,1f,0.1f},{0.1f,1f,1f},{1f,0.1f,1f}};
	private float anim = 1;
	private double spinSin = 0;
	private Random rand = new Random();
	public static float[][] cols = new float[12][3];
	public static float[][] ballPoints = {{0f,0f,0f},{0f,0f,0f},{0f,0f,0f},{0f,0f,0f}};

	public Crash() {
		this.addGLEventListener(this);
		this.addKeyListener(new Listn());
	}

	public void init(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
		glu = new GLU();
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
	}

	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
		GL2 gl = drawable.getGL().getGL2();

		if (height == 0) height = 1;
		float aspect = (float)width / height;

		//Set the view port (display area) to cover the entire window
		gl.glViewport(0, 0, width, height);

		// Setup perspective projection, with aspect ratio matches viewport
		gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
		gl.glLoadIdentity();             // reset projection matrix
		glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public float[] randvec(){
		float[] ret = {rand.nextFloat(),rand.nextFloat(),rand.nextFloat()};
		return ret;
	}

	public void randcol(){
		for (int i = 0;i<12;i++){
			cols[i] = randvec();
		}
	}

	public void drawSide(GLAutoDrawable drawable,int a,int b,int c, int d){
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3fv(cols[a-1],0);
		gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex3fv(corners[a-1],0);
			gl.glVertex3fv(corners[b-1],0);
			gl.glVertex3fv(corners[c-1],0);
			gl.glVertex3fv(corners[d-1],0);
		gl.glEnd();
	}

	public void drawCube(GLAutoDrawable drawable){
		drawSide(drawable,1,2,3,4);
		drawSide(drawable,2,6,7,3);
		drawSide(drawable,3,7,8,4);
		drawSide(drawable,4,8,5,1);
		drawSide(drawable,5,6,2,1);
		drawSide(drawable,6,7,8,5);
	}

	public void drawTri(GLAutoDrawable drawable){
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3fv(cols[6],0);
		gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex3f(0f,1f,0f);
			gl.glVertex3f(1f,0f,1f);
			gl.glVertex3f(1f,0f,-1f);
		gl.glEnd();
		gl.glColor3fv(cols[7],0);
		gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex3f(0f,1f,0f);
			gl.glVertex3f(1f,0f,1f);
			gl.glVertex3f(-1f,0f,1f);
		gl.glEnd();
		gl.glColor3fv(cols[8],0);
		gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex3f(0f,1f,0f);
			gl.glVertex3f(-1f,0f,1f);
			gl.glVertex3f(-1f,0f,-1f);
		gl.glEnd();
		gl.glColor3fv(cols[9],0);
		gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex3f(0f,1f,0f);
			gl.glVertex3f(-1f,0f,-1f);
			gl.glVertex3f(1f,0f,-1f);
		gl.glEnd();
	}

	public void display(GLAutoDrawable drawable) {
		GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
		gl.glLoadIdentity();  // reset the model-view matrix
		anim = (float)Math.sin(spin/12);

		gl.glTranslatef(0f,-3f,-15f);
		gl.glRotatef(10,1,0,0);
		ballPoints[0][0] = (float)(Math.sin(spin/12)*1.3);
		ballPoints[0][1] = (float)(Math.sin(spin/18)*1.3);
		ballPoints[0][2] = (float)(Math.cos(spin/11)*1.3);
		gl.glTranslatef(ballPoints[0][0],ballPoints[0][1],ballPoints[0][2]);
		gl.glTranslatef(0f,3+anim,0f);
		gl.glColor3fv(cols[11],0);
		glut.glutSolidSphere(0.2, 50, 50);


		gl.glLoadIdentity();

		gl.glTranslatef(0f,-3f,-15f);
		gl.glRotatef(10,1,0,0);
		ballPoints[1][0] = (float)(Math.sin(spin/13)*1.3);
		ballPoints[1][1] = (float)(Math.sin(spin/7)*1.3);
		ballPoints[1][2] = (float)(Math.cos(spin/14)*1.3);
		gl.glTranslatef(ballPoints[1][0],ballPoints[1][1],ballPoints[1][2]);
		gl.glTranslatef(0f,3+anim,0f);
		gl.glColor3fv(cols[10],0);
		glut.glutSolidSphere(0.2, 50, 50);

		gl.glLoadIdentity();


		gl.glTranslatef(0f,-3f,-15f);
		gl.glRotatef(10,1,0,0);
		ballPoints[2][0] = (float)(Math.sin(spin/14)*1.3);
		ballPoints[2][1] = (float)(Math.sin(spin/8)*1.3);
		ballPoints[2][2] = (float)(Math.cos(spin/15)*1.3);
		gl.glTranslatef(ballPoints[2][0],ballPoints[2][1],ballPoints[2][2]);
		gl.glTranslatef(0f,3+anim,0f);
		gl.glColor3fv(cols[9],0);
		glut.glutSolidSphere(0.2, 50, 50);

		gl.glLoadIdentity();

		gl.glTranslatef(0f,-3f,-15f);
		gl.glRotatef(10,1,0,0);
		ballPoints[3][0] = (float)(Math.sin(spin/11)*1.3);
		ballPoints[3][1] = (float)(Math.sin(spin/9)*1.3);
		ballPoints[3][2] = (float)(Math.cos(spin/13)*1.3);
		gl.glTranslatef(ballPoints[3][0],ballPoints[3][1],ballPoints[3][2]);
		gl.glTranslatef(0f,3+anim,0f);
		gl.glColor3fv(cols[8],0);
		glut.glutSolidSphere(0.2, 50, 50);

		gl.glLoadIdentity();

		gl.glTranslatef(0f,-3f,-15f);
		gl.glRotatef(10,1,0,0);
		gl.glRotatef(spin,0,1,0);
		gl.glTranslatef(0f,3+anim,0f);
		gl.glRotatef(spin*3,1,-1,1);

		gl.glScalef(0.4f,0.4f,0.4f);
		drawCube(drawable);

		gl.glLoadIdentity();
		gl.glTranslatef(0f,-3f,-15f);
		gl.glRotatef(10,1,0,0);
		gl.glRotatef(spin,0,1,0);
		gl.glTranslatef(-2,0f,2);
		drawTri(drawable);
		gl.glTranslatef(4f,0f,0f);
		drawTri(drawable);
		gl.glTranslatef(0f,0f,-4f);
		drawTri(drawable);
		gl.glTranslatef(-4f,0f,0f);
		drawTri(drawable);
		gl.glTranslatef(2f,0f,2f);

		gl.glLoadIdentity();
		gl.glTranslatef(0f,-3f,-15f);
		gl.glRotatef(10,1,0,0);
		gl.glRotatef(spin,0,1,0);
		gl.glColor3fv(cols[0],0);
		gl.glBegin(GL2.GL_POLYGON);
			gl.glVertex3f(-10f,0f,-10f);
			gl.glVertex3f(10f,0f,-10f);
			gl.glVertex3f(10f,0f,10f);
			gl.glVertex3f(-10f,0f,10f);
		gl.glEnd();

		spin++;
	}

	public void dispose(GLAutoDrawable drawable) { }

	public class Listn extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			Crash.this.randcol();
			Crash.this.repaint();
		}
	}

	public static void main(String[] args){
		GLCanvas canvas = new Crash();
		canvas.setPreferredSize(new Dimension(800, 600));

		final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
		final FPSAnimator animator = new FPSAnimator(canvas,30,true);
		frame.getContentPane().add(canvas);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //stop program
		frame.setTitle("Crash Course");
		frame.pack();
		frame.setVisible(true);
		animator.start();
	}
}