import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Options extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtLength;
	private JTextField txtWidth;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Options dialog = new Options();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Options() {
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 235, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblLength = new JLabel("Length");
			lblLength.setBounds(16, 17, 61, 16);
			contentPanel.add(lblLength);
		}
		{
			JLabel lblWidth = new JLabel("Width");
			lblWidth.setBounds(16, 45, 61, 16);
			contentPanel.add(lblWidth);
		}
		{
			txtLength = new JTextField();
			txtLength.setBounds(79, 11, 134, 28);
			contentPanel.add(txtLength);
			txtLength.setColumns(10);
		}
		{
			txtWidth = new JTextField();
			txtWidth.setBounds(79, 39, 134, 28);
			contentPanel.add(txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
