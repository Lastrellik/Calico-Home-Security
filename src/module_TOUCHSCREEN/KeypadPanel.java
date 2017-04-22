package module_TOUCHSCREEN;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class KeypadPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final KeypadTextField keypadTextField = new KeypadTextField();
	private final KeypadButtonPanel keypadButtonPanel = new KeypadButtonPanel(keypadTextField);
	
	public KeypadPanel(){
		buildKeypadPanelMetadata();
		buildKeypadPanelComponents();
	}
	
	private void buildKeypadPanelMetadata(){
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 5, 5, 5));
	}
	
	private void buildKeypadPanelComponents(){
		add(keypadTextField, BorderLayout.NORTH);
		add(keypadButtonPanel, BorderLayout.CENTER);
	}
}
