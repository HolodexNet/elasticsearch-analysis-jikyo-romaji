package com.github.jikyo.elasticsearch.plugin.analysis.romaji;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import com.github.jikyo.romaji.Transliterator;

import java.io.IOException;
import java.util.ArrayDeque;


public class RomajiTokenFilter extends TokenFilter {

    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);
    private final PositionIncrementAttribute positionIncrementAtt = addAttribute(PositionIncrementAttribute.class);
    private State currentState;
    private ArrayDeque<String> romajis = new ArrayDeque<>();

    public RomajiTokenFilter(TokenStream input) {
        super(input);
    }

    @Override
    public final boolean incrementToken() throws IOException {
        if (!romajis.isEmpty()) {
            restoreState(currentState);
            termAtt.setEmpty().append(romajis.pop());
            positionIncrementAtt.setPositionIncrement(0);
            return true;
        }

        if (!input.incrementToken()) {
            return false;
        }

        romajis = new ArrayDeque<>(Transliterator.transliterate(termAtt.toString()));
        currentState = captureState();
        return true;
    }

}
