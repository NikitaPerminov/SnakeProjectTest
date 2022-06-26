import javax.swing.JFrame;

public class ProjectStart {
	
	public ProjectStart() {
		
		JFrame frame = new JFrame();
		Gameworkspace gameworkspace = new Gameworkspace();
		
		frame.add(gameworkspace);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("SnakeGame");
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	}
	
	public static void main(String[] agrs) {
		
		new ProjectStart();
	}
}
