
import java.awt.*;
import javax.swing.*;

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
public class ColorCube extends GLCanvas implements GLEventListener {
   // constants
   private static String TITLE = "ColorCube";
   private static final int CANVAS_WIDTH = 800;  // width of the drawable
   private static final int CANVAS_HEIGHT = 600; // height of the drawable
   private static float spin = 0;
   private static final float[][] corners = {{-1f,1f,1f},{1f,1f,1f},{1f,-1f,1f},{-1f,-1f,1f},{-1f,1f,-1f},{1f,1f,-1f},{1f,-1f,-1f},{-1f,-1f,-1f}};
   private static final float[][] colors = {{1f,0.1f,0.1f},{0.1f,1f,0.1f},{0.1f,0.1f,1f},{1f,1f,0.1f},{0.1f,1f,1f},{1f,0.1f,1f}};

   // Setup OpenGL Graphics Renderer
   private GLU glu;  // for the GL Utility

   /** Constructor to setup the GUI for this Component */
   public ColorCube() {
      this.addGLEventListener(this);
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

	public void drawSide(GLAutoDrawable drawable,int a,int b,int c, int d){
		GL2 gl = drawable.getGL().getGL2();
		gl.glColor3fv(colors[a-1],0);
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

   /**
    * Called by OpenGL for drawing
    */
   public void display(GLAutoDrawable drawable) {
	GL2 gl = drawable.getGL().getGL2();  // get the OpenGL 2 graphics context
	gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); // clear color and depth buffers
	gl.glLoadIdentity();  // reset the model-view matrix

	gl.glViewport(0,0 ,CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
	gl.glTranslatef(0f, 0f, -10.0f);
	glu.gluLookAt(1f,1f,1f,0f,0f,0f,0f,1f,0f);
	drawCube(drawable);

	gl.glLoadIdentity();

	gl.glViewport(CANVAS_WIDTH/2,0 ,CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
	gl.glTranslatef(0f, 0f, -10.0f);
	gl.glRotatef(spin,1,1,1);
	drawCube(drawable);

	gl.glLoadIdentity();

	gl.glViewport(0,CANVAS_HEIGHT/2 ,CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
	gl.glTranslatef(0f, 0f, -6.0f);
	glu.gluLookAt(-0.6f,1f,1f,0f,0f,0f,0f,1f,0f);
	drawCube(drawable);

	gl.glLoadIdentity();

	gl.glViewport(CANVAS_WIDTH/2,CANVAS_HEIGHT/2 ,CANVAS_WIDTH/2, CANVAS_HEIGHT/2);
	gl.glTranslatef(0f, 0f, -4.0f);
	glu.gluLookAt(1f,-0.6f,-1f,0f,0f,0f,0f,1f,0f);
	drawCube(drawable);

	spin += 2f;
	}

   /**
    * Called before the OpenGL context is destroyed. Release resource such as buffers.
    */
   public void dispose(GLAutoDrawable drawable) { }

   /** The entry main() method to setup the top-level JFrame with our OpenGL canvas inside */
   public static void main(String[] args) {
       GLCanvas canvas = new ColorCube();
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
