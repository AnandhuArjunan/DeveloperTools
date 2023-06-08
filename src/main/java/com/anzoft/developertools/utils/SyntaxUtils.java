/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class SyntaxUtils {
    
    private SyntaxUtils() {}

    protected static final String[] KEYWORDS = new String[]{
            "abstract", "assert", "boolean", "break", "byte",
            "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else",
            "enum", "extends", "false", "final", "finally", "float",
            "for", "goto", "if", "implements", "import",
            "instanceof", "int", "interface", "long", "null", "native",
            "new", "package", "private", "protected", "public",
            "return", "short", "static", "strictfp", "super",
            "switch", "synchronized", "true", "this", "throw", "throws",
            "transient", "try", "var", "void", "volatile", "while"
    };

    public static final List<String> KEYWORDS_lIST = Collections.unmodifiableList(new ArrayList<>());
    public static final Map<Object, Object> CLASSES_LIST = Collections.unmodifiableMap(new HashMap<>());

    private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    private static final String PAREN_PATTERN = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String CAST_PATTERN = "<[a-zA-Z0-9,<>]+>";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    public static final String TODO_SINGLE_COMMENT_PATTERN = "//TODO[^\n]*";
    public static final String WARN_SINGLE_COMMENT_PATTERN = "//WARN[^\n]*";
    private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";
    private static final String ANNOTATION_PATTERN = "@.[a-zA-Z0-9]+";
    private static final String OPERATION_PATTERN = ":|==|>|<|!=|>=|<=|->|=|>|<|%|-|-=|%=|\\+|\\-|\\-=|\\+=|\\^|\\&|\\|::|\\?|\\*";
    private static final String HEX_PATTERN = "#[a-fA-F0-9]+";
    private static final String NUMBERS_PATTERN = "[0-9]+";
    private static final String METHOD_PATTERN = "\\.[a-zA-Z0-9_]+";

    public static final Pattern PATTERN = Pattern.compile(
                      "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<TODO>" + TODO_SINGLE_COMMENT_PATTERN + ")"
                    + "|(?<WARN>" + WARN_SINGLE_COMMENT_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
                    + "|(?<ANNOTATION>" + ANNOTATION_PATTERN + ")"
                    + "|(?<CAST>" + CAST_PATTERN + ")"
                    + "|(?<OPERATION>" + OPERATION_PATTERN + ")"
                    + "|(?<HEX>" + HEX_PATTERN + ")"
                    + "|(?<NUMBER>" + NUMBERS_PATTERN + ")"
                    + "|(?<METHOD>" + METHOD_PATTERN + ")"
    );
}