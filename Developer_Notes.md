# Developer notes
## Precompiled-server - no debug
1. package server side code with `mvn clean package`
2. goto `client` directory
3. issue `npm install`
4. issue `code .` to start VSCode in that directory
5. inside VSCode, select `Run and Debug` (Ctrl+Shift+D) and then start `Run Extension`
6. a new `Extension Development Host` window will appear, with `mvel` extension enabled
7. to "debug" server-side event, add `server.getClient().showMessage(new MessageParams(MessageType.Info, {text}));` in server-side code

## Connected remote server - debug
1. package server side code with `mvn clean package`
2. start server with `Mvel3LspTCPLauncher` from IDE on debug mode; this will start the LSP-server listening on port `9925`
3. goto `client` directory
4. issue `npm install`
5. issue `code .` to start VSCode in that directory
6. inside VSCode, select `Run and Debug` (Ctrl+Shift+D) and then start `Debug Extension`
7. the extensions will establish a connection to the server running at port `9925`
8. a new `Extension Development Host` window will appear, with `mvel` extension enabled
9. to "debug" server-side event, add breakpoints in server-side code

## Build Mvel3 Editor Extension for VSCode
Under `client` directory, run:
```bash
npm install
npm run pack:dev
```
vsix file will be generated in `dist` directory.

## Run tests
For server-side tests, run `mvn test` in the root directory.

For client-side tests, goto `client` directory and run:
```bash
npm install
npm test
```

## Debug logging
### Enable antlr4-c3 debug logging
Edit `mvel3-completion/src/test/resources/logging.properties` for `com.vmware.antlr4c3.level` to `FINE`. (But revisit when upgrading antlr4-c3 version, as the logging library may have changed.)

### Enable debug logging for VSCODE extension
`mvel3-lsp-server/src/main/resources/logback.xml` is included in `mvel3-lsp-server-jar-with-dependencies.jar`, so `logs/mvel3-lsp-server.log` is created under the current working directory. Confirmed with unit tests and actual VSCODE extension run. However `npm test` does not seem to create the log file; need to investigate further.