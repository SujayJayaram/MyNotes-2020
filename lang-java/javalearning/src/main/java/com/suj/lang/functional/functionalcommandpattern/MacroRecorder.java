package com.suj.lang.functional.functionalcommandpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sujayjayaram on 02/02/2016.
 */
public class MacroRecorder {
    private List<Action> list = new ArrayList<>();
    Editor editor = new Editor();

    public MacroRecorder() {
        // Can add these actions via explicit classes...
        list.add(new OpenAction(editor));
        list.add(new CloseAction(editor));
        list.add(new SaveAction(editor));

        // Or by lambdas...(we do not even need to define Action as a Functional Interface
        // Though it would be good to do so to prevent things breaking if we add more
        // methods to it.
        list.add(() -> editor.open());
        list.add(() -> editor.close());
        list.add(() -> editor.save());

        // This is the most interesting as its effectively a shortcut for the above
        list.add(editor::open);
        list.add(editor::close);
        list.add(editor::save);

        list.forEach( e -> e.doWork());
    }

    public static void main(String[] args){
        new MacroRecorder();
    }
}
