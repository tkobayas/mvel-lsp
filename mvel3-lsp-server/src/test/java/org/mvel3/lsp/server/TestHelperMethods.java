package org.mvel3.lsp.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.Diagnostic;
import org.eclipse.lsp4j.DidOpenTextDocumentParams;
import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.TextDocumentItem;
import org.eclipse.lsp4j.services.LanguageClient;

public class TestHelperMethods {

    private TestHelperMethods() {
    }

    public static Mvel3LspDocumentService getMvel3LspDocumentService(String mvelString) {
        Mvel3LspServer ls = getMvel3LspServerForDocument(mvelString);
        return ls.getTextDocumentService();
    }

    public static Mvel3LspServer getMvel3LspServerForDocument(String mvelString) {
        Mvel3LspServer ls = new Mvel3LspServer();
        List<Diagnostic> diagnostics = new ArrayList<>();
        ls.connect(new LanguageClient() {
            @Override
            public void telemetryEvent(Object object) {
            }

            @Override
            public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams requestParams) {
                return null;
            }

            @Override
            public void showMessage(MessageParams messageParams) {
            }

            @Override
            public void publishDiagnostics(PublishDiagnosticsParams d) {
                diagnostics.clear();
                diagnostics.addAll(d.getDiagnostics());
            }

            @Override
            public void logMessage(MessageParams message) {
            }
        });

        TextDocumentItem doc = new TextDocumentItem();
        doc.setUri("myDocument");
        doc.setText(mvelString);
        ls.getTextDocumentService().didOpen(new DidOpenTextDocumentParams(doc));
        return ls;
    }
}