package com.github.jikyo.elasticsearch.plugin.analysis.romaji;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.KeywordTokenizer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.analysis.AnalysisTestsHelper;
import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.test.ESTestCase;
import org.elasticsearch.test.ESTokenStreamTestCase;
import org.junit.Before;

import java.io.StringReader;


public class RomajiTokenStreamTests extends ESTokenStreamTestCase {

    private ESTestCase.TestAnalysis analysis;

    public RomajiTokenStreamTests() {}

    @Before
    public void setup() throws Exception {
        analysis = AnalysisTestsHelper.createTestAnalysisFromSettings(
                Settings.builder()
                        .put(Environment.PATH_HOME_SETTING.getKey(), createTempDir().toString())
                        .build(),
                new AnalysisRomajiPlugin());
    }

    public TokenStream makeStandardTokenStream(String src) {
        TokenFilterFactory tokenFilterFactory = analysis.tokenFilter.get("jikyo_romaji");
        Tokenizer tokenizer = new StandardTokenizer();
        tokenizer.setReader(new StringReader(src));
        return tokenFilterFactory.create(tokenizer);
    }

    public TokenStream makeKeywordTokenStream(String src) {
        TokenFilterFactory tokenFilterFactory = analysis.tokenFilter.get("jikyo_romaji");
        Tokenizer tokenizer = new KeywordTokenizer();
        tokenizer.setReader(new StringReader(src));
        return tokenFilterFactory.create(tokenizer);
    }

    public void testStandardEmpty() throws Exception {
        String src = "";
        String[] expect = new String[] {};
        assertTokenStreamContents(makeStandardTokenStream(src), expect);
    }

    public void testKeywordEmpty() throws Exception {
        String src = "";
        String[] expect = new String[] {""};
        assertTokenStreamContents(makeKeywordTokenStream(src), expect);
    }

    public void testStandardHiragana() throws Exception {
        String src = "あああ";
        String[] expect = new String[] {"あ", "a", "あ", "a", "あ", "a"};
        assertTokenStreamContents(makeStandardTokenStream(src), expect);
    }

    public void testKeywordHiragana() throws Exception {
        String src = "いいい";
        String[] expect = new String[] {"いいい", "iii"};
        assertTokenStreamContents(makeKeywordTokenStream(src), expect);
    }

    public void testStandardSmallHiragana() throws Exception {
        String src = "あっち";
        String[] expect = new String[] {"あ", "a", "っ", "ltsu", "ltu", "xtu", "ち", "chi", "ti"};
        assertTokenStreamContents(makeStandardTokenStream(src), expect);
    }

    public void testKeywordSmallHiragana() throws Exception {
        String src = "こっち";
        String[] expect = new String[] {"こっち", "kocchi", "koltsuchi", "koltuchi", "koltuti", "kotti", "koxtuti"};
        assertTokenStreamContents(makeKeywordTokenStream(src), expect);
    }

    public void testStandardMixedHiraganaAndKanji() throws Exception {
        String src = "お茶の水";
        String[] expect = new String[] {"お", "o", "茶", "の", "no", "水"};
        assertTokenStreamContents(makeStandardTokenStream(src), expect);
    }

    public void testKeywordEmptyMixedHiraganaAndKanji() throws Exception {
        String src = "御茶の水";
        String[] expect = new String[] {"御茶の水", "御茶no水"};
        assertTokenStreamContents(makeKeywordTokenStream(src), expect);
    }

}
