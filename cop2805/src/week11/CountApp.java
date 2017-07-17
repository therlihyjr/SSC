package week11;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Timothy Herlihy Jr. 
 *
 */

public class CountApp extends JFrame implements ActionListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container contentPane) {
//    	Use BorderLayout. Default empty constructor with no horizontal and vertical
//    	gaps
    	contentPane.setLayout(new BorderLayout(5,5));
        if (!(contentPane.getLayout() instanceof BorderLayout)) {
            contentPane.add(new JLabel("Container doesn't use BorderLayout!"));
            return;
        }

        if (RIGHT_TO_LEFT) {
            contentPane.setComponentOrientation(
                java.awt.ComponentOrientation.RIGHT_TO_LEFT);
        }

        JButton jbnSampleButtons = new JButton("Count Vowels in Big Files");
        contentPane.add(jbnSampleButtons, BorderLayout.PAGE_START);

        jbnSampleButtons = new JButton("Button 2 (CENTER)");
        jbnSampleButtons.setPreferredSize(new Dimension(200, 100));
        contentPane.add(jbnSampleButtons, BorderLayout.CENTER);

        jbnSampleButtons = new JButton("Button 3 (LINE_START)");
        contentPane.add(jbnSampleButtons, BorderLayout.LINE_START);

        jbnSampleButtons = new JButton("Long-Named Button 4 (PAGE_END)");
        contentPane.add(jbnSampleButtons, BorderLayout.PAGE_END);

        jbnSampleButtons = new JButton("5 (LINE_END)");
        contentPane.add(jbnSampleButtons, BorderLayout.LINE_END);
    }

    private static void createAndShowGUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);

        JFrame frame = new JFrame("BorderLayout Source Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane and add swing components to it
        addComponentsToPane(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
	
	
	private void addFiles()
	{ 
		String defaultDir = "C:\\Users\\ib642133\\COP2805-2017-01\\GIT\\cop2805\\src\\week11\\test_data";
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);;
		chooser.setFileFilter(new VowelFileFilter());
		setStatus("Selecting files");
		chooser.setCurrentDirectory(new File(defaultDir));
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
		{
			File[] m_files = chooser.getSelectedFiles();
			for(int i = 0; i < m_files.length; i++)
			{
				
			}
		}
				
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
