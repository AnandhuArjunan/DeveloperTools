/*
 * 
 */
package com.anandhuarjunan.developertools.core.views.tools.java;


import java.io.File;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntFunction;
import java.util.regex.Matcher;

import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import com.anandhuarjunan.developertools.core.utils.SyntaxUtils;

import javafx.beans.value.ChangeListener;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;

public class EditorController {

    private CodeArea codeTextArea;
    private TextArea resultTextArea;
    private Popup auoCompletePopup;

    private File sourceFile;
    private ExecutorService taskExecutor;

    public EditorController(CodeArea codeArea) {
        taskExecutor = Executors.newSingleThreadExecutor();
        codeTextArea = codeArea;
    }

    public void editorSettings() {
        codeAreaHighlighter();
        codeAreaAddLineNumber();
        bindContextMenu();

        //Event Listener
        codeTextArea.setOnKeyTyped(onKeyTyped);
        codeTextArea.setOnKeyPressed(onSpaceSensors);
        codeTextArea.setOnKeyReleased(onKeyReleased);
        codeTextArea.setOnNewSelectionDragFinished(onSelectionDragFinished);
        codeTextArea.caretPositionProperty().addListener(caretPositionChange);
    }

    private void codeAreaHighlighter() {
        codeTextArea.multiPlainChanges()
                .successionEnds(Duration.ofMillis(500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeTextArea.multiPlainChanges())
                .filterMap(tryTask -> {
                    if (tryTask.isSuccess()) {
                        return Optional.of(tryTask.get());
                    } else {
                        tryTask.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                })
                .subscribe(this::applyHighlighting);
    }

    private void codeAreaAddLineNumber() {
        IntFunction<Node> lineNumberFactory = LineNumberFactory.get(codeTextArea);
        codeTextArea.setParagraphGraphicFactory(lineNumberFactory);
    }

    private ContextMenu getContextMenu() {
        //Create Menu Items
        MenuItem run = new MenuItem("Run");
        MenuItem format = new MenuItem("Format");
        MenuItem save = new MenuItem("Save");
        MenuItem openFolder = new MenuItem("Show in Explorer");
        MenuItem openTerminal = new MenuItem("Open Terminal");
        MenuItem openTerminalHere = new MenuItem("Open Terminal Here");
        MenuItem screenShot = new MenuItem("ScreenShot");

        //Add Event Handler
       // run.setOnAction(e -> CodeExecutor.execute(sourceFile,resultTextArea));
        format.setOnAction(this::formatSourceCode);
        save.setOnAction(this::saveSourceCode);
        openFolder.setOnAction(this::openCurrentFolder);
        openTerminal.setOnAction(this::openTerminal);
        openTerminalHere.setOnAction(this::openTerminalHere);
        screenShot.setOnAction(this::captureScreenShot);

        //Add Menu items in Context Menu
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(run);
        contextMenu.getItems().add(format);
        contextMenu.getItems().add(save);
        contextMenu.getItems().add(openFolder);
        contextMenu.getItems().add(openTerminal);
        contextMenu.getItems().add(openTerminalHere);
        contextMenu.getItems().add(screenShot);
        
        return contextMenu;
    }

    private void bindContextMenu(){
        ContextMenu contextMenu = getContextMenu();
        codeTextArea.setContextMenu(contextMenu);
    }

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
        String text = codeTextArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>> call() {
                return computeHighlighting(text);
            }
        };
        taskExecutor.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>> highlighting) {
        codeTextArea.setStyleSpans(0, highlighting);
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = SyntaxUtils.PATTERN.matcher(text);
        int lastKeywordEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();

        while (matcher.find()) {
            String styleClass =
                    matcher.group("KEYWORD") != null ? "keyword" :
                    matcher.group("PAREN") != null ? "paren" :
                    matcher.group("BRACE") != null ? "brace" :
                    matcher.group("BRACKET") != null ? "bracket" :
                    matcher.group("SEMICOLON") != null ? "semicolon" :
                    matcher.group("CAST") != null ? "cast" :
                    matcher.group("STRING") != null ? "string" :
                    matcher.group("TODO") != null ? "todo" :
                    matcher.group("WARN") != null ? "warn" :
                    matcher.group("COMMENT") != null ? "comment" :
                    matcher.group("ANNOTATION") != null ? "annotation" :
                    matcher.group("OPERATION") != null ? "operation" :
                    matcher.group("HEX") != null ? "hex" :
                    matcher.group("NUMBER") != null ? "number" :
                    matcher.group("METHOD") != null ? "method" :
                    null; /* never happens */
            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKeywordEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKeywordEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKeywordEnd);
        return spansBuilder.create();
    }

    void updateSourceFile(File source) {
        sourceFile = source;
    }

    private void formatSourceCode(ActionEvent event) {}

    private void saveSourceCode(ActionEvent event) {}

    private void captureScreenShot(ActionEvent event) {
      //  Platform.runLater(ScreenCapture::captureScreenShot);
    }

    private void openCurrentFolder(ActionEvent event) {}

    private void openTerminal(ActionEvent event) {
   //     TerminalUtils.openTerminal();
    }

    private void openTerminalHere(ActionEvent event) {
      //    TerminalUtils.openTerminalHere(sourceFile.getParentFile());
    }

    private final ChangeListener<Integer> caretPositionChange = (observable, oldPosition, newPosition) -> {
        if (auoCompletePopup != null) auoCompletePopup.hide();
    };

    private final EventHandler<KeyEvent> onKeyTyped = event -> {};

    private final EventHandler<KeyEvent> onKeyReleased = event -> {
        if (!event.getCode().equals(KeyCode.SHIFT) &&
                !event.getCode().equals(KeyCode.ALT) &&
                !event.getCode().equals(KeyCode.CONTROL) &&
                !event.getCode().equals(KeyCode.TAB) &&
                !event.getCode().equals(KeyCode.CAPS) &&
                !event.getCode().equals(KeyCode.BACK_SPACE) &&
                !event.getCode().isArrowKey()) {
            int cursorPosition = codeTextArea.getCaretPosition();
            if (cursorPosition > 0) {
                char bracket = codeTextArea.getText(cursorPosition - 1, cursorPosition).charAt(0);
              //  PunctuationComplete.onPunctuationComplete(codeTextArea, bracket, cursorPosition);
            }
        }
    };

    private final EventHandler<KeyEvent> onSpaceSensors = event -> {
        if (event.getCode().equals(KeyCode.ENTER)) {
           // SpaceSensors.onWhiteSpaceSensors(codeTextArea);
        }
    };

    private final EventHandler<MouseEvent> onSelectionDragFinished = event -> {
        if (event.isControlDown()) {
            //Take selected text and return it with comments
          //  AutoCommenter.commentCode(codeTextArea);
        } else if (event.isAltDown()) {
            //Take selected text and return it without comments
           // AutoCommenter.unCommentCode(codeTextArea);
        }
    };
}
