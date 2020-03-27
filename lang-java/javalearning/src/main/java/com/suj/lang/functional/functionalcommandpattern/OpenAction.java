package com.suj.lang.functional.functionalcommandpattern;

/**
 * Created by sujayjayaram on 02/02/2016.
 */
public class OpenAction implements Action {

    private Editor editor;

    public OpenAction(Editor editor){
        this.editor = editor;

    }
    @Override
    public void doWork() {
        editor.open();
    }
}
