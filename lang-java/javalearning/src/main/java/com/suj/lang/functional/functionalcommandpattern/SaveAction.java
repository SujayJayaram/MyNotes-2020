package com.suj.lang.functional.functionalcommandpattern;

/**
 * Created by sujayjayaram on 02/02/2016.
 */
public class SaveAction implements Action {

    private Editor editor;

    public SaveAction(Editor editor){
        this.editor = editor;

    }
    @Override
    public void doWork() {
        editor.save();
    }
}
