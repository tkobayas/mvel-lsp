# Change Log

## 3.0.0-alpha (2026-01-21)

### Major Dependency Upgrades

#### Breaking Changes
- **BREAKING**: Upgraded `vscode-languageclient` from 5.1.1 to 9.0.1
  - Import path changed from `vscode-languageclient` to `vscode-languageclient/node`
  - Minimum VSCode version now **1.75.0** (was 1.62.0)
  - Users must upgrade to VSCode 1.75.0 or later
- **BREAKING**: Upgraded TypeScript from 4.4.4 to 5.3.0
- **BREAKING**: Minimum Node.js version now 18.x (was 14.x)

#### Updated Dependencies
- `@types/vscode`: 1.62.0 → 1.75.0
- `@types/node`: 14.x → 18.x
- `@types/glob`: 7.1.4 → 8.1.0
- `@types/mocha`: 9.0.0 → 10.0.0
- `@typescript-eslint/eslint-plugin`: 5.1.0 → 6.0.0
- `@typescript-eslint/parser`: 5.1.0 → 6.0.0
- `@vscode/test-electron`: 1.6.2 → 2.3.0
- `@vscode/vsce`: 2.19.0 → 2.22.0
- `eslint`: 8.1.0 → 8.50.0
- `glob`: 7.1.7 → 10.3.0
- `typescript`: 4.4.4 → 5.3.0

### Improvements
- Better TypeScript type safety with TypeScript 5.3
- Improved LSP protocol support (LSP 3.17+)
- Modern VSCode API compatibility
- Enhanced performance and stability
- Security updates for all dependencies

### Technical Changes
- Updated import path for vscode-languageclient
- Added `skipLibCheck` to tsconfig for better build performance
- Updated glob API usage to promise-based pattern
- Improved module resolution configuration

### Migration Guide
- **For Users**: Upgrade to VSCode 1.75.0 or later before installing this version
- **For Developers**:
  - Use Node.js 18.x or later
  - Update imports: `vscode-languageclient` → `vscode-languageclient/node`
  - No changes required for existing .mvel files
  - Extension functionality remains the same

## 3.0.0-alpha (Development Build)

- This is an alpha SNAPSHOT release, so the latest alpha will include all the latest changes.