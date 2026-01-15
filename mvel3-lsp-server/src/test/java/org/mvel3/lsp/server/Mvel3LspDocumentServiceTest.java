package org.mvel3.lsp.server;

import java.util.List;

import org.eclipse.lsp4j.CompletionItem;
import org.eclipse.lsp4j.CompletionParams;
import org.eclipse.lsp4j.Position;
import org.eclipse.lsp4j.TextDocumentIdentifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mvel3.completion.Mvel3CompletionHelper.completionItemStrings;
import static org.mvel3.lsp.server.TestHelperMethods.getMvel3LspDocumentService;

class Mvel3LspDocumentServiceTest {

    @Test
    void getCompletionItems_emptyText() {
        Mvel3LspDocumentService mvel3LspDocumentService = getMvel3LspDocumentService("");

        CompletionParams completionParams = new CompletionParams();
        completionParams.setTextDocument(new TextDocumentIdentifier("myDocument"));
        Position caretPosition = new Position();
        caretPosition.setCharacter(0);
        caretPosition.setLine(0);
        completionParams.setPosition(caretPosition);

        List<CompletionItem> result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("package", "import", "class"); // top level statement
    }

    @Test
    void getCompletionItems_javaCompletion() {
        String mvelString = """
                import java.math.BigDecimal;
                
                public class Foo {
                    public void work(BigDecimal bd) {
                        System.out.println(bd == 3.2B);
                    }
                }
                """;

        Mvel3LspDocumentService mvel3LspDocumentService = getMvel3LspDocumentService(mvelString);

        CompletionParams completionParams = new CompletionParams();
        completionParams.setTextDocument(new TextDocumentIdentifier("myDocument"));

        // Test completion at beginning of file
        completionParams.setPosition(new Position(0, 0));
        List<CompletionItem> result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("package", "import", "class");

        // Test completion before 'public'
        completionParams.setPosition(new Position(3, 4));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("public");

        // Test completion after 'work('
        completionParams.setPosition(new Position(3, 21));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("int", "BigDecimal"); // list of possible types

        // Test completion after 'System.'
        completionParams.setPosition(new Position(4, 15));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("out");

        // Test completion after 'out.'
        completionParams.setPosition(new Position(4, 19));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("println");
    }


    @Test
    void getCompletionItems_incompleteRule() {
        String mvelString = """
                import java.math.BigDecimal;
                
                public class Foo {
                    public void work(BigDecimal bd) {
                        System.out.
                """;

        Mvel3LspDocumentService mvel3LspDocumentService = getMvel3LspDocumentService(mvelString);

        CompletionParams completionParams = new CompletionParams();
        completionParams.setTextDocument(new TextDocumentIdentifier("myDocument"));
        
        // Test completion after incomplete 'System.out.'
        completionParams.setPosition(new Position(4, 19));
        List<CompletionItem> result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("println");
    }
}