package models;

import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class CodeTab extends Tab {
    private boolean isTest;
    private TextArea code;

    public CodeTab() {
        super();
        code = new TextArea();
        this.setContent(code);
        isTest = false;
    }

    public CodeTab(ClassStruct cs) {
        super();
        code = new TextArea();
        this.setContent(code);
        this.setText(cs.getName());
        code.setText(cs.getCode());
        isTest = cs.isTest();
    }

    public String getCode() {
        return code.getText();
    }

    public boolean isTest() {
        return isTest;
    }

    public void setEditable(boolean editable) {
        code.setEditable(editable);
    }
}
