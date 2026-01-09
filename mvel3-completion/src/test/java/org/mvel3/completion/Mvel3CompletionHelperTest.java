package org.mvel3.completion;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mvel3.completion.Mvel3CompletionHelper.completionItemStrings;

/**
 * Tests for Mvel3CompletionHelper completion suggestions in various positions
 * within a simple complete mvel block.
 */
class Mvel3CompletionHelperTest {

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
                        l#ArrayList#.trimToSize();
                    }
                }
                """;

        Position caretPosition = new Position();
        List<CompletionItem> result;

        // Test completion at the beginning of the method block
        caretPosition.setLine(7);
        caretPosition.setCharacter(8);
        result = helper.getCompletionItems(text, caretPosition);
        assertThat(completionItemStrings(result)).contains("var", "int", "return", "modify"); // 'modify' is a MVEL keyword

        // Test completion after 'l#'
        caretPosition.setLine(7);
        caretPosition.setCharacter(10);
        result = helper.getCompletionItems(text, caretPosition);

        assertThat(completionItemStrings(result)).contains("ArrayList");

        // Test completion after '#ArrayList#.'
        caretPosition.setLine(7);
        caretPosition.setCharacter(21);
        result = helper.getCompletionItems(text, caretPosition);

        assertThat(completionItemStrings(result)).contains("trimToSize");
    }

    @Test
    void testCreateCompletionItem() {
        CompletionItem item = Mvel3CompletionHelper.createCompletionItem("test", org.eclipse.lsp4j.CompletionItemKind.Keyword);
        
        assertThat(item.getLabel()).isEqualTo("test");
        assertThat(item.getInsertText()).isEqualTo("test");
        assertThat(item.getKind()).isEqualTo(org.eclipse.lsp4j.CompletionItemKind.Keyword);
    }
}
