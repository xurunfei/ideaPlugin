package com.mf;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

/**
 * @author fei
 */
public class SqlToolWindow {
    private JTextArea textArea;

    private JPanel myToolWindowContent;
    private JScrollPane jScrollPane;

    public SqlToolWindow(ToolWindow toolWindow) {
    }

    public JTextArea getConsole() {
        return textArea;
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

}