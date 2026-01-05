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
    void getCompletionItems_mvel3block() {
        String mvelString = """
                class Foo {
                    rule R1 {
                        var a : /as,
                        do { System.out.println(a == 3.2B);}
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

        // Test completion after 'rule '
        completionParams.setPosition(new Position(1, 9));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).containsOnly("IDENTIFIER");

        // Test completion after 'var '
        completionParams.setPosition(new Position(2, 12));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).containsOnly("IDENTIFIER");

        // Test completion after '/'
        completionParams.setPosition(new Position(2, 17));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).containsOnly("IDENTIFIER");

        // Test completion inside do block
        completionParams.setPosition(new Position(3, 12));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(result).isNotEmpty();
        // Should have Java keywords
        assertThat(completionItemStrings(result)).contains("int", "var", "if");
    }

    @Test
    void getCompletionItems_multipleRules() {
        String mvelString = """
                class Foo {
                    rule R1 {
                        var a : /as,
                        do { System.out.println(a);}
                    }
                    
                    rule R2 {
                        var b : /bs,
                        do { System.out.println(b);}
                    }
                }
                """;

        Mvel3LspDocumentService mvel3LspDocumentService = getMvel3LspDocumentService(mvelString);

        CompletionParams completionParams = new CompletionParams();
        completionParams.setTextDocument(new TextDocumentIdentifier("myDocument"));

        // Test completion between rules
        completionParams.setPosition(new Position(5, 0));
        List<CompletionItem> result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).contains("rule");

        // Test completion in second rule
        completionParams.setPosition(new Position(7, 17));
        result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).containsOnly("IDENTIFIER");
    }

    @Test
    void getCompletionItems_incompleteRule() {
        String mvelString = """
                class Foo {
                    rule R1 {
                        var a : /
                """;

        Mvel3LspDocumentService mvel3LspDocumentService = getMvel3LspDocumentService(mvelString);

        CompletionParams completionParams = new CompletionParams();
        completionParams.setTextDocument(new TextDocumentIdentifier("myDocument"));
        
        // Test completion after incomplete '/'
        completionParams.setPosition(new Position(2, 17));
        List<CompletionItem> result = mvel3LspDocumentService.getCompletionItems(completionParams);
        assertThat(completionItemStrings(result)).containsOnly("IDENTIFIER");
    }
}