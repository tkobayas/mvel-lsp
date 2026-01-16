package org.mvel3.completion;

import java.util.Set;

import org.antlr.v4.runtime.Token;
import org.mvel3.parser.antlr4.Mvel3Lexer;

public class Tokens {

    public static Set<Integer> IGNORED = Set.of(
            Token.EPSILON, Token.EOF, Token.INVALID_TYPE,

            Mvel3Lexer.DECIMAL_LITERAL, Mvel3Lexer.HEX_LITERAL,
            Mvel3Lexer.OCT_LITERAL, Mvel3Lexer.BINARY_LITERAL, Mvel3Lexer.FLOAT_LITERAL, Mvel3Lexer.HEX_FLOAT_LITERAL,
            Mvel3Lexer.BOOL_LITERAL, Mvel3Lexer.CHAR_LITERAL, Mvel3Lexer.STRING_LITERAL, Mvel3Lexer.TEXT_BLOCK,
            Mvel3Lexer.NULL_LITERAL, Mvel3Lexer.LPAREN, Mvel3Lexer.RPAREN, Mvel3Lexer.LBRACE, Mvel3Lexer.RBRACE, Mvel3Lexer.LBRACK,
            Mvel3Lexer.RBRACK, Mvel3Lexer.SEMI, Mvel3Lexer.COMMA, Mvel3Lexer.DOT, Mvel3Lexer.ASSIGN, Mvel3Lexer.GT, Mvel3Lexer.LT,
            Mvel3Lexer.BANG, Mvel3Lexer.TILDE, Mvel3Lexer.QUESTION, Mvel3Lexer.COLON, Mvel3Lexer.EQUAL, Mvel3Lexer.LE, Mvel3Lexer.GE,
            Mvel3Lexer.NOTEQUAL, Mvel3Lexer.AND, Mvel3Lexer.OR, Mvel3Lexer.INC, Mvel3Lexer.DEC, Mvel3Lexer.ADD, Mvel3Lexer.SUB, Mvel3Lexer.MUL,
            Mvel3Lexer.DIV, Mvel3Lexer.BITAND, Mvel3Lexer.BITOR, Mvel3Lexer.CARET, Mvel3Lexer.MOD, Mvel3Lexer.ADD_ASSIGN, Mvel3Lexer.SUB_ASSIGN,
            Mvel3Lexer.MUL_ASSIGN, Mvel3Lexer.DIV_ASSIGN, Mvel3Lexer.AND_ASSIGN, Mvel3Lexer.OR_ASSIGN, Mvel3Lexer.XOR_ASSIGN,
            Mvel3Lexer.MOD_ASSIGN, Mvel3Lexer.LSHIFT_ASSIGN, Mvel3Lexer.RSHIFT_ASSIGN, Mvel3Lexer.URSHIFT_ASSIGN,
            Mvel3Lexer.ARROW, Mvel3Lexer.COLONCOLON, Mvel3Lexer.AT, Mvel3Lexer.ELLIPSIS, Mvel3Lexer.WS, Mvel3Lexer.COMMENT,
            Mvel3Lexer.LINE_COMMENT, Mvel3Lexer.IDENTIFIER,
            Mvel3Lexer.BigDecimalLiteral, Mvel3Lexer.BigIntegerLiteral,
            Mvel3Lexer.MILLISECOND_LITERAL, Mvel3Lexer.SECOND_LITERAL, Mvel3Lexer.MINUTE_LITERAL, Mvel3Lexer.HOUR_LITERAL, Mvel3Lexer.DAY_LITERAL
    );
}