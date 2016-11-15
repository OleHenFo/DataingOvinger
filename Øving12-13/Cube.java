import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.gl2.GLUT;


/**
 * NeHe Lesson #2 (JOGL 2 Port): Basic 2D Shapes
 * @author Tomas Holt, based on code from Hock-Chuan Chua (May 2012)
 * @version October 2016
 */

/* Main class which extends GLCanvas. This means that this is a OpenGL canvas.
   We will use OpenGL commands to draw on this canvas.
   This implementation has no animation or user input.
*/
public class Cube extends GLCanvas implements GLEventListener{
   // constants
   private static String TITLE = "Halloen";
   private static final int CANVAS_WIDTH = 800;  // width of the drawable
   private static final int CANVAS_HEIGHT = 600; // height of the drawable
   private static final float[][] points = {{0.0f,2.0f,0.0f},{1.5f,1.5f,0.0f},{2.0f,0.0f,0.0f},{1.5f,-1.5f,0.0f},{0.0f,-2.0f,0.0f},{-1.5f,-1.5f,0.0f},{-2.0f,0.0f,0.0f},{-1.5f,1.5f,0.01f}};
   private static Random rand = new Random();
   private static float[] up = {0f,2f,0f};
   private static float[] down = {0f,-2f,0f};
   private static float[] left = {-2f,0f,0f};
   private static float[] right = {2f,0f,0f};
   private static float[] in = {0f,0f,2f};
   private static float[] out = {0f,0f,-2f};
   private static float rA = 10;
   private static float rS = 0;
   private static float x = 1f;
   private static float y = 1f;
   private static float z = 1f;
   private static float spin = 0f;
   private static float w = 1;
   private static float h = 1;
   private static float l = 1;
   private static int var = 1;
   private static float mX=0;
   private static float mY=0;
   private GLUT glut = new GLUT();

   // Setup OpenGL Graphics Renderer
   private GLU glu;  // for the GL Utility

   /** Constructor to setup the GUI for this Component */
   public Cube() {
      this.addGLEventListener(this);
      this.addKeyListener(new RotateKeyListener());
   }
// ------ Implement methods declared in GLEventListener (init,reshape,display,dispose)

   /**
    * Called immediately after the OpenGL context is initialized. Can be used
    * to perform one-time initialization. Run only once.
    */
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();      // get the OpenGL graphics context
      glu = new GLU();                         // get GL Utilities
      gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // set background (clear) color
      gl.glEnable(GL2.GL_DEPTH_TEST);           // enables depth testing
      gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
   }

   /**
    * Handler for window re-size event. Also called when the drawable is
    * first set to visible
    */
   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context

      if (height == 0) height = 1;   // prevent divide by zero
      float aspect = (float)width / height;

      //Set the view port (display area) to cover the entire window
      //gl.glViewport(0, 0, width/2, height/2);

      // Setup perspective projection, with aspect ratio matches viewport
      gl.glMatrixMode(GL2.GL_PROJECTION);  // choose projection matrix
      gl.glLoadIdentity();             // reset projection matrix
      glu.gluPerspective(45.0, aspect, 0.1, 100.0); // fovy, aspect, zNear, zFar

      // Enable the model-view transform
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity(); // reset
   }

   /**
    * Called by OpenGL for drawing
    */
    public void up(){
		y++;
	}
	public void down(){
		y--;
	}
	public void left(){
		x--;
	}
	public void right(){
		x++;
	}
	public void in(){
		z++;
	}
	public void out(){
		z--;
	}

   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
      gl.glLoadIdentity();
      glu.gluLookAt(rS,0f,rA,0f,0f,0f,0f,1f,0f);
      gl.glPushMatrix();  // reset the model-view matrix



	x = 1f;
	y = 1f;
	z = 1f;

	gl.glTranslatef(0f,0f,-30f);

	gl.glRotatef(spin,1,1,1);
	    spin += 1f;

      gl.glTranslatef(0.0f, 3f, -10.0f);

		gl.glRotatef(spin,1,1,1);
	    spin += 2f;

		gl.glScalef(w,h,l);
		if (var == 1){
			w+=0.2;
			if (h>1){
				h-=0.2;
			}
			if (l>1){
				l-=0.2;
			}
			if (w>=5){
				var = 2;
			}
		} else if (var == 2) {
			h+=0.2;
			if (w>1){
				w-=0.2;
			}
			if (l>1){
				l-=0.2;
			}
			if (h>=5){
				var = 3;
			}
		} else if (var == 3) {
			l+=0.2;
			if (w>1){
				w-=0.2;
			}
			if (h>1){
				h-=0.2;
			}
			if (l>=5){
				var = 1;
			}
		}

		gl.glBegin(GL2.GL_LINE_LOOP);
			gl.glVertex3f(x,y,z);
			down();
			gl.glVertex3f(x,y,z);
			left();
			gl.glVertex3f(x,y,z);
			out();
			gl.glVertex3f(x,y,z);
			in();
			gl.glVertex3f(x,y,z);
			up();
			gl.glVertex3f(x,y,z);
			right();
			gl.glVertex3f(x,y,z);
			left();
			gl.glVertex3f(x,y,z);
			out();
			gl.glVertex3f(x,y,z);
			right();
			gl.glVertex3f(x,y,z);
			left();
			gl.glVertex3f(x,y,z);
			down();
			gl.glVertex3f(x,y,z);
			right();
			gl.glVertex3f(x,y,z);
			in();
			gl.glVertex3f(x,y,z);
			out();
			gl.glVertex3f(x,y,z);
			up();
			gl.glVertex3f(x,y,z);
		gl.glEnd();
		gl.glPopMatrix();
		gl.glPushMatrix();
		gl.glTranslatef(0f,0f,-10f);

		gl.glRotatef(spin,1,1,1);
	    spin += 2f;

		glut.glutWireCube(1f);
		gl.glPopMatrix();

   }
   /**
    * Called before the OpenGL context is destroyed. Release resource such as buffers.
    */
   public void dispose(GLAutoDrawable drawable) { }

   private class RotateKeyListener extends KeyAdapter{
	   public void keyPressed(KeyEvent e) {
		   int id = e.getKeyCode();
		   if (id == 38){
			   rA--;
		   } else if (id==40){
			   rA++;
		   } else if (id == 37){
			   rS--;
		   } else if (id==39){
			   rS++;
		   } else if (id==32){
			   rS=0;
			   rA=10;
		   }
			System.out.println(e.getKeyCode());
		   Cube.this.repaint();//repaint our canvas

	   }
   }

   /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
   public static void main(String[] args) {
       GLCanvas canvas = new Cube();
       canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

       final JFrame frame = new JFrame(); // Swing's JFrame or AWT's Frame
       final FPSAnimator animator = new FPSAnimator(canvas,30,true);
       frame.getContentPane().add(canvas);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//stop program
       frame.setTitle(TITLE);
       frame.pack();
       frame.setVisible(true);

       animator.start();

   }
}
