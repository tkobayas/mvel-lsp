package org.mvel3.completion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mvel3.completion.Mvel3CompletionHelper.completionItemStrings;

class Mvel3CompletionHelperIncompleteCodeTest {

    @Test
    void emptyInput() {
        Mvel3CompletionHelper helper = new Mvel3CompletionHelper();

        String text = "";
        Position caretPosition = new Position(0, 0);

        List<CompletionItem> result = helper.getCompletionItemsAsBlock(text, caretPosition);
        assertThat(completionItemStrings(result)).contains("var", "int", "return");
    }

    @Test
    void testInlineCast() {

        Mvel3CompletionHelper helper = new Mvel3CompletionHelper();
        helper.addImportedClass(ArrayList.class);

        String text = """
                package org.example;
                
                import java.util.List;
                import java.util.ArrayList;
                
                public class GeneratorEvaluator__ {
                    public void eval(List l) {
                        l#ArrayList#.
                """;

        Position caretPosition = new Position();
        List<CompletionItem> result;

        // Test completion after '#ArrayList#.'
        caretPosition.setLine(7);
        caretPosition.setCharacter(21);
        result = helper.getCompletionItems(text, caretPosition);

        assertThat(completionItemStrings(result)).contains("trimToSize");
    }

}
