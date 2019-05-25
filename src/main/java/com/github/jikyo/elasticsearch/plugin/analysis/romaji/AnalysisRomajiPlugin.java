package com.github.jikyo.elasticsearch.plugin.analysis.romaji;

import org.elasticsearch.index.analysis.TokenFilterFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;


public class AnalysisRomajiPlugin extends Plugin implements AnalysisPlugin {

    public AnalysisRomajiPlugin() {
        super();
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> getTokenFilters() {
        Map<String, AnalysisModule.AnalysisProvider<TokenFilterFactory>> extra = new HashMap<>();
        extra.put("jikyo_romaji", RomajiTokenFilterFactory::new);
        return extra;
    }

}
