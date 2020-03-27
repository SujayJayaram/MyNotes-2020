package com.suj.lang.functional.functionalcommandpattern;

/**
 * Created by sujayjayaram on 02/02/2016.
 */
public class CloseAction implements Action {

    private Editor editor;

    public CloseAction(Editor editor){
        this.editor = editor;

    }
    @Override
    public void doWork() {
        editor.close();
    }
}
